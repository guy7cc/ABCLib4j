package com.guy7cc.abclib4j.enumerate;

import com.guy7cc.abclib4j.mod.ModLong;

public class Bit {
    public final int bit;
    public final int length;

    public Bit(int bit, int length){
        this.bit = bit;
        this.length = length;
    }

    public long size(){
        return ModLong.of(bit).pow(length).value;
    }

    public void forEach(IndexedBitConsumer consumer){
        long size = size();
        for (long gi = 0; gi < size; gi++) {
            long gii = gi;
            for (int i = 0; i < length; i++) {
                int bit = (int)(gii % this.bit);
                consumer.accept(bit, i, gi);
                gii /= this.bit;
            }
        }
    }

    @FunctionalInterface
    public interface IndexedBitConsumer {
        void accept(int bit, int bitPos, long gindex);
    }
}
