package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉搜索树中的众数
 * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
 * 如果树中有不止一个众数，可以按 任意顺序 返回。
 * <p>
 * <p>
 * TODO 研究一下空间复杂度O(1)的Morris中序遍历法
 * https://leetcode.cn/problems/find-mode-in-binary-search-tree/
 */
public class E_Tree_BST_findModeInBST_501 {

    //官方不用hashmap的中序遍历法，100%, 52%
    List<Integer> answer = new ArrayList<Integer>();
    int base, count, maxCount;

    public int[] findMode(TreeNode root) {
        dfs(root);
        int[] mode = new int[answer.size()];
        for (int i = 0; i < answer.size(); ++i) {
            mode[i] = answer.get(i);
        }
        return mode;
    }

    public void dfs(TreeNode o) {
        if (o == null) {
            return;
        }
        dfs(o.left);
        update(o.val);
        dfs(o.right);
    }

    public void update(int x) {
        if (x == base) {
            ++count;
        } else {
            count = 1;
            base = x;
        }
        if (count == maxCount) {
            answer.add(base);
        }
        if (count > maxCount) {
            maxCount = count;
            answer.clear();
            answer.add(base);
        }
    }

    //第一版手撸 HashMap + 中序遍历解法 29% 5%
    HashMap<Integer, Integer> int2Count = new HashMap<>();
    int max;

    public int[] findMode_mine(TreeNode root) {
        inOrder(root);
        LinkedList<Integer> res = new LinkedList<>();

        int2Count.forEach((k, v) -> {
            if (v == max) {
                res.add(k);
            }
        });

        int[] modes = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            modes[i] = res.get(i);
        }
        return modes;
    }

    public void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        int2Count.put(root.val, int2Count.getOrDefault(root.val, 0) + 1);
        max = Math.max(int2Count.get(root.val), max);
        inOrder(root.right);
    }

}
