package com.itamarbernardo.requisitionhttp;

import java.io.Serializable;

/**
 * Created by ANAFLAVIA on 13/12/2016.
 */

public class SensorAnswer implements Serializable{

    private static final long serialVersionUID = 1L;

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
