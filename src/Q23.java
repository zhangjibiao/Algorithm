import java.util.Arrays;

public class Q23 {
    public static int minShipNum(int[] arr, int limit){ // 未写对数器保证代码没bug
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > limit){
                return Integer.MAX_VALUE;
            }
        }
        Arrays.sort(arr);
        int halfLimit = limit / 2;
        int L=0,R=0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > halfLimit){
                L = i-1;
                R = i;
                break;
            }
        }

        int ans = 0;
        int pairSuc = 0;
        int pairFail = 0;

        while (L >= 0){
            while (L >= 0 && arr[L] + arr[R] > limit){ //看看L有没有到头，看看L需不需要向左移
                L--;
                pairFail++;
            }
            int RstepCount = 0; //R向右移动了几步
            while(R < arr.length-1){ // 看看R能不能往右移
                R++;
                if (arr[L] + arr[R] > limit){ // 说明移不动，不能移
                    R--;
                    break;
                }else {
                    RstepCount++;
                }
            }
            while(RstepCount-- >= 0 && L >= 0){
                pairSuc++;
                L--; //结束最近的while后，L来到没配对的位置
                if(L < 0){ // 代表左边没够配对
                    pairFail += RstepCount;
                }
            }

            if(L > 0){
                L--;
            }
            if(R < arr.length-1){
                R++;
            }
        }

        return (int) (pairSuc + Math.ceil(pairFail/2.0) + (arr.length-1-R));
    }

    public static void main(String[] args) {
        int[] arr = {1,1,1,1,1,2,3,5,5,5,5,6,6,8,8,9,10}; //8
        System.out.println(minShipNum(arr, 10));
    }
}
