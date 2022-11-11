package com.fei.playground.algorithm.company.HSBC.london;

//    Given an integer, you can swap a single digit with another to get the maximum number possible
//    (note this may not be the theoretical maximum of all digits).
//
//    Signature:
//    int calculate(int input);
//
//    E.g.1
//    Input: num = 3298
//    Output: 9238
//    Explanation: Swap the number 3 and the number 9.
//
//    E.g.2
//    Input: num = 9765
//    Output: 9765
//    Explanation: No swap.
public class HSBC_Q2_MaxSwap_670 {

    public static void main(String[] args) {
        calculate_mine(3298);
        calculate_mine(9765);
        calculate_mine(0);
        calculate_mine(90909090);// 99009090 TODO 99909000
        calculate_mine(123456789);
        //calculate(Integer.MAX_VALUE);
    }

    /**
     * 现场手撕 100% 57%
     */
    public static Integer calculate_mine(int input) {
        String str = String.valueOf(input);
        char[] arr = str.toCharArray();

        int curStart = 0;
        while (curStart <= arr.length - 2) {
            int max = Integer.MIN_VALUE;
            int maxIdx = -1;
            for (int i = curStart; i < arr.length; i++) {
                if (arr[i] >= max) {
                    max = arr[i];
                    maxIdx = i;
                }
            }
            if (arr[curStart] != max) {//swap
                swap(arr, curStart, maxIdx);
                break;
            }
            curStart++;
        }

        String res = new String(arr);
        System.out.println(res);
        return Integer.valueOf(res);
    }

    /**
     * 官方 官方 100% 68% 跟我一个思路
     */
    public int maximumSwap(int num) {
        char[] charArray = String.valueOf(num).toCharArray();
        int n = charArray.length;
        int maxIdx = n - 1;
        int idx1 = -1, idx2 = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (charArray[i] > charArray[maxIdx]) {
                maxIdx = i;
            } else if (charArray[i] < charArray[maxIdx]) {
                idx1 = i;
                idx2 = maxIdx;
            }
        }
        if (idx1 >= 0) {
            swap(charArray, idx1, idx2);
            return Integer.parseInt(new String(charArray));
        } else {
            return num;
        }
    }

    public static void swap(char[] charArray, int i, int j) {
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
    }

}
