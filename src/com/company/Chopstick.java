package com.company;

public class Chopstick {
    private boolean isAvail;

    public Chopstick() {
        isAvail = true;
    }

    public boolean getAvailability() {
        return isAvail;
    }

    public void setAvailability(boolean cond) {
        isAvail = cond;
    }
}
