import jdk.nashorn.internal.runtime.OptimisticReturnFilters;

import java.util.HashMap;

public class Q16 {
    public static void main(String[] args) {
        String s = "aabbcccdlf";
        String s1 = "ccddeellpppzzz";
        System.out.println(minStickers(new String[]{s, s1}, "aabbaacfeezz"));
    }

    public static int minStickers(String[] stickers, String target){
        //因为贴纸中字母的顺序无意义，生成贴纸字符统计
        int N = stickers.length;
        int[][] sti = new int[N][26]; // 26：因为小写字母总个数是26
        for (int i = 0; i < N; i++) {
            char[] s = stickers[i].toCharArray();
            for (int j = 0; j < s.length; j++) {
                sti[i][s[j]-'a'] += 1;
            }
        }

        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int ans = rec(target, sti, dp);
        return ans==Integer.MAX_VALUE ? -1 : ans;
    }

    private static int rec(String  t, int[][] s, HashMap<String, Integer> dp){
        if (dp.containsKey(t)){//傻缓存法
            return dp.get(t);
        }

        char[] target = t.toCharArray();
        int[] tCount = new int[26];//tCount 目标字符串词频统计
        for (char cha : target) {
            tCount[cha - 'a']++;
        }

        int N =s.length;
        int leastNum = Integer.MAX_VALUE;  // 记录需要的最少贴纸数，Integer.MAX_VALUE表示无穷张，处理不完
        for (int i = 0; i < N; i++) { //用每一张贴纸尝试处理目标字符串，形成许多分支
            int[] sCount =s[i];
            if (sCount[target[0] - 'a'] > 0){ //如果这个贴纸包含目标字符串的首字符串，那么一定可以处理掉一点
                String rest = cut(tCount, sCount);
                int stillNeed = rec(rest, s, dp);
                //如果处理得完且这个分支需要的张数比记录的少，更新记录
                if (stillNeed != Integer.MAX_VALUE && 1 + stillNeed < leastNum){
                    leastNum = 1 + stillNeed;
                }
            }
        }
        dp.put(t, leastNum);
        return leastNum; //假如处理得完，返回张数；处理不完，返回-1
//        return leastNum==Integer.MAX_VALUE ? -1 : leastNum; //假如处理得完，返回张数；处理不完，返回-1
    }

    // 用一张贴纸，处理目标字符串
    // tCount 目标字符串的词频统计
    // sCount 贴纸字符串的词频统计
    // 返回被处理后的字符
    private static String cut(int[] tCount, int[] sCount){
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < 26; j++) {
            if (tCount[j] > 0) {
                int nums = tCount[j] - sCount[j];
                for (int k = 0; k < nums; k++) {
                    builder.append((char) (j + 'a'));
                }
            }
        }
        return builder.toString();
    }

}
