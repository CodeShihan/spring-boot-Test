package com.juc.function;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

public class Test2 {
    public static void main(String[] args) {
        //1. 匿名表达式重写函数式接口 Runnable中的run 方法实现多线程

//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("匿名内部类实现多线程");
//            }
//        });
//        t1.start();
//        // lambda 表达式重写函数式接口  Runnable中的run 方法实现多线程
//        Thread t2 = new Thread(()-> System.out.println());
//        t2.start();
//
//        // 匿名表达式 重写函数式 接口 Compartor 中的compare()方法自定义比较规则
//        List<Integer> list = Arrays.asList(3, 6, 1, 4, 5, 6);
//        Collections.sort(list, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2-o1;
//            }
//        });
//
//        // lambda 表达式重写函数式接口Compartor 中的compare()方法自定义比较规则
//        Collections.sort(list,((o1, o2) -> o2-o1));
//        for (int i : list){
//            System.out.println(i +"\t");
//        }

        //3. lambda 表达式重写函数式接口 Supplier  中的get（）方法拿到最大值
        int[] nums =  {1,2,3,4,5,-6,-7,-8};
        int res = getMax(()->{
            int max = nums[0];
            for(int number : nums){
                if(max < number){
                    max = number;
                }
            }
              return max;
        });
        System.out.println("数量中最大的值为" + res);
    }

    public static Integer getMax(Supplier<Integer> integerSupplier){
        return integerSupplier.get();
    }
}
