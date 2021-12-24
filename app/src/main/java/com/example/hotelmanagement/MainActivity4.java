package com.example.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity4 extends AppCompatActivity {
    Button b1;
    TextInputEditText TIET1,TIET2;
    String name,number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        b1=findViewById(R.id.b1);
        TIET1=findViewById(R.id.TIET1);
        TIET2=findViewById(R.id.TIET2);





        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = Objects.requireNonNull(TIET1.getText()).toString();
                number = Objects.requireNonNull(TIET2.getText()).toString();
                if(TextUtils.isEmpty(name))
                {
                    Toast.makeText(getApplicationContext(), "Please enter at least name..!!", Toast.LENGTH_LONG).show();
                }
                else{
                    if (TextUtils.isEmpty(number))
                    {
                        Toast.makeText(getApplicationContext(), "Name: " + name, Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        if(number.length()==10) {
                            Toast.makeText(getApplicationContext(), "Name: " + name + "\n" + "Number: " + number, Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(),MainActivity5.class);
                            startActivity(i);
                        }
                    }
                }
            }
        });
    }
}