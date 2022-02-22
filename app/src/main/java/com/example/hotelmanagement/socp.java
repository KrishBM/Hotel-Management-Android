package com.example.hotelmanagement;

import android.content.Context;
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
            socket = IO.socket(context.getString(R.string.base_so));
            socket.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
