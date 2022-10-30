package com.spring.ws.spring.dao.xr;

import com.spring.ws.spring.entity.User;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface UserXrReadMapper extends BaseMapper<User> {
}
