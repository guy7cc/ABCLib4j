package com.guy7cc.abclib4j.graph;

public class NoOpEdge implements Edge {
    private EdgeInfo edgeInfo;

    @Override
    public EdgeInfo getInfo() {
        return edgeInfo;
    }

    @Override
    public void setInfo(EdgeInfo edgeInfo) {
        this.edgeInfo = edgeInfo;
    }
}
