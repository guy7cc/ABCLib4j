package com.guy7cc.abclib4j.graph;

public class NoOpVertex implements Vertex {
    private VertexInfo vertexInfo;

    @Override
    public VertexInfo getInfo() {
        return this.vertexInfo;
    }

    @Override
    public void setInfo(VertexInfo vertexInfo) {
        this.vertexInfo = vertexInfo;
    }
}
