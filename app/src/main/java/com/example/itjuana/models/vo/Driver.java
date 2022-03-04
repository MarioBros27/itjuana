package com.example.itjuana.models.vo;

import java.util.ArrayList;

public class Driver {
    public String name;
    public int nameLength;
    public double ss;
    public Address shipmentAddress;
    public ArrayList<Integer> factors;

    public Driver(String name, int nameLength, ArrayList<Integer> factors) {
        this.name = name;
        this.nameLength = nameLength;
        this.factors = factors;
        this.ss = 0;
        this.shipmentAddress = null;
    }

}
