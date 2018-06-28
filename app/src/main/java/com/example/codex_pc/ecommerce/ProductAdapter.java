package com.example.codex_pc.ecommerce;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {

    private Context context;

    public ProductAdapter(Context context, ArrayList<Product> items) {
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
        final Product currentItem = getItem(position);

        //Declare and assign all XML view elements from list_entry : Example
        TextView productName = listItemView.findViewById(R.id.pro_name);
        TextView productDesc = listItemView.findViewById(R.id.pro_desc);
        TextView productQuantity = listItemView.findViewById(R.id.pro_qty);
        TextView productserial_o = listItemView.findViewById(R.id.serial_no);
        ImageView productimg= listItemView.findViewById(R.id.pro_imgage);
        TextView product_delivery = listItemView.findViewById(R.id.pro_delivery);
        TextView pric = listItemView.findViewById(R.id.price);

        if (currentItem!=null) {
            productName.setText(currentItem.getPro_name());
            productDesc.setText(currentItem.getDesc().substring(0,65)+".......");
            productserial_o.setText(currentItem.getSerial_no());
            String price = String.valueOf("Rs."+currentItem.getPrice())+"/-";
            pric.setText(price);
            String quantity = String.valueOf("qty: "+currentItem.getAvailabale_qty());
            productQuantity.setText(quantity);
            product_delivery.setText("Estimated delivery: "+currentItem.getEstimated_delivery()+" days");

            if(currentItem.getProdut_img_path()!=null) {


                // Download directly from StorageReference using Glide
                // (See MyAppGlideModule for Loader registration)
                Glide.with(context)
                        .load(currentItem.getProdut_img_path())
                        .into(productimg);
            }
        }

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                final TextView title = new TextView(context);
                title.setText(currentItem.getPro_name());
                title.setTextColor(Color.BLACK);
                title.setTypeface(null, Typeface.BOLD);
                layout.addView(title);

                final TextView t1 = new TextView(context);
                t1.setHeight(8);
                layout.addView(t1);

                final TextView desc = new TextView(context);
                desc.setText(currentItem.getDesc());
                layout.addView(desc);

                final EditText nameBox = new EditText(context);
                nameBox.setHint("Enter Required Qty:");
                nameBox.setInputType(InputType.TYPE_CLASS_NUMBER);
                layout.addView(nameBox);


                final AlertDialog alert = new AlertDialog.Builder(context)
                        .setTitle("Buy this product:")
                        .setView(layout)
                        .setPositiveButton("ADD to CART",null)
                        .setNegativeButton("Cancel",null)
                        .create();

                alert.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        final Button button = alert.getButton(AlertDialog.BUTTON_POSITIVE);
                        Button button1 = alert.getButton(AlertDialog.BUTTON_NEGATIVE);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String qty = nameBox.getText().toString();
                                if(!qty.equals("")&&Integer.parseInt(qty)<= Integer.parseInt(currentItem.getAvailabale_qty())){
                                    cart cart = new cart(currentItem,qty);
                                    FirebaseDatabase.getInstance().getReference().child("carts").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .push().setValue(cart);
                                    Toast.makeText(context, "Product added to the cart.", Toast.LENGTH_SHORT).show();
                                    alert.dismiss();
                                }else {
                                    Toast.makeText(context, "Required qty is Not available in stock...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alert.dismiss();
                            }
                        });
                    }
                });
                alert.show();


            }
        });

        return listItemView;

    }
}
