package com.guy7cc.abclib4j;

import com.guy7cc.abclib4j.enumerate.Kelleher;

import java.util.Arrays;

import static com.guy7cc.abclib4j.util.IOUtil.*;

public class Main {
    public static void main(String[] args) {
        int N = i();
        int T = i();
        int M = i();
        int K = i();

        double[][] dp = new double[T + 1][T * M + 1];

        Kelleher en = new Kelleher(M);
        for (int turn = 0; turn < T; turn++) {
            for (int b = 0; b < (T - 1) * M; b++) {
                int finalTurn = turn;
                int finalB = b;
                en.forEach((a, i) -> {
                    for (int j = 0; j < a.length; j++) {
                        if(a[j] == 0) break;
                        dp[finalTurn + 1][finalB + a[j]] = Math.max(dp[finalTurn][]
                    }
                })
            }
        }
    }
}
