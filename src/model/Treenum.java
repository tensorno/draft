package model;


//树状数组更新时候只更新包含这一元素的区间；求前n项和时，通过将区间进行组合，得到从1到n的区间，然后对所有用到的区间求和。
public class Treenum {
    int max = 100000;
    //tree[i]里面存储(i-lowerbit(i),i]的值
    //
    int tree[] = new int[max];
    int lowbit(int x)
    {
        return (x&-x);
    }

    //那么如何更新呢，大家会发现更新就是一个“爬树”的过程
    //每一步都把从右边起一系列连续的1变为0，再把这一系列1的前一位0变为1。这看起来像是一个进位的过程对吧？
    // 实际上，每一次加的正是lowerbit(i)
    void update(int i,int x)
    {
        for (int pos = i; pos < max; pos += lowbit(pos))
            tree[pos] += x;
    }

    int query(int n)
    {
        int ans = 0;
        for (int pos = n; pos!=0; pos -= lowbit(pos))
            ans += tree[pos];
        return ans;
    }
}
