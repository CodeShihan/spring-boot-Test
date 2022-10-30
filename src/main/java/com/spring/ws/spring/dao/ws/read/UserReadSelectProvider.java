package com.spring.ws.spring.dao.ws.read;

public class UserReadSelectProvider {

    public String selectList(){
        StringBuilder sql=new StringBuilder();
        sql.append("select id,name from user");
        return sql.toString();
    }

    public String selectOne(Integer id){
        StringBuilder sql=new StringBuilder();
        sql.append("select");
        sql.append("  id,name,ddd,dd,dd  ");
        return sql.toString();
    }
}
