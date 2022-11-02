package com.juc.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 题目要求：一分钟内完成此题，只能用一行代码实现！
 * 现在有5个用户！筛选：
 * 1、ID 必须是偶数
 * 2、年龄必须大于23岁
 * 3、用户名转为大写字母
 * 4、用户名字母倒着排序
 * 5、只输出一个用户！
 */
public class Test {
    public static void main(String[] args) {
        User u1 = new User(1,"a",21);
        User u2 = new User(2,"b",22);
        User u3 = new User(3,"c",23);
        User u4 = new User(4,"d",24);
        User u5 = new User(6,"e",25);
        // 集合就是存储
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);
        Map<String, User> userMap = list.stream().collect(Collectors.toMap(User::getName, user -> user,(k1, k2)->k1));
        userMap.values().forEach(System.out::println);
//        list.stream()
//                .filter(u->{return u.getId()%2==0;})
//                .filter(u->{return u.getAge()>23;})
//                .map(u->{return u.getName().toUpperCase();})
//                .sorted((uu1,uu2)->{return uu2.compareTo(uu1);})
//                .limit(1)
//                .forEach(System.out::println);

//        int sun = Stream.of(1,2,3).reduce(0,(a,b)->(a+b));
//        System.out.println(sun);
//        List<String> list =  Arrays.asList("jay","tianluo");
//        // 转化为大写
//        List<String> upperCaselist = list.stream().map(String::toUpperCase).collect(Collectors.toList());
//        upperCaselist.forEach(System.out::println);
//        Stream<String> stream = Stream.of("A","B","C","D");
//        boolean match = stream.allMatch(s -> s.contains("c"));
//        System.out.println(match);
    }
}
