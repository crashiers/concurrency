package com.mmall.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.mmall.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * 不可变对象
 *
 * @description:
 * @author: XuJinNiu
 * @create: 2018-06-25 14:10
 **/
@Slf4j
@ThreadSafe
public class ImmutableExample2 {
    private static final ImmutableList list = ImmutableList.of(1, 2, 3);//Guava

    private static final ImmutableSet set = ImmutableSet.copyOf(list);

    private static final ImmutableMap<Integer,Integer> map2 = ImmutableMap.of(1,2,3,4,5,6);
    public static void main(String[] args) {
        list.add(1);
        set.add(1);
    }
}
