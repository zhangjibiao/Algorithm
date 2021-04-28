public class Q13 {
    public static void main(String[] args) {
        String str1 = "abc123d";
        String str2 = "c12dabc";
        String str3 = "bsbininm";
        String str4 = "jmjkbkjkv";
        System.out.println(maxLength1(str1, str2));//1
        System.out.println(maxLength1(str3, str4));//4
        System.out.println(maxLength2(str1, str2));
        System.out.println(maxLength2(str3, str4));
    }

    public static int maxLength1(String str1, String str2){
        //入参检验
        if(str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0){
            return 0;
        }

        char[] str1Arr = str1.toCharArray();
        char[] str2Arr = str2.toCharArray();
        int N = str1Arr.length;
        int M = str2Arr.length;
        return rec1(str1Arr, str2Arr, N-1, M-1);
    }


    //i,j为str1、str2中最后要考虑的下标
    private static int rec1(char[] str1, char[] str2, int i, int j){
        if(i==0 && j==0){
            return str1[0]==str2[0] ? 1 : 0;
        }else if(i == 0){
            return str1[0] == str2[j] ? 1 : rec1(str1, str2, 0, j-1);
        }else if(j == 0){
            return str1[i] == str2[0] ? 1 : rec1(str1, str2, i-1, 0);
        }else {
            int p1 = (str1[i]==str2[j] ? 1 : 0) + rec1(str1, str2, i-1, j-1);
            int p2 = rec1(str1, str2, i, j-1);
            int p3 = rec1(str1, str2, i-1, j);
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    public static int maxLength2(String text1, String text2){
        //入参检验
        if(text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0){
            return 0;
        }

        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int N = s1.length;
        int M = s2.length;
        int[][] dp = new int[N][M];

        dp[0][0] = s1[0] == s2[0] ? 1 : 0;
        for (int j = 1; j < M; j++) {
            dp[0][j] = s1[0] == s2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = s1[i] == s2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = (s1[i]==s2[j] ? 1 : 0) + dp[i-1][j-1];
                int p2 = dp[i][j-1];
                int p3 = dp[i-1][j];
                dp[i][j] = Math.max(p1, Math.max(p2, p3));

            }
        }

        return dp[N-1][M-1];
    }
}
