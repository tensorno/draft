package model;
import java.util.*;
public class dpinnum {
    int a[] = new int[10];
    public int rotatedDigits(int n) {
        int change[] = new int[]{0,1,5,-1,-1,2,9,-1,8,6};
        int len = 0;
        int temp = n;
        while(temp>0)
        {
            a[len++] = temp%10;
            temp=temp/10;
        }
        int dp[][] = new int[len][2];
        for(int i = 0;i<len;i++)
        {
            Arrays.fill(dp[i], -1);
        }
        return dfs(dp,0,len-1,true);
    }

    int dfs(int dp[][],int prestatus,int index,boolean bound)
    {
        if(index==-1&&prestatus==1) return 1;
        else if(index==-1&&prestatus!=1) return 0;
        int ans = 0;
        if(!bound&&dp[index][prestatus]!=-1) return dp[index][prestatus];
        int up;
        if(bound) up = a[index];
        else up = 9;
        for(int i = 0;i<=up;i++)
        {
            if(i!=3&&i!=4&&i!=7&&prestatus==1)
            {
                ans += dfs(dp,1,index-1,bound&&i==a[index]);
            }
            else if(i!=3&&i!=4&&i!=7&&prestatus!=1)
            {
                if(i!=0&&i!=1&&i!=8)
                {
                    ans+= dfs(dp,1,index-1,bound&&i==a[index]);
                }
                else
                {
                    ans+= dfs(dp,0,index-1,bound&&i==a[index]);
                }
            }
        }
        if(!bound) dp[index][prestatus] = ans;
        return ans;
    }

    public int countSpecialNumbers(int n) {
        int len = 0;
        int temp = n;
        while(temp>0)
        {
            a[len++] = temp%10;
            temp=temp/10;
        }
        int dp[][] = new int[len][1<<10];
        for(int i = 0;i<len;i++)
        {
            Arrays.fill(dp[i],-1);
        }
        return dfs(dp,0,true,true,len-1)-1;//0不能算进去
    }

    int dfs(int dp[][],int status,boolean lead,boolean bound,int index)
    {
        if(index==-1) return 1;
        int ans = 0;
        if(!bound&&dp[index][status]!=-1&&!lead) return dp[index][status];
        int up;
        if(bound) up=a[index];
        else up = 9;
        for(int i = 0;i<=up;i++)
        {
            if(lead&&i==0) ans+=dfs(dp,0,true,bound&&a[index]==i,index-1);
            else if(lead&&i!=0) ans+=dfs(dp,status|1<<i,false,bound&&a[index]==i,index-1);
            else if((status&1<<i)==0) ans+=dfs(dp,status|1<<i,false,bound&&a[index]==i,index-1);
        }
        if(!bound&&!lead) dp[index][status] = ans;
        return ans;
    }

    int dig[];
//    int a[] = new int[10];
    //本题没有特殊的状态需要考虑，所以去掉dp数组的一个维度
    public int atMostNGivenDigitSet(String[] digits, int n) {
        dig = new int[digits.length];
        for(int i = 0;i<digits.length;i++)
        {
            dig[i] = Integer.parseInt(digits[i]);
        }
        int len = 0;
        int temp = n;
        while(temp>0)
        {
            a[len++] = temp%10;
            temp=temp/10;
        }
        int dp[] = new int[len];
        Arrays.fill(dp,-1);
        return dfs(dp,true,true,len-1)-1;//0不能算进去
    }

    int dfs(int dp[],boolean lead,boolean bound,int index)
    {
        if(index==-1) return 1;
        int ans = 0;
        if(!bound&&dp[index]!=-1&&!lead) return dp[index];
        if(lead) ans = dfs(dp,true,bound&&a[index]==0,index-1);
        int up;
        if(bound) up=a[index];
        else up = 9;
        for(int i = 0;i<dig.length;i++)
        {
            if(dig[i]>up) break;
            ans+=dfs(dp,false,bound&&a[index]==dig[i],index-1);
        }
        if(!bound&&!lead) dp[index] = ans;
        return ans;
    }

    int mod = (int) 1e9+7;
    int n;
    int m;
    int next[];
    String s1;
    String s2;
    String evil;
    public int findGoodStrings(int n, String s1, String s2, String evil) {
        this.n = n;
        this.s1 = s1;
        this.s2 = s2;
        this.evil = evil;
        m = evil.length();
        int dp[][] = new int[n][m];
        for(int i []:dp) Arrays.fill(i,-1);
        next = new int[m];
        int j = 0;
        for(int i = 1;i<m;i++)
        {
            while(j>0&&evil.charAt(j)!=evil.charAt(i))
            {
                j = next[j-1];
            }
            if(evil.charAt(j)==evil.charAt(i)) j++;
            next[i] = j;
        }
        return dfs1(dp,0,true,true,0);
    }

    int dfs1(int dp[][],int index,boolean down,boolean up,int pri)
    {
        if(pri==m) return 0;
        if(index == n) return 1;
        if(!down&&!up&&dp[index][pri]!=-1) return dp[index][pri];
        int ans = 0;
        char a = 'a';
        char b = 'z';
        if(down) a = s1.charAt(index);
        if(up) b = s2.charAt(index);
        for(char i = a;i<=b;i++)
        {
            int temp = pri;
            while(temp>0&&evil.charAt(temp)!=i)
            {
                temp = next[temp-1];
            }
            if(evil.charAt(temp)==i) temp++;
            ans = (ans+dfs(dp,index+1,down&&i==s1.charAt(index),up&&i==s2.charAt(index),temp))%mod;
        }
        if(!down&&!up) dp[index][pri] = ans;
        return ans;
    }
}
