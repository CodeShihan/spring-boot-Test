package com.spring.ws.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author shihan
 * @version 1.0
 * @description:
 * @date 2021/5/10 9:43
 */
public class steamtest1 {

    public static void main(String[] args) {
        String[] arr = {"河北省", "山西省", "吉林省", "辽宁省",
                "黑龙江省", "陕西省", "甘肃省", "青海省", "山东省",
                "福建省", "浙江省", "台湾省", "河南省", "湖北省",
                "湖南省", "江西省", "江苏省", "安徽省", "广东省",
                "海南省", "四川省", "贵州省", "云南省", "北京市",
                "天津市", "上海市", "重庆市", "内蒙古自治区",
                "新疆维吾尔自治区", "宁夏回族自治区", "广西壮族自治区",
                "西藏自治区", "香港特别行政区", "澳门特别行政区"};

        long count = Stream.of(arr).filter(s -> s.endsWith("省")).filter(s -> s.length() == 3).count();
        System.out.println("三个字的省份有：" + count + "个");

//        long cou = Stream.of(arr).filter(s ->s.endsWith("省")).filter(s ->s.contains("东")
//        || s.contains("西") || s.contains("北")||s.contains("南")).count();
//        System.out.println("名字中包含方位名词的省份有"+cou+"个");
//        System.out.println("名字中包含方位名词的普通省份：");
//
        Stream.of(arr).filter(s -> s.endsWith("省")).filter(s -> s.contains("东") || s.contains("南") || s.contains("西") || s.contains("北")).forEach(System.out::println);

        System.out.println();

        String[] strings = Stream.of(arr).filter(s ->s.endsWith("市") || s.endsWith("自治区")
                || s.endsWith("行政区")).toArray(String[]::new);

        System.out.println("特殊省份分别是");
        for (String string: strings
             ) {
            System.out.println(string);

        }


    }
}
