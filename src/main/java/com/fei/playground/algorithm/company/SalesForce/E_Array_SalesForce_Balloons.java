import java.util.Arrays;

/**
 * There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.
 * <p>
 * Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
 * <p>
 * Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
 * <p>
 * <p>
 * Input: points = [[10,16],[2,8],[1,6],[7,12]]
 * Output: 2
 * Explanation: The balloons can be burst by 2 arrows:
 * - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
 * - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
 */
public class E_Array_SalesForce_Balloons {

    public static int shoot(int[][] arr) {
        int count = 0;

        //1 sort by xstart
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);

        //2 loop from arr[0], check overlap
        for (int i = 0; i < arr.length; i++) {
            System.out.println("当前i：" + i);
            if (i == arr.length - 1) {
                count++;
                break;
            }
            count++;
            int next = i + 1;
            while (next < arr.length && arr[i][1] > arr[next][0]) next++;
            i = next - 1;
            System.out.println("next:" + next);
            System.out.println("------");
        }

        //3 return count
        return count;
    }

    public static void main(String[] args) {
        //[[10,16],[2,8],[1,6],[7,12]]
        //[[10,16],[2,8],[1,6],[7,12]]
//        System.out.println(shoot(new int[][]{{1, 6}, {2, 8}, {7, 12}, {10, 16}, {20, 21}}));
        System.out.println(shoot(new int[][]{{20, 21}, {1, 6}, {2, 8}, {7, 12}, {10, 16}}));
//        System.out.println(shoot(new int[][]{{1, 3}, {2, 10}, {3, 10}, {4, 10}, {5, 6}}));
    }

}
