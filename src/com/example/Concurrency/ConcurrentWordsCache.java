package com.example.Concurrency;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class ConcurrentWordsCache {

    private static ConcurrentHashMap<String, LongAdder> myHashMap = new ConcurrentHashMap<String, LongAdder>(32768, 0.9f, 1);

    public ConcurrentWordsCache () {

    }

    public static void restart() {
        myHashMap.clear();
    }

    public static void add(final String key, Long addToValue ) {

        myHashMap.computeIfAbsent(key, k-> new LongAdder()).add(addToValue);

    }

    public static void print() {

        System.out.println(myHashMap);

    }

}
