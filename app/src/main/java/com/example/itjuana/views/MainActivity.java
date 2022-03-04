package com.example.itjuana.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.itjuana.R;
import com.example.itjuana.models.vo.Driver;
import com.example.itjuana.viewmodels.DriverListViewmodel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ListView driversListView;
    Driver[] drivers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        driversListView = (ListView) findViewById(R.id.drivers_list_view);
        displayDrivers();
        driversListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Driver driver = drivers[position];
                String shipmentAddress = driver.shipmentAddress.address;
                String ss = String.valueOf(driver.ss);
                displayShipmentView(shipmentAddress, ss);
            }
        });
    }

    void displayDrivers() {
        DriverListViewmodel driverListViewmodel = new DriverListViewmodel();
        drivers = driverListViewmodel.fetchListData(getApplicationContext(), "data.json");
        for(Driver driver: drivers){
            Log.v("driver", "driver: "+ driver.name+ " Address: "+driver.shipmentAddress.address);
        }
        TextView fetchingTextView = (TextView) findViewById(R.id.fetching_text_view);
        fetchingTextView.setVisibility(View.GONE);
        List<String> driversNames = new ArrayList<>();
        for (Driver driver : drivers) {
            driversNames.add(driver.name);
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, driversNames);
        driversListView.setAdapter(arrayAdapter);
        driversListView.setVisibility(View.VISIBLE);
    }

    void displayShipmentView(String address, String ss) {
        Intent intent = new Intent(this, ShipmentView.class);
        intent.putExtra("shipmentAddress", address);
        intent.putExtra("ss", ss);
        startActivity(intent);
    }

}