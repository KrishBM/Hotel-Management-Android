package com.example.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity4 extends AppCompatActivity {

    Button b1;
    Socket socket;
    TextInputEditText TIET1,TIET2;
    String name,number;
    TextView T2;
//    String Tno="1",Tid="1",Tstate="Busy";   TODO:inicial input
    String Tno,Tid,Tstate;

//    {
//        try {
//            socket = IO.socket(getString(R.string.base_url));
//            socket.connect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

//        socket.connect();
//        Log.d("5555555555555", String.valueOf(socket));

        b1=findViewById(R.id.b1);
        T2=findViewById(R.id.T2);
        TIET1=findViewById(R.id.TIET1);
        TIET2=findViewById(R.id.TIET2);

        if (getIntent().hasExtra("T_data")){
        ArrayList<String> T_data=getIntent().getExtras().getStringArrayList("T_data");
        Tno= T_data.get(0);
        Tid= T_data.get(1);
        Tstate= T_data.get(2);
        T2.setText(Tno);
        }



        b1.setOnClickListener(view -> {
            name = Objects.requireNonNull(TIET1.getText()).toString();
            number = TIET2.getText().toString();
            if(TextUtils.isEmpty(name))
            {
                Toast.makeText(getApplicationContext(), "Please enter at least name..!!", Toast.LENGTH_LONG).show();
            }
            else
            {
                if(number.length()!=10 && number.length()!=0){
                    Toast.makeText(getApplicationContext(), "Please enter a valid number..!!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Name: " + name + "\n" + "Number: " + number, Toast.LENGTH_LONG).show();

                    String url=getString(R.string.base_url)+"/customer/addCustomer";
//                        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//
//                            }
//                        },
                    Map<String, String> postParam= new HashMap<>();
                    postParam.put("name",name);
                    if(number.length()==0){
                        postParam.put("mobileNo"," ");
                    }
                    else{
                        postParam.put("mobileNo",number);
                    }
                    postParam.put("tableNo",Tno);


                    JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                            url, new JSONObject(postParam),
                            response -> {
//                                        Log.d(TAG, response.toString());
//                                        msgResponse.setText(response.toString());
//                                        hideProgressDialog();
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
                    RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
                    queue.add(jsonObjReq);

                    Map<String, String> cTableState= new HashMap<>();
                    cTableState.put("tableNo",Tno);
                    cTableState.put("status","Busy");

//                    socket.emit("change_table_status", new JSONObject(cTableState));
//                    socket.on("listenStatus",listionS);
                    socp ss=socp.getInstance(this);
                    ss.getSocket().emit("change_table_status", new JSONObject(cTableState));
                    ss.getSocket().on("listenStatus",listionS);

                    Intent i = new Intent(getApplicationContext(),MainActivity5.class);

                    ArrayList<String> C_data= new ArrayList<>();
                    C_data.add(Tno);
                    C_data.add(Tid);
                    C_data.add(Tstate);
                    C_data.add(name);
                    C_data.add(number);
                    i.putStringArrayListExtra("C_data", C_data);
                    startActivity(i);
                }
            }
        });
    }
    private final Emitter.Listener listionS = args -> {


//                JSONObject data = (JSONObject) args[0];
////                    Log.d("5555555555", String.valueOf(data));
//                String lTno;
//                String lTstatus;
//                try {
//                    lTno = data.getString("tableNo");
//                    lTstatus = data.getString("status");
//
////                        Intent i1 = new Intent(getApplicationContext(),MainActivity3.class);
////
////                        ArrayList<String> S_busy=new ArrayList<String>();
////                        S_busy.add(Tno);
////                        S_busy.add(Tid);
////                        S_busy.add(Tstate);
////                        S_busy.add(name);
////                        S_busy.add(number);
////                        i1.putStringArrayListExtra("S_data", S_busy);
////                        startActivity(i1);
////                    Log.d("5555555555",lTno);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

            };
}