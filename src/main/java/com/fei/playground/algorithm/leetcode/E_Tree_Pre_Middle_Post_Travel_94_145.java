package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 前序遍历 https://leetcode.cn/problems/binary-tree-preorder-traversal/
 * 中序遍历 https://leetcode.cn/problems/binary-tree-inorder-traversal/
 * 后序遍历 https://leetcode.cn/problems/binary-tree-postorder-traversal/
 * <p>
 * 遍历结果规律：
 * 1. 前序遍历，数组第一个是根节点，
 * 2. 后序遍历，数组最后一个是根节点
 * 3. 中序遍历，根节点左边是左子树的中序遍历，右边是右子树的中序遍历（可以查出左右子树size）
 * 4. 无论哪种遍历，左子树和右子树，在结果中都是连续的
 */
public class E_Tree_Pre_Middle_Post_Travel_94_145 {

    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        travel_middle_inOrder(root, res);
//        travel_pre(root, res);
//        travel_post(root, res);
        return res;
    }

    /**
     * 前序遍历 https://leetcode.cn/problems/binary-tree-preorder-traversal/
     * 特点:
     * 1 前序遍历的第一个元素U一定是树的根节点
     * 2.(当你知道了左子树的总数量为n): (根节点idx+1) -> (根节点idx+n-1) = 左子树的先序遍历
     */
    public void travel_preOrder(TreeNode root, LinkedList<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        travel_preOrder(root.left, res);
        travel_preOrder(root.right, res);
    }

    /**
     * 中序遍历 https://leetcode.cn/problems/binary-tree-inorder-traversal/
     * 特点：
     * 1.遍历结果中，根节点在中间，root左边部分一定是左子树，root右边部分是右子树
     * 2.当你知道了root是哪个，就能找出，左子树的中序遍历，和，右子树的后序遍历
     */
    public void travel_middle_inOrder(TreeNode root, LinkedList<Integer> res) {
        if (root == null) return;
        travel_middle_inOrder(root.left, res);
        res.add(root.val);
        travel_middle_inOrder(root.right, res);
    }

    /**
     * 后序遍历 https://leetcode.cn/problems/binary-tree-postorder-traversal/
     * 1.最后一个数字是根节点
     * 2.当给你知道左子树数量n，右子树数量m，那么遍历数组中，左子树是[0->n-1]，右子树是[n->n+m-1] TODO 待验证
     */
    public void travel_postOrder(TreeNode root, LinkedList<Integer> res) {
        if (root == null) return;
        travel_postOrder(root.left, res);
        travel_postOrder(root.right, res);
        res.add(root.val);
    }

    //以下为stack版本

    /**
     * 前序遍历 迭代版本 用stack
     */
    public void travel_preOrder_iteration(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     * 中序遍历 迭代版本 stack
     */
    public void travel_middle_inOrder_iteration(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            if (node.right != null) {
                cur = node.right;
            }
        }
    }

    /**
     * 后序遍历 迭代版本 stack
     */
    public void travel_postOrder_iteration(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(head);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().val + " ");
        }
    }
}
