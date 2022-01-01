package com.fei.playground.algorithm.hackerRank;

import java.util.HashMap;
import java.util.Stack;

/**
 * https://www.hackerrank.com/challenges/find-the-merge-point-of-two-joined-linked-lists/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=linked-lists
 *
 * 多种解法 https://blog.csdn.net/fengxinlinux/article/details/78885764
 */
public class E_LinkedListFindMergePoint {

     class  SinglyLinkedListNode {
         int data;
         SinglyLinkedListNode next;
     }

    /**
     * 组成2个环解法
     *
     */
    static int findMergeNode_doubleCircle(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        SinglyLinkedListNode currentA = head1;
        SinglyLinkedListNode currentB = head2;

        //Do till the two nodes are the same
        while(currentA != currentB){
            //If you reached the end of one list start at the beginning of the other one
            //currentA
            if(currentA.next == null){
                currentA = head1;
            }else{
                currentA = currentA.next;
            }
            //currentB
            if(currentB.next == null){
                currentB = head2;
            }else{
                currentB = currentB.next;
            }
        }
        return currentB.data;
    }

    /**
     * hashmap解法
     */
    static int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        //hashmap
        HashMap<SinglyLinkedListNode, Integer> map = new HashMap<>();
        while(head1 != null){
            map.put(head1, head1.data);
            head1 = head1.next;
        }

        while(head2 !=null){
            if(map.containsKey(head2)){
                return head2.data;
            }
            head2=head2.next;
        }
        return 0;
     }

    /**
     * stack解法 runtime error 可能是内存溢出
     */
    static int findMergeNodeByStack(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        //by stack
        Stack<SinglyLinkedListNode> s1 = new Stack<>();
        Stack<SinglyLinkedListNode> s2 = new Stack<>();

        while(head1!=null){
            s1.push(head1);
            head1 = head1.next;
        }
        while(head2!=null){
            s2.push(head2);
            head2 = head2.next;
        }

        SinglyLinkedListNode temp;
        while(!s1.isEmpty() && !s2.isEmpty()){
            temp = s1.peek();
            s1.pop();
            s2.pop();
            if(s1.peek()!=s2.peek()){
                return temp.data;
            }
        }
        return 0;
    }

}
