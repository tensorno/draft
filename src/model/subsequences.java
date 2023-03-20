package model;

public class subsequences {

    public int numDistinct(String s, String t) {
        //dp[i][j]：从开头到s[i-1]的子串中，出现『从开头到t[j-1]的子串』的 次数。
        // 即：前 i 个字符的 s 子串中，出现前 j 个字符的 t 子串的次数。
        //到 i位和j位字符相等的时候，有两种情况，选用i位和不选用i位上
        //不等的时候，则是 dp[i-1][j]
        //需要考虑初始化当j为0，表示空串，初始为1
        int len1 = s.length();
        int len2 = t.length();
        if(len1<len2) return 0;
        int dp[][] = new int[len1+1][len2+1];
        for(int i = 0;i<len1;i++)
        {
            dp[i][0] = 1;
        }
        for(int i = 0;i<len1;i++)
        {
            for(int j = 0;j<=i&&j<len2;j++)
            {
                if(s.charAt(i)==t.charAt(j))
                {
                    dp[i+1][j+1] = dp[i][j] + dp[i][j+1];
                }
                else
                {
                    dp[i+1][j+1] = dp[i][j+1];
                }
            }
        }
        return dp[len1][len2];
    }

    public int distinctSubseqII(String s) {
        //dp[i] = dp[i - 1] + newCount - repeatCount
        //newCount为加上s[i]后新增的子序列个数，repeatCount为重复的子序列个数
        //repeatCount就是上次重复字符的情况数量，使用一个数组记录上次字符的数量
        int ans = 1;
        int mod = (int) 1e9+7;
        int len = s.length();
        int pre[] = new int[26];
        for(int i = 0;i<len;i++)
        {
            int cur = ans;
            ans=((ans+cur)%mod-pre[s.charAt(i)-'a']+mod)%mod;
            pre[s.charAt(i)-'a'] = cur;
        }
        return (ans-1+mod)%mod;
    }
}
