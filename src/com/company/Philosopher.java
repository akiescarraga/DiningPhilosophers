package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class Philosopher implements Runnable {
    State hlp;
    Chopstick left, right;
    int id;
    int minTime = 2000;
    int maxTime = 6000;

    public Philosopher(State hlp, Chopstick l, Chopstick r, int id) {
        this.hlp = hlp;
        this.left = l;
        this.right = r;
        this.id = id;
    }

    private void eat() {
        try {
            sleep();
            System.out.println("Philosopher " + this.id + " is eating.");
        } catch (Exception e) {
        }
    }

    private void think() {
        try {
            sleep();
            System.out.println("Philosopher " + this.id + " is thinking.");
        } catch (Exception e) {
        }
    }

    private void sleep() {
        int duration = ThreadLocalRandom.current().nextInt(minTime, maxTime);
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e){
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void run() {
        while(true) {
            hlp.pickupChopsticks(this.id, this.left, this.right);
            eat();
            hlp.releaseChopsticks(this.id, this.left, this.right);
            think();
        }
    }
}
