package com.guy7cc.abclib4j.graph;

import com.guy7cc.abclib4j.util.IOUtil;

import java.util.ArrayList;
import java.util.List;

public class MinimumGraph {
    private int size;
    private List<Integer>[] e;

    public MinimumGraph(int size){
        this.size = size;
        this.e = new List[size];
        for (int i = 0; i < size; i++) {
            this.e[i] = new ArrayList<>();
        }
    }

    public static MinimumGraph directed(int size, int edgeNum){
        MinimumGraph graph = new MinimumGraph(size);
        for (int i = 0; i < edgeNum; i++) {
            int from = IOUtil.i() - 1;
            int to = IOUtil.i() - 1;
            graph.addEdge(from, to);
        }
        return graph;
    }

    public static MinimumGraph undirected(int size, int edgeNum){
        MinimumGraph graph = new MinimumGraph(size);
        for (int i = 0; i < edgeNum; i++) {
            int from = IOUtil.i() - 1;
            int to = IOUtil.i() - 1;
            graph.addEdge(from, to);
            graph.addEdge(to, from);
        }
        return graph;
    }

    public void addEdge(int from, int to){
        e[from].add(to);
    }

    public List<Integer> getEdges(int vertex){
        return e[vertex];
    }

    public int size(){
        return size;
    }
}
