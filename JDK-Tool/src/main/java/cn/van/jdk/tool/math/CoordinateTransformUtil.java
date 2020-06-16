package cn.van.jdk.tool.math;


/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: CoordinateTransformUtil
 *
 * @author: Van
 * Date:     2020-06-13 22:15
 * Description: 坐标系转换工具
 * Version： V1.0
 */
public class CoordinateTransformUtil {

    static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
    /**
     * π
     */
    static double pi = 3.1415926535897932384626;
    /**
     * 长半轴
     */
    static double a = 6378245.0;
    /**
     * 扁率
     */
    static double ee = 0.00669342162296594323;

    static double outOfChinaMin_lng = 72.004;
    static double outOfChinaMax_lng = 137.8347;
    static double outOfChinaMin_lat = 0.8293;
    static double outOfChinaMax_lat = 55.8271;

    /**
     * 将原本坐标系的经纬度转换成新的坐标系的经纬度
     *
     * @param newCoordinateType      新坐标系，如baidu，wgs84
     * @param originalCoordinateType 原坐标系，如wgs84
     * @param lng                    原经度
     * @param lat                    原纬度
     * @return 新坐标系的经纬度
     */
    public static double[] convertLatLonByCoordinate(String newCoordinateType, String originalCoordinateType, double lng, double lat) {
        if (originalCoordinateType == null) {
            return null;
        }

        boolean bd09ToWgs84 = (originalCoordinateType.equalsIgnoreCase("bd09") || originalCoordinateType.equalsIgnoreCase("baidu")
                && (newCoordinateType.equalsIgnoreCase("google")) || newCoordinateType.equalsIgnoreCase("wgs84"));
        boolean gcj02toWgs84 = (originalCoordinateType.equalsIgnoreCase("gaode") || originalCoordinateType.equalsIgnoreCase("gcj02")
                && (newCoordinateType.equalsIgnoreCase("google")) || newCoordinateType.equalsIgnoreCase("wgs84"));

        boolean wgs84ToBd09 = (originalCoordinateType.equalsIgnoreCase("google") || originalCoordinateType.equalsIgnoreCase("wgs84"))
                && (newCoordinateType.equalsIgnoreCase("bd09") || newCoordinateType.equalsIgnoreCase("baidu"));
        boolean gcj02ToBd09 = (originalCoordinateType.equalsIgnoreCase("gaode") || originalCoordinateType.equalsIgnoreCase("gcj02"))
                && (newCoordinateType.equalsIgnoreCase("bd09") || newCoordinateType.equalsIgnoreCase("baidu"));

        boolean wgs84ToGcj02 = (originalCoordinateType.equalsIgnoreCase("google") || originalCoordinateType.equalsIgnoreCase("wgs84"))
                && (newCoordinateType.equalsIgnoreCase("gaode") || newCoordinateType.equalsIgnoreCase("gcj02"));
        boolean bd09ToGcj02 = (originalCoordinateType.equalsIgnoreCase("bd09") || originalCoordinateType.equalsIgnoreCase("baidu"))
                && (newCoordinateType.equalsIgnoreCase("gaode") || newCoordinateType.equalsIgnoreCase("gcj02"));

        if (originalCoordinateType.equals(newCoordinateType)) {
            return new double[]{lat, lng};
        } else if (bd09ToWgs84) {
            return bd09ToWgs84(lng, lat);
        } else if (gcj02toWgs84) {
            return gcj02ToWgs84(lng, lat);
        } else if (wgs84ToBd09) {
            return wgs84ToBd09(lng, lat);
        } else if (gcj02ToBd09) {
            return gcj02ToBd09(lng, lat);
        } else if (wgs84ToGcj02) {
            return wgs84togcj02(lng, lat);
        } else if (bd09ToGcj02) {
            return bd09ToGcj02(lng, lat);
        } else {
            return null;
        }
    }

    /**
     * 百度坐标系(BD-09)转WGS坐标
     *
     * @param lng 百度坐标纬度
     * @param lat 百度坐标经度
     * @return WGS84坐标数组
     */
    public static double[] bd09ToWgs84(double lng, double lat) {
        double[] gcj = bd09ToGcj02(lng, lat);
        double[] wgs84 = gcj02ToWgs84(gcj[0], gcj[1]);
        return wgs84;
    }

    /**
     * WGS坐标转百度坐标系(BD-09)
     *
     * @param lng WGS84坐标系的经度
     * @param lat WGS84坐标系的纬度
     * @return 百度坐标数组
     */
    public static double[] wgs84ToBd09(double lng, double lat) {
        double[] gcj = wgs84togcj02(lng, lat);
        double[] bd09 = gcj02ToBd09(gcj[0], gcj[1]);
        return bd09;
    }

    /**
     * 火星坐标系(GCJ-02)转百度坐标系(BD-09)
     * <p>
     * 谷歌、高德——>百度
     *
     * @param lng 火星坐标经度
     * @param lat 火星坐标纬度
     * @return 百度坐标数组
     */
    public static double[] gcj02ToBd09(double lng, double lat) {
        double z = Math.sqrt(lng * lng + lat * lat) + 0.00002 * Math.sin(lat * x_pi);
        double theta = Math.atan2(lat, lng) + 0.000003 * Math.cos(lng * x_pi);
        double bdLng = z * Math.cos(theta) + 0.0065;
        double bdLat = z * Math.sin(theta) + 0.006;
        return new double[]{bdLng, bdLat};
    }

    /**
     * 百度坐标系(BD-09)转火星坐标系(GCJ-02)
     * <p>
     * 百度——>谷歌、高德
     *
     * @param bd_lon 百度坐标纬度
     * @param bd_lat 百度坐标经度
     * @return 火星坐标数组
     */
    public static double[] bd09ToGcj02(double bd_lon, double bd_lat) {
        double x = bd_lon - 0.0065;
        double y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        double ggLng = z * Math.cos(theta);
        double ggLat = z * Math.sin(theta);
        return new double[]{ggLng, ggLat};
    }

    /**
     * WGS84转GCJ02(火星坐标系)
     *
     * @param lng WGS84坐标系的经度
     * @param lat WGS84坐标系的纬度
     * @return 火星坐标数组
     */
    public static double[] wgs84togcj02(double lng, double lat) {
        if (outOfChina(lng, lat)) {
            return new double[]{lng, lat};
        }
        double dlat = transformLat(lng - 105.0, lat - 35.0);
        double dlng = transformLng(lng - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dlng = (dlng * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mglat = lat + dlat;
        double mglng = lng + dlng;
        return new double[]{mglng, mglat};
    }

    /**
     * GCJ02(火星坐标系)转GPS84
     *
     * @param lng 火星坐标系的经度
     * @param lat 火星坐标系纬度
     * @return WGS84坐标数组
     */
    public static double[] gcj02ToWgs84(double lng, double lat) {
        if (outOfChina(lng, lat)) {
            return new double[]{lng, lat};
        }
        double dlat = transformLat(lng - 105.0, lat - 35.0);
        double dlng = transformLng(lng - 105.0, lat - 35.0);
        double radlat = lat / 180.0 * pi;
        double magic = Math.sin(radlat);
        magic = 1 - ee * magic * magic;
        double sqrtmagic = Math.sqrt(magic);
        dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * pi);
        dlng = (dlng * 180.0) / (a / sqrtmagic * Math.cos(radlat) * pi);
        double mglat = lat + dlat;
        double mglng = lng + dlng;
        return new double[]{lng * 2 - mglng, lat * 2 - mglat};
    }

    /**
     * 纬度转换
     *
     * @param lng
     * @param lat
     * @return
     */
    public static double transformLat(double lng, double lat) {
        double ret = -100.0 + 2.0 * lng + 3.0 * lat + 0.2 * lat * lat + 0.1 * lng * lat + 0.2 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * pi) + 20.0 * Math.sin(2.0 * lng * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lat * pi) + 40.0 * Math.sin(lat / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(lat / 12.0 * pi) + 320 * Math.sin(lat * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    /**
     * 经度转换
     *
     * @param lng
     * @param lat
     * @return
     */
    public static double transformLng(double lng, double lat) {
        double ret = 300.0 + lng + 2.0 * lat + 0.1 * lng * lng + 0.1 * lng * lat + 0.1 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * pi) + 20.0 * Math.sin(2.0 * lng * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lng * pi) + 40.0 * Math.sin(lng / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(lng / 12.0 * pi) + 300.0 * Math.sin(lng / 30.0 * pi)) * 2.0 / 3.0;
        return ret;
    }

    /**
     * 判断是否在国内，不在国内不做偏移
     *
     * @param lng
     * @param lat
     * @return
     */
    public static boolean outOfChina(double lng, double lat) {
        if (lng < outOfChinaMin_lng || lng > outOfChinaMax_lng) {
            return true;
        } else if (lat < outOfChinaMin_lat || lat > outOfChinaMax_lat) {
            return true;
        }
        return false;
    }

}
