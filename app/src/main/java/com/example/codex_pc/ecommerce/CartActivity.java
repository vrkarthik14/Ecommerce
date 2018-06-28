package com.example.codex_pc.ecommerce;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ListView cart_list;
    ArrayList<cart> product_choosen = new ArrayList<>();
    CartAdapter cartAdapter;
    DatabaseReference databaseReference;
    Button order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cart_list = findViewById(R.id.cart_list);
        cartAdapter = new CartAdapter(getApplicationContext(),product_choosen);
        order = findViewById(R.id.order);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("carts").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        addListerners();

        cart_list.setAdapter(cartAdapter);


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //does nothing but a toast;
                Toast.makeText(CartActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();
                databaseReference.removeValue();
                finish();
            }
        });


    }

    public void addListerners(){
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                product_choosen.add(dataSnapshot.getValue(cart.class));
                cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
