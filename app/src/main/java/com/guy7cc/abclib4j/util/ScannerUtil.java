package com.guy7cc.abclib4j.util;

import java.util.Scanner;

public final class ScannerUtil {
    public static final Scanner scanner = new Scanner(System.in);

    private ScannerUtil(){

    }

    public static boolean b(){
        return scanner.nextBoolean();
    }

    public static int i(){
        return scanner.nextInt();
    }

    public static long l(){
        return scanner.nextLong();
    }

    public static float f(){
        return scanner.nextFloat();
    }

    public static double d(){
        return scanner.nextDouble();
    }

    public static String s(){
        return scanner.next();
    }

    public static void close(){
        scanner.close();
    }
}
