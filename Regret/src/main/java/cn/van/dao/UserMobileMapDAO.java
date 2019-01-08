package cn.van.dao;

import cn.van.model.UserMobileMapDO;

public interface UserMobileMapDAO {
    int insert(UserMobileMapDO record);

    int insertSelective(UserMobileMapDO record);
}