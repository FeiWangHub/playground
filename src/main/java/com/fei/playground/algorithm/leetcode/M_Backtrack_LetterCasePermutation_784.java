package com.fei.playground.algorithm.leetcode;

import java.util.*;

/**
 * 784. 字母大小写全排列
 * 给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。
 * 返回 所有可能得到的字符串集合 。以 任意顺序 返回输出。
 */
public class M_Backtrack_LetterCasePermutation_784 {

    List<String> res = new LinkedList<>();
    char[] arr;

    /**
     * 手撕回溯 100% 5% 时间2的n次方 空间n
     * 每个位置有3中情况，大写，小写，数字跳过
     */
    public List<String> letterCasePermutation(String s) {
        this.arr = s.toCharArray();
        backtrack(0);
        return res;
    }

    public void backtrack(int curIdx) {
        if (curIdx == arr.length) {//end
            res.add(new String(arr));
            return;
        }

        if (Character.isDigit(arr[curIdx])) {
            backtrack(curIdx + 1);
        } else {
            //小写
            arr[curIdx] = Character.toLowerCase(arr[curIdx]);
            backtrack(curIdx + 1);
            //大写
            arr[curIdx] = Character.toUpperCase(arr[curIdx]);
            backtrack(curIdx + 1);
        }
    }

    /**
     * 官方广度优先
     * 从左往右依次遍历字符，在队列中存储当前为已遍历过字符的字母大小全排列。例如当前字符串为:
     * s=“abc"
     * 假设我们当前已经遍历到字符的第 2 个字符‘b’ 时，则此时队列中已经存储的序列为:
     * “ab",“Ab",“aB",“AB"
     * 遍历策略：
     * - 如果 c 为一个数字，则队列中所有的序列的末尾均加上  c c，将修改后的序列再次进入到队列中
     * - 如果  c c 为一个字母，此时我们在上述序列的末尾依次分别加上 c 的小写形式 lowercase(c) 和
     *    c 的大写形式 uppercase(c) 后，再次将上述数列放入队列
     */
    public List<String> letterCasePermutation_officialBFS(String s) {
        List<String> ans = new ArrayList<String>();
        Queue<StringBuilder> queue = new ArrayDeque<StringBuilder>();
        queue.offer(new StringBuilder());
        while (!queue.isEmpty()) {
            StringBuilder curr = queue.peek();
            if (curr.length() == s.length()) {
                ans.add(curr.toString());
                queue.poll();
            } else {
                int pos = curr.length();
                if (Character.isLetter(s.charAt(pos))) {
                    StringBuilder next = new StringBuilder(curr);
                    next.append((char) (s.charAt(pos) ^ 32));
                    queue.offer(next);
                }
                curr.append(s.charAt(pos));
            }
        }
        return ans;
    }

}
