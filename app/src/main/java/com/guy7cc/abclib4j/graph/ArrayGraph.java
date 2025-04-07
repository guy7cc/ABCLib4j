package com.guy7cc.abclib4j.graph;

import com.guy7cc.abclib4j.util.ArrayUtil;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayGraph<V, E> implements Graph<V, E> {
    private final int N;
    private final V[] v;
    private final E[][] e;

    public ArrayGraph(int N, V[] v, E[] e){
        if(N <= 0) throw new IllegalArgumentException("Graph size N must be more than 0.");
        this.N = N;
        this.v = ArrayUtil.newArray(N, v);
        this.e = ArrayUtil.newArray(N, N, e);
    }

    @Override
    public V getVertex(int index) {
        return v[index];
    }

    @Override
    public E getEdge(int from, int to) {
        return e[from][to];
    }

    @Override
    public E[] getEdgeAllFrom(int from) {
        return e[from];
    }

    @Override
    public E[] getEdgeAllTo(int to) {
        E[] array = ArrayUtil.newArray(N, e[0]);
        for(int i = 0; i < N; i++){
            array[i] = e[i][to];
        }
        return array;
    }

    @Override
    public E setEdge(int from, int to, E e) {
        E previous = this.e[from][to];
        this.e[from][to] = e;
        return previous;
    }

    @Override
    public int getSize() {
        return N;
    }

    @Override
    public Iterator<V> iterator() {
        return Arrays.stream(v).iterator();
    }
}
