package model;
import java.util.*;
public class DFS {
    List<List<Integer>> ans;
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        ans = new ArrayList();
        List<Integer> temp = new ArrayList();
        boolean vis[] = new boolean[nums.length];
        Arrays.fill(vis,false);
        dfs(temp,nums,vis);
        return ans;
    }

    void dfs(List<Integer> temp,int nums[],boolean vis[])
    {
        if(temp.size()==nums.length)
        {
            ans.add(new ArrayList(temp));
            return;
        }
        for(int i = 0;i<nums.length;i++)
        {
            if(!vis[i])
            {
                if(i>0&&vis[i-1]&&nums[i-1]==nums[i]) continue;
                temp.add(nums[i]);
                vis[i] = true;
                dfs(temp,nums,vis);
                temp.remove(temp.size()-1);
                vis[i] = false;
            }
        }
    }

    public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
        }
    }
    HashMap<TreeNode,Integer> hash = new HashMap<>();
    int ans1[] ;
    public int[] treeQueries(TreeNode root, int[] queries) {
        hash.put(null,0);
        getheight(root);
        ans1 = new int[hash.size()+1];
        dfs(root,0,0);
        for(int i = 0;i<queries.length;i++)
        {
            queries[i] = ans1[queries[i]];
        }
        return queries;
    }

    int getheight(TreeNode root)
    {
        if(root==null) return 0;
        int max = 1+Math.max(getheight(root.left),getheight(root.right));
        hash.put(root,max);
        return max;
    }

    //res表示移除当前的节点的二叉树高度
    //1.移除左子树
    //2.移除右子树
    //移除子树，最长路径可能经过移除的节点，
    //可能不经过，两种情况取最大
    void dfs(TreeNode root,int depth,int res)
    {
        if(root==null) return;
        // depth++;
        ans1[root.val] = res;
        dfs(root.left,depth+1,Math.max(res,depth+hash.get(root.right)));
        dfs(root.right,depth+1,Math.max(res,depth+hash.get(root.left)));
    }


    //两次dfs，一次找bob路径，一次找所有alice路径，bob路径固定
    List<Integer> g[];
    HashMap<Integer,Integer> bobstep;
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        bobstep = new HashMap<>();
        int n = edges.length+1;
        g = new List[n];
        for(int i = 0;i<n;i++)
        {
            g[i] = new ArrayList();
        }
        for(int i = 0;i<n-1;i++)
        {
            g[edges[i][0]].add(edges[i][1]);
            g[edges[i][1]].add(edges[i][0]);
        }
        bobstep.put(bob,0);
        dfsb(0,-1,bob);
        dfsa(0,-2,0,0,amount);
        // System.out.print(bobstep);
        return ans2;
    }

    void dfsb(int cur,int par,int bob)
    {
        if(cur==bob)
        {
            bobstep.put(par,1);
            return;
        }
        for(int i =0;i<g[cur].size();i++)
        {
            if(g[cur].get(i)!=par)
            {
                dfsb(g[cur].get(i),cur,bob);
            }
        }
        if(bobstep.containsKey(cur)) bobstep.put(par,1+bobstep.get(cur));
    }

    int ans2 = -100001;
    void dfsa(int cur,int par,int step,int res,int amount[])
    {
        if(bobstep.containsKey(cur)&&step==bobstep.get(cur)) res += amount[cur]/2;
        else if(bobstep.containsKey(cur)&&step>bobstep.get(cur)) ;
        else res+=amount[cur];
        if(g[cur].size()==1&&g[cur].get(0)==par)
        {
            ans2 = Math.max(res,ans2);
            return;
        }
        for(int i =0;i<g[cur].size();i++)
        {
            if(g[cur].get(i)!=par)
            {
                dfsa(g[cur].get(i),cur,step+1,res,amount);
            }
        }
    }

    int mask;//二进制表示树
    int max;//树形dp找树的直径
    int dfs(int cur)
    {
        mask^=1<<cur;
        int first = 0;
        int second = 0;
        for(int next:g[cur])
        {
            if((mask&(1<<next))!=0)
            {
                int distance = 1+dfs(next);
                if(distance>first){
                    second = first;
                    first = distance;
                }
                else if(distance>second){
                    second = distance;
                }
            }
        }
        max = Math.max(max,first+second);
        return first;
    }
}
