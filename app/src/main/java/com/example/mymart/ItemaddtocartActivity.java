package com.example.mymart;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import Fragments.HomeFragment;
import Interface.Api;
import Url.Url;
import createchannel.CreateChannel;
import de.hdodenhof.circleimageview.CircleImageView;
import model.CartModel;
import model.ItemModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemaddtocartActivity extends AppCompatActivity {

    private TextView itemname,itemprice,itemdesc,itemcategory,itemid,imagecart;
    private EditText city,street,quantity;
    private CircleImageView imageview;
    private Button button;
    List<ItemModel> itemModels;
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemaddtocart);

        itemname=findViewById(R.id.name2);
        imagecart=findViewById(R.id.imgcart);
        itemprice=findViewById(R.id.price2);
        itemdesc=findViewById(R.id.description2);
        itemcategory=findViewById(R.id.category2);
        imageview=findViewById(R.id.imgview2);
        itemid=findViewById(R.id.itemid);
        button=findViewById(R.id.btnaddtocart);
        city=findViewById(R.id.etcity);
        street=findViewById(R.id.etstreet);
        quantity=findViewById(R.id.etquantity);

        notificationManagerCompat= NotificationManagerCompat.from(this);
        CreateChannel channel=new CreateChannel(this);
        channel.createChannel();

        Bundle bundle=getIntent().getExtras();
        Bundle extras = getIntent().getExtras();


        if (bundle!=null){
            String imagename = bundle.getString("image");

            final String imgPath = "http://10.0.2.2:3001/uploads/"+ imagename;
            StrictMode();

            try {
                URL url=new URL(imgPath);
                imageview.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
            }catch (Exception e){
                e.printStackTrace();
            }
            itemname.setText(bundle.getString("name"));
            itemprice.setText(bundle.getString("price"));
            itemdesc.setText(bundle.getString("description"));
            itemcategory.setText(bundle.getString("category"));
            itemid.setText(bundle.getString("id"));




          
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(ItemaddtocartActivity.this);
                builder.setMessage("Are you sure?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                addCart();
                            }
                        })
                        .setNegativeButton("cancel",null);

                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void addCart() {
        Api api=Url.getInstance().create(Api.class);
        SharedPreferences sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
        String uid = sharedPreferences.getString("id","");
        String uname = sharedPreferences.getString("username","");
        String iid=itemid.getText().toString();
        String iname=itemname.getText().toString();
        String iprice=itemprice.getText().toString();
        String icategory=itemcategory.getText().toString();
        String idescription=itemdesc.getText().toString();
        String cit=city.getText().toString();
        String stree=street.getText().toString();
        String quantit=quantity.getText().toString();

        if (TextUtils.isEmpty(city.getText())) {
            city.setError("Enter name");
            return;
        }

        else if (TextUtils.isEmpty(street.getText())) {
            street.setError("Enter street");
            return;
        }

        else if (TextUtils.isEmpty(quantity.getText())) {
            quantity.setError("Enter quantity");
            return;
        }

        CartModel cartModel=new CartModel(uname,uid,iid,iname,iprice,icategory,idescription,cit,stree,quantit);

        Call<Void> voidCall=api.addtocart(Url.token,cartModel);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                Toast.makeText(ItemaddtocartActivity.this, "Items added to cart successfully", Toast.LENGTH_SHORT).show();

                notifyaddcart();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ItemaddtocartActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void notifyaddcart() {
        Notification notification=new NotificationCompat.Builder(this,CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_add_shopping_cart_black_24dp)
                .setContentTitle("MyMart")
                .setContentText( itemname.getText().toString() + " added to cart")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(3,notification);
    }


}
