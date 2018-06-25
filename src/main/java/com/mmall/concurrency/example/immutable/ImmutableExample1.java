package com.mmall.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.mmall.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**不可变对象
 * @description:
 * @author: XuJinNiu
 * @create: 2018-06-25 14:10
 **/
@Slf4j
@ThreadSafe
public class ImmutableExample1 {
    private final static Integer              a =1;
    private final static String               b = "2";
    private  static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(2,3);
        map.put(4,5);
        map.put(6,7);
        map = Collections.unmodifiableMap(map); //JDK
    }
    public static void main(String[] args){
        map.put(4,5);
    }
}
