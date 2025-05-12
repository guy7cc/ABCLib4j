package com.guy7cc.abclib4j.mod;

public class ModLong {
    public static final int MOD998244353 = 998244353;

    public static long mod = MOD998244353;
    public final long value;

    private ModLong(long value) {
        this.value = mod(value);
    }

    public static ModLong of(long value) {
        return new ModLong(value);
    }

    public static long mod(long value){
        return value >= 0 ? value % mod : value % mod + mod;
    }

    public ModLong add(ModLong other) {
        return ModLong.of(this.value + other.value);
    }

    public ModLong add(long value){
        return ModLong.of(this.value + value);
    }

    public ModLong sub(ModLong other) {
        return ModLong.of(this.value - other.value);
    }

    public ModLong sub(long value){
        return sub(this.value - value);
    }

    public ModLong mul(ModLong other) {
        return ModLong.of(this.value * other.value);
    }

    public ModLong mul(long value){
        return ModLong.of(this.value * value);
    }

    public ModLong div(ModLong other) {
        return ModLong.of(this.value * inv(other.value));
    }

    public ModLong div(long value){
        return ModLong.of(this.value * inv(value));
    }

    public static long inv(long a) {
        long b = mod, u = 1, v = 0;
        while (b > 0) {
            long t = a / b;

            a -= t * b;
            long tmp = a;
            a = b;
            b = tmp;

            u -= t * v;
            tmp = u;
            u = v;
            v = tmp;
        }
        u %= mod;
        if (u < 0) u += mod;
        return u;
    }

    public ModLong pow(long n) {
        ModLong a = ModLong.of(value);
        ModLong res = ModLong.of(1);
        while (n > 0) {
            if ((n & 1) > 0) res = res.mul(a);
            a = a.mul(a);
            n >>= 1;
        }
        return res;
    }
}
