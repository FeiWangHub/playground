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
public class HSBC_Q2_MaxValAfterSwap {

    public static void main(String[] args) {
        calculate(3298);
        calculate(9765);
        calculate(0);
        calculate(90909090);// 99009090 TODO 99909000
        calculate(123456789);
        //calculate(Integer.MAX_VALUE);
    }

    public static Integer calculate(int input) {
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

    public static void swap(char[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = (char) temp;
    }

}
