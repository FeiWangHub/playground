package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 我的思路1 先把二叉树转化为BST 然后就很好找
 * 官方思路1 递归
 * 官方思路2 A和B的公共访问路径
 *
 * 祖先的定义：到达目标途径所有的节点，包括目标自己，都是祖先
 * 最近公共祖先的定义: root为p和q的公共祖先，且root的左边和右边都不是p和q的公共祖先，则root是最近的
 *
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class M_Tree_CommonAncestor236 {

    HashMap<TreeNode, TreeNode> node2father = new HashMap<>();
    HashSet<TreeNode> pPathSet = new HashSet<>();

    /**
     * 递归解法
     */
    TreeNode result = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //递归分析：每个节点可能状况：
        //如果左右各一个，就是这个节点
        //如果左节点包含两个，向左找
        //如果右节点包含两个，向右找
        //如何自己就是其中一个，ture
        if(root.val == p.val || root.val == q.val) return root;

        dfs(root, p, q);
        return result;
    }

    //deep first search 返还当前节点自己或旗下是否包含p或者q
    public boolean dfs(TreeNode root, TreeNode p, TreeNode q){
        if(root==null) return false;

        boolean leftContain = dfs(root.left, p, q);
        boolean rightContain = dfs(root.right, p, q);

        if(leftContain && rightContain) result = root;
        if((leftContain || rightContain) && (root.val == p.val || root.val == q.val) ) result = root;
        if(root.val == p.val || root.val == q.val || leftContain || rightContain) return true;

        return false;
    }

    /**
     * 官方思路2 A和B的公共访问路径，击败11.85%和8%
     */
    public TreeNode lowestCommonAncestor_path(TreeNode root, TreeNode p, TreeNode q) {
        node2father.put(root, null);
        logFather(root);

        //找p经过的所有点
        while(p!=null){
            pPathSet.add(p);
            p = node2father.get(p);//回到p的father
        }

        //找q经过的所有点，检查第一个出现的祖先
        while(q!=null){
            if(pPathSet.contains(q)) return q;
            q = node2father.get(q);
        }
        return null;
    }

    public void logFather(TreeNode node){
        //当前节点逻辑
        if(node.left != null){
            node2father.put(node.left, node);
            logFather(node.left);
        }
        if(node.right != null){
            node2father.put(node.right, node);
            logFather(node.right);
        }
    }

}
