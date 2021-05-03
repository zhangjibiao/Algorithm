import java.util.HashMap;
import java.util.HashSet;

public class Q17 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
    public static int lengthOfLongestSubstring(String str){
        // 入参规范性判断
        if (str == null ){
            return -1;
        }else {
            HashMap<String, Integer> dp = new HashMap<>();
            return rec(str, str.length()-1, dp);
        }
    }

    private static int rec(String str, int i, HashMap<String, Integer> dp){
        if (i < 0){
            return 0;
        }else if (i == 0){
            return 1;
        }else {
            if (dp.containsKey(str)){
                return dp.get(str);
            }

            int p1 = rec(str, i-1, dp);
            int p2 = 1;
            char[] strArr = str.toCharArray();
            HashSet<Character> set = new HashSet<>();
            set.add(strArr[i]);
            for (int j = i-1; j >= 0; j--) {
                if (set.contains(strArr[j])){
                    break;
                }else {
                    set.add(strArr[j]);
                }
                p2++;
            }

            int ans = Math.max(p1,p2);
            dp.put(str, ans);
            return ans;
        }
    }

    public static int dp(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        //map[i] = v 表示 i这个ASCII码上次出现在字符串的位置
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;//表示所有字符上一次出现的位置：-1
        }
        map[str[0]] = 0;
        int N = str.length;
        int ans = 1;
        int pre = 1; //上一个位置，向左推了多长
        for (int i = 1; i < N; i++) {
            pre = Math.min(i - map[str[i]], pre + 1);
            ans = Math.max(ans, pre);
            map[str[i]] = i;
        }
        return ans;
    }
}
