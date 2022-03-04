package com.example.itjuana.views;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itjuana.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShipmentView extends AppCompatActivity {
    String shipmentAddress;
    String ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment_view);
        Intent intent = getIntent();
        shipmentAddress = intent.getStringExtra("shipmentAddress");
        ss = intent.getStringExtra("ss");
        updateViewsData();
    }

    void updateViewsData() {
        TextView shipmentTextView = (TextView) findViewById(R.id.shipment_address);
        TextView ssTextView = (TextView) findViewById(R.id.ss);
        shipmentTextView.setText(shipmentAddress);
        ssTextView.setText(ss);
    }

}