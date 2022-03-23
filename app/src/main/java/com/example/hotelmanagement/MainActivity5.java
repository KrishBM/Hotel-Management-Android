package com.example.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.socket.emitter.Emitter;

public class MainActivity5 extends AppCompatActivity
{
    // get instances of AutoCompleteTextView and TextView
    AutoCompleteTextView itemNA,qtyNA,itemCty;
    TextInputEditText itemNT;
    ImageButton add_item;
    MaterialButton plc_odr;
    RecyclerView t_i;
    TextView C_Name,T2;
    String Tno="1";
    String Tid="1";
    String Tstate="Busy";
    String Cname,Wid="6215bcc739834430f4e38528";
    String Cno="";
    String s2="";
    int flag=0;
    String itemNames,itemQtys,itemNotes,itemStatus,orderId;
    ArrayList<String> items2=new ArrayList<>();
    ArrayList<String> Cty2=new ArrayList<>();
    Map<String, String> itemWithId= new HashMap<>();
    private SwipeRefreshLayout swipeContainer2;
    String[] qtys ={
            "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"
    };
    List<Order> Olist = new ArrayList<>();//todo
    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);//todo
    OrderListAdapter orderListAdapter=new OrderListAdapter(Olist, this, position -> {
        if(Olist.get(position).getI_State().equals("Order")){
            Map<String, String> deletOrder= new HashMap<>();
            deletOrder.put("_id",Olist.get(position).getO_id());
            socp ss=socp.getInstance(this);
            ss.getSocket().emit("delete_order", new JSONObject(deletOrder));
        }
        Olist.remove(position);
        t_i.getAdapter().notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),"Item Removed..!",Toast.LENGTH_SHORT).show();
    });

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

//        swipeContainer2 = findViewById(R.id.swipeContainer2);
//        swipeContainer2.setOnRefreshListener(this::refreshOrder);
//        swipeContainer2.setColorSchemeResources(android.R.color.holo_orange_light);//todo

        socp ss1=socp.getInstance(this);
        ss1.getSocket().on("listen_order_status",listionOS);

        plc_odr=findViewById(R.id.plc_odr);
        //API
        String url_allItem=getString(R.string.base_url)+"/item/allItem";
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET,url_allItem,null, response -> {
            try {
                JSONArray message_array=response.getJSONArray("message");
                //for items2 and Cty2
                int k=0;
                s2=s2.concat("{");
                int mal=message_array.length();
                for (int i = 0; i < mal; i++) {
                    int mvl=message_array.getJSONObject(i).getJSONArray("value").length();
                    Cty2.add(message_array.getJSONObject(i).getString("category"));
                    s2=s2.concat("\""+ Cty2.get(i) +"\": [");
                    for (int j = 0; j < mvl; j++) {
                        items2.add(message_array.getJSONObject(i).getJSONArray("value").getJSONObject(j).getString("name"));
                        itemWithId.put(items2.get(k),message_array.getJSONObject(i).getJSONArray("value").getJSONObject(j).getString("_id"));
                        s2=s2.concat("\""+ items2.get(k) +"\"");
                        if (j<mvl-1) {s2=s2.concat(",");}
                        k=k+1;
                    }
                    s2=s2.concat("]");
                    if (i<mal-1) {s2=s2.concat(",");}
                }
                s2=s2.concat("}");

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {

        }
        );
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(jor);

        //Data from another Activity
        if (getIntent().hasExtra("T_data")) {
            ArrayList<String> T_data = getIntent().getExtras().getStringArrayList("T_data");
            Tno = T_data.get(0);
            Tid = T_data.get(1);
            Tstate = T_data.get(2);
            Wid = T_data.get(3);
        }
        if (getIntent().hasExtra("C_data")) {
            ArrayList<String> C_data = getIntent().getExtras().getStringArrayList("C_data");
            Tno = C_data.get(0);
            Tid = C_data.get(1);
            Tstate = C_data.get(2);
            Cname = C_data.get(3);
            Cno = C_data.get(4);
            Wid = C_data.get(5);
        }
        T2=findViewById(R.id.T2);
        T2.setText(Tno);
        C_Name=findViewById(R.id.C_Name);
        C_Name.setText(Cname);

        t_i=findViewById(R.id.t_i);

        t_i.setLayoutManager(gridLayoutManager);

        itemNT=findViewById(R.id.itemNT);
        add_item=findViewById(R.id.add_item);
        add_item.setOnClickListener(view -> {
            String i_name=itemNA.getText().toString().trim();
            String i_qty=qtyNA.getText().toString().trim();
            String i_note=itemNT.getText().toString().trim();
            if(TextUtils.isEmpty(i_qty) || TextUtils.isEmpty(i_name)) {
                if(TextUtils.isEmpty(i_qty) && TextUtils.isEmpty(i_name)){
                    Toast.makeText(getApplicationContext(), "Please enter a item name and qty..!!", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(i_name)) {
                    Toast.makeText(getApplicationContext(), "Please enter a item name..!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please enter a item qty..!!", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Olist.add(new Order(Tno,Tid,i_name,i_qty,i_note,"Taking","61dfef6242b75ad557216a17"));
                t_i.getAdapter().notifyDataSetChanged();

                Toast.makeText(getApplicationContext(), "Item: "+i_name+"("+i_qty+") "+"added.", Toast.LENGTH_SHORT).show();
            }
            itemCty.setText("");
            itemNA.setText("");
            qtyNA.setText("");
            itemNT.setText("");
        });

        t_i.setAdapter(orderListAdapter);
        //AutoCompleteTextView
        itemNA = findViewById(R.id.itemNA);
        itemCty=findViewById(R.id.itemCty);
        qtyNA=findViewById(R.id.qtyNA);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, items2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,qtys);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, Cty2);


        itemNA.setAdapter(adapter1);
        qtyNA.setAdapter(adapter2);
        itemCty.setAdapter(adapter3);
        itemCty.setOnItemClickListener((adapterView, view, i, l) -> {

            String item = adapterView.getItemAtPosition(i).toString();

            JSONArray catItemArray = null;
            try {
                catItemArray = (new JSONObject(s2)).getJSONArray(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            List<String> itemList = new ArrayList<>();
            for(int j1=0; j1< catItemArray.length(); j1++){
                try {
                    itemList.add(catItemArray.getString(j1));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            int size = itemList.size();
            String[] itemArray = itemList.toArray(new String[size]);
            ArrayAdapter<String> adapter4 = new ArrayAdapter<>(MainActivity5.this, android.R.layout.simple_list_item_1, itemArray);
            itemNA.setAdapter(adapter4);
        });
        itemNA.setOnItemClickListener((adapterView, view, i, l) -> {
            String item = adapterView.getItemAtPosition(i).toString();
            itemNA.setText(item);
        });
        qtyNA.setOnItemClickListener((adapterView, view, i, l) -> {
            String qty = adapterView.getItemAtPosition(i).toString();
            qtyNA.setText(qty);
        });
        plc_odr.setOnClickListener(view -> {

            String url_addOrder =getString(R.string.base_url)+"/order/addOrder";
            JSONArray itemAll=new JSONArray();
            for (int j3 = 0; j3 < Olist.size(); j3++) {
                Map<String, String> itemOrder= new HashMap<>();
                if(String.valueOf(Olist.get(j3).I_State).equals("Taking")){
                    if(Olist.get(j3).I_Note.length()!=0){
                        itemOrder.put("extraDetail",String.valueOf(Olist.get(j3).I_Note));
                    }
                    itemOrder.put("item",itemWithId.get(String.valueOf(Olist.get(j3).I_Name)));
                    itemOrder.put("tableNo",String.valueOf(Olist.get(j3).T_no));
                    itemOrder.put("quantity",String.valueOf(Olist.get(j3).I_Qty));
                    itemOrder.put("waiter",Wid);   //TODO: waiter id change.
                    itemAll.put(new JSONObject(itemOrder));
                }
            }
            JSONObject orderall=new JSONObject();
            try {
                orderall.put("order",itemAll);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.POST,
                    url_addOrder, orderall,
                    response -> {
                        refreshOrder();
                    },
                    error -> {

                    })
            {
                @Override
                public Map<String,String> getHeaders() {
                    Map<String,String> params= new HashMap<>();
                    params.put("key","value");
                    return params;
                }
            };
            RequestQueue queue1 = Volley.newRequestQueue(getApplicationContext());
            queue1.add(jsonArrayRequest);

            Toast.makeText(getApplicationContext(),"Order place succsessfully...!!",Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),MainActivity3.class);
        startActivity(b);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshOrder();
    }

    public void refreshOrder(){
        Olist.removeAll(Olist);
        String url_tableNo=getString(R.string.base_url)+"/table/getTableNumber/"+Tno;

        JsonObjectRequest jorr = new JsonObjectRequest(Request.Method.GET,
                url_tableNo,
                null,
                response -> {
                    try {
                        C_Name.setText(response.getJSONObject("message").getJSONObject("customer").getString("name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("78787878",String.valueOf(response));
                    int oldOrderLength = 0;
                    try {
                        oldOrderLength = response.getJSONObject("message").getJSONArray("order").length();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    for (int i = 0; i < oldOrderLength; i++) {
                        try {
                            itemNames = response.getJSONObject("message").getJSONArray("order").getJSONObject(i).getJSONObject("item").getString("name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            itemQtys = response.getJSONObject("message").getJSONArray("order").getJSONObject(i).getString("quantity");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            itemNotes = response.getJSONObject("message").getJSONArray("order").getJSONObject(i).optString("extraDetail","");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            itemStatus = response.getJSONObject("message").getJSONArray("order").getJSONObject(i).getString("status");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            orderId = response.getJSONObject("message").getJSONArray("order").getJSONObject(i).getString("_id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Olist.add(new Order(Tno,Tid,itemNames,itemQtys,itemNotes,itemStatus,orderId));
                        t_i.getAdapter().notifyDataSetChanged();
                    }

                },
                error -> {

                }
        );
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(jorr);
        if(flag==0){
            flag=1;
        }
        else {
//            swipeContainer2.setRefreshing(false);//todo
        }
    }
    private final Emitter.Listener listionOS = args -> {
        refreshOrder();
    };
}