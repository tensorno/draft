package model;

public class dichotomy {
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;  //使用二分法验证答案是否在矩阵中存在k个小于等于该数的数字
        int n = matrix[0].length;
        int left = matrix[0][0];
        int right = matrix[m-1][n-1];
        while(left<=right)
        {
            int middle =(right-left)/2+left;
            if(countless(matrix,middle)==k)
            {
                right = middle-1;
            }
            else if(countless(matrix,middle)>k)
            {
                right = middle-1;
            }
            else
            {
                left = middle+1;
            }
        }
        return left;
    }

    int countless(int[][] matrix,int num)  //寻找矩阵中小于等于的num的数字数量
    {
        //1.二分
//        int sum = 0;
//        int m = matrix.length;
//        int n = matrix[0].length;
//        for(int i = 0;i<m;i++)
//        {
//            int left = 0;
//            int right = n-1;
//            while(left<=right)
//            {
//                int middle =(right-left)/2+left;
//                if(num<matrix[i][middle])
//                {
//                    right = middle-1;
//                }
//                else if(num>matrix[i][middle])
//                {
//                    left = middle+1;
//                }
//                else
//                {
//                    left=middle+1;
//                }
//            }
//            sum+=left;
//        }
//        return sum;
        //从矩阵的左下角开始寻找，如果遇到比num小列向上，否则sum加列数
        int sum = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int j = n-1;
        int i = 0;
        while(j>=0&&i<m)
        {
            if(matrix[i][j]<=num)
            {
                sum+=j+1;
                i++;
            }
            else
            {
                j--;
            }
        }
        return sum;
    }
}
