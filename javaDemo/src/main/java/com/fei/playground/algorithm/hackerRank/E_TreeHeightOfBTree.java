package com.fei.playground.algorithm.hackerRank;

/**
 * https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree/problem?isFullScreen=true
 */
public class E_TreeHeightOfBTree {

    class Node{
        int data;
        Node left;
        Node right;
    }

    public static int height(Node root) {
        // Write your code here.
        if(root == null){
            return -1;
        }

        int leftHeight = 0;
        int rightHeight = 0;

        if(root.left!=null){
            leftHeight = 1 + height(root.left);
        }

        if(root.right!=null){
            rightHeight =  1 + height(root.right);
        }

        return Math.max(leftHeight, rightHeight);
    }

}
