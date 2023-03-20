package model;

import java.util.Random;

public class Sort {
    void quicksort(int nums[],int left,int right)
    {
        int i = left;
        int j = right;
        if(left>right)
        {
            return;
        }
        int temp = nums[left];
        while(i<j)
        {
            while(i<j&&nums[j]>=temp) j--;
            while(i<j&&nums[i]<=temp) i++;
            if (i<j)
            {
                int t = nums[j];
                nums[j] = nums[i];
                nums[i] = t;
            }
        }
        nums[left] = nums[i];
        nums[i] = temp;
        quicksort(nums,left,i - 1);
        quicksort(nums,i + 1,right);
    }

    //快速选择第k个元素
    Random ran = new Random();

    void randomselect(int nums[],int left,int right)
    {
        int i = ran.nextInt(right-left+1)+left;
        swap(nums,i,left);
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    void quickselect(int nums[],int left,int right,int k)
    {
        randomselect(nums,left,right);
        int i = left;
        int j = right;
        if(left>right)
        {
            return;
        }
        int temp = nums[left];
        while(i<j)
        {
            while(i<j&&nums[j]>=temp) j--;
            while(i<j&&nums[i]<=temp) i++;
            if (i<j)
            {
                swap(nums,i,j);
            }
        }
        nums[left] = nums[i];
        nums[i] = temp;
        if(i>k) quickselect(nums,left,i-1,k);
        else if(i<k) quickselect(nums,i+1,right,k);
    }





    int arr[];
    public void mergeSort(int[] nums, int l, int r) {
        //定义 mergeSort(nums, l, r) 函数表示对 nums 数组里 [l,r][l,r] 的部分进行排序，整个函数流程如下：
        //
        //递归调用函数 mergeSort(nums, l, mid) 对 nums 数组里 [l,\textit{mid}][l,mid] 部分进行排序。
        //
        //递归调用函数 mergeSort(nums, mid + 1, r) 对 nums 数组里 [\textit{mid}+1,r][mid+1,r] 部分进行排序。
        //
        //此时 nums 数组里 [l,\textit{mid}][l,mid] 和 [\textit{mid}+1,r][mid+1,r] 两个区间已经有序，我们对两个有序区间线性归并即可使 nums 数组里 [l,r][l,r] 的部分有序。
        if(l>=r) return;
        int middle = l+(r-l)/2;
        mergeSort(nums,l,middle);
        mergeSort(nums,middle+1,r);
        int begin1 = l;
        int begin2 = middle+1;
        int index = 0;
        while(begin1<=middle&&begin2<=r)
        {
            if (nums[begin1]>nums[begin2])
            {
                arr[index] = nums[begin2];
                begin2++;
            }
            else
            {
                arr[index] = nums[begin1];
                begin1++;
            }
            index++;
        }
        while(begin2<=r)
        {
            arr[index] = nums[begin2];
            begin2++;
            index++;
        }
        while(begin1<=middle)
        {
            arr[index] = nums[begin1];
            begin1++;
            index++;
        }
        for(int i = 0;i<r-l+1;i++)
        {
            nums[i+l] = arr[i];
        }
    }
}
