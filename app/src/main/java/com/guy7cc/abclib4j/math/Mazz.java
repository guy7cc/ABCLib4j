package com.guy7cc.abclib4j.math;

import com.guy7cc.abclib4j.data.FenwickTree;

public class Mazz {
    public static int min(int[] a){
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            min = Math.min(min, a[i]);
        }
        return min;
    }

    public static long min(long[] a){
        long min = Long.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            min = Math.min(min, a[i]);
        }
        return min;
    }

    public static int max(int[] a){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            max = Math.max(max, a[i]);
        }
        return max;
    }

    public static long max(long[] a){
        long max = Long.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            max = Math.max(max, a[i]);
        }
        return max;
    }

    public static int[] freq(int[] a, int length){
        int[] freq = new int[length];
        for (int i = 0; i < length; i++) {
            freq[a[i]]++;
        }
        return freq;
    }

    public static long[] prefixSum(int[] a){
        long[] sum = new long[a.length + 1];
        long v = 0;
        for (int i = 0; i < a.length; i++) {
            v += a[i];
            sum[i + 1] = v;
        }
        return sum;
    }

    public static long[] prefixSum(long[] a){
        long[] sum = new long[a.length + 1];
        long v = 0;
        for (int i = 0; i < a.length; i++) {
            v += a[i];
            sum[i + 1] = v;
        }
        return sum;
    }

    public static long gcd(long a, long b){
        if(b == 0) return a;
        else return gcd(b, a % b);
    }

    public static long gcd(long a, long b, long... more){
        long gcd = gcd(a, b);
        for(long m : more){
            gcd = gcd(gcd, m);
        }
        return gcd;
    }

    public static boolean isPrime(long a){
        long sqrt = (long)Math.ceil(Math.sqrt(a));
        for (int i = 2; i <= sqrt; i++) {
            if(a % i == 0) return false;
        }
        return true;
    }

    public static long inversionNum(int[] a){
        int max = max(a) + 1;
        FenwickTree ft = new FenwickTree(max);
        long sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += ft.sum(a[i], max);
            ft.add(a[i], 1);
        }
        return sum;
    }
}
