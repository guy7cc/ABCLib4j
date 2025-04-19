package com.guy7cc.abclib4j.graph;

public class UnionFind {
    private final int[] parent;
    private final int[] height;

    public UnionFind(int N){
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        height = new int[N];
    }

    public int find(int i){
        if(parent[i] == i) return i;
        return parent[i] = find(parent[i]);
    }

    public boolean same(int i, int j){
        return find(i) == find(j);
    }

    public void unite(int i, int j){
        i = find(i);
        j = find(j);
        if(i == j) return;
        if(height[i] < height[j]) parent[i] = j;
        else{
            parent[j] = i;
            if(height[i] == height[j]) height[i]++;
        }
    }
}
