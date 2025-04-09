package com.guy7cc.abclib4j.graph;

import com.guy7cc.abclib4j.common.Sized;

import java.util.Collection;

public interface Graph<V extends Vertex, E extends Edge> extends Sized {
    V vertex(int index);

    E edge(int index);

    Collection<E> fromVertex(int index);

    void addEdge(int from, int to, E edge);

    Collection<V> vertices();

    Collection<E> edges();
}
