package cn.van.jdk.tool.net;

import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: LocalHostUtil
 *
 * @author: Van
 * Date:     2019-12-22 23:17
 * Description: 本地主机工具类
 * Version： V1.0
 */
public class LocalHostUtil {

    private static InetAddress inetAddress;

    /**
     * 返回 InetAddress
     * @return
     */
    public static InetAddress getLocalInetAddress() {
        if (inetAddress == null) {
            load();
        }
        return inetAddress;
    }

    /**
     * 返回本机IP
     * @return
     */
    public static String getLocalHostAddress() {
        if (inetAddress == null) {
            load();
        }
        return inetAddress.getHostAddress();
    }

    /**
     * 返回主机名
     * @return
     */
    public static String getLocalHostName() {
        if (inetAddress == null) {
            load();
        }
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return inetAddress.getHostName();
        }
    }

    /**
     * 判断操作系统是否是 Windows
     * @return
     */
    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    /**
     * 判断操作系统是否是 MacOS
     * @return
     */
    public static boolean isMacOS() {
        boolean isWindowsOS = false;
        String osName = getProperty("os.name");
        if (osName.toLowerCase().indexOf("mac") > -1) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    private static InetAddress findValidateIp(List<Address> addresses) {
        InetAddress local = null;
        int size = addresses.size();
        int maxWeight = -1;

        for (int i = 0; i < size; i++) {
            Address address = addresses.get(i);
            if (address.isInet4Address()) {
                int weight = 0;

                if (address.isSiteLocalAddress()) {
                    weight += 8;
                }

                if (address.isLinkLocalAddress()) {
                    weight += 4;
                }

                if (address.isLoopbackAddress()) {
                    weight += 2;
                }

                if (address.hasHostName()) {
                    weight += 1;
                }

                if (weight > maxWeight) {
                    maxWeight = weight;
                    local = address.getAddress();
                }
            }
        }

        return local;
    }

    private static String getProperty(String name) {
        String value = null;

        value = System.getProperty(name);

        if (value == null) {
            value = System.getenv(name);
        }

        return value;
    }

    private static void load() {
        String ip = getProperty("host.ip");

        if (ip != null) {
            try {
                inetAddress = InetAddress.getByName(ip);
                return;
            } catch (Exception e) {
                System.err.println(e);
                // ignore
            }
        }

        try {
            List<NetworkInterface> nis = Collections.list(NetworkInterface.getNetworkInterfaces());
            List<Address> addresses = new ArrayList<>();
            InetAddress local = null;

            try {
                // 遍历网络接口
                for (NetworkInterface ni : nis) {
                    if (ni.isUp() && !ni.isLoopback()) {
                        List<InetAddress> list = Collections.list(ni.getInetAddresses());
                        // 遍历网络地址
                        for (InetAddress address : list) {
                            addresses.add(new Address(address, ni));
                        }
                    }
                }
                local = findValidateIp(addresses);
            } catch (Exception e) {
                // ignore
            }
            inetAddress = local;
        } catch (SocketException e) {
            // ignore it
        }
    }

    static class Address {
        private InetAddress inetAddress;

        private boolean loopback;

        public Address(InetAddress address, NetworkInterface ni) {
            inetAddress = address;

            try {
                if (ni != null && ni.isLoopback()) {
                    loopback = true;
                }
            } catch (SocketException e) {
                // ignore it
            }
        }

        public InetAddress getAddress() {
            return inetAddress;
        }

        public boolean hasHostName() {
            return !inetAddress.getHostName().equals(inetAddress.getHostAddress());
        }

        public boolean isLinkLocalAddress() {
            return !loopback && inetAddress.isLinkLocalAddress();
        }

        public boolean isLoopbackAddress() {
            return loopback || inetAddress.isLoopbackAddress();
        }

        public boolean isSiteLocalAddress() {
            return !loopback && inetAddress.isSiteLocalAddress();
        }

        public boolean isInet4Address(){
            return inetAddress instanceof Inet4Address;
        }
    }
}