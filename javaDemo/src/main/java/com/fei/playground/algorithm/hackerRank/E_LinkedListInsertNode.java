package com.fei.playground.algorithm.hackerRank;

/**
 * https://www.hackerrank.com/challenges/insert-a-node-at-a-specific-position-in-a-linked-list/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=linked-lists
 */
public class E_LinkedListInsertNode {

    public static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode llist, int data, int position) {
        // Write your code here
        SinglyLinkedListNode node = new SinglyLinkedListNode(data);
        node.data=data;
        if(position==0){
            node.next = llist;
            return node;
        }else{
            int curPos = 1;
            SinglyLinkedListNode cur = llist;
            while(cur!=null){
                if(curPos == position){
                    node.next = cur.next;
                    cur.next = node;
                    return llist;
                }
                curPos++;
                cur = cur.next;
            }
        }
        return llist;
    }

    static class SinglyLinkedListNode {
        int data;
        SinglyLinkedListNode next;

        public SinglyLinkedListNode(int data){
            this.data=data;
        }

    }

}
