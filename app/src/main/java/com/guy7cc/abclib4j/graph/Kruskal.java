package com.guy7cc.abclib4j.graph;

import java.util.Comparator;
import java.util.List;

public class Kruskal {
    public static Result getMinimumSpanningTree(int N, List<Cost> edges){
        UnionFind uf = new UnionFind(N);
        CostGraph graph = new CostGraph(N);
        edges.sort(Comparator.comparingLong(Cost::getWeight));
        long sum = 0;
        int edgeNum = 0;
        for (Cost edge : edges){
            if(edgeNum == N - 1) break;
            if(!uf.same(edge.from(), edge.to())){
                uf.unite(edge.from(), edge.to());
                sum += edge.getWeight();
                edgeNum++;
                graph.addEdge(edge);
            }
        }
        return new Result(graph, sum);
    }

    public record Result(CostGraph graph, long sum){
    }
}
