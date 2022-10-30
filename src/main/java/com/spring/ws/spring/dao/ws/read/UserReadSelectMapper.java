package com.spring.ws.spring.dao.ws.read;

import com.spring.ws.spring.entity.User;
import com.spring.ws.spring.util.SqlStringUtil;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface UserReadSelectMapper {

    @SelectProvider(type = UserReadSelectProvider.class,method = "selectList")
    @Results(value = {
            @Result(property = "id",column = "id",javaType = Integer.class),
            @Result(property = "name",column = "name",javaType = String.class),
    })
    List<User> selectList();

    @SelectProvider(type = UserReadSelectProvider.class,method = "selectOne")
    @Results(value = {
            @Result(property = "id",column = "id",javaType = Integer.class),
            @Result(property = "name",column = "name",javaType = String.class),
    })

    //<resultMaps>
    //<result id="" prop="" type="" method="selectOne" classtype="">
    //</result>
    //</resultMaps>
    User selectOne(Integer id);

    static void main(String[] args) throws Exception {
        String results = SqlStringUtil.getResults(User.class);
        System.err.println(results);
    }
}
