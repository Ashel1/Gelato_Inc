package com.example.gelatoinc;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    public static final String USER_KEY ="user_key" ;
    Context context;
    ArrayList<ItemActivity> items;


    public Adapter(Context c, ArrayList<ItemActivity> i){
        context=c;
        items=i;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ItemActivity i=items.get(position);

        holder.item.setText(i.getItem());
        holder.price.setText(i.getPrice());
        Picasso.get().load(i.getPic()).into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=i.getId();
                Intent intent=new Intent(context,OpenDetailsActivity.class);
                intent.putExtra(USER_KEY,id);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView item,price;
        ImageView pic;
        View mView;

        public ViewHolder(View itemView){
            super(itemView);

            mView=itemView;
            item = itemView.findViewById(R.id.item);
            price=itemView.findViewById(R.id.price);
            pic=itemView.findViewById(R.id.pic);
        }

    }

}
