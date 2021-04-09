import java.util.Arrays;

public class Q4 {
    public static void selectionSort(int[] arr){

        if (null==arr || arr.length<2) return;

        for (int i = 0; i < arr.length-1; i++) { //从i到n中选最小的数，所以i只需要到n-1
            int minIndex=i;
            for (int j = i; j < arr.length; j++) {//找到最小的数的下标
                if(arr[minIndex] >= arr[j]){
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    public static void swap(int[] arr, int i, int j){
        if (i==j) return;
        //不使用额外变量交换两个数（两个数的内存地址不同）
        arr[i] = arr[i]^arr[j];
        arr[j] = arr[i]^arr[j];
        arr[i] = arr[i]^arr[j];
    }

    //for test
    public static void comparator(int[] arr){//对数器
        Arrays.sort(arr);
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
    public static int[] copyArr(int[] arr){
        if(arr==null){
            return null;
        }

        int[] arrNew = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrNew[i] = arr[i];
        }

        return arrNew;
    }

    //for test
    public static boolean isEqual(int[] arr1, int[] arr2){
        boolean equal = true;
        if(arr1==null || arr2==null || arr1.length!=arr2.length){
            return false;
        }

        for (int i = 0; i < arr1.length; i++) {
            if(arr1[i] != arr2[i]){
                equal = false;
                break;
            }
        }

        return equal;
    }

    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }



    //for test
    public static void main(String[] args) {
        int testTimes = 1000;
        int maxLength = 300;
        int maxValue = 500;
        boolean successd = true;

        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = generateRandomArray(maxLength, maxValue);
            int[] arr2 = copyArr(arr1);

            selectionSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                successd = false;
                printArr(arr1);
                break;
            }
        }

        System.out.println(successd ? "Get it！" : "Sorry!");
    }
}
