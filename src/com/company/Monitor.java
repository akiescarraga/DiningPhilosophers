package com.company;

import java.util.concurrent.locks.*;

public class Monitor {

    private final ReentrantLock entLock;
    private final Condition self[];
    private int p_state[];

    public Monitor(int number){
        entLock = new ReentrantLock();
        self = new Condition[number];
        p_state = new int[number];

        for(int i=0; i<number; i++){
            self[i]=entLock.newCondition();
            p_state[i]=STATE.THINKING;
        }
    }

    void go(int philosopher){
        try {

            pickup(philosopher);
            putdown(philosopher);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void pickup(int philosopher) throws InterruptedException {
        entLock.lock();
        p_state[philosopher]=STATE.HUNGRY;
        System.out.println("Philosopher " + philosopher + " is hungry.\n");
        Test(philosopher);


        if(p_state[philosopher]!=STATE.EATING)
            self[philosopher].await();

        entLock.unlock();
    }

    void Test(int philosopher){
        if(p_state[Global.Left(philosopher)]!=STATE.EATING &&
                p_state[Global.Right(philosopher)]!=STATE.EATING &&
                p_state[philosopher]==STATE.HUNGRY){
            p_state[philosopher]=STATE.EATING;
            System.out.println("Philosopher " + philosopher + " is eating.\n");

            self[philosopher].signal();
        }
    }

    void putdown(int philosopher){

        entLock.lock();
        p_state[philosopher]=STATE.THINKING;
        System.out.println("Philosopher " + philosopher + " is thinking.\n");

        Test(Global.Left(philosopher));
        Test(Global.Right(philosopher));

        entLock.unlock();
    }




}
