package com.guy7cc.abclib4j.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class ArrayGraph<V extends Vertex, E extends Edge> implements Graph<V, E> {
    private int vNum;
    private Object[] viToV;
    private Object[] viToE;
    private List<E> eiToE;

    public ArrayGraph(int vNum, Supplier<V> defaultValue){
        this(vNum, 16, defaultValue);
    }

    public ArrayGraph(int vNum, int eNum, Supplier<V> defaultValue){
        this.vNum = vNum;
        viToV = new Object[vNum];
        for (int i = 0; i < vNum; i++) {
            V v = defaultValue.get();
            v.setInfo(new VertexInfo(i));
            viToV[i] = v;
        }
        viToE = new Object[vNum];
        for (int i = 0; i < vNum; i++) {
            viToE[i] = new ArrayList<E>();
        }
        eiToE = new ArrayList<>(eNum);
    }

    @Override
    public V vertex(int index) {
        return (V) viToV[index];
    }

    @Override
    public E edge(int index) {
        return eiToE.get(index);
    }

    @Override
    public Collection<E> fromVertex(int index) {
        return (List<E>) viToE[index];
    }

    @Override
    public void addEdge(int from, int to, E edge) {
        edge.setInfo(new EdgeInfo(from, to, eiToE.size()));
        List<E> edges = (List<E>) viToE[edge.getInfo().from()];
        edges.add(edge);
        eiToE.add(edge);
    }

    @Override
    public Collection<V> vertices() {
        return Arrays.asList((V[]) viToV);
    }

    @Override
    public Collection<E> edges() {
        return eiToE;
    }

    @Override
    public int size() {
        return vNum;
    }
}
