package cn.van.dao;

import cn.van.model.UserInfoDO;

public interface UserInfoDAO {
    int insert(UserInfoDO record);

    int insertSelective(UserInfoDO record);
}