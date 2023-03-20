package caogao;

import javax.print.DocFlavor;
import java.util.Arrays;
import java.util.Scanner;

class Vscode {
//    public static void main(String[] args) {
//        Scanner sca = new Scanner(System.in);
//        String s = sca.nextLine();
//        System.out.println(two(s));
//    }

    public static int one(String s, String t)
    {
        int ans = 0;
        String temp[] = s.split(" ");
        for (String value : temp) {
            int len = value.length();
            int tlen = t.length();
            int dp[] = new int[len + 1];
            Arrays.fill(dp, -1);
            for (int j = 0; j < len; j++) {
                for (int k = 0; k < tlen; k++) {
                    if (value.charAt(j)==t.charAt(k)) {
                        dp[j] = k;
                        break;
                    }
                }
            }
            for (int j = 0; j < len; j++) {
                if (dp[j] != -1) {
                    int start = dp[j];
                    while (dp[j + 1] != -1 && dp[j + 1] - dp[j] == 1) j++;
                    int end = dp[j];
                    ans += tlen - (end - start + 1)+1;
                    if (start > 0)
                        ans += 2 * (end - start + 1);
                }
            }
        }
        return ans;
    }
    public static int two(String s)
    {
        int len = s.length();
        int s0n0 = 0;
        int s0n1 = 0;
        int s1n0 = 0;
        int s1n1 = 0;
        for(int i = 0;i<len;i++)
        {
            if (i%2==0)
            {
                if (s.charAt(i)!='B') s0n0++;
                else s1n1++;
            }
            else
            {
                if (s.charAt(i)!='G') s0n1++;
                else s1n0++;
            }
        }
        if (s0n0 == s0n1 && s1n0 != s1n1) return s0n0;
        if (s0n0 != s0n1 && s1n0 == s1n1 ) return s1n0;
        return Math.min(s0n0,s1n0);
    }
}
