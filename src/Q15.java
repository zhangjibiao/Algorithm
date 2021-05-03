public class Q15 {
    public static void main(String[] args) {
        String str = "1314159";
        System.out.println(transAmount("7210231231232031203123"));//54
        System.out.println(transAmount("7210245621340046640704431231232031203123"));//0
        System.out.println(transAmount(str));//8

        System.out.println(dp("7210231231232031203123"));//54
        System.out.println(dp("7210245621340046640704431231232031203123"));//0
        System.out.println(dp(str));//8
    }

    public static int transAmount(String str){
        if (str == null){
            return -1;
        }else if (str.length() == 0){
            return 0;
        }else {
            return rec(str.toCharArray(), str.length()-1);
        }
    }

    private static int rec(char[] str, int index){
        if (index < 0){
            return 1;
        }
        int p1 = str[index]!='0' ? rec(str,index-1): 0;
        int p2 = 0;
        if (index > 0 && str[index-1] != '0' && (str[index-1]-'0')*10+(str[index]-'0') < 27 ){
            p2 = rec(str,index-2);
        }
        return p1+p2;
    }

    public static int dp(String s){
        if (s == null ){
            return -1;
        }else if (s.length() == 0){
            return 0;
        }else {
            char[] str = s.toCharArray();
            int[] dp = new int[str.length];
            dp[0] = str[0]!='0' ? 1: 0;
            for (int i = 1; i < dp.length; i++) {
                int p1 = str[i]!='0' ? dp[i-1]: 0;
                int p2 = 0;
                if (str[i-1] != '0' && (str[i-1]-'0')*10+(str[i]-'0') < 27 ){
                    p2 = i>=2 ? dp[i-2] : 1; //如果i>=2，说明前面还有数，可以继续迭代。否则，前面没数，说明这一次转化的尝试成功了
                }
                dp[i] = p1+p2;
            }

            return dp[s.length()-1];
        }
    }

    public static int number(String str) { // 左程云老师的方法
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    // str[0..i-1]转化无需过问
    // str[i.....]去转化，返回有多少种转化方法
    public static int process(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }
        // i没到最后，说明有字符
        if (str[i] == '0') { // 之前的决定有问题
            return 0;
        }
        // str[i] != '0'
        // 可能性一，i单转
        int ways = process(str, i + 1);
        if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
            ways += process(str, i + 2);
        }
        return ways;
    }


}
