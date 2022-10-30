package com.kwq.lanbda;
/*
推导lambda表达式
*/
public class TestLambda1 {
  /*  // 3.静态内部类
   static class Like2 implements ILike {
        @Override
        public void lamda() {
            System.out.println("I like lamda2");
        }
    }*/
    public static void main(String[] args) {
        ILike like = null;

      /*  //4.局部内部类
        class Like13 implements ILike {
            @Override
            public void lamda() {
                System.out.println("I like lamda3");
            }
        }
        like = new Like13();
        like.lamda();

        //5.匿名内部类,没有类的名称,必须借助接口或者父类
         like = new ILike () {
            @Override
            public void lamda() {
                System.out.println("I like lamda4");
            }
        };*/
     /*   like.lamda();*/
        //用lambda简化  jdk8才有的
        like=()->{
            System.out.println("i like lambda5");
        };
        like.lamda();
    }

    }


// 1.定义一个函数式接口
interface ILike {
    void lamda();
}

// 2.实现类
/*class Like implements ILike {
    @Override
    public void lamda() {
        System.out.println("I like lamda");
    }
}*/


