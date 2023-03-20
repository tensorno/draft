package model;

public class gcd {
    public static void main(String[] args) {
        gcd g = new gcd();
        System.out.println(g.commonmut(3,5));
    }
    int gcd(int a,int b)//最小公因数
    {
//        return b!=0?gcd(b,a%b):a;
        int c = 0;
        while(b!=0)
        {
            c = a%b;
            a = b;
            b = c;
        }
        return a;
    }

    int commonmut(int a,int b) //最小公倍数
    {
        int ans = a*b;
        return ans/gcd(a,b);
    }
}
