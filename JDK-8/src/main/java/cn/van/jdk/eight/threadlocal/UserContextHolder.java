package cn.van.jdk.eight.threadlocal;

import cn.van.jdk.eight.threadlocal.domain.User;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: UserContextHolder
 *
 * @author: Van
 * Date:     2020-01-11 15:35
 * Description: ${DESCRIPTION}
 * Version： V1.0
 */
public class UserContextHolder {

    public static ThreadLocal<User> holder = new ThreadLocal<>();

}
