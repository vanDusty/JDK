package cn.van.jdk.lambda.domain;

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
public class UserDomain {

    private Long userId;

    private String userName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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
