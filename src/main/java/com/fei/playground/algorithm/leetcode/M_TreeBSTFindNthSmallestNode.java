package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

import java.util.ArrayList;

/**
 * 二叉搜索树中第K小的元素
 * 重要属性：对BST的遍历结果，是一个升序的结果
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
 */
public class M_TreeBSTFindNthSmallestNode {

    private int count = 0;
    private int num = Integer.MIN_VALUE;
    /**
     * 不用array的解法
     */
    public int kthSmallestBest(TreeNode root, int k) {
        hlp(root, k);
        return num;
    }

    private void hlp(TreeNode node, int k){
        if(node == null) return;

        hlp(node.left, k);

        //中间访问自己的代码总是按照递增顺序运行的
        count++;
        if(count == k){
            num = node.val;
            return;
        }

        hlp(node.right, k);
    }


    /**
     * 我的遍历解法
     */
    public int kthSmallest(TreeNode root, int k) {
        //travel through
        ArrayList<Integer> arr = new ArrayList<>();
        loopTree(root, arr);
        return arr.get(k - 1);
    }

    public void loopTree(TreeNode node, ArrayList<Integer> arr) {
        //visit left
        if (node.left != null) {
            loopTree(node.left, arr);
        }

        //visit self
        arr.add(node.val);

        //visit right
        if (node.right != null) {
            loopTree(node.right, arr);
        }
    }

}
