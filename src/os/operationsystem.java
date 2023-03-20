package os;

import java.io.*;
import java.util.concurrent.CountDownLatch;

public class operationsystem extends Thread{
    public static void main(String[] args) {
        String filename1 = "E:\\苏州大学合\\操作系统\\M128B\\M1024A.txt";
        String filename2 = "E:\\苏州大学合\\操作系统\\M128B\\M1024B.txt";
        int len = 1024;
        try {
            FileInputStream fis1 = new FileInputStream(filename1);
            FileInputStream fis2 = new FileInputStream(filename2);
            InputStreamReader isr1 = new InputStreamReader(fis1);
            InputStreamReader isr2 = new InputStreamReader(fis2);
            BufferedReader bufferedReader1 = new BufferedReader(isr1);
            BufferedReader bufferedReader2 = new BufferedReader(isr2);
            String temp;
            int row = 0;

            double temp1[][] = new double[len][len];
            double temp2[][] = new double[len][len];
            double[][] ans = new double[len][len];
            while ((temp = bufferedReader1.readLine()) != null) {
                int col = 0;
                String s[] = temp.split(" ");
                for (String i : s) {
                    temp1[row][col] = Double.parseDouble(i);
                    col++;
                }
                row++;
            }
            row = 0;
            while ((temp = bufferedReader2.readLine()) != null) {
                int col = 0;
                String s[] = temp.split(" ");
                for (String i : s) {
                    temp2[row][col] = Double.parseDouble(i);
                    col++;
                }
                row++;
            }
            double begintime = System.currentTimeMillis();
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    for (int k = 0; k < len; k++) {
                        ans[i][j] += temp1[i][j] * temp2[j][k];
                    }
                }
            }
            double endtime = System.currentTimeMillis();
            double time = endtime - begintime;
            System.out.println("串行时间："+time / 60 + "s");
            fis1.close();
            fis2.close();
            isr1.close();
            isr2.close();
            bufferedReader1.close();
            bufferedReader2.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
