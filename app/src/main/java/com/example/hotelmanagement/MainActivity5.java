package com.example.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity5 extends AppCompatActivity
{
    // get instances of AutoCompleteTextView and TextView
    AutoCompleteTextView itemNA,qtyNA;

    String items[] = {
            "Schezwan Noodles",
            "Schezwan Fried Rice",
            "Paneer Fried Rice",
            "Paneer Manchurian Dry",
            "Veg Momos",
            "Momos Chutney",
            "Veg Spring Rolls"
    };
    String qtys[]={
            "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        itemNA = findViewById(R.id.itemNA);
        qtyNA=findViewById(R.id.qtyNA);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,qtys);

        itemNA.setAdapter(adapter1);
        qtyNA.setAdapter(adapter2);
        qtyNA.setText("1");
        itemNA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();

                // create Toast with user selected value
                Toast.makeText(getApplicationContext(), "Item: \t" + item, Toast.LENGTH_LONG).show();

                // set user selected value to the TextView
                itemNA.setText(item);
            }
        });
        qtyNA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String qty = adapterView.getItemAtPosition(i).toString();

                // create Toast with user selected value
                Toast.makeText(getApplicationContext(), "Quantity: \t" + qty, Toast.LENGTH_LONG).show();

                // set user selected value to the TextView
                qtyNA.setText(qty);
            }
        });
    }
}