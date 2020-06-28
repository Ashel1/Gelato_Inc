package com.example.gelatoinc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartActivity  extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Button btn_clear,btn_po;
    TextView total_amt;
    ArrayList<Cart> list;
    CartAdapter adapter;
    int price,quantity,total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView=findViewById(R.id.cartList);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        list=new ArrayList<Cart>();

        btn_clear=findViewById(R.id.btn_clear);
        btn_po=findViewById(R.id.btn_po);

        total_amt=findViewById(R.id.total);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        final DatabaseReference cartListRef= FirebaseDatabase.getInstance().getReference().child("Cart List");
        cartListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren() ){
                    Cart i=dataSnapshot1.getValue(Cart.class);
                    i.setId(dataSnapshot1.getKey());
                    price= Integer.parseInt(i.getPrice());
                    quantity=Integer.parseInt(i.getQuantity());
                    total=total+(price*quantity);
                    list.add(i);
                }
                adapter=new CartAdapter(CartActivity.this,list);
                recyclerView.setAdapter(adapter);
                total_amt.setText("Total Amount="+total);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CartActivity.this,"Something is wrong",Toast.LENGTH_SHORT).show();
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartListRef.setValue(null);
                Toast.makeText(CartActivity.this,"Cart Emptied",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(CartActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btn_po.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartActivity.this, "Order Placed!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(CartActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
