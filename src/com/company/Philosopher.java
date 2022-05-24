package com.company;

public class Philosopher {
    Monitor monitor;
    int id;

    public Philosopher(Monitor monitor, int id) {
        this.monitor = monitor;
        this.id = id;
    }

    @Override
    public void run() {

    }
}
