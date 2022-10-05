package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

import java.util.ArrayList;

/**
 * 1382. 将二叉搜索树变平衡 (题目108衍生)
 * 给你一棵二叉搜索树，请你返回一棵平衡后的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。如果有多种构造方法，请你返回任意一种。
 * 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是平衡的 。
 * https://leetcode.cn/problems/balance-a-binary-search-tree/
 */
public class M_Tree_BalanceABinarySearchTree_1382 {

    /**
     * 手撕 99% 16%
     * 1. 中序遍历，把当前Tree改成有序List
     * 2. 二分法构建新Tree
     */
    public TreeNode balanceBST(TreeNode root) {
        ArrayList<Integer> list = new ArrayList();
        midTravel(root, list);//build sorted list
        return travel(list, 0, list.size() - 1);
    }

    public void midTravel(TreeNode root, ArrayList<Integer> list) {
        if (null == root) return;
        midTravel(root.left, list);
        list.add(root.val);
        midTravel(root.right, list);
    }

    public TreeNode travel(ArrayList<Integer> list, int left, int right) {
        if (left > right) return null;
        if (left == right) return new TreeNode(list.get(left));

        int mid = left + (right - left) / 2;
        TreeNode cur = new TreeNode(list.get(mid));
        cur.left = travel(list, left, mid - 1);
        cur.right = travel(list, mid + 1, right);
        return cur;
    }

}
