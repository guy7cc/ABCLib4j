package com.guy7cc.abclib4j.graph;

import java.util.*;


public class Graphs {
    public static <V extends Vertex, E extends Edge> List<List<Integer>> connectedVertices(Graph<V, E> graph){
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
                Collection<E> edges = graph.fromVertex(v);
                for(Edge edge : edges){
                    EdgeInfo info = edge.getInfo();
                    if(!visited[info.to()]) {
                        connected.add(info.to());
                        queue.add(info.to());
                        visited[info.to()] = true;
                    }
                }
            }
            list.add(connected);
            while(visited[min]) min++;
        }
        return list;
    }

    public static <V extends Vertex, E extends Edge> int[] bfs(Graph<V, E> graph, int start){
        int[] cost = new int[graph.size()];
        Arrays.fill(cost, Integer.MAX_VALUE);
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        cost[start] = 0;
        while(!queue.isEmpty()){
            int from = queue.poll();
            Collection<E> edges = graph.fromVertex(from);
            for(E edge : edges){
                int to = edge.getInfo().to();
                if(cost[to] > cost[from] + 1){
                    cost[to] = cost[from] + 1;
                    queue.add(to);
                }
            }
        }
        return cost;
    }

    public static <V extends Vertex, E extends Cost> long[] dijkstra(Graph<V, E> graph, int start){
        long[] costs = new long[graph.size()];
        Arrays.fill(costs, Long.MAX_VALUE);
        Queue<CostIndex> queue = new PriorityQueue<>(Comparator.comparingLong(ci -> ci.cost));
        queue.add(new CostIndex(0, start));
        costs[start] = 0;
        while(!queue.isEmpty()){
            CostIndex ci = queue.poll();
            if(costs[ci.index] != ci.cost) continue;
            Collection<E> edgeHolders = graph.fromVertex(ci.index);
            for(E cost : edgeHolders){
                EdgeInfo edgeInfo = cost.getInfo();
                if(costs[edgeInfo.to()] > costs[edgeInfo.from()] + cost.getCost()){
                    costs[edgeInfo.to()] = costs[edgeInfo.from()] + cost.getCost();
                    queue.add(new CostIndex(costs[edgeInfo.from()] + cost.getCost(), edgeInfo.to()));
                }
            }
        }
        return costs;
    }

    private record CostIndex(long cost, int index){

    }
}
