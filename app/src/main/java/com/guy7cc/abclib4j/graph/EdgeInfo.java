package com.guy7cc.abclib4j.graph;

import com.guy7cc.abclib4j.common.Indexed;

public record EdgeInfo(int from, int to, int index) implements Indexed{
    @Override
    public int getIndex() {
        return this.index;
    }
}
