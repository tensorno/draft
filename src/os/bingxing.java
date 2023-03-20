package os;

import java.io.*;
import java.util.concurrent.CountDownLatch;

public class bingxing extends Thread{
    private double[][] A;
    private double[][] B;
    private int index;
    private int gap;
    private double[][] result;
    private CountDownLatch countDownLatch;

    public bingxing(double[][] A, double[][] B, int index, int gap, double[][] result, CountDownLatch countDownLatch) {
        this.A = A;
        this.B = B;
        this.index = index;
        this.gap = gap;
        this.result = result;
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        // TODO Auto-generated method stub
        for (int i = index * gap; i < (index + 1) * gap&&i< A.length; i++)
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < B.length; k++)
                    result[i][j] += A[i][k] * B[k][j];
            }
        // 线程数减1
        countDownLatch.countDown();
    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        long startTime;
        long endTime;
        int len = 1024;
        double A[][] = new double[len][len];
        double B[][] = new double[len][len];
        double ans[][] = new double[len][len];
        String filename1 = "E:\\苏州大学合\\操作系统\\M128B\\M"+len+"A.txt";
        String filename2 = "E:\\苏州大学合\\操作系统\\M128B\\M"+len+"B.txt";
        int threadNum;
        for(threadNum = 1;threadNum<=20;threadNum++)
        {
            int gap = len / threadNum;
            CountDownLatch countDownLatch = new CountDownLatch(threadNum);
            try {
                FileInputStream fis1 = new FileInputStream(filename1);
                FileInputStream fis2 = new FileInputStream(filename2);
                InputStreamReader isr1 = new InputStreamReader(fis1);
                InputStreamReader isr2 = new InputStreamReader(fis2);
                BufferedReader bufferedReader1 = new BufferedReader(isr1);
                BufferedReader bufferedReader2 = new BufferedReader(isr2);
                String temp;
                int row = 0;

                while ((temp = bufferedReader1.readLine()) != null) {
                    int col = 0;
                    String s[] = temp.split(" ");
                    for (String i : s) {
                        A[row][col] = Double.parseDouble(i);
                        col++;
                    }
                    row++;
                }
                row = 0;
                while ((temp = bufferedReader2.readLine()) != null) {
                    int col = 0;
                    String s[] = temp.split(" ");
                    for (String i : s) {
                        A[row][col] = Double.parseDouble(i);
                        col++;
                    }
                    row++;
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            startTime = System.currentTimeMillis();
            for (int i = 0; i <= threadNum; i++) {
                bingxing ct = new bingxing(A, B, i, gap, ans, countDownLatch);
                ct.start();
            }
            countDownLatch.await();
            endTime = System.currentTimeMillis();
            System.out.println("线程数量为"+threadNum+"  "+"并行时间:"+(endTime-startTime)/60+"s");
        }
        //子线程的分片计算间隔



    }
}
