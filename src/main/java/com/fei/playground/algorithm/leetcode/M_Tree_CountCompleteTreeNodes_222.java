package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

/**
 * 222. 完全二叉树的节点个数
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。
 * 若最底层为第 h 层，则该层包含 1~2h个节点。
 * https://leetcode.cn/problems/count-complete-tree-nodes/
 */
public class M_Tree_CountCompleteTreeNodes_222 {

    /**
     * 不断寻找一个完全二叉树，计算它的size 100% 17%
     * 有待理解
     * 再来回顾一下满二叉的节点个数怎么计算，如果满二叉树的层数为h，则总节点数为：2^h - 1.
     * 那么我们来对 root 节点的左右子树进行高度统计，分别记为 left 和 right，有以下两种结果
     * https://leetcode.cn/problems/count-complete-tree-nodes/solution/chang-gui-jie-fa-he-ji-bai-100de-javajie-fa-by-xia/
     */
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if(left == right){
            return countNodes(root.right) + (1<<left);//2^left
        }else{
            return countNodes(root.left) + (1<<right);//2^right
        }
    }
    private int countLevel(TreeNode root){
        int level = 0;
        while(root != null){
            level++;
            root = root.left;
        }
        return level;
    }

    /**
     * 暴力法 100% 58%
     */
    int sum = 0;

    public int countNodes_brute(TreeNode root) {
        //暴力法
        if (root == null) return 0;
        if (root != null) sum++;
        countNodes_brute(root.left);
        countNodes_brute(root.right);
        return sum;
    }

}
