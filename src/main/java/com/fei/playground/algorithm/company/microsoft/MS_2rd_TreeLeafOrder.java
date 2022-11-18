package com.fei.playground.algorithm.company.microsoft;

import com.fei.playground.util.DateUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 给定一个普通二叉树，和一个包含了n个叶子节点的列表
 * 判断按顺序走完列表中的节点的话，会不会把某一个路径走两遍
 */
public class MS_2rd_TreeLeafOrder {

    //    static List<Integer> exp = Arrays.asList(5, 8, 10);
    static List<Integer> exp = Arrays.asList(8,5, 10);
    static boolean result = true;

    public static void main(String[] args) {
        System.out.println(String.format("---- Started, time now is %s ----", DateUtil.HHmmssSSS.format(new Date())));

        Node root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(4);

        root.left.left = new Node(3);
        root.left.right = new Node(5);

        root.right.left = new Node(6);
        root.right.right = new Node(7);

        root.right.left.left = new Node(8);
        root.right.left.right = new Node(10);

        ds(root);
        System.out.println(result);
    }

    //is this node valid
    static public void ds(Node root){
        if(root==null) return;
        ds(root.left);
        ds(root.right);
        if(root.left!=null && exp.contains(root.left.val)
                && root.right!=null && exp.contains(root.right.val)){
            if( Math.abs(exp.indexOf(root.left.val) - exp.indexOf(root.right.val)) > 1 ){
                result = false;
            }
        }
    }

    static class Node {
        public Node(int val){
            this.val=val;
        }
        int val;
        Node left;
        Node right;
    }
    
}
