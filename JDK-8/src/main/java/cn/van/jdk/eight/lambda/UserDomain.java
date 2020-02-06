package cn.van.jdk.eight.lambda;

import lombok.Data;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: UserDomain
 *
 * @author: Van
 * Date:     2020-01-11 15:34
 * Description: ${DESCRIPTION}
 * Version： V1.0
 */
@Data
public class UserDomain {

    private Long userId;

    private String userName;

    public UserDomain() {

    }
    public UserDomain(String userName) {
        this.userName = userName;
    }

    public UserDomain(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
