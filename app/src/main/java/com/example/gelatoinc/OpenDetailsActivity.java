package com.example.gelatoinc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class OpenDetailsActivity extends AppCompatActivity {

    TextView item,price,ing;
    ImageView pic;
    ElegantNumberButton numberButton;
    Button addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_details);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        item=findViewById(R.id.dItem);
        price=findViewById(R.id.dPrice);
        pic=findViewById(R.id.dPic);
        ing=findViewById(R.id.dIng);

        numberButton=findViewById(R.id.number_btn);
        addButton=findViewById(R.id.cart_btn);

        final String id=getIntent().getStringExtra(Adapter.USER_KEY);
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("items").child(id);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ItemActivity i=dataSnapshot.getValue(ItemActivity.class);
                item.setText(i.getItem());
                price.setText(i.getPrice());
                ing.setText(i.getIng());
                String img=dataSnapshot.child("pic").getValue().toString();
                Picasso.get().load(img).into(pic);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String saveCD, saveCT;

                Calendar calForDate= Calendar.getInstance();

                SimpleDateFormat currentDate=new SimpleDateFormat("dd-MM-yyyy");
                saveCD=currentDate.format(calForDate.getTime());

                SimpleDateFormat currentTime=new SimpleDateFormat("hh:mm:ss a");
                saveCT=currentTime.format(calForDate.getTime());

                DatabaseReference cartList=FirebaseDatabase.getInstance().getReference().child("Cart List");

                final HashMap<String,Object> cartMap=new HashMap<>();
                cartMap.put("id",id);
                cartMap.put("item",item.getText().toString());
                cartMap.put("price",price.getText().toString());
                cartMap.put("quantity",numberButton.getNumber());
                cartMap.put("date",saveCD);
                cartMap.put("time",saveCT);

                cartList.child(id).updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(OpenDetailsActivity.this, "Item(s) Added to Cart", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(OpenDetailsActivity.this,MainActivity.class);
                            startActivity(intent);

                        }
                    }
                });
            }
        });


    }

}
