package com.fei.playground.algorithm.leetcode;

import cn.hutool.core.lang.Pair;
import com.fei.playground.algorithm.TreeNode;

import java.util.*;

/**
 * 652. 寻找重复的子树
 * 给你一棵二叉树的根节点 root ，返回所有 重复的子树 。
 * 对于同一类的重复子树，你只需要返回其中任意 一棵 的根结点即可。
 * 如果两棵树具有 相同的结构 和 相同的结点值 ，则认为二者是 重复 的。
 * <p>
 * https://leetcode.cn/problems/find-duplicate-subtrees/
 */
public class M_Tree_FindDuplicateSubTree_652 {

    /**
     * 官方Pair 98% 98%解法 O(n) O(n)
     * 方法二：使用三元组进行唯一表示(也可以理解为哈希)
     * 我们可以用一个三元组直接表示一棵子树，即
     * (x,l,r)，它们分别表示：
     * 根节点的值为 x；
     * 左子树的序号为 l；
     * 右子树的序号为 r。
     */
    Map<String, Pair<TreeNode, Integer>> seen = new HashMap<String, Pair<TreeNode, Integer>>();//key是某子树哈希值，pair的value是它的序号
    Set<TreeNode> repeat = new HashSet<TreeNode>();
    int idx = 0;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return new ArrayList<TreeNode>(repeat);
    }

    //返回的int是某节点代表的子树的序号
    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int[] tri = {node.val, dfs(node.left), dfs(node.right)};
        String hash = Arrays.toString(tri);
        if (seen.containsKey(hash)) {
            Pair<TreeNode, Integer> pair = seen.get(hash);
            repeat.add(pair.getKey());
            return pair.getValue();
        } else {
            seen.put(hash, new Pair<TreeNode, Integer>(node, ++idx));
            return idx;
        }
    }

    /**
     * 官方74% 6%
     * StringBuilder 时间空间都是O(n平方)
     * x(左子树的序列化结果)(右子树的序列化结果)
     * 左右子树分别递归地进行序列化。如果子树为空，那么序列化结果为空串。示例 1中的二叉树可以序列化为：
     * 1(2(4()())())(3(2(4()())())(4()()))
     */
//    Map<String, TreeNode> seen = new HashMap<String, TreeNode>();
//    Set<TreeNode> repeat = new HashSet<TreeNode>();
//
//    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
//        dfs(root);
//        return new ArrayList<TreeNode>(repeat);
//    }
//
//    public String dfs(TreeNode node) {
//        if (node == null) {
//            return "";
//        }
//        StringBuilder sb = new StringBuilder();
//        sb.append(node.val);
//        sb.append("(");
//        sb.append(dfs(node.left));
//        sb.append(")(");
//        sb.append(dfs(node.right));
//        sb.append(")");
//        String serial = sb.toString();
//        if (seen.containsKey(serial)) {
//            repeat.add(seen.get(serial));
//        } else {
//            seen.put(serial, node);
//        }
//        return serial;
//    }

    /**
     * 手撕 5% 5%
     * 每棵节点都返回自己的序列化
     * 时间空间都是O(n平方)
     */
    HashMap<String, Integer> subTrees2count = new HashMap<>();
    List<TreeNode> res = new LinkedList<>();

    public List<TreeNode> findDuplicateSubtrees_mine(TreeNode root) {
        //以每个节点为根的后序遍历字符串，为子树 的序列化
        preOrderTravel(root);
        return res;
    }

    //每棵节点都返回自己的序列化
    public LinkedList<Integer> preOrderTravel(TreeNode node) {
        LinkedList<Integer> curTree = new LinkedList<>();
        if (node == null) {
            curTree.add(null);
            return curTree;
        }

        LinkedList<Integer> left = preOrderTravel(node.left);
        LinkedList<Integer> right = preOrderTravel(node.right);
        curTree.add(node.val);
        if (left != null) curTree.addAll(left);
        if (right != null) curTree.addAll(right);

        String str = curTree.toString();
        subTrees2count.put(str, subTrees2count.getOrDefault(str, 0) + 1);
        if (subTrees2count.get(str) == 2) {
            res.add(node);
        }

        return curTree;
    }

}
