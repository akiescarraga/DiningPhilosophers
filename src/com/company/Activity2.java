package com.company;

public class Activity2 {

    public static void main(String[] args) {

        Chopstick[] chopstick = new Chopstick[5];
        Philosopher[] philospher = new Philosopher[5];
        State hlp = new State();

        for (int i = 0; i < 5; i++) {

            chopstick[i] = new Chopstick();

        }
        for (int i = 0; i < 5; i++) {
            philospher[i] = new Philosopher(hlp, chopstick[i], chopstick[(i + 4) % 5],i);
            new Thread(philospher[i]).start();

        }

    }
}
