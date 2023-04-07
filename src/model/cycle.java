package model;
import java.util.*;

public class cycle {
    //拓扑排序，判断有无环，额外使用一个vis去除环，然后历遍长度
    public int longestCycle_tuopo(int[] edges) {
        int n = edges.length;
        int indegree[] = new int[n];
        //拓扑排序找环
        for(int i = 0;i<n;i++)
        {
            if(edges[i]==-1) continue;
            indegree[edges[i]]++;
        }
        Deque<Integer> dq = new ArrayDeque<>();
        int cnt = 0;
        boolean vis[] = new boolean[n];
        for(int i = 0;i<n;i++)
        {
            if(indegree[i]==0)
            {
                vis[i] = true;
                dq.offerLast(i);
                cnt++;
            }
        }
        while(!dq.isEmpty())
        {
            int poll = dq.pollFirst();
            int next = edges[poll];
            if(next==-1) continue;
            if(--indegree[next]==0)
            {
                vis[next] = true;
                dq.offerLast(next);
                cnt++;
            }
        }


        if(cnt==n) return -1;
        int ans = -1;
        for(int i = 0;i<n;i++)
        {
            if(!vis[i])
            {
                int temp = 1;
                vis[i] = true;
                int cur = edges[i];
                int end = i;
                // System.out.println(end);
                while(cur!=end)
                {

                    vis[cur] = true;
                    cur = edges[cur];
                    temp++;
                }
                ans = Math.max(ans,temp);
            }
        }
        // System.out.println(vis[2]);
        return ans;
    }

    //最长环
    public int longestCycle(int[] edges) {
        int n = edges.length;
        int time[] = new int[n];
        int ans = -1;
        int clock = 1;
        //记录每一个节点访问的时间
        //如果访问了多次，则已经遇到环
        //特殊：从别的节点出发访问到已经访问的路径
        //为最开始的节点增加一个开始时间，如果大于则是访问到一个环
        for(int i = 0;i<n;i++)
        {
            if(time[i]!=0) continue;
            int start = clock;
            int j = i;
            while(edges[j]!=-1)
            {
                if(time[j]>0)
                {
                    if(time[j]>=start)
                        ans = Math.max(ans,clock-time[j]);
                    break;
                }
                time[j] = clock++;
                j = edges[j];
            }
        }
        return ans;
    }

    //判断是否有环
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

    //最短环
    //删除边，使用bfs寻找两个点之间的距离，如果可达，则环的长度 = 两点之间的距离 + 1
    public int findShortestCycle(int n, int[][] edges) {
        List<Integer> graph[] = new List[n];
        Arrays.setAll(graph,e->new ArrayList<Integer>());
        for(int i[]:edges)
        {
            graph[i[0]].add(i[1]);
            graph[i[1]].add(i[0]);
        }
        int ans = 1000000000;
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<graph[i].size();j++)
            {
                int target = graph[i].get(j);
                Deque<Integer> dq = new ArrayDeque<>();
                int dist[] = new int[n];
                Arrays.fill(dist,-1);
                dist[i] = 0;
                dq.offerLast(i);
                while(!dq.isEmpty())
                {
                    int poll = dq.pollFirst();
                    for(int next:graph[poll])
                    {
                        if(poll==i&&next==target) continue;
                        if(dist[next]==-1)
                        {
                            dist[next] = dist[poll] + 1;
                            dq.offerLast(next);
                        }
                    }
                }
                if(dist[target]!=-1) ans = Math.min(ans,1+dist[target]);
            }
        }
        if(ans==(int)1e9) return -1;
        return ans;
    }

    //最短环，以每一个点为起点做bfs
    //bfs:如果一个点已经被访问并且不是父节点，说明遇到了一个环，此时环的长度为dist[next] + dist[cur] + 1
    public int findShortestCycle_point(int n, int[][] edges) {
        List<Integer> graph[] = new List[n];
        Arrays.setAll(graph,e->new ArrayList<Integer>());
        for(int i[]:edges)
        {
            graph[i[0]].add(i[1]);
            graph[i[1]].add(i[0]);
        }
        int ans = 1000000000;
        for(int i = 0;i<n;i++)
        {
            Deque<int[]> dq = new ArrayDeque<>();
            int dist[] = new int[n];
            Arrays.fill(dist,-1);
            dist[i] = 0;
            dq.offerLast(new int[]{i,-1});
            while(!dq.isEmpty())
            {
                int poll[] = dq.pollFirst();
                int cur = poll[0];
                int par = poll[1];
                for(int next:graph[cur])
                {
                    if(dist[next]==-1)
                    {
                        dist[next] = dist[cur] + 1;
                        dq.offerLast(new int[]{next,cur});
                    }
                    else if(next!=par) ans = Math.min(ans,dist[cur]+dist[next]+1);
                }
            }
        }
        if(ans==(int)1e9) return -1;
        return ans;
    }


}
