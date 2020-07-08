package com.example.demo.util;

import java.util.List;

public class CircularList<T> {
    private List<T> list;
    private int counter = 0;

    public CircularList(List<T> list) {this.list = list;}

    // list에서 왜 getOne...?
    public synchronized T getOne() {
        if (counter >= list.size()) {
            counter = 0;
        }
        return list.get(counter++);
    }
}
