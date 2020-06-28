package com.example.gelatoinc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
        CartActivity context;
        ArrayList<Cart> arrayList;
        TextView total_amt;

public CartAdapter(CartActivity c, ArrayList<Cart> a){
        context=c;
        arrayList=a;
        }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_list, parent, false));
        return viewHolder;
    }

    @Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
final Cart i=arrayList.get(position);

        holder.item.setText(i.getItem());
        holder.price.setText(i.getPrice());
        holder.qua.setText(i.getQuantity());
        }

@Override
public int getItemCount() {
        return arrayList.size();
        }
static class ViewHolder extends RecyclerView.ViewHolder{
    TextView item,price,qua;

    public ViewHolder(View itemView){
        super(itemView);
        item = itemView.findViewById(R.id.cItem);
        price=itemView.findViewById(R.id.cPrice);
        qua=itemView.findViewById(R.id.cQuantity);

    }

}
}

