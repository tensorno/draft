package model;
import java.util.*;
public class dynamicpro {


    public int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        int dp[][] = new int[m+1][m+1];
        // for(int i = 1;i<=m;i++) Arrays.fill(dp[i]);
        //dp[i][j] 表示选择前i个和后j个
        for(int i = 1;i<=m;i++)
        {
            for(int j = 0;j<=i;j++)
            {
                if(j==0) dp[j][i-j] = dp[j][i-j-1] + multipliers[i-1] * nums[n-(i-j)];
                else if(j==i) dp[j][i-j] = dp[j-1][0] + multipliers[i-1] * nums[j-1];
                else dp[j][i-j] = Math.max(dp[j-1][i-j]+multipliers[i-1]*nums[j-1],dp[j][i-j-1]+multipliers[i-1]*nums[n-(i-j)]);
            }
        }
        int ans = Integer.MIN_VALUE;
        for(int i = 0;i<=m;i++)
        {
            ans = Math.max(ans,dp[i][m-i]);
        }
        return ans;
    }


    //f[i][j] 为考虑字符串 s 中的[i,j] 范围内回文子序列的个数
    // 不失一般性考虑 f[i][j]f[i][j] 该如何转移，通过枚举 abcd 作为回文方案「边缘字符」来进行统计
    // 即分别统计各类字符作为「边缘字符」时对 f[i][j]f[i][j] 的贡献，此类统计方式天生不存在重复性问题。
    int mod = (int) 1e9+7;
    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        int dp[][] = new int[n][n];
        char temp[] = s.toCharArray();
        int left[] = new int[4];
        int right[] = new int[4];
        Arrays.fill(left,-1);
        for(int i = n-1;i>=0;i--)
        {
            left[temp[i]-'a'] = i;
            Arrays.fill(right,-1);
            for(int j = i;j<n;j++)
            {
                right[temp[j]-'a'] = j;
                for(int k = 0;k<4;k++)
                {
                    if(left[k]==-1||right[k]==-1) continue;
                    if(right[k]==left[k]) dp[i][j] = (dp[i][j]+1)%mod;
                    else if(right[k]==left[k]+1) dp[i][j] = (dp[i][j]+2)%mod;
                    else
                    {
                        dp[i][j] = (dp[i][j]+dp[left[k]+1][right[k]-1]+2)%mod;
                    }
                }
            }
        }
        return dp[0][n-1];
    }

    public int maxProfit(int k, int[] prices) {
        if(prices.length <= 1 || k == 0) return 0;
        int days = prices.length;
        int[][][] dp = new int[days][2][k+1];
        //base case
        for(int i=1;i<=k;i++) {
            //dp[0][0][i] = 0;
            dp[0][1][i] = -prices[0];
        }
        for(int i=1;i<days;i++) {
            for(int j=1;j<=k;j++) {
                dp[i][0][j] = Math.max(dp[i-1][0][j],dp[i-1][1][j]+prices[i]);
                dp[i][1][j] = Math.max(dp[i-1][1][j],dp[i-1][0][j-1]-prices[i]);
            }
        }
        int ans = 0;
        for(int i = 1;i<=k;i++)
        {
            ans = Math.max(ans,dp[days-1][0][i]);
        }
        return dp[days-1][0][k];
    }

    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        int m = factory.length;
        int n = robot.size();
        Collections.sort(robot);
        Arrays.sort(factory,(a,b)->a[0]-b[0]);
        long dp[][] = new long[m+1][n+1];  //dp[i][j] 前i个工厂修理j个机器人
        for(int i =0;i<m;i++)
        {
            Arrays.fill(dp[i],(long)1e18);
        }
        for(int i = 0;i<m;i++) dp[i][0] = 0;
        for(int i = 1;i<=m;i++)
        {
            for(int j = 1;j<=n;j++)
            {
                dp[i][j] = dp[i-1][j];
                int up =Math.min(factory[i-1][1],j);
                long distance = 0;
                for(int k = 1;k<=up;k++)
                {
                    distance+=Math.abs(robot.get(j-k)-factory[i-1][0]);
                    dp[i][j] = Math.min(dp[i][j],dp[i-1][j-k]+distance);
                }
            }
        }
        return dp[m][n];
    }

    public int minDistance(int[] houses, int k) {
        Arrays.sort(houses);
        int n = houses.length;
        int dp[][] = new int[k+1][n+1]; //dp[i][j]表示前i个信箱 使用第j个房屋
        for(int i = 0;i<=k;i++)
        {
            Arrays.fill(dp[i],(int) 1e9);
        }
        for(int i = 0;i<=k;i++) dp[i][0] = 0;
        int distance [][] = new int[n][n];  //distance[i][j] 表示第i个房子到第j个房子放置一个邮筒的距离，距离最小为中位数。
        for(int i = 1;i<n;i++)
        {
            for(int j = i-1;j>=0;j--)
            {
                distance[j][i] = houses[i]-houses[j]+distance[j+1][i-1];
            }
        }
        for(int i = 1;i<=k;i++)
        {
            for(int j=1;j<=n;j++)
            {
                dp[i][j] = dp[i-1][j];
                for(int temp = 1;temp<=j;temp++)
                {
                    dp[i][j] = Math.min(dp[i-1][j-temp]+distance[j-temp][j-1],dp[i][j]);
                }
            }
        }
        return dp[k][n];
    }
}
