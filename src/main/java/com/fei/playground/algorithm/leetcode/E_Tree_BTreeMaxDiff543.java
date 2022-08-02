package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * https://leetcode-cn.com/problems/diameter-of-binary-tree/
 */
public class E_Tree_BTreeMaxDiff543 {

    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max;
    }

    //高度，返还此树的高度
    public int dfs(TreeNode root){
        //出口
        if(root == null) return 0;
        int leftH = dfs(root.left);
        int rightH = dfs(root.right);
        max = Math.max(leftH + rightH, max);
        return Math.max(leftH, rightH) + 1;
    }
}
