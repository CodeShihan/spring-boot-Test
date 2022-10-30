package com.spring.ws.spring.service;

import com.spring.ws.spring.domain.request.UserReq;
import com.spring.ws.spring.entity.User;
import com.spring.ws.spring.response.BasePageResponseInfo;
import com.spring.ws.spring.response.BaseResponse;


public interface UserService {
    BaseResponse<BasePageResponseInfo<User>> select(UserReq request);

    BaseResponse<User> selectOne(Integer id);

    BaseResponse add(User request);
}
