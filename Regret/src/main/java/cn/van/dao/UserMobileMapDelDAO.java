package cn.van.dao;

import cn.van.model.UserMobileMapDelDO;

public interface UserMobileMapDelDAO {
    int insert(UserMobileMapDelDO record);

    int insertSelective(UserMobileMapDelDO record);
}