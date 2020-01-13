package cn.van.jdk.eight.threadlocal.domain;

import lombok.Data;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: User
 *
 * @author: Van
 * Date:     2020-01-11 15:34
 * Description: ${DESCRIPTION}
 * Version： V1.0
 */
@Data
public class User {
    private String userName;

    public User() {

    }

    public User(String userName) {
        this.userName = userName;
    }
}
