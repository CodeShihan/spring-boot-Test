package com.spring.ws.spring.response;

import com.github.pagehelper.PageInfo;

public class BaseDTOUtil {

    private static final int CODE_SUCCESS = 200;
    private static final int CODE_ERROR  = 9008;

    public static BaseResponse getBaseResponseSuccess(){
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setStatus(CODE_SUCCESS);
        return baseResponse;
    }

    public static <T> BaseResponse<T> getBaseResponseSuccess(T t){
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setStatus(CODE_SUCCESS);
        baseResponse.setDate(t);
        return baseResponse;
    }

    public static  BaseResponse getBaseResponseSuccess(String message){
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setStatus(CODE_SUCCESS);
        baseResponse.setMessage(message);
        return baseResponse;
    }

    public static <T> BaseResponse<T> getBaseResponseSuccess(String message,T t){
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setStatus(CODE_SUCCESS);
        baseResponse.setDate(t);
        baseResponse.setMessage(message);
        return baseResponse;
    }

    public static BaseResponse getBaseResponseFail(String message){
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setStatus(CODE_ERROR);
        baseResponse.setMessage(message);
        return baseResponse;
    }

    public static <T> BaseResponse<T> getBaseResponseFail(T t){
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setStatus(CODE_ERROR);
        return baseResponse;
    }

    public static <T> BaseResponse<T> getBaseResponseFail(T t,String message){
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setStatus(CODE_ERROR);
        baseResponse.setMessage(message);
        baseResponse.setDate(t);
        return baseResponse;
    }

    public static <T> BaseResponse<T> getBaseResponseFail(Integer status,T t){
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setStatus(status);
        baseResponse.setDate(t);
        return baseResponse;
    }

    public static BaseResponse getBaseResponseFail(Integer status,String message){
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setStatus(status);
        baseResponse.setMessage(message);
        return baseResponse;
    }

    public static <T> BasePageResponseInfo<T> getBasePageResponseInfo(PageInfo<T> pageInfo){
        BasePageResponseInfo<T> baseResponse=new BasePageResponseInfo<T>();
        baseResponse.setPage(pageInfo.getPageNum());
        baseResponse.setRecords(pageInfo.getTotal());
        baseResponse.setTotalPage(pageInfo.getPages());
        baseResponse.setRows(pageInfo.getList());
        return baseResponse;
    }
}
