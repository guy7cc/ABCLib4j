package com.guy7cc.abclib4j.graph;

import java.util.*;

public class Graphs {
    public static List<List<Integer>> connectedVertices(MinimumGraph graph){
        boolean[] visited = new boolean[graph.size()];
        int min = 0;
        List<List<Integer>> list = new ArrayList<>();
        while(min < graph.size()){
            List<Integer> connected = new ArrayList<>();
            connected.add(min);
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(min);
            visited[min] = true;
            while(!queue.isEmpty()){
                int v = queue.poll();
                for(int to : graph.getEdges(v)){
                    if(!visited[to]) {
                        connected.add(to);
                        queue.add(to);
                        visited[to] = true;
                    }
                }
            }
            list.add(connected);
            while(min < graph.size() && visited[min]) min++;
        }
        return list;
    }

    public static BFSResult bfs(MinimumGraph graph, int start){
        int[] cost = new int[graph.size()];
        int maxCost = 0;
        int maxCostVertex = start;
        Arrays.fill(cost, Integer.MAX_VALUE);
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        cost[start] = 0;
        while(!queue.isEmpty()){
            int from = queue.poll();
            List<Integer> edges = graph.getEdges(from);
            for(int to : edges){
                if(cost[to] > cost[from] + 1){
                    cost[to] = cost[from] + 1;
                    queue.add(to);
                    if(cost[to] > maxCost){
                        maxCost = cost[to];
                        maxCostVertex = to;
                    }
                }
            }
        }
        return new BFSResult(cost, maxCost, maxCostVertex);
    }

    public static <V extends Vertex, E extends Edge & Weighted> DijkstraResult dijkstra(DecoratedGraph<V, E> graph, int start){
        long[] costs = new long[graph.size()];
        Arrays.fill(costs, Long.MAX_VALUE);
        Queue<CostIndex> queue = new PriorityQueue<>(Comparator.comparingLong(ci -> ci.cost));
        queue.add(new CostIndex(0, start));
        costs[start] = 0;
        while(!queue.isEmpty()){
            CostIndex ci = queue.poll();
            if(costs[ci.index] != ci.cost) continue;
            Collection<E> edgeHolders = graph.getEdges(ci.index);
            for(E cost : edgeHolders){
                if(costs[cost.to()] > costs[cost.from()] + cost.getWeight()){
                    costs[cost.to()] = costs[cost.from()] + cost.getWeight();
                    queue.add(new CostIndex(costs[cost.from()] + cost.getWeight(), cost.to()));
                }
            }
        }
        long maxCost = 0;
        int maxCostVertex = start;
        for(int i = 0; i < costs.length; i++){
            if(costs[i] > maxCost){
                maxCost = costs[i];
                maxCostVertex = i;
            }
        }
        return new DijkstraResult(costs, maxCost, maxCostVertex);
    }

    private record CostIndex(long cost, int index){

    }
}
