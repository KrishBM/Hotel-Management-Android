package com.example.hotelmanagement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder>{

    List<Order> mTables;
    Context mContext;
    OnItemClickListener onItemClickListener;

    public OrderListAdapter(List<Order> mTables, Context mContext, OnItemClickListener onItemClickListener) {
        this.mTables = mTables;
        this.mContext = mContext;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_item, parent, false);
        return new MyViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Order tables = mTables.get(position);

        if(tables.getI_State().equals("Process") || tables.getI_State().equals("Deliver")){
            holder.tb2.setVisibility(View.INVISIBLE);
            if(tables.getI_State().equals("Process")){
                holder.is.setTextColor(mContext.getColor(R.color.brown));
            }
            else if(tables.getI_State().equals("Deliver")){
                holder.is.setTextColor(mContext.getColor(R.color.purple_500));
            }
        }
        else {
            holder.tb1.setVisibility(View.VISIBLE);
        }
        if(tables.getI_State().equals("Order")){
            holder.is.setTextColor(mContext.getColor(R.color.green));
        }
        else if(tables.getI_State().equals("Taking")){
            holder.is.setTextColor(mContext.getColor(R.color.red));
        }

        holder.in.setText(tables.getI_Name());
        holder.is.setText(tables.getI_State());
        holder.iq.setText(tables.getI_Qty());
        holder.tb1.setOnClickListener(v-> Toast.makeText(mContext.getApplicationContext(), tables.getI_Note(), Toast.LENGTH_SHORT).show());
        if(tables.getI_Note().isEmpty()){
            holder.tb1.setVisibility(View.INVISIBLE);
        }
        else {
            holder.tb1.setVisibility(View.VISIBLE);
        }
        holder.tb2.setOnClickListener(view -> onItemClickListener.onDelete(position));
    }
    public interface OnItemClickListener{
        void onDelete(int position);
    }
    @Override
    public int getItemCount() {
        return mTables.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageButton tb1,tb2;
        TextView in,iq,is;

        public MyViewHolder(View v) {
            super(v);
            tb1=v.findViewById(R.id.tb1);
            tb2=v.findViewById(R.id.tb2);
            in=v.findViewById(R.id.in);
            iq=v.findViewById(R.id.iq);
            is=v.findViewById(R.id.is);
        }

    }
}
