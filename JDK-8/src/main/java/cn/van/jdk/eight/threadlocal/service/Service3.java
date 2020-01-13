package cn.van.jdk.eight.threadlocal.service;

import cn.van.jdk.eight.threadlocal.UserContextHolder;
import cn.van.jdk.eight.threadlocal.domain.User;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: Service1
 *
 * @author: Van
 * Date:     2020-01-11 15:33
 * Description: ${DESCRIPTION}
 * Version： V1.0
 */
public class Service3 {

    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service3拿到用户名: " + user.getUserName());
    }
}
