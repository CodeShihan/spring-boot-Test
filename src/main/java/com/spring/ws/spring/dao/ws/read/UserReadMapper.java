package com.spring.ws.spring.dao.ws.read;

import com.spring.ws.spring.entity.User;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface UserReadMapper extends BaseMapper<User> {
}
