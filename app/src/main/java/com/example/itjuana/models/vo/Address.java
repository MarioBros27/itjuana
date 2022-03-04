package com.example.itjuana.models.vo;

import java.util.ArrayList;

public class Address {
    public String address;
    public int streetLength;
    public ArrayList<Integer> factors;

    public Address(String address, int streetLength, ArrayList<Integer> factors) {
        this.address = address;
        this.streetLength = streetLength;
        this.factors = factors;
    }
}
