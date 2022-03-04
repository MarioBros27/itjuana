package com.example.itjuana.models;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    static void printMatrix(double[][] matrix) {
        for (double[] column : matrix) {
            String str = "";
            for (double row : column) {
                str = str + " " + row;
            }
            Log.v("Matrix", str);
        }
    }
}