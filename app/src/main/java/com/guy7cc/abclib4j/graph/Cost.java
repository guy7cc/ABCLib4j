package com.guy7cc.abclib4j.graph;

public class Cost extends Edge implements Weighted {
    private long weight;

    public Cost(long weight){
        this.weight = weight;
    }

    @Override
    public long getWeight() {
        return this.weight;
    }
}
