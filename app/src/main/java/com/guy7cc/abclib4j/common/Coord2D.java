package com.guy7cc.abclib4j.common;

public class Coord2D {
    private final int v0;
    private final int v1;

    public Coord2D(int v0, int v1){
        this.v0 = v0;
        this.v1 = v1;
    }

    public static Coord2D xy(int x, int y){
        return new Coord2D(x, y);
    }

    public static Coord2D lr(int l, int r){
        return new Coord2D(l, r);
    }

    public int x(){
        return v0;
    }

    public int y(){
        return v1;
    }

    public int l(){
        return v0;
    }

    public int r(){
        return v1;
    }
}
