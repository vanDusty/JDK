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
public class Service1 {

    public void process() {
        User user = new User("Van");
        //将User对象存储到 holder 中
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}
