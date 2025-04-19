package com.guy7cc.abclib4j.graph;

import com.guy7cc.abclib4j.util.IOUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class DecoratedGraph<V extends Vertex, E extends Edge> {
    private int size;
    private List<V> v;
    private List<E>[] e;

    public DecoratedGraph(int size, Supplier<V> vertexFactory){
        this.size = size;
        this.v = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            V vertex = vertexFactory.get();
            vertex.setInfo(i);
            this.v.add(vertex);
        }
        this.e = new List[size];
        for (int i = 0; i < size; i++) {
            this.e[i] = new ArrayList<>();
        }
    }

    public static <V extends Vertex, E extends Edge> DecoratedGraph<V, E> directed(int size, Supplier<V> vertexFactory, int edgeNum, Supplier<E> edgeFactory){
        DecoratedGraph<V, E> graph = new DecoratedGraph<>(size, vertexFactory);
        for (int i = 0; i < edgeNum; i++) {
            int from = IOUtil.i() - 1;
            int to = IOUtil.i() - 1;
            E edge = edgeFactory.get();
            graph.addEdge(from, to, edge);
        }
        return graph;
    }

    public static <V extends Vertex, E extends Edge> DecoratedGraph<V, E> undirected(int size, Supplier<V> vertexFactory, int edgeNum, Supplier<E> edgeFactory){
        DecoratedGraph<V, E> graph = new DecoratedGraph<>(size, vertexFactory);
        for (int i = 0; i < edgeNum; i++) {
            int from = IOUtil.i() - 1;
            int to = IOUtil.i() - 1;
            E edge1 = edgeFactory.get();
            E edge2 = edgeFactory.get();
            graph.addEdge(from, to, edge1);
            graph.addEdge(to, from, edge2);
        }
        return graph;
    }

    public void addEdge(int from, int to, E edge){
        edge.setInfo(from, to);
        List<E> edges = e[from];
        edges.add(edge);
    }

    public V getVertex(int index){
        return v.get(index);
    }

    public List<E> getEdges(int vertex){
        return e[vertex];
    }

    public int size(){
        return size;
    }
}
