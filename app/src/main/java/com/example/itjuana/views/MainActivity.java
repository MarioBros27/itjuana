package com.example.itjuana.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import com.example.itjuana.R;
import com.example.itjuana.models.vo.Driver;
import com.example.itjuana.viewmodels.TableViewmodel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TableViewmodel optimizationHelper = new TableViewmodel();
        Driver[] drivers = optimizationHelper.fetchTableData(getApplicationContext(), "data.json");
        for(Driver driver: drivers){
            Log.v("new", driver.name +" "+driver.shipmentAddress.address);
        }
    }


}