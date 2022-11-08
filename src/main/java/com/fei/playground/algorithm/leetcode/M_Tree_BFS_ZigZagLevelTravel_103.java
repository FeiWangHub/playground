package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

import java.util.*;

/**
 * 103. 二叉树的锯齿形层序遍历
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。
 * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 */
public class M_Tree_BFS_ZigZagLevelTravel_103 {

    List<List<Integer>> res = new ArrayList<>();

    /**
     * 手撕BFS 70% 70%
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        dfs(root, 0);
        for (int i = 1; i < res.size(); i = i + 2) {
            Collections.reverse(res.get(i));
        }
        return res;
    }

    public void dfs(TreeNode root, int curLevel) {
        if (root == null) return;
        if (res.size() <= curLevel) res.add(curLevel, new LinkedList<>());

        res.get(curLevel).add(root.val);
        dfs(root.left, curLevel + 1);
        dfs(root.right, curLevel + 1);
    }

    /**
     * 官方Queue循环解法 70% 26%
     */
    public List<List<Integer>> zigzagLevelOrder_officialQueue(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> nodeQueue = new ArrayDeque<TreeNode>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;

        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<Integer>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<Integer>(levelList));
            isOrderLeft = !isOrderLeft;
        }

        return ans;
    }

}
