package com.fei.playground.algorithm.hackerRank;

/**
 * https://www.hackerrank.com/challenges/reverse-a-doubly-linked-list/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=linked-lists
 */
public class E_ReverseDoubleLinkedList {

     class DoublyLinkedListNode {
         int data;
         DoublyLinkedListNode next;
         DoublyLinkedListNode prev;
     }
    
    public static DoublyLinkedListNode reverse(DoublyLinkedListNode llist) {
        // Write your code here

        // for each node,
        // 1.make pre become next
        // 2.make next become pre
        DoublyLinkedListNode current = llist;
        DoublyLinkedListNode lastVisited = current.prev;
        DoublyLinkedListNode nextToVisit = current.next;

        while(current != null){
            //temp memory
            // lastVisited = current.prev;
            nextToVisit = current.next;

            //swap
            current.next = lastVisited;
            current.prev = nextToVisit;

            //loop
            lastVisited = current;
            current = nextToVisit;
        }
        return lastVisited;
    }

}
