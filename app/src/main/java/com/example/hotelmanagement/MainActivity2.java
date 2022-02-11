package com.example.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity2 extends AppCompatActivity {

    TextInputEditText pwet,unet;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        pwet=findViewById(R.id.pwet);
        unet=findViewById(R.id.unet);
        b=findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(unet.getText().toString().trim().equals("krish") && pwet.getText().toString().trim().equals("krish")){
                    Intent i = new Intent(getApplicationContext(),MainActivity3.class);
                    startActivity(i);
                }
                else{
                    if(!(unet.getText().toString().trim().equals("krish"))){
                        Toast.makeText(getApplicationContext(), "Enter Valid User Name", Toast.LENGTH_SHORT).show();
                    }
                    if(!(pwet.getText().toString().trim().equals("krish"))){
                        Toast.makeText(getApplicationContext(), "Enter Valid Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}