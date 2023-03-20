package model;
import java.util.*;
public class String_res {

    long base = 59;
    long mod = (int)1e9+7;
    public int distinctEchoSubstrings(String text) {
        //字符哈希
        int len = text.length();
        long p[] = new long[len+1];
        long pre[] = new long[len+1];
        p[0] = 1;
        pre[0] = 0;
        for(int i = 1;i<=len;i++)
        {
            p[i] = p[i-1]*base%mod;
            pre[i] = (pre[i-1]*base%mod+text.charAt(i-1)-'a')%mod;
        }
        HashSet<String> set  =new HashSet<>();
        for(int i = 1;i<=len;i++)
        {
            for(int j = i+1;2*j-i-1<=len;j++)
            {
                int l = j-i;
                if(get(pre,p,i,j-1)==get(pre,p,j,j+l-1)) {
                    set.add(get(pre,p,i,j-1)+"");
                }
            }
        }
        return set.size();
    }

    long get(long pre[],long p[],int left,int right)
    {
        return (pre[right]-p[right-left+1]*pre[left-1]%mod+mod)%mod;
    }


    public static int kmp(String str, String dest){
        //1.首先计算出 部分匹配表
        int[] next = kmpnext(dest);

        System.out.println("next ="+Arrays.toString(next));
        //2.查找匹配位置
        for(int i = 0, j = 0; i < str.length(); i++){
            while(j > 0 && str.charAt(i) != dest.charAt(j)){
                j = next[j-1];
            }
            if(str.charAt(i) == dest.charAt(j)){
                j++;
            }
            if(j == dest.length()){
                return i-j+1;
            }
        }
        return -1;
    }

    /**
     * 计算部分匹配表
     */
    public static int[] kmpnext(String dest){
        int[] next = new int[dest.length()];
        next[0] = 0;

        for(int i = 1,j = 0; i < dest.length(); i++){
            while(j > 0 && dest.charAt(j) != dest.charAt(i)){
                j = next[j - 1];
            }
            if(dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
