package com.guy7cc.abclib4j.graph;

public class Cost extends NoOpEdge {
    private int cost;

    protected Cost(int cost) {
        this.cost = cost;
    }

    public static Cost of(int cost){
        return new Cost(cost);
    }

    public int getCost(){
        return this.cost;
    }
}
