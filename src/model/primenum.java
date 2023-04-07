package model;

import java.util.Arrays;

public class primenum {
    public static void main(String[] args) {
        System.out.println(cntprimecnt(5));
    }
    static int cntprimecnt(int n)
    {
        //判断是不是一个素数
        boolean isprime[] = new boolean[n+1];
        //第i个素数的值
        int prime[] = new int[n+1];
        Arrays.fill(isprime,true);
        isprime[1] = false;
        int cnt = 0;
//        对于 i%prime[j] == 0 就break的解释 ：当 i是prime[j]的倍数时，i = kprime[j]，
//        如果继续运算 j+1，i * prime[j+1] = prime[j] * k prime[j+1]，这里prime[j]是最小的素因子，
//        当i = k * prime[j+1]时会重复，所以才跳出循环。
//        举个例子 ：i = 8 ，j = 1，prime[j] = 2，如果不跳出循环，prime[j+1] = 3，8 * 3 = 2 * 4 * 3 = 2 * 12，
//        在i = 12时会计算。因为欧拉筛法的原理便是通过最小素因子来消除
        for(int i = 2;i<=n;i++)
        {
            if(isprime[i]) prime[++cnt] = i;
            for(int j = 1;j<=n&&i*prime[j]<=n;j++)
            {
                isprime[i*prime[j]] = false;
                if(i%prime[j]==0) break;
            }
        }
        return cnt;
    }
}
