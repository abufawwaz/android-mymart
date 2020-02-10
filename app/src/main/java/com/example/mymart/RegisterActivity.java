package com.example.mymart;

import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mymart.businessLogic.RegisterUser;

import java.util.ArrayList;
import java.util.List;

import createchannel.CreateChannel;

public class RegisterActivity extends AppCompatActivity {

    private Spinner spinner;
    private NotificationManagerCompat notificationManagerCompat;

    private EditText username, password, address, contact,gend;
    private Button register;
    public static final int PICK_IMAGE = 1;

    private String gender[]= {"male","female","others"};
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = findViewById(R.id.genderregister);
        username = findViewById(R.id.usernameregister);
        password = findViewById(R.id.passwordregister);
        address = findViewById(R.id.addressregister);
        contact = findViewById(R.id.contactregister);

        register = findViewById(R.id.btnregister);

        List<String> list = new ArrayList<String>();
        list.add("Male");
        list.add("Female");
        list.add("Others");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        notificationManagerCompat=NotificationManagerCompat.from(this);
        CreateChannel channel=new CreateChannel(this);
        channel.createChannel();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

    }

    private void save(){
        String user=username.getText().toString();
        String pass=password.getText().toString();
        String add=address.getText().toString();
        String cont=contact.getText().toString();
        String gend=spinner.getSelectedItem().toString();
        String userType = "User";

        if (TextUtils.isEmpty(username.getText())) {
            username.setError("Enter username");
            return;

        } else if (TextUtils.isEmpty(password.getText())) {
            password.setError("Enter password");
            return;
        }
        else if (TextUtils.isEmpty(address.getText())) {
            address.setError("Enter address");
            return;
        }else if (TextUtils.isEmpty(contact.getText())) {
            contact.setError("Enter contact");
            return;
        }

        final RegisterUser registerUser=new RegisterUser(user,pass,add,cont,gend,userType);

        StrictMode();
        if (registerUser.register()){
            Toast.makeText(this, "Registered success", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
            notifiyregister();
                finish();
        }
        else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }



    }

    private void notifiyregister() {
        Notification notification=new NotificationCompat.Builder(this,CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_add_shopping_cart_black_24dp)
                .setContentTitle("MyMart")
                .setContentText("Welcome to MyMart : " + username.getText().toString())
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(2,notification);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to exit")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    private void StrictMode(){
        android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }
}
