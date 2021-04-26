public class Q10 {
    public static void main(String[] args) {
        System.out.println(findWays1(6,2,4,4));//4:3454 3234 1234 3434
        System.out.println(findWays1(5,2,6,4));//13
        System.out.println(findWays2(5,2,6,4));//13
        System.out.println(findWays3(5,2,6,4));//13
    }

    private static int findWays1(int N, int M, int K, int P){
        //判断入参规范性
        //为什么要在这里判断入参规范性：因为在这里只需要判断一次，如果放在递归函数，因为递归函数是自己调用的，入参完全是正确的
        // 而递归函数会调用很多次，极大地浪费资源在没必要的入参判断
        if(N<2 || M>N || M<1 || K<0 || P<0 || P>N){
            return -1;
        }
        return findByRec1(N, M, K, P);
    }

    private static int findByRec1(int N, int M, int K, int P) {
        //basicCase
        if(K == 0){
            return P==M ? 1 : 0;
        }

        if(M==1){//只能往右走
            return findByRec1(N,M+1, K-1, P);
        }
        if(M==N){//只能往左走
            return findByRec1(N,M-1, K-1, P);
        }

        //机器人的位置在中间
        return findByRec1(N, M-1, K-1, P) + findByRec1(N, M+1, K-1, P);
    }

    private static int findWays2(int N, int M, int K, int P){
        //判断入参规范性
        //为什么要在这里判断入参规范性：因为在这里只需要判断一次，如果放在递归函数，因为递归函数是自己调用的，入参完全是正确的
        // 而递归函数会调用很多次，极大地浪费资源在没必要的入参判断
        if(N<2 || M>N || M<1 || K<0 || P<0 || P>N){
            return -1;
        }

        int[][] dp = new int[N+1][K+1];//首行弃而不用
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }

        return findByRec2(N, M, K, P, dp);
    }

    private static int findByRec2(int N, int M, int K, int P, int[][] dp) {
        if(dp[M][K] != -1){
            return dp[M][K];
        }

        int ans;
        //basicCase
        if(K == 0){
            ans = P==M ? 1 : 0;
        }else if(M==1){//只能往右走
            ans =  findByRec2(N,M+1, K-1, P, dp);
        }else if(M==N){//只能往左走
            ans = findByRec2(N,M-1, K-1, P, dp);
        }else {
            //机器人的位置在中间
            ans = findByRec2(N, M-1, K-1, P, dp) + findByRec2(N, M+1, K-1, P, dp);
        }

        dp[M][K] = ans;
        return ans;
    }

    private static int findWays3(int N, int M, int K, int P){
        //判断入参规范性
        if(N<2 || M>N || M<1 || K<0 || P<0 || P>N){
            return -1;
        }

        int[][] dp = new int[N+1][K+1];//首行弃而不用
        for (int i = 1; i <= N; i++) {
            if(i == P){
                dp[i][0] = 1;
            }else {
                dp[i][0] =0;
            }
        }

        for (int j = 1; j <= K ; j++) {
            dp[1][j] = dp[2][j-1];
            for (int i = 1; i < N; i++) {
                dp[i][j] = dp[i-1][j-1] + dp[i+1][j-1];
            }
            dp[N][j] = dp[N-1][j-1];
        }

        return dp[M][K];
    }
}
