package com.example.itjuana.models;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DataHelper {
    //This function fetches the json file and returns it as a string
    public static String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonString;
    }

    //This function returns the strings of the arrays on the JSON file
    public static String[] getArrayFromJSON(String kind, String data) {
        try {
            JSONObject obj = new JSONObject(data);
            JSONArray array = obj.getJSONArray(kind);
            String[] elements = new String[array.length()];
            for (int i = 0; i < array.length(); i++) {
                elements[i] = array.getString(i);
            }
            return elements;
        } catch (Exception e) {
            Log.v("Exception", e.toString());
        }
        return new String[1];
    }
}
