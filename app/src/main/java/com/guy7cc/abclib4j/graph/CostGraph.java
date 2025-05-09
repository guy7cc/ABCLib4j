package com.guy7cc.abclib4j.graph;

import java.util.function.Supplier;

public class CostGraph extends DecoratedGraph<Vertex, Cost>{
    public CostGraph(int size) {
        super(size, Vertex::new);
    }
}
