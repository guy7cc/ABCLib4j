package com.guy7cc.abclib4j.mod;

public class ModLong {
    public static long mod = 988244353L;

    public long value;

    private ModLong(long value) {
        this.value = parse(value);
    }

    public static ModLong of(long value) {
        return new ModLong(value);
    }

    public ModLong add(ModLong other) {
        value = parse(value + other.value);
        return this;
    }

    public ModLong sub(ModLong other) {
        value = parse(value - other.value);
        return this;
    }

    public ModLong mul(ModLong other) {
        value = parse(value * other.value);
        return this;
    }

    public ModLong div(ModLong other) {
        value = parse(value * inv(other.value, mod));
        return this;
    }

    public static long inv(long a, long m) {
        long b = m, u = 1, v = 0;
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
        u %= m;
        if (u < 0) u += m;
        return u;
    }

    public ModLong pow(long n) {
        long a = value;
        long res = 1;
        while (n > 0) {
            if ((n & 1) > 0) res = res * a % mod;
            a = a * a % mod;
            n >>= 1;
        }
        return ModLong.of(res);
    }

    public static long parse(long value){
        return value >= 0 ? value % mod : value % mod + mod;
    }
}
