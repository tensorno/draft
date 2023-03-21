package model;

public class kuohao_dp {
    static int mod = (int)1e9+7;
    //括号序列
    //1.添加的左右括号互不影响，可以单独求添加左括号，右括号最后相乘。
    //以下针对添加左括号的情况
    //2.dp[i][j]表示的是前i个字符串中，添加左括号后得到的结果字符串左括号比右括号多j个
    //3.遇到左括号并不需要添加左括号直接有之前的状态得到，即dp[i][j] = dp[i-1][j-1]
    //4.遇到右括号，这时候需要添加左括号，要使得左括号的数量比右括号多j个，可能会从多0，1，2，...，j+1的情况转变而来
    //dp[i][j] = dp[i-1][0] + dp[i-1][1] +... +dp[i-1][j+1]
    //对于j == 0的特殊情况 dp[i][0] = dp[i-1][0] + dp[i-1][1]
    static int solvekuohao(String s) {
        int len = s.length();
        int dp[][] = new int[5010][5010];
        dp[0][0] = 1;
        for(int i = 1;i<=len;i++)
        {
            if(s.charAt(i-1)=='(')
            {
                for(int j = 1;j<=len;j++)
                {
                    dp[i][j] = dp[i-1][j-1]%mod;
                }
            }
            else
            {
                dp[i][0] = (dp[i-1][0]+dp[i-1][1])%mod;
                for(int j = 1;j<=len;j++)
                {
                    dp[i][j] = (dp[i-1][j+1]+dp[i][j-1])%mod;
                }
            }
        }
        for(int i:dp[len])
        {
            if(i!=0) return i;
        }
        return -1;
    }
}
