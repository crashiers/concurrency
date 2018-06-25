package com.mmall.concurrency.example.threadLocal;

/**
 * @description:
 * @author: XuJinNiu
 * @create: 2018-06-25 16:31
 **/
public class RequestHolder {
    private final static ThreadLocal<Long> requsetHolder = new ThreadLocal<>();

    public static void add(Long id){
        requsetHolder.set(id);
    }

    public static Long getId(){
        return requsetHolder.get();
    }

    public static void remove(){
        requsetHolder.remove();
    }
}
