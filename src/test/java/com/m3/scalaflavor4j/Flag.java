package com.m3.scalaflavor4j;

public class Flag {

    private boolean value = false;

    public boolean getValue() {
        return value;
    }

    public void toggle() {
        value = !value;
    }

}
