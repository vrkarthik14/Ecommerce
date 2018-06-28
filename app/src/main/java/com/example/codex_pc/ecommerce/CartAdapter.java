package com.example.codex_pc.ecommerce;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CartAdapter extends ArrayAdapter<cart> {

    private Context context;

    public CartAdapter(Context context, ArrayList<cart> items) {
        super(context, 0,items);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {

            //Inflate to custom list_entry.xml
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.product_list_item, parent, false);

        }

        //Get Current CustomClass Object
        final cart currentItem = getItem(position);

        //Declare and assign all XML view elements from list_entry : Example
        TextView productName = listItemView.findViewById(R.id.pro_name);
        TextView productDesc = listItemView.findViewById(R.id.pro_desc);
        TextView productQuantity = listItemView.findViewById(R.id.pro_qty);
        TextView productserial_o = listItemView.findViewById(R.id.serial_no);
        ImageView productimg= listItemView.findViewById(R.id.pro_imgage);
        TextView product_delivery = listItemView.findViewById(R.id.pro_delivery);
        TextView pric = listItemView.findViewById(R.id.price);

        if (currentItem!=null) {
            productName.setText(currentItem.getProduct().getPro_name());
            productDesc.setText(currentItem.getProduct().getDesc().substring(0,65)+".......");
            productserial_o.setText(currentItem.getProduct().getSerial_no());
            String price = String.valueOf("Rs."+currentItem.getProduct().getPrice())+"/-";
            pric.setText(price);
            String quantity = String.valueOf("qty: "+currentItem.getUser_chhosen_qty());
            productQuantity.setText(quantity);
            product_delivery.setText("Estimated delivery: "+currentItem.getProduct().getEstimated_delivery()+" days");

            if(currentItem.getProduct().getProdut_img_path()!=null) {


                // Download directly from StorageReference using Glide
                // (See MyAppGlideModule for Loader registration)
                Glide.with(context)
                        .load(currentItem.getProduct().getProdut_img_path())
                        .into(productimg);
            }
        }
        return listItemView;

    }
}

