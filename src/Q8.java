import java.util.Arrays;

public class Q8 {
    //在一个有序数组中，找到小于或等于luckOne的最右边的数，实际上求最后一个不比luckOne大的数
    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1; // no exist
        }
        int L = 0, R = arr.length - 1, mid;

        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[R] < arr[R - 1]) {
            return arr.length - 1;
        }

        //一定存在局部最小，因为一定要在while后面有返回值，所以跳过1位置
        L += 2;
        R--;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] < arr[mid + 1] && arr[mid] > arr[mid - 1]) { //判断arr[mid]是不是局部最小值
                return mid;
            } else if (arr[mid] < arr[mid + 1]) { //曲线上升
                R = mid - 1;
            } else { //曲线下降
                L = mid + 1;
            }
        }

        //如果跳出循环，说明局部最小一定在被跳过的1位置
        return 1;
    }
}


