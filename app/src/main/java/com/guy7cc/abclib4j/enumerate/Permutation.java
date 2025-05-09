package com.guy7cc.abclib4j.enumerate;

import java.util.function.Consumer;

public class Permutation {
    public final int N;

    public Permutation(int N){
        this.N = N;
    }

    public void forEach(Consumer<int[]> consumer){
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i;
        }
        do {
            consumer.accept(arr);
        } while (Permutation.next(arr));
    }

    public static boolean next(int[] array) {
        int n = array.length;

        for (int i = n - 2; i >= 0; i--) {
            if (array[i] < array[i + 1]) {
                int j = n - 1;
                while (array[i] >= array[j]) {
                    j--;
                }
                swap(array, i, j);
                reverse(array, i + 1, n - 1);
                return true;
            }
        }

        reverse(array, 0, n - 1);
        return false;
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void reverse(int[] array, int from, int to) {
        while (from < to) {
            swap(array, from++, to--);
        }
    }
}
