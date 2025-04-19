package com.guy7cc.abclib4j.graph;

public class TreeProperty {
    private MinimumGraph graph;
    private DecoratedGraph<?, ?> decoratedGraph;
    private DecoratedGraph<?, ? extends Weighted> weightedGraph;

    private Type type;

    private long diameter;
    private int end1;
    private int end2;

    private int[] costFromEnd1Int;
    private int[] costFromEnd2Int;
    private long[] costFromEnd1Long;
    private long[] costFromEnd2Long;

    public TreeProperty(MinimumGraph graph) {
        this.type = Type.MINIMUM;
        this.graph = graph;
        this.diameter = -1;
        this.end1 = -1;
        this.end2 = -1;
    }

    public TreeProperty(DecoratedGraph<?, ? extends Weighted> graph){
        this.type = Type.WEIGHTED;
        this.weightedGraph = graph;
        this.diameter = -1;
        this.end1 = -1;
        this.end2 = -1;
    }

    public void calcDiameter(){
        switch(type){
            case MINIMUM:
                BFSResult result1 = Graphs.bfs(graph, 0);
                end1 = result1.maxCostVertex();
                BFSResult result2 = Graphs.bfs(graph, end1);
                costFromEnd1Int = result2.cost();
                end2 = result2.maxCostVertex();
                diameter = result2.maxCost();
                break;
            case DECORATED:
                // not implemented yet
                break;
            case WEIGHTED:
                DijkstraResult result5 = Graphs.dijkstra(weightedGraph, 0);
                end1 = result5.maxCostVertex();
                DijkstraResult result6 = Graphs.dijkstra(weightedGraph, end1);
                costFromEnd1Long = result6.cost();
                end2 = result6.maxCostVertex();
                diameter = result6.maxCost();
                break;
        }
    }

    public long diameter(){
        if(diameter == -1) throw new IllegalStateException("Call calcDiameter() before this method");
        return diameter;
    }

    public int end1(){
        if(diameter == -1) throw new IllegalStateException("Call calcDiameter() before this method");
        return end1;
    }

    public int end2(){
        if(diameter == -1) throw new IllegalStateException("Call calcDiameter() before this method");
        return end2;
    }

    public void calcCostFromEnds(){
        switch(type){
            case MINIMUM:
                costFromEnd2Int = Graphs.bfs(graph, end2).cost();
                break;
            case DECORATED:
                // not implemented yet
                break;
            case WEIGHTED:
                costFromEnd2Long = Graphs.dijkstra(weightedGraph, end2).cost();
                break;
        }
    }

    public long maxCostFrom(int vertex){
        if(costFromEnd1Int != null && costFromEnd2Int != null) {
            return Math.max(costFromEnd1Int[vertex], costFromEnd2Int[vertex]);
        } else if(costFromEnd1Long != null && costFromEnd2Long != null) {
            return Math.max(costFromEnd1Long[vertex], costFromEnd2Long[vertex]);
        }
        throw new IllegalStateException("Call calcCostFromEnds() before this method");
    }

    private enum Type{
        MINIMUM,
        DECORATED,
        WEIGHTED
    }
}
