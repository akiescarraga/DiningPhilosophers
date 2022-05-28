package com.company;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class State {

    Lock mutex = new ReentrantLock();
    Condition[] condition = new Condition[5];
    String[] state = new String[5];
    int[] ph_id = new int[5];

    public State() {
        for (int i = 0; i < 5; i++) {

            ph_id[i] = i;
            state[i] = "O";

            condition[i] = mutex.newCondition();
        }
    }

    public void setState(int id, String s) {

        state[id] = s;
    }

    void printState(int id) {

        StringBuffer line = new StringBuffer();

        for (int i = 0; i < 5; i++){
            line.append(state[i] + " ");
        }
        System.out.println(line + "(" + id + ")");
    }

    public void pickupChopsticks(int id, Chopstick left, Chopstick right) {

        mutex.lock();

        try {
            setState(id, "o");
            while (!left.getAvailability() || !right.getAvailability()) {
                condition[id].await();
            }

            left.setAvailability(false);
            right.setAvailability(false);

            setState(id, "X");
            printState(id);

            System.out.println("Philosopher " + id + " acquired its left and right chopsticks.");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mutex.unlock();
        }
    }

    public void releaseChopsticks(int id, Chopstick left, Chopstick right) {

        mutex.lock();
        setState(id, "O");

        left.setAvailability(true);
        right.setAvailability(true);

        condition[(id + 1) % 5].signalAll();
        condition[(id + 4) % 5].signalAll();
        printState(id);

        System.out.println("Philosopher " + id + " released its left and right chopsticks.");

        mutex.unlock();

    }








}
