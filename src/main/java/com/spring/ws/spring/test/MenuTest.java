package com.spring.ws.spring.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.function.Function;
//import java.util.stream.Collectors;

public class MenuTest {

    public static void main(String[] args) {
        List<Menu> menuList=new ArrayList<>();
        init(menuList);
        new HashMap<>();
        //一级
//        List<Menu> menus = menuList.stream().filter(s -> StringUtils.isEmpty(s.getParentCode())).collect(Collectors.toList());
        //子级
//        Map<String, List<Menu>> map = menuList.stream().filter(s -> !StringUtils.isEmpty(s.getParentCode())).collect(Collectors.groupingBy(Menu::getParentCode));
//        t(map,menus);
//        String s = JSON.toJSONString(menus);
        int i=2<<3;
        int  j =111^15;
        System.out.println(j);
//        System.out.println(s);
    }
    private static void init(List<Menu> menuList){
        Menu menu=new Menu();
        menu.setCode("A");
        menu.setParentCode("");
        menuList.add(menu);

        menu=new Menu();
        menu.setCode("B");
        menu.setParentCode("");
        menuList.add(menu);

         menu=new Menu();
        menu.setCode("C");
        menu.setParentCode("A");
        menuList.add(menu);

         menu=new Menu();
        menu.setCode("D");
        menu.setParentCode("B");
        menuList.add(menu);

         menu=new Menu();
        menu.setCode("E");
        menu.setParentCode("C");
        menuList.add(menu);

         menu=new Menu();
        menu.setCode("F");
        menu.setParentCode("D");
        menuList.add(menu);

    }

    private static void t( Map<String, List<Menu>> map,List<Menu> menus){
        if (CollectionUtils.isEmpty(menus)){
            return;
        }
        for (Menu menu : menus) {
            List<Menu> list = map.get(menu.getCode());
            menu.setMenuList(list);
            t(map, list);
        }
    }
}
