import java.sql.SQLOutput;
import java.util.Arrays;

public class Q5 {
    public static boolean isExist(int[] sortedArr, int luckOne){
        int L=0, R=sortedArr.length-1, mid;

        while(L<=R){
            mid = L + ((R-L)>>1);
            if(sortedArr[mid] == luckOne){
                return true;
            }else if(luckOne > sortedArr[mid]){//说明luckOne在mid的右边，舍弃左半部
                L=mid+1;
            }else {
                R=mid-1;
            }
        }

        return false;
    }




    //for test
    public static boolean comparator(int[] arr, int luckOne){//对数器
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==luckOne){
                return true;
            }
        }
        return false;
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
        int maxLength = 10000;
        int maxValue = 500;
        boolean successd = true;

        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(maxLength, maxValue);
            Arrays.sort(arr);

            //随机生成一个值（可能大于数组的值，验证要找的数不存在的情况）
            int luckOne= (int) (Math.random() * maxValue*2) - (int) (Math.random() * maxValue*2);

            if(comparator(arr,luckOne) != isExist(arr,luckOne)){
                successd = false;
                System.out.println("luckOne:"+luckOne);
                System.out.println("comparator:"+comparator(arr,luckOne));
                System.out.println("isExist:"+isExist(arr,luckOne));
                printArr(arr);

                break;
            }
        }

        System.out.println(successd ? "Get it！" : "Sorry!");
    }
}
