package model;

public class Quickpow {
    public static void main(String[] args) {
        System.out.println(qpow(5,2));
    }
    static int mod = (int) 1e9+7;
    static long qpow(long base, long index){ //快速求的base^index
        long ans = 1;
        while(index !=0){
            if((index &1)==1)        //如果index的当前末位为1
                ans = (ans*base)%mod;  //ans乘上当前的base
            base = (base*base)%mod;        //base自乘
            index >>= 1;       //n往右移一位
        }
        return ans;
    }

    //https://blog.csdn.net/boliu147258/article/details/105758354
    //组合数公式位C(m,n) = n!/((n-m)!*m!)
    //由于除数无法使用求余，使用逆元进行求解
    //公式转化为 n!*inv[(n-m)!]*inv[m!]
    //inv[m!]*m! = mod
    //根据费马定理推出逆元为 inv[x] = pow(x,mod-2) 其中mod一般会使用1e9+7
    //n!*inv[n!] = 1;
    //(n-1)!*inv[(n-1)!] = 1;
    //inv[(n-1)!] = n*inv[n!]
    static int n = 10005;
    static long[] fac = new long[n];//存放阶乘的值
    static long inv[] = new long[n];//存放逆元的值
    static void intial()
    {
        fac[0] = 1;
        for (int i = 1; i < n; i++) {
            fac[i] = (fac[i-1]*i)%mod;
        }
        inv[n-1] = qpow(fac[n-1],mod-2);
        for (int i = n-2; i >=0 ; i--) {
            inv[i] = (inv[i+1]*(i+1))%mod;
        }
    }

    static long C(int m,int n)
    {
        if(m>n) return 0;
        if(m==0) return 1;
        return fac[n]*inv[m]%mod*inv[n-m]%mod;
    }
}
