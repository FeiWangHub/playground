package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

/**
 * 二叉搜索树的最近公共祖先
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大
 * （一个节点也可以是它自己的祖先）。”
 */
public class E_Tree_BST_LowestCommonAncestor235_offer68 {

    /**
     * 手撕 32% 64%
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int max = Math.max(p.val, q.val);
        int min = Math.min(p.val, q.val);
        return dfs(root, max, min);
    }

    public TreeNode dfs(TreeNode n, int max, int min){
        if(n==null){
            return null;
        }

        if(n.val >= min && n.val <= max){
            return n;
        }

        TreeNode left = dfs(n.left, max, min);
        TreeNode right = dfs(n.right, max, min);
        return left==null ? right : left ;
    }

    /**
     * 利用BST左边都比自己小，右边都比自己大原理
     * 99.98% 18.83%
     */
    public TreeNode lowestCommonAncestor_smart(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        }else if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }else {
            return root;
        }
    }

}
