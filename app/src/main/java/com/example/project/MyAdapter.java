package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList from,to,seats,date;
    int[] images;

    public MyAdapter(Context context,  ArrayList from, ArrayList to, ArrayList seats,ArrayList date) {
        this.context = context;
        this.from = from;
        this.to = to;
        this.seats = seats;
        this.date=date;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.busentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.from.setText(String.valueOf(from.get(position)));
        holder.to.setText(String.valueOf(to.get(position)));
        holder.seats.setText(String.valueOf(seats.get(position)));
        holder.date.setText(String.valueOf(date.get(position)));

//        holder.rowimage.setImageResource(images[position]);

    }

    @Override
    public int getItemCount() {
        return from.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView from,to,seats,date;
//        ImageView rowimage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            rowimage=itemView.findViewById(R.id.imageViewBus);
            from=itemView.findViewById(R.id.busname);
            to=itemView.findViewById(R.id.busto);
            seats=itemView.findViewById(R.id.busseats);
            date=itemView.findViewById(R.id.busdate);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            Intent intent=new Intent(context,SeatSelection.class);
            context.startActivity(intent);
        }
    }
}
