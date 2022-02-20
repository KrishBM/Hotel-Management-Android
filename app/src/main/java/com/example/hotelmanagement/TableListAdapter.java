package com.example.hotelmanagement;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hotelmanagement.socp.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import static com.example.hotelmanagement.MainActivity3.*;
public class TableListAdapter extends RecyclerView.Adapter<TableListAdapter.MyViewHolder>{

    List<Tables> mTables;
    Context mContext;

//    private Socket socket;
//    {
//        try {
//            socket = IO.socket("http://13.126.70.189:3001");
//            socket.connect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public TableListAdapter(List<Tables> tables, Context context) {
        mTables = tables;
        mContext = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_table, parent, false);
        return new MyViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tables tables = mTables.get(position);
        holder.T_no.setText(tables.getT_no());


        if(tables.getT_state().equals("Free")){
            holder.S_color.setBackground(mContext.getDrawable(R.drawable.buttonborder2));
            holder.T_state.setTextColor(mContext.getColor(R.color.green));
        }
        else if(tables.getT_state().equals("Busy")){
            holder.S_color.setBackground(mContext.getDrawable(R.drawable.buttonborder3));
            holder.T_state.setTextColor(mContext.getColor(R.color.red));
        }
        else{
            holder.S_color.setBackground(mContext.getDrawable(R.drawable.buttonborder1));
            holder.T_state.setTextColor(mContext.getColor(R.color.yello_2));
        }
        holder.T_state.setText(tables.getT_state());
        holder.c1.setOnClickListener(v->{
            Intent intent1 = new Intent(mContext,MainActivity4.class);
            Intent intent2 = new Intent(mContext,MainActivity5.class);

            ArrayList<String> T_data=new ArrayList<String>();
            T_data.add(tables.T_no.toString());
            T_data.add(tables.T_id.toString());
            T_data.add(tables.T_state.toString());
            if (tables.T_state.toString().equals("Free")){
                intent1.putStringArrayListExtra("T_data", T_data);
                mContext.startActivity(intent1);
            }
            else if (tables.T_state.toString().equals("Busy")){
                intent2.putStringArrayListExtra("T_data", T_data);
                mContext.startActivity(intent2);
            }
            else {
                Toast.makeText(mContext.getApplicationContext(), "Payment", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTables.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout S_color;
        TextView T_no, T_state;
        CardView c1;

        public MyViewHolder(View v) {
            super(v);
            T_no = v.findViewById(R.id.T_no);
            T_state = v.findViewById(R.id.T_state);
            S_color = v.findViewById(R.id.S_color);
            c1 = v.findViewById(R.id.c1);
        }

    }
}
