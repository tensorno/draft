package model;

import java.util.*;

public class graph {
    private List<Integer>[] g;
    private int[] colors;
    //染色法，用1 2表示两种互斥颜色，0表示没有上色
    public boolean possibleBipartition(int n, int[][] dislikes) {
        //初始化
        g = new List[n];
        colors = new int[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : dislikes) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].add(b);
            g[b].add(a);
        }
        //在判断过程中上色
        for(int i = 0;i<n;i++)
        {
            if(colors[i]==0)
            {
                if(!dfs(i,1)) return false;
            }
        }
        return true;
    }

    boolean dfs(int index,int color)
    {
        //上色
        colors[index] = color;
        for(int i=0;i<g[index].size();i++)
        {
            //两种情况，一种已经上色，一种没有，没有的时候，给他上色
            int temp = g[index].get(i);
            if(colors[temp]==color) return false;
            else if(colors[temp]==0&&!dfs(temp,3-color)) return false;
        }
        return true;
    }

    //找环使用三种状态表示
    boolean dfs(int i,int[][] graph,int temp[])
    {
        if(temp[i]!=0) return temp[i]==2;
        temp[i] = 1;
        for(int j:graph[i])
        {
            if(!dfs(j,graph,temp)) return false;
        }
        temp[i] = 2;
        return true;
    }

//    每次从 「未求出最短路径的点」中取出距离距离起点最小路径的点，
//    以这个点为桥梁 刷新「未求出最短路径的点」的距离
    int[] dijkstra(int[][] graph,int start)
    {
        int n = graph.length;
        int dist[]  =new int[n];
        boolean vis[] = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        for (int i = 0; i < n; i++) {
            int index = -1;   // 每次找到「最短距离最小」且「未被更新」的点 index
            for (int j = 0; j < n; j++) {
                if (!vis[j] && (index == -1 || dist[j] < dist[index])) index = j;
            }
            // 标记点 t 为已更新
            vis[index] = true;
            // 用点 t 的「最小距离」更新其他点
            for (int j = 0; j < n; j++) dist[j] = Math.min(dist[j], dist[index] + graph[index][j]);
        }
        return dist;
    }


    int[] dijkstra(List<int[]>[] graph, int start) {
        int[] shortestPaths = new int[graph.length];
        Arrays.fill(shortestPaths, Integer.MAX_VALUE);
        shortestPaths[start] = 0;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        priorityQueue.offer(new int[]{start, 0});
        while (!priorityQueue.isEmpty()) {
            int[] currentShortestNode = priorityQueue.poll();
            int node = currentShortestNode[0], distance = currentShortestNode[1];
            if (distance > shortestPaths[node]) {
                continue;
            }
            for (int[] edge : graph[node]) {
                int neighbor = edge[0], newDistance = distance + edge[1];
                if (newDistance < shortestPaths[neighbor]) {
                    shortestPaths[neighbor] = newDistance;
                    priorityQueue.offer(new int[]{neighbor, newDistance});
                }
            }
        }
        return shortestPaths;
    }

    int[] dijkstra1(List<int[]>[] graph, int start) {
        int[] shortestPaths = new int[graph.length];
        Arrays.fill(shortestPaths, Integer.MAX_VALUE);
        boolean vis[] = new boolean[graph.length];
        shortestPaths[start] = 0;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        priorityQueue.offer(new int[]{start, 0});
        // 使用「优先队列」存储所有可用于更新的点
        // 以 (点编号, 到起点的距离) 进行存储，优先弹出「最短距离」较小的点
        while (!priorityQueue.isEmpty()) {
            int[] currentShortestNode = priorityQueue.poll();
            int node = currentShortestNode[0], distance = currentShortestNode[1];
//            if (distance > shortestPaths[node]) {
//                continue;
//            }
            if(vis[node]) continue; // 如果弹出的点被标记「已更新」，则跳过
            vis[node] = true;
            for (int[] edge : graph[node]) {   // 标记该点「已更新」，并使用该点更新其他点的「最短距离」
                int neighbor = edge[0], newDistance = distance + edge[1];
                if (newDistance < shortestPaths[neighbor]) {
                    shortestPaths[neighbor] = newDistance;
                    priorityQueue.offer(new int[]{neighbor, newDistance});
                }
            }
        }
        return shortestPaths;
    }

    //拓扑排序，寻找入度为0的点位
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int len = prerequisites.length;
        List<Integer> lst []=new List[numCourses];
        for(int i =0;i<numCourses;i++)
        {
            lst[i] = new ArrayList();
        }
        int in[] = new int[numCourses];
        for(int i =0;i<prerequisites.length;i++)
        {
            lst[prerequisites[i][1]].add(prerequisites[i][0]);
            in[prerequisites[i][0]]++;
        }

        int cnt=0;
        Queue queue = new LinkedList();
        for(int i =0;i<numCourses;i++)
        {
            if (in[i]==0)
            {
                queue.offer(i);
            }
        }
        while(!queue.isEmpty())
        {
            int p =(int) queue.poll();
            cnt++;
            for(int j:lst[p])
            {
                if(--in[j]==0)
                {
                    queue.offer(j);
                }
            }
        }
        return cnt==numCourses;
    }
}
