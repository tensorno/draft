package model;

import java.util.Scanner;

public class JointUnion {

    int parent[];
    int m;
    int n;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        if(m<n)
        {
            int temp = n;
            n = m;
            m = temp;
        }
        JointUnion ans = new JointUnion(m,n);
        for(int i= 1;i<=m;i++)
        {
            if(i<=n)
            {
                ans.merge(ans.get(1,i), ans.get(i,1));
                ans.merge(ans.get(i,m), ans.get(1,m-i+1));
            }
            else if(i>n)
            {
                ans.merge(ans.get(i,1),ans.get(i-m+1,m));
                ans.merge(ans.get(i,m),ans.get(i-m+1,1));
            }
            if(i<=m-n+1)
            {
                ans.merge(ans.get(i,1),ans.get(m+i-1,m));
                ans.merge(ans.get(i,m),ans.get(m+i-1,1));
            }
            else
            {
                ans.merge(ans.get(i,1),ans.get(n,n-i+1));
                ans.merge(ans.get(i,m),ans.get(n,m-n+i));
            }
        }
//        for(int i = 1;i<=m;i++)
//        {
//            for(int j = 1;j<=n;j++)
//            {
//                System.out.print(ans.get(i,j)+" ");
//            }
//            System.out.println();
//        }
        int cnt=0;
        for(int i = 1;i<=2*(m+n)-4;i++)
        {
            if(i==ans.parent[i]) cnt++;
        }
        System.out.println(cnt);
    }

    JointUnion(int m,int n)
    {
        this.m = m;
        this.n = n;
        parent = new int[2*(m+n)];
        for(int i= 1;i<=2*(m+n)-4;i++) parent[i] = i;
    }

    void merge(int x, int y)
    {
        x=find(x);
        y=find(y);
        if(x==y)return ;
        parent[x]=y;
    }

    int find(int x)
    {
        if(parent[x]!=x)
        {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    int get(int x,int y)
    {
        if(y==1) return x;
        else if(x==m) return m+y-1;
        else if(y==n) return m+n-1+m-x;
        else if(x==1) return m+n+n+-2+m-y;
        return 0;
    }


    //联通矩阵中的量+离线查询+并查集
//    int dir[][] = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
//    public int[] maxPoints(int[][] grid, int[] queries) {
//        int n = grid.length;
//        int m = queries.length;
//        Integer index[] = new Integer[m];
//        for(int i = 0;i<m;i++) index[i] = i;
//        Arrays.sort(index,(a,b)->queries[a]-queries[b]);
//        int ans[] = new int[m];
//        int matrix[][] = new int[n*grid[0].length][3];
//        int size[] = new int[n*grid[0].length];
//        Arrays.fill(size,1);
//        for(int i = 0;i<n;i++)
//        {
//            for(int j = 0;j<grid[0].length;j++)
//            {
//                matrix[i*grid[0].length+j] = new int[]{grid[i][j],i,j};
//            }
//        }
//        int parent[] = new int[n*grid[0].length];
//        for(int i = 0;i<n*grid[0].length;i++) parent[i] = i;
//        Arrays.sort(matrix,(a,b)->a[0]-b[0]);
//        for(int i = 0,j = 0;i<m;i++)
//        {
//            while(j<n*grid[0].length&&queries[index[i]]>matrix[j][0])
//            {
//                int x = matrix[j][1];
//                int y = matrix[j][2];
//                for(int k[]:dir)
//                {
//                    int temp1 = x+k[0];
//                    int temp2 = y+k[1];
//                    if(temp1>=0&&temp1<n&&temp2>=0&&temp2<grid[0].length&&grid[temp1][temp2]<queries[index[i]])
//                    {
//                        union(size,parent,x*grid[0].length+y,temp1*grid[0].length+temp2);
//                    }
//                }
//                j++;
//            }
//            if(grid[0][0]<queries[index[i]]) ans[index[i]] = size[find(parent,0)];
//        }
//        return ans;
//    }
//
//    int find(int parent[],int x)
//    {
//        if(parent[x]!=x)
//        {
//            parent[x] = find(parent,parent[x]);
//        }
//        return parent[x];
//    }
//
//    void union(int size[],int parent[],int x,int y)
//    {
//        int one = find(parent,x);
//        int two = find(parent,y);
//        if(one!=two)
//        {
//            parent[one] = two;
//            size[two]+=size[one];
//        }
//    }


    //联通图中的量，离线查询+并查集
    // public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
    //     Integer ans[] = new Integer[n+1];
    //     int m = queries.length;
    //     Arrays.sort(edgeList,(a,b)->a[2]-b[2]);
    //     int parent[] = new int[n];
    //     Integer index[] = new Integer[m];
    //     for(int i = 0;i<m;i++) index[i] = i;
    //     for(int i = 0;i<n;i++) parent[i] = i;
    //     Arrays.sort(index,(a,b)->queries[a][2]-queries[b][2]);
    //     boolean res[] = new boolean[m];
    //     for(int i = 0,j = 0;i<m;i++)
    //     {
    //         while(j<edgeList.length&&edgeList[j][2]<queries[index[i]][2])
    //         {
    //             union(parent,edgeList[j][0],edgeList[j][1]);
    //             j++;
    //         }
    //         res[index[i]] = find(parent,queries[index[i]][1])==find(parent,queries[index[i]][0]);
    //     }
    //     return res;
    // }

    // void union(int parent[],int x,int y)
    // {
    //     int one = find(parent,x);
    //     int two = find(parent,y);
    //     parent[one] = two;
    // }
    // int find(int parent[],int x)
    // {
    //     if(parent[x]!=x)
    //     {
    //         parent[x] = find(parent,parent[x]);
    //     }
    //     return parent[x];
    // }
}
