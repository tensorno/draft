package model;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Union_Value {
    static int maxn = 100100;
    static int parent[] = new int[maxn];
    static long dist[] = new long[maxn];
    static void solve() throws Exception {
        String nums[] = in.readLine().split(" ");
        int n = Integer.parseInt(nums[0]);
        int a = Integer.parseInt(nums[1]);
        int b = Integer.parseInt(nums[2]);
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < a; i++) {
            String s[] = in.readLine().split(" ");
            int l = Integer.parseInt(s[0]);
            int r = Integer.parseInt(s[1]);
            long temp = Long.parseLong(s[2]);
            merge(l - 1, r, temp);
        }
        for (int i = 0; i < b; i++) {
            String s[] = in.readLine().split(" ");
            int l = Integer.parseInt(s[0]);
            int r = Integer.parseInt(s[1]);
            int x = find(l - 1);
            int y = find(r);
            if (x != y) System.out.println("UNKNOWN");
            else System.out.println(dist[r] - dist[l - 1]);
        }
    }

    static int find(int x) {
        if (parent[x] != x) {
            int temp = find(parent[x]);
            dist[x] += dist[parent[x]];
            parent[x] = temp;
        }
        return parent[x];
    }

    static void merge(int x, int y, long k) {
        int one = find(x);
        int two = find(y);
        dist[two] = k - dist[y] + dist[x];
        parent[two] = one;
    }

    static Scanner sc = new Scanner(System.in);
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
}
