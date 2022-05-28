package com.company;

public class Philosopher implements Runnable {
    State hlp;
    Chopstick left, right;
    int id;

    public Philosopher(State hlp, Chopstick l, Chopstick r, int id) {
        this.hlp = hlp;
        this.left = l;
        this.right = r;
        this.id = id;
    }

    private void eat() {
        try {
            Thread.sleep(2000);
            System.out.println("Philosopher " + this.id + " is eating.");
        } catch (Exception e) {
        }
    }

    private void think() {
        try {
            Thread.sleep(2000);
            System.out.println("Philosopher " + this.id + " is thinking.");
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        while(true) {
            hlp.pickupChopsticks(this.id, this.left, this.right);
            System.out.println("Philosopher " + this.id + " acquired its left and right chopsticks.");
            eat();
            hlp.releaseChopsticks(this.id, this.left, this.right);
            System.out.println("Philosopher " + this.id + " released its left and right chopsticks.");
            think();
        }
    }
}
