import java.util.Comparator;
import java.util.HashSet;

public class Q19 {
    public static int kindsOfStrings(String[] str){
        //入参检验
        if (str == null || str.length == 0){
            return 0;
        }else {
            return rec(str);
        }
    }

    private static int rec(String[] s){
        //将字符串数组转换成Boolean二维数组
        int N = s.length;

        //放到HashSet里
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            char[] strArr = s[i].toCharArray();
            boolean[] map = new boolean[26];
            for (int j = 0; j < strArr.length; j++) {
                    map[strArr[j] - 'a'] = true;
            }

            String key = "";
            for (int j = 0; j < 26; j++) {
                if (map[j]){
                    key += String.valueOf((char) (j + 'a'));
                }
            }

            set.add(key);

        }

        return set.size();
    }

    public static int types1(String[] arr) {
        HashSet<String> types = new HashSet<>();
        for (String str : arr) {
            char[] chs = str.toCharArray();
            boolean[] map = new boolean[26];
            for (int i = 0; i < chs.length; i++) {
                map[chs[i] - 'a'] = true;
            }
            String key = "";
            for (int i = 0; i < 26; i++) {
                if (map[i]) {
                    key += String.valueOf((char) (i + 'a'));
                }
            }
            types.add(key);
        }
        return types.size();
    }

    public static int kindsOfStrings1(String[] arr){
        //入参检验
        if (arr == null || arr.length == 0){
            return 0;
        }

        HashSet<Integer> set = new HashSet<>();
        for(String str : arr){
            int key = 0;
            for (int i = 0; i < str.length(); i++) {
                key |= (1 << (str.charAt(i)) - 'a');
            }
            set.add(key);
        }
        return set.size();
    }


    // for test
    public static String[] getRandomStringArray(int possibilities, int strMaxSize, int arrMaxSize) {
        String[] ans = new String[(int) (Math.random() * arrMaxSize) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = getRandomString(possibilities, strMaxSize);
        }
        return ans;
    }

    // for test
    public static String getRandomString(int possibilities, int strMaxSize) {
        char[] ans = new char[(int) (Math.random() * strMaxSize) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strMaxSize = 10;
        int arrMaxSize = 100;
        int testTimes = 500000;
        System.out.println("test begin, test time : " + testTimes);
        for (int i = 0; i < testTimes; i++) {
            String[] arr = getRandomStringArray(possibilities, strMaxSize, arrMaxSize);
            int ans1 = kindsOfStrings(arr);
            int ans2 = kindsOfStrings1(arr);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");

    }
}
