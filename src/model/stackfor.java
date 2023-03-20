package model;

import java.util.*;

public class stackfor {
    //单调队列，f[i]=min(f[i],f[j]+cs[i−1]−cs[j]+2)
    //单调队列可以求窗口的最值
    //求满足条件的窗口中f(j)-cs[j]的最小值
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        long weight[] = new long[n+1];
        int sum[] = new int[n];
        int dp[] = new int[n+1];
        for(int i = 0;i<n;i++)
        {
            weight[i+1] = weight[i]+boxes[i][1];
            if(i<n-1&&boxes[i][0]!=boxes[i+1][0]) sum[i+1] = sum[i]+1;
            else if(i<n-1&&boxes[i][0]==boxes[i+1][0]) sum[i+1] = sum[i];
        }
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(0);
        for(int i = 1;i<=n;i++)
        {
            while(!deque.isEmpty()&&(i-deque.peekFirst()>maxBoxes||weight[i]-weight[deque.peekFirst()]>maxWeight))
            {
                deque.pollFirst();
            }
            dp[i] = dp[deque.peekFirst()]+2+sum[i-1]-sum[deque.peekFirst()];
            if(i<n)
            {
                while(!deque.isEmpty()&&dp[deque.peekLast()]-sum[deque.peekLast()]>=dp[i]-sum[i])
                {
                    deque.pollLast();
                }
                deque.offerLast(i);
            }
        }
        return dp[n];
    }
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        Deque<Integer> monoStack = new ArrayDeque<Integer>();
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            while (!monoStack.isEmpty() && arr[i] <= arr[monoStack.peek()]) {
                monoStack.pop();
            }
            left[i] = i - (monoStack.isEmpty() ? -1 : monoStack.peek());
            monoStack.push(i);
        }
        monoStack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!monoStack.isEmpty() && arr[i] < arr[monoStack.peek()]) {
                monoStack.pop();
            }
            right[i] = (monoStack.isEmpty() ? n : monoStack.peek()) - i;
            monoStack.push(i);
        }
        long ans = 0;
        final int MOD = 1000000007;
        for (int i = 0; i < n; i++) {
            ans = (ans + (long) left[i] * right[i] * arr[i]) % MOD;
        }
        return (int) ans;
    }

    public long subArrayRanges(int[] nums) {
        long ans  = 0;
        int len = nums.length;
        int minleft[] = new int[len];
        int minright[] = new int[len];
        int maxleft[] = new int[len];
        int maxright[] = new int[len];
        Stack<Integer> stack = new Stack();
        stack.push(-1);
        // 从左往右单调增栈 不能出栈的时候栈顶就是当前元素左侧最近的小于当前元素的节点
        for(int i = 0;i<len;i++)
        {
            while(stack.peek()!=-1&&nums[stack.peek()]>=nums[i])
            {
                stack.pop();
            }
            minleft[i] = stack.peek();
            stack.push(i);
        }
        // 从右往左单调增栈 不能出栈的时候栈顶就是当前元素右侧最近的小于当前元素的节点
        stack = new Stack();
        stack.push(len);
        for(int i=len-1;i>-1;i--)
        {
            while(stack.peek()!=len&&nums[stack.peek()]>nums[i])
            {
                stack.pop();
            }
            minright[i] = stack.peek();
            stack.push(i);
        }
        // 从左往右单调减栈 不能出栈的时候栈顶就是当前元素左侧最近的大于当前元素的节点
        stack = new Stack();
        stack.push(-1);
        for(int i = 0;i<len;i++)
        {
            while(stack.peek()!=-1&&nums[stack.peek()]<=nums[i])
            {
                stack.pop();
            }
            maxleft[i] = stack.peek();
            stack.push(i);
        }
        // 从右往左单调增栈 不能出栈的时候栈顶就是当前元素右侧最近的大于当前元素的节点
        stack = new Stack();
        stack.push(len);
        for(int i=len-1;i>-1;i--)
        {
            while(stack.peek()!=len&&nums[stack.peek()]<nums[i])
            {
                stack.pop();
            }
            maxright[i] = stack.peek();
            stack.push(i);
        }
//        for(int i = 0;i<len;i++)
//        {
//            while(stack.peek()!=len&&nums[stack.peek()]<nums[i])
//            {
//                maxright[stack.pop()]=i;
//            }
//            stack.push(i);
//        }
        for(int i = 0;i<len;i++)
        {
            ans+=(long)nums[i]*(i-maxleft[i])*(maxright[i]-i);
            ans-=(long)nums[i]*(i-minleft[i])*(minright[i]-i);
        }
        return ans;
    }
}
