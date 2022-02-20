package com.example.hotelmanagement;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.socket.emitter.Emitter;


public class MainActivity3 extends AppCompatActivity {

    private SwipeRefreshLayout swipeContainer1;
    RecyclerView r_v;
    int TotleTable,tableNos;
    String statuss,tableids;
    int flag=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Log.d("878787878787","c3");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        refresh();
        swipeContainer1 = findViewById(R.id.swipeContainer1);
        swipeContainer1.setOnRefreshListener(this::refresh);
        swipeContainer1.setColorSchemeResources(android.R.color.holo_orange_light);

        socp ss=socp.getInstance(this);
//        Log.d("7878787878",ss.toString());
        ss.getSocket().on("listenStatus",listionS);
//        socket.on("listenStatus",listionS);

    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    public void refresh(){
//        Log.d("878787878787","r3");

        String url=getString(R.string.base_url)+"/table/getAllLiveTable";

        JsonArrayRequest jor = new JsonArrayRequest(Request.Method.GET,url,null, response -> {

            TotleTable=response.length();

            r_v=findViewById(R.id.r_v);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            List<Tables> list = new ArrayList<>();
            for (int i = 1; i <= TotleTable; i++) {
                try {
                    tableNos=response.getJSONObject(i-1).getInt("tableNo");
//                    System.out.println(tableNos);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    statuss=response.getJSONObject(i-1).getString("status");
//                    System.out.println(statuss);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    tableids=response.getJSONObject(i-1).getString("_id");
//                    System.out.println(tableids);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                list.add(new Tables(String.valueOf(tableNos),tableids,String.valueOf(statuss)));
            }

//            list.add(new Tables("1","tid","Free"));
//            list.add(new Tables("2","tid","Busy"));
//            list.add(new Tables("3","tid","Payment"));


            r_v.setLayoutManager(gridLayoutManager);
            r_v.setAdapter(new TableListAdapter(list,this));


        }, error -> {

        }
        );
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(jor);
        if(flag==0){
            flag=1;
        }
        else {
            swipeContainer1.setRefreshing(false);
        }

    }
    private final Emitter.Listener listionS = args -> {
//        Log.d("878787878787","socp");
        refresh();
//        Log.d("878787878787","em3");
        JSONObject data = (JSONObject) args[0];
//                    Log.d("5555555555", String.valueOf(data));
        String lTno;
        String lTstatus;
        try {
            lTno = data.getString("tableNo");
            lTstatus = data.getString("status");
//                Log.d("98989898989",lTstatus);
//                mTables.get(Integer.parseInt(lTno)-1).T_state=lTstatus;
//                Log.d("5555555555",mTables.get(Integer.parseInt(lTno)-1).T_state);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    };
}
