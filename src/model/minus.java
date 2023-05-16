package model;

public class minus {
    /*力扣放置邮票
    二维前缀和判断一块区域能否放置邮票
    如果对于这块区域能够放置邮票
    对这块区间修改（使用二维差分数组的方法）
    最后求前缀和查看是否为0，如果为0则不能放置邮票
     */
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length;
        int n = grid[0].length;
        int presum[][] = new int[m+1][n+1];
        for(int i = 0;i<m;i++)
        {
            for(int j = 0;j<n;j++)
            {
                presum[i+1][j+1] = presum[i][j+1] + grid[i][j];
            }
        }
        for(int i = 0;i<m;i++)
        {
            for(int j = 0;j<n;j++)
            {
                presum[i+1][j+1] += presum[i+1][j];
            }
        }
        int minus[][] = new int[m+2][n+2];
        for(int i = stampHeight;i<=m;i++)
        {
            for(int j = stampWidth;j<=n;j++)
            {
                if(grid[i-1][j-1]==1) continue;
                int temp = presum[i-stampHeight][j-stampWidth] + presum[i][j] - presum[i][j-stampWidth] - presum[i-stampHeight][j];  //can put the stamp
                if(temp==0)
                {
                    minus[i-stampHeight+1][j-stampWidth+1]++;
                    minus[i+1][j+1]++;
                    minus[i-stampHeight+1][j+1]--;
                    minus[i+1][j-stampWidth+1]--;
                }
            }
        }
        for(int i = 1;i<=m;i++)
        {
            for(int j = 1;j<=n;j++)
            {
                minus[i][j] += minus[i-1][j];
            }
        }
        for(int i = 1;i<=m;i++)
        {
            for(int j = 1;j<=n;j++)
            {
                minus[i][j] += minus[i][j-1];
                if(grid[i-1][j-1]==0&&minus[i][j]==0) return false;
            }
        }
        return true;
    }

    public int[][] rangeAddQueries(int n, int[][] queries) {
        //差分
        //miuns[i] 表示nums[i]-nums[i-1]




        //一维差分
        // int ans[][] = new int[n][n];
        // for(int i[]:queries)
        // {
        //     for(int j = i[0];j<=i[2];j++)
        //     {
        //         ans[j][i[1]]++;
        //         if(i[3]+1<n) ans[j][i[3]+1]--;
        //     }
        // }
        // for(int i[]:ans)
        // {
        //     for(int j = 1;j<n;j++)
        //     {
        //         i[j] += i[j-1];
        //     }
        // }
        // return ans;
        //二维差分

        int pre[][] = new int[n+1][n+1];
        for(var i:queries)
        {
            pre[i[0]][i[1]]++;
            pre[i[0]][i[3]+1]--;
            pre[i[2]+1][i[1]]--;
            pre[i[2]+1][i[3]+1]++;
        }
        for(int i = 0; i < n; ++i)
            for(int j = 1; j < n; ++j) pre[i][j] += pre[i][j-1];
        for(int i = 1; i < n; ++i)
            for(int j = 0; j < n; ++j) pre[i][j] += pre[i-1][j];
        int ans[][] = new int[n][n];
        for(int i = 0; i < n; ++i)
            for(int j = 0; j < n; ++j) ans[i][j] = pre[i][j];
        return ans;
    }
}
