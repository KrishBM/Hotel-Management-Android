package com.example.hotelmanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BooksListAdapter extends RecyclerView.Adapter<BooksListAdapter.MyViewHolder>{

    List<Tables> mTables;
    Context mContext;

    public BooksListAdapter(List<Tables> books, Context context) {
        mTables = books;
        mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new MyViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tables tables = mTables.get(position);

//        Drawable d = getResources().getDrawable(R.drawable.ic_launcher);
//        Button one = new Button(mContext);
//// mediocre
//        one.setBackgroundDrawable(d);
//        Button two = new Button(mContext);
//// better
//        two.setBackgroundResource(R.drawable.ic_launcher);

        holder.T_no.setText(tables.getT_no());
        if(tables.getT_state()=="Free"){
            holder.S_color.setBackground(mContext.getDrawable(R.drawable.buttonborder2));
            holder.T_state.setTextColor(mContext.getColor(R.color.green));
        }
        if(tables.getT_state()=="Busy"){
            holder.S_color.setBackground(mContext.getDrawable(R.drawable.buttonborder3));
            holder.T_state.setTextColor(mContext.getColor(R.color.red));
        }
        if(tables.getT_state()=="Payment"){
            holder.S_color.setBackground(mContext.getDrawable(R.drawable.buttonborder1));
            holder.T_state.setTextColor(mContext.getColor(R.color.yello_2));
        }
        holder.T_state.setText(tables.getT_state());
        holder.c1.setOnClickListener(v->{
            Intent intent = new Intent(mContext,MainActivity4.class);
            mContext.startActivity(intent);
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
