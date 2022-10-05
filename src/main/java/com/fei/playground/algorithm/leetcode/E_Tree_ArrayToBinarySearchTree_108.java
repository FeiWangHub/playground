package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

/**
 * 将有序数组转换为二叉搜索树
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 * <p>
 * https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/
 */
public class E_Tree_ArrayToBinarySearchTree_108 {

    /**
     * 手撕 二分 100% 76%
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return travel(nums, 0, nums.length - 1);
    }

    public TreeNode travel(int[] nums, int left, int right) {
        if (left > right) return null;
        if (left == right) return new TreeNode(nums[left]);

        int mid = left + (right - left) / 2;
        TreeNode cur = new TreeNode(nums[mid]);
        cur.left = travel(nums, left, mid - 1);
        cur.right = travel(nums, mid + 1, right);
        return cur;
    }

}
