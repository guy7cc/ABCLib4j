package com.guy7cc.abclib4j.enumerate;

import java.util.*;
import java.util.function.Consumer;

public class Bell {
    public final int N;
    private long[][] num;
    private Stack<Set<Integer>> stack;

    public Bell(int N){
        this.N = N;
    }

    public long size(){
        if(num == null){
            num = new long[N][];
            num[0] = new long[1];
            num[0][0] = 1;
            for (int i = 1; i < N; i++) {
                num[i] = new long[i + 1];
                num[i][0] = num[i - 1][i - 1];
                for (int j = 1; j < i + 1; j++) {
                    num[i][j] = num[i][j - 1] + num[i - 1][j - 1];
                }
            }
        }
        return num[N - 1][N - 1];
    }

    public void forEach(Consumer<Stack<Set<Integer>>> consumer){
        stack = new Stack<>();
        forEach(consumer, 0);
    }

    private void forEach(Consumer<Stack<Set<Integer>>> consumer, int index){
        for (int i = 0; i <= stack.size(); i++) {
            if(index == N) consumer.accept(stack);
            else if(i < stack.size()) {
                stack.get(i).add(index);
                forEach(consumer, index + 1);
                stack.get(i).remove(index);
            } else {
                Set<Integer> newGroup = new TreeSet<>();
                newGroup.add(index);
                stack.push(newGroup);
                forEach(consumer, index + 1);
                stack.pop();
            }
        }
    }
}
