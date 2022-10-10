package com.fei.playground.algorithm.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 146. LRU 缓存
 * 剑指 Offer II 031. 最近最少使用缓存 https://leetcode.cn/problems/OrIXps/
 * 运用所掌握的数据结构，设计和实现一个 LRU (Least Recently Used，最近最少使用) 缓存机制 。
 * <p>
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以正整数作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 */
public class M_Hashmap_LRUCache_146_offer031 {

    /**
     * 官方手撕双向链表 78% 78%
     */
    public class LRUCache {
        class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;

            public DLinkedNode() {
            }

            public DLinkedNode(int _key, int _value) {
                key = _key;
                value = _value;
            }
        }

        private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
        private int size;
        private int capacity;
        private DLinkedNode head, tail;

        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            head = new DLinkedNode();// 使用伪头部和伪尾部节点(这两个节点不保存数值)
            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                return -1;
            }
            moveToHead(node);// 如果 key 存在，先通过哈希表定位，再移到头部
            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                DLinkedNode newNode = new DLinkedNode(key, value);// 如果 key 不存在，创建一个新的节点
                cache.put(key, newNode);// 添加进哈希表
                addToHead(newNode);// 添加至双向链表的头部
                ++size;
                if (size > capacity) {
                    DLinkedNode tail = removeTail(); // 如果超出容量，删除双向链表的尾部节点
                    cache.remove(tail.key);// 删除哈希表中对应的项
                    --size;
                }
            } else {
                node.value = value;// 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
                moveToHead(node);
            }
        }

        private void addToHead(DLinkedNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void moveToHead(DLinkedNode node) {
            removeNode(node);
            addToHead(node);
        }

        private DLinkedNode removeTail() {
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }
    }

    /**
     * 手撕低效 7% 16%
     * 因为LinkedList.remove操作不是O(1)
     * 高效版本需要手撕双向链表
     */
    class LRUCache_Mine {

        HashMap<Integer, Integer> map;
        LinkedList<Integer> keyQueue;//last is least used
        int cap;

        public LRUCache_Mine(int capacity) {
            map = new HashMap<>(capacity);
            keyQueue = new LinkedList<>();
            this.cap = capacity;
        }

        public int get(int key) {
            if (!map.containsKey(key)) return -1;
            keyQueue.remove(Integer.valueOf(key));//这个不是O(1)操作
            keyQueue.offerFirst(key);
            return map.get(key);
        }

        /**
         * 注意put操作也会吧key当成最近使用过
         */
        public void put(int key, int value) {
            //新加入的，不属于least used
            if (map.containsKey(key)) {//update
                keyQueue.remove(Integer.valueOf(key));
            } else {//add new
                if (map.size() == cap) {
                    map.remove(keyQueue.pollLast());
                }
            }
            map.put(key, value);
            keyQueue.addFirst(key);//新加入的，不属于least used
        }
    }

    /**
     * 借助JDK
     */
    class LRUCache_byJDK extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LRUCache_byJDK(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }
}
