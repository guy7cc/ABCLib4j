package com.guy7cc.abclib4j.enumerate;

public class Kelleher {
    public final int N;
    private long[] dp;

    public Kelleher(int N){
        this.N = N;
    }

    public long size(){
        if(dp == null){
            dp = new long[N + 1];
            dp[0] = 1;

            for (int m = 1; m <= N; m++) {
                long total = 0;
                for (int k = 1; ; k++) {
                    int g1 = (3 * k * k - k) / 2;
                    int g2 = (3 * k * k + k) / 2;
                    if (g1 > m && g2 > m) break;

                    int sign = (k % 2 == 0) ? -1 : 1;

                    if (g1 <= m) total += sign * dp[m - g1];
                    if (g2 <= m) total += sign * dp[m - g2];
                }
                dp[m] = total;
            }
        }
        return dp[N];
    }

    public void forEach(IndexedSequenceConsumer consumer){
        int[] a = new int[N];
        int i = 0;
        int k = 0;
        a[k] = N;
        while (true) {
            consumer.accept(a, i);
            i++;
            int rem = 0;
            while (k >= 0 && a[k] == 1) {
                rem += a[k];
                k--;
            }
            if (k < 0) return;
            a[k]--;
            rem++;
            while (rem > a[k]) {
                a[k+1] = a[k];
                rem -= a[k];
                k++;
            }
            a[k+1] = rem;
            k++;
        }
    }

    @FunctionalInterface
    public interface IndexedSequenceConsumer {
        void accept(int[] seq, int index);
    }
}
