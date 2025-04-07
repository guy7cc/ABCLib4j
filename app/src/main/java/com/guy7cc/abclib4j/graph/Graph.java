package com.guy7cc.abclib4j.graph;

import com.guy7cc.abclib4j.common.Sized;

public interface Graph<V, E> extends Iterable<V>, Sized {
    V getVertex(int index);

    E getEdge(int from, int to);

    E[] getEdgeAllFrom(int from);

    E[] getEdgeAllTo(int to);

    E setEdge(int from, int to, E e);
}
