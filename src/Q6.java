import java.util.Arrays;

public class Q6 {
    //在一个有序数组中，找到大于或等于luckOne的最左边的数，实际上求第一个不比luckOne小的数
    public static int firstBigger(int[] sortedArr, int luckOne){
        int L=0, R=sortedArr.length-1, mid=-1;
        int first  = -1; //假定不存在，返回-1
        while(L<=R){
            mid = L + ((R-L)>>1);
            if(sortedArr[mid] >= luckOne){
                first = mid; //用first标记大于等于luckOne的坐标
                R=mid-1;
            }else {
                L=mid+1;
            }
        }

        return first;
    }




    //for test
    public static int comparator(int[] sortedArr, int luckOne){//对数器
        for (int i = 0; i < sortedArr.length; i++) {
            if(sortedArr[i]>= luckOne){
                return i;
            }
        }
        return -1;
    }

    //for test
    public static int[] generateRandomArray(int maxLength, int maxValue){
        int[] arr = new int[(int) ((maxLength+1) * Math.random())];//随机数组大小：
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue+1) * Math.random()) - (int) ((maxValue) * Math.random()) ;
        }
        return arr;
    }

    //for test
    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    //for test
    public static void main(String[] args) {
        int testTimes = 10000;
        int maxLength = 1000;
        int maxValue = 500;
        boolean successd = true;

        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(maxLength, maxValue);
            Arrays.sort(arr);

            //随机生成一个值（可能大于数组的值，验证要找的数不存在的情况）
            int luckOne= (int) (Math.random() * maxValue*2) - (int) (Math.random() * maxValue*2);

            if(comparator(arr,luckOne) != firstBigger(arr,luckOne)){
                successd = false;
                System.out.println("luckOne:"+luckOne);
                System.out.println("comparator:"+comparator(arr,luckOne));
                System.out.println("firstBigger:"+firstBigger(arr,luckOne));
                printArr(arr);
                break;
            }


        }

        System.out.println(successd ? "Get it！" : "Sorry!");
    }
}
