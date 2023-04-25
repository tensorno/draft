package model;

import java.util.*;

public class scannerLine {
    public int fieldOfGreatestBlessing(int[][] forceField) {
        int n = forceField.length;
        Set<Long> set = new HashSet<>();
        List<long[]> sweep = new ArrayList<>();
        for(int i = 0;i<n;i++)
        {
            sweep.add(new long[]{(long)forceField[i][0]*2-forceField[i][2],i,1});
            sweep.add(new long[]{(long)forceField[i][0]*2+forceField[i][2],i,-1});
            set.add((long)forceField[i][1]*2+forceField[i][2]);
            set.add((long)forceField[i][1]*2-forceField[i][2]);
        }
        List<Long> lst = new ArrayList<>(set);
        Collections.sort(sweep,(a,b)->
        {
            if(Long.compare(a[0],b[0])!=0) return Long.compare(a[0],b[0]);
            return Long.compare(b[2],a[2]);
        });
        Collections.sort(lst);
        int ans = 0;
        int m = lst.size();
        int cnt[] = new int[m];
        for(int i = 0;i<2*n;)
        {
            int j = i;
            while(j<2*n&&sweep.get(j)[0]==sweep.get(i)[0]) j++;
            for(int k = i;k<j;k++)
            {
                long temp[] = sweep.get(k);
                int index = (int)temp[1];
                long low = (long)forceField[index][1]*2-forceField[index][2];
                long high = (long)forceField[index][1]*2+forceField[index][2];
                for(int height = 0;height<m;height++)
                {
                    if(low<=lst.get(height)&&lst.get(height)<=high)
                    {
                        cnt[height]+=temp[2];
                    }
                    ans = Math.max(ans,cnt[height]);
                }
            }
            i = j;
        }
        return ans;
    }


//    想象一条竖直的直线从平面的最左端扫到最右端，在扫描的过程中，直线上的一些线段会被给定的矩形覆盖。
//    将这些覆盖的线段长度进行积分，就可以得到矩形的面积之和。
//    每个矩形有一个左边界和一个右边界
//    在扫描到矩形的左边界时，覆盖的长度可能会增加；在扫描到矩形的右边界时，覆盖的长度可能会减少。
//    如果给定了 n 个矩形，那么覆盖的线段长度最多变化 2n 次，此时我们就可以将两次变化之间的部分合并起来，
//    一起计算：即这一部分矩形的面积，等于覆盖的线段长度，乘以扫描线在水平方向移动过的距离。
//

    public int rectangleArea(int[][] rectangles) {
        HashSet<Integer> set = new HashSet<>();
        final int MOD = 1000000007;
        int m = rectangles.length;
        for(int i = 0;i<m;i++)
        {
            set.add(rectangles[i][1]);
            set.add(rectangles[i][3]);
        }
        List<Integer> lst = new ArrayList<>(set);
        Collections.sort(lst);
        int n = lst.size();
        int seg[] = new int[n-1];
        List<int[]> sweep = new ArrayList<>();
        for(int i = 0;i<m;i++)
        {
            sweep.add(new int[]{rectangles[i][0],i,1});
            sweep.add(new int[]{rectangles[i][2],i,-1});
        }
        Collections.sort(sweep,(a,b)->a[0]-b[0]);
        long ans = 0;
        for(int i = 0;i<2*m;i++)
        {
            int j = i;
            while(j<2*m-1&&sweep.get(j)[0]==sweep.get(j+1)[0]) j++;
            if(j==2*m-1) break;
            for(int k = i;k<=j;k++)
            {
                int temp[] = sweep.get(k);
                int index = temp[1];
                int low = rectangles[index][1];
                int high = rectangles[index][3];
                for(int change = 0;change<n-1;change++)
                {
                    if(low<=lst.get(change)&&lst.get(change+1)<=high)
                    {
                        seg[change]+=temp[2];
                    }
                }
            }
            int height = 0;
            for(int k=0;k<n-1;k++)
            {
                if(seg[k]>0) height+=lst.get(k+1)-lst.get(k);
            }
            ans += (long)height*(sweep.get(j+1)[0]-sweep.get(j)[0]);
            i = j;
        }
        return (int) (ans % MOD);
    }
}
