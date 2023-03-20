package caogao;

import java.util.*;

public class Main {
    static int mod = (int)1e9+7;
    public static void main(String[] args) {
//        so();
//        so1();
        System.out.println(solve());
    }

    static int solve()
    {
        Scanner scanner = new Scanner(System.in);
        String begin = scanner.nextLine();
        String end = scanner.nextLine();
        int n = begin.length();
        Deque<String> dq =new ArrayDeque<>();
        dq.offerLast(begin);
        HashSet<String> vis = new HashSet<>();
        vis.add(begin);
        int ans = 0;
        while(!dq.isEmpty())
        {
            int size = dq.size();
            while(size-->0)
            {
                String poll = dq.pollFirst();
                if(poll.equals(end)) return ans;
                for (int i = 0; i < n; i++) {
                    if(poll.charAt(i)=='*') continue;
                    if(i+1<n&&poll.charAt(i+1)=='*')
                    {
                        StringBuilder stringBuilder = new StringBuilder(poll);
                        char x = poll.charAt(i);
                        char y = poll.charAt(i+1);
                        stringBuilder.setCharAt(i,y);
                        stringBuilder.setCharAt(i+1,x);
                        String temp = stringBuilder.toString();
                        if(!vis.contains(temp))
                        {
                            dq.offerLast(temp);
                            vis.add(temp);
                        }
                    }
                    if(i+2<n&&poll.charAt(i+1)!='*'&&poll.charAt(i+2)=='*')
                    {
                        StringBuilder stringBuilder = new StringBuilder(poll);
                        char x = poll.charAt(i);
                        char y = poll.charAt(i+2);
                        stringBuilder.setCharAt(i,y);
                        stringBuilder.setCharAt(i+2,x);
                        String temp = stringBuilder.toString();
                        if(!vis.contains(temp))
                        {
                            dq.offerLast(temp);
                            vis.add(temp);
                        }
                    }
                    else if(i+3<n&&poll.charAt(i+1)!='*'&&poll.charAt(i+2)!='*'&&poll.charAt(i+3)=='*')
                    {
                        StringBuilder stringBuilder = new StringBuilder(poll);
                        char x = poll.charAt(i);
                        char y = poll.charAt(i+3);
                        stringBuilder.setCharAt(i,y);
                        stringBuilder.setCharAt(i+3,x);
                        String temp = stringBuilder.toString();
                        if(!vis.contains(temp))
                        {
                            dq.offerLast(temp);
                            vis.add(temp);
                        }
                    }
                    if(i-1>=0&&poll.charAt(i-1)=='*')
                    {
                        StringBuilder stringBuilder = new StringBuilder(poll);
                        char x = poll.charAt(i);
                        char y = poll.charAt(i-1);
                        stringBuilder.setCharAt(i,y);
                        stringBuilder.setCharAt(i-1,x);
                        String temp = stringBuilder.toString();
                        if(!vis.contains(temp))
                        {
                            dq.offerLast(temp);
                            vis.add(temp);
                        }
                    }
                    if(i-2>=0&&poll.charAt(i-1)!='*'&&poll.charAt(i-2)=='*')
                    {
                        StringBuilder stringBuilder = new StringBuilder(poll);
                        char x = poll.charAt(i);
                        char y = poll.charAt(i-2);
                        stringBuilder.setCharAt(i,y);
                        stringBuilder.setCharAt(i-2,x);
                        String temp = stringBuilder.toString();
                        if(!vis.contains(temp))
                        {
                            dq.offerLast(temp);
                            vis.add(temp);
                        }
                    }
                    else if(i-3>=0&&poll.charAt(i-1)!='*'&&poll.charAt(i-2)!='*'&&poll.charAt(i-3)=='*')
                    {
                        StringBuilder stringBuilder = new StringBuilder(poll);
                        char x = poll.charAt(i);
                        char y = poll.charAt(i-3);
                        stringBuilder.setCharAt(i,y);
                        stringBuilder.setCharAt(i-3,x);
                        String temp = stringBuilder.toString();
                        if(!vis.contains(temp))
                        {
                            dq.offerLast(temp);
                            vis.add(temp);
                        }
                    }
                }
            }
            ans++;
        }
//        System.out.println(ans);
        return -1;
    }
    static void so1()
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int ans[] = new int[n+1];
        for (int i = 0; i < n+1; i++) {
            ans[i] = i;
        }
        for (int i = 0; i < m; i++) {
            int target = scanner.nextInt();
            int step = scanner.nextInt();
//            int index = 0;
            int begin = ans[target];
            ans[target] += step;
            if(step>0)
            {
                for (int j = 1; j <= n; j++) {
                    if(begin<ans[j]&&ans[j]<=ans[target]&&j!=target)
                    {
                        ans[j]--;
                    }
                }
            }
            else {
                for (int j = 1; j <= n; j++) {
                    if(ans[target]<=ans[j]&&ans[j]<begin&&j!=target)
                    {
                        ans[j]++;
                    }
                }
            }
        }
        int res[] = new int[n+1];
        for(int i = 1;i<=n;i++)
        {
            res[ans[i]] = i;
        }
        for(int i = 1;i<=n;i++) System.out.print(res[i]+" ");
    }
    static void so()
    {
        Scanner scanner = new Scanner(System.in);
        int n =scanner.nextInt();
        int m = scanner.nextInt();
        int time[] = new int[m+1];
        int indegre[]  =new int[m+1];
        int outdegree[]  = new int[m+1];
        for(int i = 0;i<m;i++)
        {
            int temp = scanner.nextInt();
            indegre[i+1] = temp;
            outdegree[temp] = i+1;
        }
        for (int i = 0; i < m; i++) {
            time[i+1] = scanner.nextInt();
        }

        int early[] =new int[m+1];
        boolean flag = true;
        for (int i = 1; i < m+1; i++) {
            if(indegre[i]==0) early[i] = 1;
            else early[i] = early[indegre[i]] + time[indegre[i]];
            if(early[i]+time[i]>n+1) flag = false;
        }
        for (int i = 1; i < m+1; i++)
        {
            System.out.print(early[i]+" ");
        }
        if(flag) {
            int last[] = new int[m + 1];
//            Arrays.fill(last, n);
            for (int i = m; i > 0; i--) {
                if (outdegree[i] == 0) last[i] = n - time[i] + 1;
                else last[i] = Math.max(last[i], last[outdegree[i]] - time[i]);
            }
            System.out.println();
            for (int i = 1; i < m + 1; i++) {
                System.out.print(last[i] + " ");
            }
        }

    }


//    static void solve()
//    {
//        Scanner scanner = new Scanner(System.in);
//        int m =scanner.nextInt();
//        int n = scanner.nextInt();
//        HashMap<String,String> ans = new HashMap<>();
//        StringBuilder s = new StringBuilder();
//        scanner.nextLine();
//        for (int i = 0; i < m; i++) {
//            s.append(scanner.nextLine());
//        }
//        String str = s.toString().replace(" ","");
////        System.out.println(str);
//        str = str.substring(1,str.length()-1);
//        Deque<String> dq = new ArrayDeque<>();
//        dq.offerLast(str);
//        while(!dq.isEmpty())
//        {
//            String poll = dq.pollFirst();
//            int left = 0;;
//            int right = 0;
//            while(right<poll.length()&&poll.charAt(right)!='}')
//            {
//                if(poll.charAt(right)=='{') left = right;
//                right++;
//            }
//            if(left==0)
//            {
//                String ss[] = poll.split(",");
//                for(String i:ss)
//                {
//                    String j[] = i.split(":");
//                    ans.put(j[0],j[1]);
//                }
//                continue;
//            }
//            int begin = left;
//            while(poll.charAt(begin)!=',')
//            {
//                begin--;
//            }
//            String change = poll.substring(begin+1,left-1);
//            String start = poll.substring(0, begin), end = poll.substring(right + 1);
//            ans.put(change,"OBJECT");
//            String words[] = poll.substring(left+1,right).split(",");
//            for(String word:words)
//            {
//                String keyword[] = word.split(":");
//                dq.offerLast(start+","+change+"."+keyword[0]+":"+keyword[1]+end);
//            }
//        }
//        HashMap<String,String> res = new HashMap<>();
//        for(String i:ans.keySet()) {
//            boolean change = false;
//            String in = "", out = "";
//            if (i.indexOf('\\') != -1) {
//                change = true;
//                in = change(i);
//            }
//            if (ans.get(i).indexOf('\\') != -1) {
//                change = true;
//                out = change(ans.get(i));
//            }
//            if (change) res.put(in, out);
//        }
//        for(String i:ans.keySet()) res.put(i,ans.get(i));
//        System.out.println(res);
//        for (int i = 0; i < n; i++) {
//            String temp = scanner.nextLine();
//            if(!res.containsKey(temp)) System.out.println("NOTEXIST");
//            else if(res.get(temp).equals("OBJECT")) System.out.println("OBJECT");
//            else System.out.println("STRING"+" "+res.get(temp));
//        }
//    }

//    static String change(String s)
//    {
//        StringBuilder ans = new StringBuilder();
//        for (int i = 0; i < s.length(); i++) {
//            if(s.charAt(i)=='\\')
//            {
//                if(i<s.length()-1&&s.charAt(++i)=='\\') ans.append('\\');
//                else ans.append('"');
//            }
//            else ans.append(s.charAt(i));
//        }
//        return ans.toString();
//    }



    //括号序列
    //1.添加的左右括号互不影响，可以单独求添加左括号，右括号最后相乘。
    //以下针对添加左括号的情况
    //2.dp[i][j]表示的是前i个字符串中，添加左括号后得到的结果字符串左括号比右括号多j个
    //3.遇到左括号并不需要添加左括号直接有之前的状态得到，即dp[i][j] = dp[i-1][j-1]
    //4.遇到右括号，这时候需要添加左括号，要使得左括号的数量比右括号多j个，可能会从多0，1，2，...，j+1的情况转变而来
    //dp[i][j] = dp[i-1][0] + dp[i-1][1] +... +dp[i-1][j+1]
    //对于j == 0的特殊情况 dp[i][0] = dp[i-1][0] + dp[i-1][1]
    static int solvekuohao(String s) {
        int len = s.length();
        int dp[][] = new int[5010][5010];
        dp[0][0] = 1;
        for(int i = 1;i<=len;i++)
        {
            if(s.charAt(i-1)=='(')
            {
                for(int j = 1;j<=len;j++)
                {
                    dp[i][j] = dp[i-1][j-1]%mod;
                }
            }
            else
            {
                dp[i][0] = (dp[i-1][0]+dp[i-1][1])%mod;
                for(int j = 1;j<=len;j++)
                {
                    dp[i][j] = (dp[i-1][j+1]+dp[i][j-1])%mod;
                }
            }
        }
        for(int i:dp[len])
        {
            if(i!=0) return i;
        }
        return -1;
    }
}