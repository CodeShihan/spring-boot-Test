package com.demo;


import com.alibaba.fastjson.JSONObject;
import com.demo.entity.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <Description> <br>
 *
 * @author shihan@qq.com<br>
 * @version 1.0<br>
 * @date 2022/10/30 <br>
 */
public class MapTest {

    public static void main(String[] args) {
        Person person = new Person();
        Map<String, Object> map = new HashMap<>();
        person.setName("小米");
        person.setAge("10");
        JSONObject jsonObject = JSONObject.parseObject(person.toString());
        System.out.println(jsonObject);
        map.put("1", jsonObject);
        System.out.println(map.get("1"));
    }

}
