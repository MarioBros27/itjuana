package com.example.itjuana.models;

import com.example.itjuana.models.vo.Address;
import com.example.itjuana.models.vo.Driver;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenericDAO {
    private int getNameLength(String name) {
        ArrayList<String> allMatches = new ArrayList<String>();
        Matcher m = Pattern.compile("[a-zA-Z\\.]+")
                .matcher(name);
        while (m.find()) {
            allMatches.add(m.group());
        }
        int counter = 0;
        for (String st : allMatches) {
            counter += st.length();
        }

        return counter;
    }

    private ArrayList<Integer> getFactors(int n) {
        ArrayList<Integer> factors = new ArrayList<Integer>();
        for (int i = n; i > 1; i--) {
            if (n % i == 0) {
                factors.add(i);
            }
        }
        return factors;
    }

    public Driver[] getDrivers(String[] driverNames) {
        Driver[] drivers = new Driver[driverNames.length];
        for (int i = 0; i < driverNames.length; i++) {
            String name = driverNames[i];
            int nameLength = getNameLength(name);
            ArrayList<Integer> factors = getFactors(nameLength);
            Driver driver = new Driver(name, nameLength, factors);
            drivers[i] = driver;
        }
        return drivers;
    }

    public Address[] getAddresses(String[] addressesNames) {
        Address[] addresses = new Address[addressesNames.length];
        for (int i = 0; i < addresses.length; i++) {
            String name = addressesNames[i];
            int nameLength = getNameLength(name);
            ArrayList<Integer> factors = getFactors(nameLength);
            Address address = new Address(name, nameLength, factors);
            Driver driver = new Driver(name, nameLength, factors);
            addresses[i] = address;
        }
        return addresses;
    }
}
