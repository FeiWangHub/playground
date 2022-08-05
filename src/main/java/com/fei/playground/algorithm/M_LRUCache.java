package com.fei.playground.algorithm;

import java.util.*;

/**
 * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以正整数作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶：你是否可以在O(1) 时间复杂度内完成这两种操作？
 *
 * 链接：https://leetcode-cn.com/problems/lru-cache
 */
public class M_LRUCache {

    int capacity;
    LinkedHashMap<Integer, Integer> cache;

    public M_LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return cache.size() > capacity;
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }

    //这个是我写的solution 22个用例1个超时，map的value改成自己的对象，指向一个双向链表就可以完美解决了
//    HashMap<Integer, Integer> cache;
//    int currentSize = 0;
//    int cap = 0;
//    Deque<Integer> accessQueue;
//
//    public LRUCache(int capacity) {
//        cache = new HashMap<>(capacity);
//        accessQueue = new LinkedList<>();
//        cap = capacity;
//    }
//
//    public int get(int key) {
//        if(cache.containsKey(key)){
//            accessQueue.remove(key);
//            accessQueue.offerFirst(key);
//            return cache.get(key);
//        }else{
//            return -1;
//        }
//    }
//
//    public void put(int key, int value) {
//        if(cache.containsKey(key)){
//            cache.put(key, value);
//            accessQueue.remove(key);
//            accessQueue.offerFirst(key);
//        }else{
//            //未到达上限
//            if(currentSize < cap){
//                cache.put(key, value);
//                accessQueue.offerFirst(key);
//                currentSize++;
//            }else{//到达上限
//                cache.remove(accessQueue.pollLast());
//                cache.put(key, value);
//                accessQueue.offerFirst(key);
//            }
//        }
//    }

}
