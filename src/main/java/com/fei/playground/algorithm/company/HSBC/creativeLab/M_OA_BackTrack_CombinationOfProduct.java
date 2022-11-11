package com.fei.playground.algorithm.company.HSBC.creativeLab;

import java.util.LinkedList;
import java.util.List;

/**
 * SHL题目
 * Dr. Jackson, a researcher, wishes to perform an experiment. He has a variety of toxic chemicals.
 * Each chemical has some vapor rate. When two chemicals are mixed, then the vapor rate of the mixture is the multiplication of their respective vapor rates.
 * <p>
 * Dr. Jackson picks two equal-sized sets of non-overlapping, consecutively-placed chemicals from a series of chemicals in his lab.
 * He reverses the positions of the chemicals in the second set. He then mixes each chemical from the first set with the correspondingly-placed chemical of the second set.
 * The total vapor rate at the end of the experiment is the sum of the products of the respective vapor rates of the chemicals that he mixed from both sets.
 * If the total vapor rate is negative, he will not pick any set;
 * <p>
 * Write an algorithm to find the maximum vapor rate obtainable after the experiment.
 * <p>
 * Input
 * first line is number N ,chemicals in series ,The second line consists of N space seprated integers.
 * <p>
 * Output
 * print an integer representing the maximum vapor rate after the experiment.
 * <p>
 * Constraints
 * 2<=num<=3000
 * 0<=2*S<=num,where S is the size of the set
 * -10^6<=vaporRate<=10^6
 * 0<=i<num
 * <p>
 * example
 * 6
 * 8 0 5 3 9 6
 * output-72
 * <p>
 * 关键字：consecutively-placed, non-overlapping
 *
 * 暂时不确定正确性
 */
public class M_OA_BackTrack_CombinationOfProduct {

    static int max = 0;
    static LinkedList<Integer> tempSetB;

    public static int maximumVaporRate(int[] vaporRate) {
        //1 split into 2 sets
        int len = vaporRate.length / 2;
        int start = 0;
        int end = start + len - 1;
        LinkedList<Integer> setA;
        while (end < vaporRate.length) {
            setA = new LinkedList<>();
            tempSetB = new LinkedList<>();
            for (int i = 0; i < vaporRate.length; i++) {
                //TODO 考虑vaporRate不是偶数个的情况?
                if (i >= start && i <= end) {
                    setA.add(vaporRate[i]);
                } else {
                    tempSetB.add(vaporRate[i]);
                }
            }
            System.out.println("");
            System.out.println(String.format("Start %s End %s", start, end));

            backtracking(setA, 0);
            start++;
            end = start + len - 1;
        }
        //2 set2 is fixed, try all combination of set 1, get sum of it
        return max;
    }

    public static void calMax(LinkedList<Integer> setA, LinkedList<Integer> setB) {
        //for all permutation of setA, 每一个都跟setB配对算结果
        int sum = 0;
        System.out.println("setA" + setA);
        System.out.println("setB" + setB);
        for (int i = 0; i < setA.size(); i++) {
            sum += setA.get(i) * setB.get(i);
        }
        System.out.println("sum: " + sum);
        max = Math.max(sum, max);
    }

    public static void backtracking(LinkedList<Integer> setA, int curIdx) {
        if (curIdx == setA.size()) {
            calMax(setA, tempSetB);
            return;
        }

        //把当前位置，跟后边所有的位置做依次做交换
        for (int i = curIdx; i < setA.size(); i++) {
            swap(setA, curIdx, i);//当前状态
            backtracking(setA, curIdx + 1);
            swap(setA, curIdx, i);//回到当前装填
        }
    }

    public static void swap(List<Integer> setA, int curIdx, int targetIdx) {
        int temp = setA.get(curIdx);
        setA.set(curIdx, setA.get(targetIdx));
        setA.set(targetIdx, temp);
    }

    public static void main(String[] args) {
        int[] input={8,0,5,3,9,6};//答案是102？
        System.out.println(maximumVaporRate(input));
    }
}
