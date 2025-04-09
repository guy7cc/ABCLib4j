package com.guy7cc.abclib4j.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class IOUtil {
    public static final Scanner scanner = new Scanner(System.in);

    private IOUtil(){

    }

    public static boolean b(){
        return scanner.nextBoolean();
    }

    public static boolean[] ba(int N){
        boolean[] a = new boolean[N];
        for (int i = 0; i < N; i++) {
            a[i] = b();
        }
        return a;
    }

    public static List<Boolean> bl(int N){
        List<Boolean> l = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            l.add(b());
        }
        return l;
    }

    public static int i(){
        return scanner.nextInt();
    }

    public static int[] ia(int N){
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = i();
        }
        return a;
    }

    public static List<Integer> il(int N){
        List<Integer> l = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            l.add(i());
        }
        return l;
    }

    public static long l(){
        return scanner.nextLong();
    }

    public static long[] la(int N){
        long[] a = new long[N];
        for (int i = 0; i < N; i++) {
            a[i] = l();
        }
        return a;
    }

    public static List<Long> ll(int N){
        List<Long> l = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            l.add(l());
        }
        return l;
    }

    public static float f(){
        return scanner.nextFloat();
    }

    public static float[] fa(int N){
        float[] a = new float[N];
        for (int i = 0; i < N; i++) {
            a[i] = f();
        }
        return a;
    }

    public static List<Float> fl(int N){
        List<Float> l = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            l.add(f());
        }
        return l;
    }

    public static double d(){
        return scanner.nextDouble();
    }

    public static double[] da(int N){
        double[] a = new double[N];
        for (int i = 0; i < N; i++) {
            a[i] = d();
        }
        return a;
    }

    public static List<Double> dl(int N){
        List<Double> l = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            l.add(d());
        }
        return l;
    }

    public static String s(){
        return scanner.next();
    }

    public static String[] sa(int N){
        String[] a = new String[N];
        for (int i = 0; i < N; i++) {
            a[i] = s();
        }
        return a;
    }

    public static List<String> sl(int N){
        List<String> l = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            l.add(s());
        }
        return l;
    }

    public static void p(Object obj){
        System.out.print(obj);
    }

    public static void pl(Object obj){
        System.out.println(obj);
    }

    public static void close(){
        scanner.close();
    }
}
