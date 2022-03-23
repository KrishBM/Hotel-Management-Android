package com.example.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        String url_signin=getString(R.string.base_url)+"/waiter/signin";

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject signin_data=new JSONObject();
                try {
                    signin_data.put("username",unet.getText().toString().trim());
                    signin_data.put("password",pwet.getText().toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url_signin, signin_data,
                        response -> {
                            try {
                                String W_id=response.getJSONArray("message").getJSONObject(0).getString("_id");

                                ArrayList<String> W_data=new ArrayList<String>();
                                W_data.add(W_id);
                                Toast.makeText(getApplicationContext(),"Login successfully",Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(getApplicationContext(),MainActivity3.class);
                                i.putStringArrayListExtra("W_data", W_data);
                                startActivity(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                    },

                        error -> {
                        Toast.makeText(getApplicationContext(),"Please enter a valid username and password...!!",Toast.LENGTH_SHORT).show();
                    })
                {
                    @Override
                    public Map<String,String> getHeaders() {
                        Map<String,String> params= new HashMap<>();
                        params.put("key","value");
//                        Log.d("969696969696",params.get(0));
                        return params;
                    }
                };
                RequestQueue queue1 = Volley.newRequestQueue(getApplicationContext());
                queue1.add(jsonObjectRequest);
            }
        });
    }
}