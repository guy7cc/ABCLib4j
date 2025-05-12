package com.guy7cc.abclib4j.mod;

public class ModCombination {
    public static int mod = ModLong.MOD998244353;

    public final int max;
    public final long[] fac;
    public final long[] finv;
    public final long[] inv;

    public ModCombination(int max){
        this.max = max;
        fac = new long[max];
        finv = new long[max];
        inv = new long[max];
        fac[0] = fac[1] = 1;
        finv[0] = finv[1] = 1;
        inv[1] = 1;
        for (int i = 2; i < max; i++){
            fac[i] = fac[i - 1] * i % mod;
            inv[i] = mod - inv[mod%i] * (mod / i) % mod;
            finv[i] = finv[i - 1] * inv[i] % mod;
        }
    }

    public long get(int n, int k){
        if (n < k) return 0;
        if (n < 0 || k < 0) return 0;
        return fac[n] * (finv[k] * finv[n - k] % mod) % mod;
    }

    public long C(int n, int k){
        return get(n, k);
    }

    public long H(int n, int k){
        return get(n + k - 1, k);
    }
}
