package com.guy7cc.abclib4j.util;

import java.lang.reflect.Array;

public final class ArrayUtil {
    @SuppressWarnings("unchecked")
    public static <T> T[] newArray(final int length, final T[] type) {
        return (T[]) Array.newInstance(type.getClass().getComponentType(), length);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[][] newArray(final int y, final int x, final T[] type) {
        return (T[][]) Array.newInstance(type.getClass().getComponentType(), y, x);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[][][] newArray(final int z, final int y, final int x, final T[] type) {
        return (T[][][]) Array.newInstance(type.getClass().getComponentType(), z, y, x);
    }
}
