package com.spring.ws.spring.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.ws.spring.dao.ws.read.UserReadSelectMapper;
import com.spring.ws.spring.dao.ws.write.UserWriteMapper;
import com.spring.ws.spring.database.fina.DataBaseConfig;
import com.spring.ws.spring.domain.request.UserReq;
import com.spring.ws.spring.entity.User;
import com.spring.ws.spring.response.BaseDTOUtil;
import com.spring.ws.spring.response.BasePageResponseInfo;
import com.spring.ws.spring.response.BaseResponse;
import com.spring.ws.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserReadSelectMapper userReadSelectMapper;

    @Autowired(required = false)
    @Lazy
    private UserWriteMapper userWriteMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    @Qualifier(DataBaseConfig.WS_JDBCTEMPLATE)
    private JdbcTemplate jdbcTemplate;

    @Override
    public BaseResponse<BasePageResponseInfo<User>> select(UserReq request) {
        PageInfo<User> pageInfo = PageHelper.startPage(request.getPage(), request.getRows())
                .setOrderBy(request.getOrderBy().replaceAll("[A-Z]", "_$0").toLowerCase())
                .doSelectPageInfo(() -> userReadSelectMapper.selectList());
        return BaseDTOUtil.getBaseResponseSuccess(BaseDTOUtil.getBasePageResponseInfo(pageInfo));
    }

    @Override
    @Transactional(transactionManager = DataBaseConfig.WS_TRANSACTION_MANAGER,rollbackFor = Exception.class)
    public BaseResponse<User> selectOne(Integer id) {
//        String sql="select * from user where id="+id;
//        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class));
        List<User> users = userReadSelectMapper.selectList();
        System.out.println("------->");
        List<User> users1 = userReadSelectMapper.selectList();
        System.err.println(users==users1);
        return BaseDTOUtil.getBaseResponseSuccess();
    }

    @Override
    @Transactional(rollbackFor=Exception.class,transactionManager = DataBaseConfig.WS_TRANSACTION_MANAGER)
    public BaseResponse add(User request) {
        int insert = userWriteMapper.insert(request);
        if (0==insert){
            throw new RuntimeException("添加失败！");
        }
        return BaseDTOUtil.getBaseResponseSuccess();
    }
}
