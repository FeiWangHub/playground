package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

import java.util.ArrayList;

/**
 * 662. 二叉树最大宽度
 * 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
 * 树的 最大宽度 是所有层中最大的 宽度 。
 * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。
 * 将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
 * 题目数据保证答案将会在 32 位 带符号整数范围内。
 * https://leetcode.cn/problems/maximum-width-of-binary-tree/
 */
public class M_Tree_DFS_MaxTreeWidthWithNull_662 {

    ArrayList<Integer> levels = new ArrayList<>();//记录每个level最左边的index
    int max = 0;

    /**
     * 手撕DFS 记录每层最小index 99% 31%
     */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return max;
        dfs(root, 0, 0);
        return max;
    }

    //每一个节点，负责更新自己level的left最小值，和更新max
    public void dfs(TreeNode root, int curLevel, int curIdx) {
        //if (root == null || (root.left == null && root.right == null)) return;
        if (root == null) return;
        if (levels.size() <= curLevel) levels.add(curIdx);
        max = Math.max(max, curIdx - levels.get(curLevel) + 1);
        dfs(root.left, curLevel + 1, curIdx * 2);
        dfs(root.right, curLevel + 1, curIdx * 2 + 1);
    }

}
