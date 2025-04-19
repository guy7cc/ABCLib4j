package com.guy7cc.abclib4j.graph;

public class Edge {
    private int from;
    private int to;

    public Edge(){

    }

    public void setInfo(int from, int to){
        this.from = from;
        this.to = to;
    }

    public int from(){
        return this.from;
    }

    public int to(){
        return this.to;
    }
}
