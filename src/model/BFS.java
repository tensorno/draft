package model;
import java.util.*;
public class BFS {
    public int maximumMinutes(int[][] grid) {
        int m = grid.length;  //两次bfs，第一次寻找火到的时间，check是寻找人到达是火是否能能烧，即人是否能到达终点
        int n = grid[0].length;
        int dp[][] = new int[m][n];
        for(int i = 0;i<m;i++)
        {
            Arrays.fill(dp[i],1000000000);
        }
        Queue<int []> queue = new LinkedList();
        for(int i =0;i<m;i++)
        {
            for(int j = 0;j<n;j++)
            {
                if (grid[i][j]==1)
                {
                    queue.offer(new int[]{i,j});
                    dp[i][j] = 0;
                }
                else if(grid[i][j]==2)
                {
                    dp[i][j] = -1;
                }
            }
        }
        while(!queue.isEmpty())
        {
            int size = queue.size();
            while(size-->0)
            {
                int temp[] = queue.poll();
                for(int i = 0;i<4;i++)
                {
                    int x = temp[0]+dx[i];
                    int y = temp[1]+dy[i];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0 &&dp[x][y] == 1000000000)
                    {
                        dp[x][y] = dp[temp[0]][temp[1]] + 1;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
        }
        int left = -1;
        int right = m*n;
        while(left<right)
        {
            int middle=(left+right+1)/2;
            if (check(middle,dp,grid)) left = middle;
            else right = middle-1;
        }
        return left==m*n?1000000000:left;
    }

    static int dx[] = {1,-1,0,0};
    static int dy[] = {0,0,1,-1};
    boolean check(int time,int dp[][],int grid[][])
    {
        int m = dp.length;
        int n = dp[0].length;
        int d[][] = new int[m][n];
        for(int i = 0;i<m;i++)
        {
            Arrays.fill(d[i],-1);
        }
        d[0][0] = time;
        Queue<int[]> queue = new LinkedList();
        queue.offer(new int[]{0,0});
        while(!queue.isEmpty())
        {
            int size = queue.size();
            while(size-->0)
            {
                int temp[] = queue.poll();
                for(int i=0;i<4;i++)
                {
                    int x = temp[0]+dx[i];
                    int y = temp[1]+dy[i];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0 &&d[x][y] == -1)
                    {
                        d[x][y] = d[temp[0]][temp[1]] + 1;
                        if (x == m - 1 && y == n - 1)
                        {
                            return d[x][y] <= dp[x][y];
                        }
                        if (d[x][y] < dp[x][y])
                        {
                            queue.offer(new int[]{x, y});
                        }
                    }
                }
            }
        }
        return false;
    }
}
