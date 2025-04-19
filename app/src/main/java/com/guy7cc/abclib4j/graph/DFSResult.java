package com.guy7cc.abclib4j.graph;

import java.util.Objects;

public final class DFSResult {
    private final int[] cost;
    private int maxCost;
    private int maxCostVertex;

    public DFSResult(int[] cost, int maxCost, int maxCostVertex) {
        this.cost = cost;
        this.maxCost = maxCost;
        this.maxCostVertex = maxCostVertex;
    }

    public int[] cost() {
        return cost;
    }

    public int maxCost() {
        return maxCost;
    }

    public int maxCostVertex() {
        return maxCostVertex;
    }

    public void setMaxCost(int maxCost) {
        this.maxCost = maxCost;
    }

    public void setMaxCostVertex(int maxCostVertex) {
        this.maxCostVertex = maxCostVertex;
    }
}
