package com.fei.playground.algorithm.company;

import com.fei.playground.algorithm.TreeNode;
import com.fei.playground.util.DateUtil;

import java.util.Date;
import java.util.LinkedList;

/**
 * 2022 Jan 28一面
 * 左右翻转树
 */
public class MS_Tree_FlipLeftRight {

    public static void main(String[] args) {
        System.out.println(String.format("---- Started, time now is %s ----", DateUtil.HHmmssSSS.format(new Date())));

        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);

        root.left = left;
        root.right = right;

        flipBTreeLoop(root);
        System.out.println(root.right);//==2
    }

    static TreeNode temp;
    static TreeNode cur;
    public static void flipBTreeLoop(TreeNode root){
        if(root==null) return;

        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (nodes.size()!=0){
            cur = nodes.pop();

            temp = cur.left;
            cur.left=cur.right;
            cur.right=temp;

            if(cur.left!=null) nodes.add(cur.left);
            if(cur.right!=null) nodes.add(cur.right);
        }
    }

    public static void flipBTree(TreeNode root){
        if(root==null) return;

        temp = root.left;
        root.left=root.right;
        root.right=temp;

        flipBTree(root.left);
        flipBTree(root.right);
    }
    
}
