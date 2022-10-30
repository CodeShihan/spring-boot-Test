package com.spring.ws.spring;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author shihan
 * @version 1.0
 * @description:
 * @date 2021/5/10 9:43
 */
public class steamtest {
    public static void main(String[] args) {
        ArrayList<Integer> arr =new ArrayList<>();
        Map<String,Integer>  map = new HashMap<>();
        map.put("张三",85);
        map.put("李四",80);
        map.put("王五",90);
        map.put("赵六",95);
        map.put("田七",70);
        Set<String> str = map.keySet();
        str.stream().forEach(s -> arr.add(map.get(s)));
        int sum = arr.stream().mapToInt(value -> value).sum()/arr.size();
        System.out.println(sum);
    }


}
