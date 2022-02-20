package com.example.hotelmanagement;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class socp {
    private static socp socc=null;

    public Socket getSocket() {
        return socket;
    }

    public static socp getInstance(Context context){

        if(socc==null){
            socc=new socp(context);
        }
        return socc;
    }
    private Socket socket;
    private socp (Context context)

    {
        try {
            Log.d("878787878787","socpm");
            Log.d("87878787", "socp: "+String.valueOf(R.string.base_so));
            socket = IO.socket(context.getString(R.string.base_so));
            socket.connect();
            Log.d("878787878787","socpm");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
