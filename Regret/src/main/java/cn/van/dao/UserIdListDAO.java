package cn.van.dao;

import cn.van.model.UserIdListDO;

public interface UserIdListDAO {
    int deleteByPrimaryKey(Long id);

    int insert(UserIdListDO record);

    int insertSelective(UserIdListDO record);

    UserIdListDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserIdListDO record);

    int updateByPrimaryKey(UserIdListDO record);
}