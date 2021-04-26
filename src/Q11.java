public class Q11 {
    public static void main(String[] args) {
        int[] arr1 = {10,100,20,5};//先手105 后手30
        System.out.println(findBestGrade1(arr1));
        int[] arr2 = {10,100,20,5,50};//先手80 后手105
        System.out.println(findBestGrade1(arr2));
        int[] arr3 = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};//获胜者32
        System.out.println(findBestGrade1(arr3));
        System.out.println(findBestGrade2(arr3));
        System.out.println(findBestGrade3(arr3));
    }

    private static int findBestGrade1(int[] arr){
        //入参检验
        int first = f1(arr, 0, arr.length-1);
        int second = g1(arr, 0, arr.length-1);
        return Math.max(first,second);
    }

    private static int f1(int[] arr, int L, int R){
        if(L == R){
            return arr[L];
        }
        int p1 = arr[L] + g1(arr, L+1, R); //拿最左的牌
        int p2 = arr[R] + g1(arr, L, R-1); //拿最右的牌
        return Math.max(p1,p2);
    }

    private static int g1(int[] arr, int L, int R){
        if(L == R){
            return 0;
        }
        int p1 = f1(arr, L+1, R);
        int p2 = f1(arr, L, R-1);
        return Math.min(p1,p2);
    }

    private static int findBestGrade2(int[] arr){
        //入参检验
        int[][] fdp = new int[arr.length+1][arr.length+1];
        int[][] gdp = new int[arr.length+1][arr.length+1];
        for (int i = 0; i < arr.length+1; i++) {
            for (int j = 0; j < arr.length+1; j++) {
                fdp[i][j] = -1;
                gdp[i][j] = -1;
            }
        }

        int first = f2(arr, 0, arr.length-1, fdp, gdp);
        int second = g2(arr, 0, arr.length-1, fdp, gdp);
        return Math.max(first,second);
    }

    private static int f2(int[] arr, int L, int R, int[][] fdp, int[][] gdp){
        if(fdp[L][R] != -1){
            return fdp[L][R];
        }

        if(L == R){
             fdp[L][R] = arr[L];
             return arr[L];
        }else {
            int p1 = arr[L] + g2(arr, L+1, R, fdp, gdp); //拿最左的牌
            int p2 = arr[R] + g2(arr, L, R-1, fdp, gdp); //拿最右的牌
            int ans;
            ans =  Math.max(p1,p2);

            fdp[L][R] = ans;
            return ans;
        }

    }

    private static int g2(int[] arr, int L, int R,  int[][] fdp, int[][] gdp){
        if(gdp[L][R] != -1){
            return gdp[L][R];
        }
        if(L == R){
            gdp[L][L] = 0;
            return 0;
        }else {
            int p1 = f2(arr, L+1, R, fdp, gdp);
            int p2 = f2(arr, L, R-1, fdp, gdp);
            int ans;
            ans =  Math.min(p1,p2);

            gdp[L][R] = ans;
            return ans;
        }

    }

    private static int findBestGrade3(int[] arr){
        //入参检验
        int[][] fdp = new int[arr.length][arr.length];
        int[][] gdp = new int[arr.length][arr.length];

        for(int row = 0; row<arr.length; row++){
            int L=0;
            int R=row;
            while(R<arr.length){
                if(L==R){
                    fdp[L][R] = arr[L];
                    gdp[L][R] = 0;
                }else {
                    int p1 = arr[L] + gdp[L+1][R]; //拿最左的牌
                    int p2 = arr[R] + gdp[L][R-1]; //拿最右的牌
                    int ans =  Math.max(p1,p2);
                    fdp[L][R] = ans;

                    p1 = fdp[L+1][R];
                    p2 = fdp[L][R-1];
                    ans =  Math.min(p1,p2);
                    gdp[L][R] = ans;
                }
                L++;
                R++;
            }
        }

        int first = fdp[0][arr.length-1];
        int second = gdp[0][arr.length-1];
        return Math.max(first,second);
    }


}
