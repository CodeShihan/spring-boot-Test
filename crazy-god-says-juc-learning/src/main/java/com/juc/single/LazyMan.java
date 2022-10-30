package com.juc.single;



import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

// 懒汉式单例
// 道高一尺，魔高一丈！
public class LazyMan {
    private volatile static LazyMan lazyMan;

    private static boolean keweiqin=false;

    private LazyMan(){
        synchronized (LazyMan.class){
        if (keweiqin==false){
            keweiqin=!keweiqin;
        }else {
            throw new RuntimeException("不要试图使用反射破坏异常");
        }
    }
}
   //双重检测锁模式 懒汉式单例 DCL懒汉式
    public static LazyMan getInstance() {
        if (lazyMan==null){
            synchronized (LazyMan.class)
            {
                if (lazyMan == null) {
                  lazyMan = new LazyMan();//不是一个原子性操作

                }
            }
        }
        return lazyMan;
    }
  // 只要有反射任何的私有构造都没有用 反射都能破解
    public static void main(String[] args) throws Exception {
      //  LazyMan instance1 = LazyMan.getInstance();
        //拿到构造器
        Field keweiqin = LazyMan.class.getDeclaredField("keweiqin");
        keweiqin.setAccessible(true);
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
         declaredConstructor.setAccessible(true);
        LazyMan instance1 = declaredConstructor.newInstance();
        keweiqin.set(instance1,false);
        LazyMan instance2 = declaredConstructor.newInstance();

        System.out.println(instance1);
        System.out.println(instance2);
    }
}

