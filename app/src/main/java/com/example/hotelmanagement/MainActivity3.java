package com.example.hotelmanagement;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        refresh();
        swipeContainer1 = findViewById(R.id.swipeContainer1);
        swipeContainer1.setOnRefreshListener(this::refresh);
        swipeContainer1.setColorSchemeResources(android.R.color.holo_orange_light);

        socp ss=socp.getInstance(this);
        ss.getSocket().on("listenStatus",listionS);

    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    public void refresh(){

        String url=getString(R.string.base_url)+"/table/getAllLiveTable";

        JsonArrayRequest jor = new JsonArrayRequest(Request.Method.GET,url,null, response -> {

            TotleTable=response.length();

            r_v=findViewById(R.id.r_v);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            List<Tables> list = new ArrayList<>();
            for (int i = 1; i <= TotleTable; i++) {
                try {
                    tableNos=response.getJSONObject(i-1).getInt("tableNo");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    statuss=response.getJSONObject(i-1).getString("status");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    tableids=response.getJSONObject(i-1).getString("_id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                list.add(new Tables(String.valueOf(tableNos),tableids,String.valueOf(statuss)));
            }

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
        refresh();
    };
}
