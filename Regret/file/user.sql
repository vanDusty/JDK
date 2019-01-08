CREATE TABLE `user_id_list` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `is_user` int(11) DEFAULT '0',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

CREATE TABLE `user_info` (
                           `user_id` bigint(20) NOT NULL COMMENT '用户id',
                           `user_name` varchar(20) NOT NULL DEFAULT '0' COMMENT '用户账号(手机号、邮箱)',
                           `user_pwd` varchar(20) DEFAULT '0' COMMENT '用户密码',
                           `nick_name` varchar(20) CHARACTER SET utf8mb4 NOT NULL DEFAULT '0' COMMENT '用户昵称',
                           `real_name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
                           `sex` int(2) DEFAULT '0' COMMENT '性别（1:男，2：女）',
                           `id_card` varchar(25) DEFAULT '0' COMMENT '身份证号',
                           `birthday` datetime DEFAULT NULL COMMENT '出生日期',
                           `user_icon` varchar(300) DEFAULT NULL COMMENT '头像',
                           `reg_ip` varchar(255) DEFAULT '0' COMMENT '注册时ip',
                           `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `gmt_modify` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_mobile_map` (
                                 `user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
                                 `mobile` varchar(15) NOT NULL DEFAULT '0' COMMENT '登录手机号',
                                 `zone_num` int(5) NOT NULL DEFAULT '86' COMMENT '手机区号',
                                 `project_name` varchar(10) NOT NULL DEFAULT '0' COMMENT '项目名称',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `gmt_modify` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                 UNIQUE KEY `login_mobile` (`mobile`,`zone_num`) USING BTREE,
                                 KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `user_mobile_map_del` (
                                     `user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
                                     `mobile` varchar(15) NOT NULL DEFAULT '0' COMMENT '手机号',
                                     `zone_num` int(5) NOT NULL DEFAULT '86' COMMENT '手机区号',
                                     `project_name` varchar(15) NOT NULL COMMENT '项目名称',
                                     `valid_user` bigint(20) NOT NULL COMMENT '操作人id',
                                     `valid_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
                                     KEY `user_id` (`user_id`) USING BTREE,
                                     KEY `login_mobile` (`mobile`,`zone_num`,`project_name`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;