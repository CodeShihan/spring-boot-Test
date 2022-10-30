package com.kwq.lanbda;

public class TestLambda2 {

    public static void main(String[] args) {
        //lamda表示简化
        ILove love = (int a) -> {
            System.out.println("I love you -->" + a);
        };
        love.love(520);
        //简化1 参数类型
        love = (a) -> {
            System.out.println("I love you -->" + a);
        };
        //简化2 简化括号
        love = a -> {
            System.out.println("I love you -->" + a);
        };
        // 简化3 去掉花括号
        love = a -> System.out.println("I love you -->" + a);

        /**总结:
         * {}简略的条件是只能有一行代码,多行{}就不能简略了
         * 前提是接口为函数式接口(只能有一个方法)
         * 多个参数也可以去掉参数类型,要去掉就都去掉,必须加上()
         */


    }
}

interface ILove {
    void love(int a);
}

