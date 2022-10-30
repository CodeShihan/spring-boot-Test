package com.spring.ws.spring.test;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private String code;

    private String parentCode;

    private List<Menu> menuList;


}
