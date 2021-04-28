public class Q14 {
    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        int[] weights1 = { 10, 2, 4, 2, 3, 1, 7 };
        int[] values1 = { 5, 6, 3, 19, 12, 4, 2 };
        int bag1 = 15;
        System.out.println(maxValue(weights, values, bag));//42
        System.out.println(maxValue(weights1, values1, bag1));//44
        System.out.println(dp(weights, values, bag));
    }

    public static int maxValue(int[] weights, int[] values, int bag){
        if(weights == null || weights.length != values.length || bag <= 0){
            return 0;
        }
        return rec(weights, values, bag, weights.length-1);
    }

    private static int rec(int[] w, int[] v, int bag, int index){
        if(bag < 0){
            return 0;
        }else if (index < 0){
            return 0;
        }else {
            int p1 = rec(w, v, bag-w[index], index-1);
            if (bag >= w[index]){//判断这个物品能不能背得动
                p1 += v[index];
            }
            int p2 = rec(w, v, bag, index-1);
            return Math.max(p1, p2);
        }
    }

    //动态规划做法
    //变化的只有bag和index
    public static int dp(int[] w, int[] v, int bag){
        if(w == null || w.length != v.length || bag <= 0){
            return 0;
        }

        int[][] dp = new int[bag+1][w.length];//因为bag的变化范围是0~bag， index的变化范围是0~w.length-1
        //从rec函数可以看出，dp中一个元素dp[i][j]的依赖是 dp[i-w[j]][j], dp[i][j-1],所以需要填写方向是↑ →
        for (int i = 0; i < bag+1; i++) {
            for (int j = 0; j < w.length; j++) {
                int p1 = (i-w[j] < 0 || j-1<0) ? 0 : dp[i-w[j]][j-1];
                if (i >= w[j]){//判断这个物品能不能背得动
                    p1 += v[j];
                }
                int p2 = (i-w[j] < 0 || j-1<0) ? 0 :dp[i][j-1];
                dp[i][j] = Math.max(p1, p2);
            }
        }

        return dp[bag][w.length-1];
    }
}
