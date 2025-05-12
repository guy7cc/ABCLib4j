package com.guy7cc.abclib4j.data;

public class FenwickTree {
    public final int size;
    private final long[] arr;

    public FenwickTree(int size){
        this.size = size;
        this.arr = new long[size];
    }

    public void add(int p, long x){
        p++;
        while(p <= size){
            arr[p - 1] += x;
            p += p & -p;
        }
    }

    public long sum(int l, int r){
        return sum(r) - sum(l);
    }

    private long sum(int r){
        long sum = 0;
        while(r > 0){
            sum += arr[r - 1];
            r -= r & -r;
        }
        return sum;
    }
}
