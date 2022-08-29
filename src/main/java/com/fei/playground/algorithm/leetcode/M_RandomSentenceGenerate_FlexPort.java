package com.fei.playground.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * https://zhuanlan.zhihu.com/p/397536030
 * 随机句子问题
 * 题意为：输入一个句子（即一个单词序列）和一个预期长度，需从输入句子随机抽取单词组成一个新句子（只是一个单词序列，不考虑语法），
 * 新句子的单词数量等于预期长度。随机抽取的具体规则为：随机取一个单词作为首个单词加入新句子，每当新句子加入一个单词，
 * 对于这个单词在输入句子中的所有出现位置，随机选择一个出现位置的下一个单词（即下邻单词）加入新句子，这一加入又会引起新单词的加入，
 * 由此类推，直到新句子达到预期长度，则输出新句子。也就是要实现一个函数String generateSentence(String sentence, int length)。
 * <p>
 * 例如，输入sentence="this is a sentence it is not a good one and it is also bad" length=5，
 * 若"sentence"被选为首个单词，则下一个单词只有一个选择"it"，然后"it"的下一个单词可以在两个位置选择，
 * 但这两个位置都刚好是"is"，而"is"的下一个单词就可以选择"not"或"also"，若选择"not"，则下一个单词只有一个选择"a"，此时凑足了5个单词，
 * 得到新句子"sentence it is not a"。
 * <p>
 * (PLUS)以上是此题的基础版，它还有一个难度加强版：对现有规则做一个修改，再给定一个输入m，
 * 首次随机取输入句子的连续m个单词加入新句子，之后每次仍加入一个单词，每当新句子加入一个单词，不再是找到这个单词在输入句子中的出现位置，
 * 而是对于新句子的“最后m个单词所形成的词组”在输入句子中的所有出现位置，随机选择一个出现位置的下邻单词加入新句子。基础版可看做m=1的特殊情况。
 */
public class M_RandomSentenceGenerate_FlexPort {

    public static String generateSentence_byHashMap(String sentence, int k) {
        StringBuilder sb = new StringBuilder();

        //1 拆分 tokenize
        String[] words = sentence.split(" ");
        System.out.println("words: " + words);
        int len = words.length;

        //3 构建每个word->nextStrs 的hashmap映射
        HashMap<String, List<String>> word2nextStrsMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            List<String> nexts = word2nextStrsMap.computeIfAbsent(words[i], key -> new ArrayList<>());
            nexts.add(words[nextPos(i, words)]);
        }

        //2 第一个random
        Random rand = new Random();
        String targetStr = words[rand.nextInt(len)];
        sb.append(targetStr);
        k--;

        while (k != 0) {
            //3 random get next word
            List<String> nexts = word2nextStrsMap.get(targetStr);
            int chosen = rand.nextInt(nexts.size());
            targetStr = nexts.get(chosen);
            sb.append(targetStr);
            k--;
        }

        return sb.toString();
    }

    public static int nextPos(int pos, String[] words) {
        int next = pos + 1;
        if (next >= words.length) {
            next -= words.length;
        }
        return next;
    }

    public static void main(String[] args) {
        System.out.println(
                M_RandomSentenceGenerate_FlexPort.generateSentence_byHashMap(
                        "this is a sentence it is not a good one and it is also bad", 5));
    }
}
