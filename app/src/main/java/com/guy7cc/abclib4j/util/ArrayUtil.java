package com.guy7cc.abclib4j.util;

import java.lang.reflect.Array;

public final class ArrayUtil {
    @SuppressWarnings("unchecked")
    public static <T> T[] newArray(final int length, final T[] type) {
        return (T[]) Array.newInstance(type.getClass().getComponentType(), length);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[][] newArray(final int n, final int m, final T[] type) {
        return (T[][]) Array.newInstance(type.getClass().getComponentType(), n, m);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[][][] newArray(final int n, final int m, final int l, final T[] type) {
        return (T[][][]) Array.newInstance(type.getClass().getComponentType(), n, m, l);
    }
}
