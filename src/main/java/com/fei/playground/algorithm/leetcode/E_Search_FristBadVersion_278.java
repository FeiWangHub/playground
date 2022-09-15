package com.fei.playground.algorithm.leetcode;

public class E_Search_FristBadVersion_278 {

    /**
     * 标准简洁的二分法
     */
    int firstBadVersion_valid(int n) {
        int lo = 1;
        int hi = n;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (isBadVersion(mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return hi;
    }

    /**
     * 击败5% 31% 手撕二分法
     * 今天学到的，不能用（left+right）/2形式，当left和right都是int，
     * 两个值的初始值都超过int限定大小的一半，那么left+right就会发生溢出，
     * 所以应该用left+(right-left)/2来防止求中值时候的溢出。
     */
    public int firstBadVersion(int n) {
        int left = 1, right = n, mid = left + (right - left) / 2;

        //mid==0
        //bad(mid)==true && bad(mid+1)==true --> go left
        //bad(mid)==false && bad(mid+1)==false --> go right
        //bad(mid)==false && bad(mid+1)==true --> return mid+1
        //mid==0 --> return mid
        //bad(mid)==true && bad(mid+1)==false --> 不可能
        while (mid != 0) {
            if (mid == 1 && isBadVersion(1)) {
                return 1;
            } else if (isBadVersion(mid) && isBadVersion(mid + 1)) {
                //go left
                right = mid;
            } else if (!isBadVersion(mid) && !isBadVersion(mid + 1)) {
                //go right
                left = mid;
            } else if (!isBadVersion(mid) && isBadVersion(mid + 1)) {
                return mid + 1;
            }

            mid = left + (right - left) / 2;
        }
        return 1;
    }

    // 下边解法效率不够
    public int firstBadVersion_brute(int n) {
        while (n != 0) {
            if (!isBadVersion(n)) return n + 1;
            n--;
        }
        return 1;
    }

    //mock
    public boolean isBadVersion(int i) {
        return true;
    }
}
