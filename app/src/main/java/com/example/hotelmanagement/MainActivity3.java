package com.example.hotelmanagement;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

//    GridView GridT;


    RecyclerView r_v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        r_v=findViewById(R.id.r_v);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        List<Tables> list = new ArrayList<>();
        list.add(new Tables("1","tid","Free"));
        list.add(new Tables("2","tid","Busy"));
        list.add(new Tables("3","tid","Payment"));
        list.add(new Tables("4","tid","Free"));
        list.add(new Tables("5","tid","Busy"));
        list.add(new Tables("6","tid","Free"));
        list.add(new Tables("7","tid","Busy"));
        list.add(new Tables("8","tid","Payment"));
        list.add(new Tables("9","tid","Busy"));
        list.add(new Tables("10","tid","Free"));
        list.add(new Tables("11","tid","Busy"));
        list.add(new Tables("12","tid","Payment"));
        list.add(new Tables("13","tid","Free"));
        list.add(new Tables("14","tid","Busy"));
        r_v.setLayoutManager(gridLayoutManager);
        r_v.setAdapter(new BooksListAdapter(list,this));
    }
}
