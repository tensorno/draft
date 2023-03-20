package model;
import java.util.*;
public class Solution{
    public static void main(String[] args) {

    }

    //动态规划，中心拓展
    public int countSubstrings(String s) {
        //动态规划 dp[i][j] 表示在i到j是不是回文
        // int ans =0;
        // int len = s.length();
        // boolean dp[][] = new boolean [len][len];
        // for(int i = 0;i<len;i++)
        // {
        //     for(int j = 0;j<=i;j++)
        //     {
        //         if (s.charAt(i)==s.charAt(j))
        //         {
        //             if(i-j<2||dp[j+1][i-1]) //表示两种情况，1.只有两个或者一个字符2.两头去掉的字符串仍然是回文
        //             {
        //                 dp[j][i] = true;
        //                 ans++;
        //             }
        //         }
        //     }
        // }
        // return ans;
        //中心拓展，历遍每一个最简单的中心，有两个或者一个例如a和ab，逐一向两边拓展
        int ans = 0;
        int len = s.length();
        
        for(int i = 0;i<2*len-1;i++)
        {
            int start = i/2;
            int end = start+i%2;
            while(start>=0&&end<len&&s.charAt(start)==s.charAt(end))
            {
                start--;
                end++;
                ans++;
            }
        }
        return ans;
    }
    //滑动窗口
    public int characterReplacement(String s, int k) {
        int ans = 0;
        int max = 0;
        int cnt[] = new int [26];
        int len = s.length();
        int left = 0;
        for(int right = 0;right<len;right++)
        {
            cnt[s.charAt(right)-'A']++;
            max = Math.max(max,cnt[s.charAt(right)-'A']);
            while(right-left+1>max+k)
            {
                cnt[s.charAt(left)-'A']--;
                left++;
            }
            ans = Math.max(ans,right-left+1);
        }
        return ans;
    }
    //滑动窗口
    public int equalSubstring(String s, String t, int maxCost) {
        int ans = 0;
        int len = s.length();
        int sum = 0;
        int left=0;
        for(int right =0;right<len;right++)
        {
            sum+=Math.abs(t.charAt(right)-s.charAt(right));
            while(sum>maxCost)
            {
                sum-=Math.abs(t.charAt(left)-s.charAt(left));
                left++;
            }
            ans = Math.max(ans,right-left+1);
        }
        return ans;
    }

    public boolean  canReorderDoubled(int[] arr) {
        //如果使用int类型会出现5/2 = 2 的现象
//        int len = arr.length;
//        Arrays.sort(arr);
//        HashMap<Double, Double> hash = new HashMap();
//        double next = 0;
//        for (int i = 0; i < len; i++) {
//            if (arr[i] > 0) {
//                next = arr[i] / 2.0;
//            } else {
//                next = arr[i] * 2.0;
//            }
//            if (hash.get(next) != null && hash.get(next) != 0) {
//                hash.put(next, hash.get(next) - 1.0);
//            } else {
//                hash.put((double) arr[i], hash.getOrDefault((double)arr[i], 0.0)+1 );
//            }
//        }
//        for (double i : hash.keySet()) {
//            if (hash.get(i) != 0.0) return false;
//        }
//        return true;
        // 只使用*2
        Arrays.sort(arr);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {  //因为排序，所以小的先进入hashmap
            if (i < 0) {
                if (map.containsKey(2 * i) && map.get(2 * i) != 0) {
                    map.put(2 * i, map.get(2 * i) - 1);
                } else {
                    map.put(i, map.getOrDefault(i, 0) + 1);
                }
            }
            else {
                if (map.containsKey(i) && map.get(i) != 0) {
                    map.put(i, map.get(i) - 1);
                } else {
                    map.put(2 * i, map.getOrDefault(2 * i, 0) + 1);
                }
            }
        }
        for (int i : map.values()) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
    public int countDigitOne(int n) { //数字1的数量
        int ans = 0;
        int cur = 0;
        int front = 0;
        int behind = 0;
        for(int i = 1;i<=n;i=i*10)
        {
            cur = (n/i)%10;
            front = n/10/i;
            behind = n%i;
            ans += front*i;
            if(cur>1) ans+=i;
            else if(cur==1) ans+=behind+1;
        }
        return ans;
    }

    public int longestValidParentheses(String s) {
        //动态规划
        int ans = 0;
        int len = s.length();
        int dp[] = new int [len];  //dp[i]表示以i结尾的字符串有多长符合要求
        for(int i = 1;i<len;i++)
        {
            if (s.charAt(i)==')')  //遇到)开始判断
            {
                if (s.charAt(i-1)=='(')  //如果前面是一个(直接加上dp[i-2]
                {
                    dp[i] = 2;
                    if(i>=2) dp[i] = dp[i]+dp[i-2];
                }
                else  //如果前面也是(
                {
                    //i-1-dp[i-1]表示i-1对应的(前面一位
                    if (i-dp[i-1]-1>=0&&s.charAt(i-1-dp[i-1])=='(')    //如果这一个位置位位(,代表这i这一位和它配对，数量是dp[i-1]+2
                    {
                        dp[i] = dp[i-1]+2;
                        if (i-dp[i-1]-2>=0)  //同时，如果配对成功的话，dp[i-1]不能包含住dp[i-dp[i-1]-2]，因为缺少被一个(即i-dp[i-1]-1这一位破坏
                        {
                            dp[i] = dp[i]+dp[i-dp[i-1]-2];  //i - dp[i - 1] - 1和 i 组成了有效括号对，这将是一段独立的有效括号序列，如果之前的子序列是形如 (...)(...) 这种序列，那么当前位置的最长有效括号长度还需要加上这一段。
                        }
                    }
                }
            }
            ans = Math.max(ans,dp[i]);
        }
        return ans;
        //栈
//        Stack<Integer> stack = new Stack();
//        stack.push(-1);
//        int max = 0;
//        int len = s.length();
//        for(int i = 0;i<len;i++)
//        {
//            if (s.charAt(i)=='(') stack.push(i);
//            else
//            {
//                stack.pop();
//                if(!stack.isEmpty())
//                {
//                    max = Math.max(max,i-stack.peek());
//                }
//                else
//                {
//                    stack.push(i);
//                }
//            }
//        }
//        return max;
    }
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];  //tail【i】 表示长度为i+1的递增子序列末尾元素
        int res = 0;
        for(int num : nums) {
            int i = 0, j = res;
            while(i < j) {
                int m = (i + j) / 2;
                if(tails[m] < num) i = m + 1;  //二分找到第一个num>tail[i]
                else j = m;
            }
            tails[i] = num;
            if(res == j) res++;
        }
        return res;
    }

    public int[] getMaxMatrix(int[][] matrix) {  //将二维数组合并位一维数组然后，通过前缀和合并
        int m = matrix.length;
        int n = matrix[0].length;
        int max = matrix[0][0];
        int[] res = new int[4];

        // 构造列的前缀和
        int[][] preSum = new int[m + 1][n];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 0; j < n; j++) {
                preSum[i][j] = preSum[i - 1][j] + matrix[i - 1][j];
            }
        }

        // 合并行
        for (int top = 0; top < m; top++) {
            for (int bottom = top; bottom < m; bottom++) {
                // 构造一维矩阵
                int[] arr = new int[n];
                for (int i = 0; i < n; i++) {
                    arr[i] = preSum[bottom + 1][i] - preSum[top][i];
                }
                // 最大子数组问题
                int start = 0;
                int sum = arr[0];
                for (int i = 1; i < n; i++) {
                    if (sum > 0) {
                        sum += arr[i];
                    } else {
                        sum = arr[i];
                        start = i;
                    }
                    if (sum > max) {
                        max = sum;
                        res[0] = top;
                        res[1] = start;
                        res[2] = bottom;
                        res[3] = i;
                    }
                }
            }
        }
        return res;
    }

    public int findDuplicate(int[] nums) {
        int slow = 0;  //数组中有一个重复的整数 <==> 链表中存在环
        int fast = 0;
        //low = fast 时，快慢指针相遇，low 走过的距离是初始点（0）到环状开始的点 （x） 加上 环状开始的点（x） 到相遇点（y） 这段距离，
        // 而fast走过的距离是 初始点（0）到环状开始的点（x），点（x） 到点（y），点（y）到点（x），点（x）到点（y）。
        // 又因为fast走过的距离是low的两倍，设0到x长度为a，x到y长度为b,则有2*（a+b） = a+ b+ (y到x的距离) + b，
        // 则y到x的距离就等于0到x的距离。
        // 所以当新的两个指针 一个从0出发，一个从相遇点y出发时，他们走到的相同的值就是环状开始的点，即x点。
        slow = nums[slow];
        fast = nums[nums[fast]];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        int pre1 = 0;
        int pre2 = slow;
        while(pre1 != pre2){
            pre1 = nums[pre1];
            pre2 = nums[pre2];
        }
        return pre1;
    }

    public int uniqueLetterString(String s) {

        // 可以转换为每一个字符对答案的贡献
        //AXXXAXXA，
        // 对于中间的A字符而言，需要找到左边第一个A l和右边第一个A的位置r，然后求得贡献值为(i - l) * (r - i)
        //具体写法需要找到每一个字符的左右边界
//        char[] cs = s.toCharArray();
//        int n = cs.length, ans = 0;
//        int[] l = new int[n], r = new int[n], idx = new int[26];
//        Arrays.fill(l, -1);
//        Arrays.fill(r, n);
//        Arrays.fill(idx, -1);
//        for (int i = 0; i < n; i++) {
//            if (idx[cs[i] - 'A'] != -1) {
//                int last = idx[cs[i] - 'A'];
//                r[last] = i;
//                l[i] = last;
//            }
//            idx[cs[i] - 'A'] = i;
//        }
//        for (int i = 0; i < n; i++) ans += (i - l[i]) * (r[i] - i);
//        return ans;

        //计算以所有s【i】的子串中，独立字符的数量
        //其中s[i]结尾的子串，是所有以s[i-1]结尾的子串，在其末尾添加上s[i]
        //XAXXAXBA
        //以最后一个A为例，以B为结尾的所有子串在加入A后：
        //● B, XB 加入A 得分+1，此外还有新出现的A自己作为一个单独的子串A得分也是1，所以总变化量为增加：i - 上次出现的idx
        //● AXB, XAXB, XXAXB 加入A后，由于A出现重复，这些子串得分-1，所以总变化量为减少：上次出现的idx - 上上次出现的idx
        //● AXXAXB, XAXXAXB 本来就有重复A 所以得分不变
        //用变量sum统计当前的s[i]结尾的所有子串得分，相对于s[i - 1]的sum而言：
        //sum += i - 上一次出现的idx - (上一次出现的idx - 上上次出现的idx)
        //因此我们需要记录每个字母，上一次出现和上上次出现的位置。
        char[] cs = s.toCharArray();
        int[] last = new int[26], llast = new int[26]; // 分别存储上上次和上一次出现的位置
        Arrays.fill(last, -1);
        Arrays.fill(llast, -1);
        int n = cs.length, ans = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            int c = cs[i] - 'A';
            sum += (i - last[c]) - (last[c] - llast[c]);
            ans += sum;
            llast[c] = last[c]; // 更新上上次出现位置
            last[c] = i; // 更新上次出现位置
        }
        return ans;
    }

    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        double ans = 1e9;
        int len = quality.length;
        Integer[] h = new Integer[len];
        for (int i = 0; i < len; i++) {
            h[i] = i;
        }
        Arrays.sort(h, (a, b) -> {
            return quality[b] * wage[a] - quality[a] * wage[b];
        });
        PriorityQueue<Integer> pr = new PriorityQueue<>((a, b)->
        {
            return b>a?1:-1;
        });
        int sum = 0;
        double salary = 0;
        for(int i = 0;i<len;i++)
        {
            if(pr.size()<k)
            {
                pr.offer(quality[h[i]]);
                salary = wage[h[i]]*1.0/quality[h[i]];
                sum+=quality[h[i]];
            }
            else if(quality[h[i]]<pr.peek())
            {
                salary = wage[h[i]]*1.0/quality[h[i]];
                sum-=pr.poll();
                pr.offer(quality[h[i]]);
                sum+=quality[h[i]];
            }
            if(pr.size()==k)
            {
                ans = Math.min(ans,sum*salary);
            }
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
        List<Integer> lst = new ArrayList<Integer>(set);
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

    public int[] missingTwo(int[] nums) {
        //找到最低的值为 1 的位置即为 k
        //将数组分为第 k 位为 1 和第 k 位不为 1 两组
        //那么这两个数被分到不同的组, 将问题转化在该组中除了一个数外其他数出现两次, 找出出现一次的数的子问题。
        // 两组分别异或结果为要找的两个数。
        int ans[] = new int[2];
        int len = nums.length;
        int temp = 0;
        for(int i:nums)
        {
            temp^= i;
        }
        for(int i = 1;i<=len+2;i++)
        {
            temp^=i;
        }
        int diff = temp&-temp;
        int one = 0;
        int two = 0;
        for(int i = 0;i<nums.length;i++)
        {
            if((diff&nums[i])==0) one^=nums[i];
            else two^=nums[i];
        }
        for(int i = 1;i<=len+2;i++)
        {
            if((diff&i)==0) one^=i;
            else two^=i;
        }
        ans[0] = one;
        ans[1] = two;
        return ans;
    }


    //两个优先队列模拟
    public long totalCost(int[] costs, int k, int candidates) {
        long ans = 0;
        int n = costs.length;
        PriorityQueue<Integer> pri1 = new PriorityQueue<>();
        PriorityQueue<Integer> pri2 = new PriorityQueue<>();
        int leftbound = candidates;
        int rightbound = n-candidates;
        if(leftbound>=n) leftbound = n;
        if(leftbound>rightbound) rightbound = leftbound;
        for(int i = 0;i<leftbound;i++)
        {
            pri1.add(costs[i]);
        }
        for(int i = rightbound;i<n;i++)
        {
            pri2.add(costs[i]);
        }
        while(k-->0)
        {
            if(pri1.isEmpty()||(!pri2.isEmpty()&&pri1.peek()>pri2.peek()))
            {
                ans += pri2.poll();
                rightbound--;
                if(rightbound>=leftbound) pri2.add(costs[rightbound]);
            }
            else
            {
                ans += pri1.poll();
                if(leftbound<n&&rightbound>leftbound) pri1.add(costs[leftbound]);
                leftbound++;
            }
        }
        return ans;
    }

    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return false;
        }
        int s = Arrays.stream(nums).sum();
        for (int i = 0; i < n; ++i) {
            nums[i] = nums[i] * n - s;
        }
        int m = n >> 1;

        //降低时间复杂度，将数组一分为二
        Set<Integer> vis = new HashSet<>();
        for (int i = 1; i < 1 << m; ++i) {
            int t = 0;
            for (int j = 0; j < m; ++j) {
                if (((i >> j) & 1) == 1) {
                    t += nums[j];
                }
            }
            if (t == 0) {
                return true;
            }
            vis.add(t);
        }

        //同上求法用每一位的1或者0表示选择或者选择
        for (int i = 1; i < 1 << (n - m); ++i) {
            int t = 0;
            for (int j = 0; j < (n - m); ++j) {
                if (((i >> j) & 1) == 1) {
                    t += nums[m + j];
                }
            }
            if (t == 0 || (i != (1 << (n - m)) - 1) && vis.contains(-t)) {
                return true;
            }
        }
        return false;
    }

    //分桶，我们不妨将 wordswords 中的所有单词根据首字母来分桶
    //然后我们从 ss 的第一个字符开始遍历，假设当前字符为 'a'，我们从 'a' 开头的桶中取出所有单词。
    // 对于取出的每个单词，如果此时单词长度为 11，说明该单词已经匹配完毕，我们将答案加 11；
    // 否则我们将单词的首字母去掉，然后放入下一个字母开头的桶中

    public int numMatchingSubseq(String s, String[] words) {
        Deque<String>[] d = new Deque[26];
        for (int i = 0; i < 26; ++i) {
            d[i] = new ArrayDeque<>();
        }
        for (String w : words) {
            d[w.charAt(0) - 'a'].add(w);
        }
        int ans = 0;
        for (char c : s.toCharArray()) {
            Deque<String> q = d[c - 'a'];
            for (int k = q.size(); k > 0; --k) {
                String t = q.pollFirst();
                if (t.length() == 1) {
                    ++ans;
                } else {
                    d[t.charAt(1) - 'a'].offer(t.substring(1));
                }
            }
        }
        return ans;
    }

    //两个数组中找到中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        //因为数组是从索引0开始的，因此我们在这里必须+1，即索引(k+1)的数，才是第k个数。
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }
    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        //因为索引和算数不同6-0=6，但是是有7个数的，因为end初始就是数组长度-1构成的。
        //最后len代表当前数组(也可能是经过递归排除后的数组)，符合当前条件的元素的个数
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        //就是如果len1长度小于len2，把getKth()中参数互换位置，即原来的len2就变成了len1，即len1，永远比len2小
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        //如果一个数组中没有了元素，那么即从剩余数组nums2的其实start2开始加k再-1.
        //因为k代表个数，而不是索引，那么从nums2后再找k个数，那个就是start2 + k-1索引处就行了。因为还包含nums2[start2]也是一个数。因为它在上次迭代时并没有被排除
        if (len1 == 0) return nums2[start2 + k - 1];

        //如果k=1，表明最接近中位数了，即两个数组中start索引处，谁的值小，中位数就是谁(start索引之前表示经过迭代已经被排出的不合格的元素，即数组没被抛弃的逻辑上的范围是nums[start]--->nums[end])。
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        //为了防止数组长度小于 k/2,每次比较都会从当前数组所生长度和k/2作比较，取其中的小的(如果取大的，数组就会越界)
        //然后素组如果len1小于k / 2，表示数组经过下一次遍历就会到末尾，然后后面就会在那个剩余的数组中寻找中位数
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        //如果nums1[i] > nums2[j]，表示nums2数组中包含j索引，之前的元素，逻辑上全部淘汰，即下次从J+1开始。
        //而k则变为k - (j - start2 + 1)，即减去逻辑上排出的元素的个数(要加1，因为索引相减，相对于实际排除的时要少一个的)
        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }
}
