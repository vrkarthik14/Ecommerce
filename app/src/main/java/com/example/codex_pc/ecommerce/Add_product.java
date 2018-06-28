package com.example.codex_pc.ecommerce;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_product extends AppCompatActivity {

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        reference = FirebaseDatabase.getInstance().getReference().child("products");
        Product product = new Product("13MP primary camera with geo-tagging, touch focus, face detection, panorama, HDR and 5MP front facing camera\n" +
                "12.7 centimeters (5-inch) PLS TFT capacitive touchscreen with 1280 x 720 pixels resolution, 294 ppi pixel density and 16M color support\n" +
                "Android v6.0.1 Marshmallow operating system with 1.4GHz Exynos 7570 Cortex-A53 quad core processor, Mali-T720 GPU, 2GB RAM, 16GB internal memory expandable up to 256GB and dual SIM (nano+nano) dual-standby (4G+4G)","8999","4","4","3","https://firebasestorage.googleapis.com/v0/b/ecommerce-de9d3.appspot.com/o/product_imgs%2Fphone.jpg?alt=media&token=bc35205f-bf23-47ed-9ec1-c362bcbce05a","Samsung Galaxy s4","smg2rt412312");

        reference.push().setValue(product);

    }
}
