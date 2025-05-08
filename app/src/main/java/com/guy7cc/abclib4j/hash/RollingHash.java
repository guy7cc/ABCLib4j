package com.guy7cc.abclib4j.hash;

public class RollingHash {
    public static final long MASK30 = (1L << 30) - 1;
    public static final long MASK31 = (1L << 31) - 1;
    public static final long MASK61 = (1L << 61) - 1;
    public static final long MOD = MASK61;
    public static final long BASE = 10007;

    public final String str;

    private final long[] hashed;
    private final long[] power;

    public RollingHash(String s) {
        this.str = s;
        int n = s.length();
        hashed = new long[n + 1];
        power = new long[n + 1];
        power[0] = 1;
        for (int i = 0; i < n; i++) {
            power[i + 1] = mul(power[i], BASE);
            long h = mul(hashed[i], BASE) + s.charAt(i);
            hashed[i + 1] = (h >= MOD ? h - MOD : h);
        }
    }

    public static long mul(long a, long b){
        long au = a >> 31;
        long ad = a & MASK31;
        long bu = b >> 31;
        long bd = b & MASK31;
        long mid = ad * bu + au * bd;
        long midu = mid >> 30;
        long midd = mid & MASK30;
        return mod(au * bu * 2 + midu + (midd << 31) + ad * bd);
    }

    public static long mod(long x) {
        long xu = x >> 61;
        long xd = x & MASK61;
        long res = xu + xd;
        if (res >= MOD) res -= MOD;
        return res;
    }

    public long get(int l, int r) {
        long x = mul(hashed[l], power[r - l]);
        long ret = hashed[r] - (int)x;
        if (ret < 0) ret += MOD;
        return ret;
    }

    public long connect(int h1, int h2, int h2len) {
        long x = mul(h1, power[h2len]) + h2;
        return x % MOD;
    }

    public int lcp(RollingHash other, int l1, int r1, int l2, int r2) {
        int max = Math.min(r1 - l1, r2 - l2);
        int low = 0, high = max + 1;
        while (high - low > 1) {
            int mid = (low + high) >>> 1;
            if (this.get(l1, l1 + mid) == other.get(l2, l2 + mid)) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
