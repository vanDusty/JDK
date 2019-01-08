package cn.van.domain.user;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用户信息实体
 * @author: Van
 * @create: 2018-09-20 14:38
 **/
public class LoginParamVO implements Serializable {


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
