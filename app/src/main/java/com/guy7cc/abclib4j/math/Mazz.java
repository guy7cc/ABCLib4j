package com.guy7cc.abclib4j.math;

public class Mazz {
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
}
