public class Q9 {
    //一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
    public static void task2(int[] arr) {
        int eor=0;
        for (int i = 2; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    //for test
    public static void main(String[] args){
        int[] arr  = {0,0,1,2,4,5,2,4,5,1,5,5,10};
        //task2(arr);

        //task3 test
        //System.out.println(task3(128));//40: 101000

        //task4 test
        int[] arr4 = {0,0,1,1,2,2,4,8};
        //task4(arr4);

        //task5 test
        //task5(45);//45:00101101

        //task6 test
        int[] arr6 = {0,0,1,1,1,1,5,5,5,5,8,8,8,8};
        task6(arr6, 2,4);//实际上k没用
    }

    public static int task3(int arg){
        return arg & (-arg);
    }

    public static void task4(int[] arr){
        int eor=0, eor1;
        for (int value:arr) {
            eor ^= value;
        }

        //提取eor最右侧的1，算出位置
        int rightOne = eor^(-eor);

        eor1 = eor;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0){ //如果该位置相同，得1
                eor1 ^= arr[i];
            }
        }

        eor = eor1 ^ eor;
        System.out.println("a:"+eor+"\nb:"+eor1);
    }

    public static void task5(int num){
        int count = 0;
        int rightOne;

        while(num != 0){
            rightOne = num & (-num); //提取最右侧的1
            count++;  //计算有多少个1
            num = num ^ rightOne; //将最右侧的1抹为0，相同为0
        }

        System.out.println(count);
    }

    public static void task6(int[] arr, int K, int M){
        //记录每一位上出现1的次数
        int[] arr32 = new int[32];
        for(int num : arr){
            for(int i=0; i < 31; i++){
                arr32[i] += (num>>i) & 1; // 取出i位
            }
        }

        //如果i位上的总次数模上M != 0， 说明a在这位上是1，因为如果是1，总次数不会是M的整数倍
        int ans = 0;
        for(int i = 0; i < 31; i++){
            if(arr32[i] % M != 0){
                ans |= (1<<i);
            }
        }
        System.out.println(ans);
    }
}




