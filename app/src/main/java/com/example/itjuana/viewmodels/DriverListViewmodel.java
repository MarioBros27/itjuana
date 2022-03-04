package com.example.itjuana.viewmodels;

import android.content.Context;

import com.example.itjuana.models.vo.Address;
import com.example.itjuana.models.vo.Driver;
import com.example.itjuana.models.DataHelper;
import com.example.itjuana.models.GenericDAO;
import com.example.itjuana.models.HungarianAlgorithm;

import java.util.ArrayList;

public class DriverListViewmodel {
    public Driver[] fetchListData(Context context, String fileName) {

        String jsonFileString = DataHelper.getJsonFromAssets(context, fileName);
        String[] driversStrings = DataHelper.getArrayFromJSON("drivers", jsonFileString);
        String[] shipmentsStrings = DataHelper.getArrayFromJSON("shipments", jsonFileString);
        GenericDAO genericDAO = new GenericDAO();
        Driver[] drivers = genericDAO.getDrivers(driversStrings);
        Address[] addresses = genericDAO.getAddresses(shipmentsStrings);
        double[][] initialMatrix = getInitialMatrix(addresses, drivers);
        double[][] resultsMatrix = HungarianAlgorithm.hgAlgorithmAssignments(initialMatrix,
                "max");
        assignShipmentsToDrivers(drivers, addresses, initialMatrix, resultsMatrix);
        return drivers;
    }

    private void assignShipmentsToDrivers(Driver[] drivers, Address[] addresses,
                                          double[][] initialMatrix, double[][] resultsMatrix) {
        for (int i = 0; i < drivers.length; i++) {
            int addressIndex = i;
            int driverIndex = (int) resultsMatrix[i][1];
            double ss = initialMatrix[addressIndex][driverIndex];
            Driver driver = drivers[driverIndex];
            Address address = addresses[addressIndex];
            driver.ss = ss;
            driver.shipmentAddress = address;
        }

    }

    private double[][] getInitialMatrix(Address[] addresses, Driver[] drivers) {
        double[][] matrix = new double[drivers.length][addresses.length];
        for (int i = 0; i < addresses.length; i++) {
            for (int j = 0; j < drivers.length; j++) {
                Address address = addresses[i];
                Driver driver = drivers[j];
                double ss = 0.0;
                //Requirements 1 and 2
                if (address.streetLength % 2 == 0) {
                    ss = driver.nameLength * 1.5;
                } else {
                    ss = driver.nameLength * 1.0;
                }
                //Third requirement
                for (int addressFactor : address.factors) {
                    if (isIn(addressFactor, driver.factors)) {
                        ss = ss * 1.5;
                        break;
                    }
                }
                matrix[i][j] = ss;

            }
        }
        return matrix;
    }


    private Boolean isIn(int n, ArrayList<Integer> list) {

        for (int i = 0; i < list.size(); i++) {
            if (n == list.get(i)) {
                return true;
            }
        }
        return false;
    }
}
