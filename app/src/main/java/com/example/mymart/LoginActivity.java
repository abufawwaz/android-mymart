package com.example.mymart;

import android.app.Activity;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.os.StrictMode;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymart.businessLogic.checkUser;

import java.io.IOException;
import java.io.PrintStream;

import Interface.Api;
import Url.Url;
import broadcast.BroadcastReciever;
import createchannel.CreateChannel;
import model.LoginRegisterResponse;
import model.Result;
import model.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public TextView notmember;
    public EditText username, password;
    public Button login;
    public NotificationManagerCompat notificationManagerCompat;
    public SensorManager sensorManager;
    boolean isSuccess = false;
    LoginRegisterResponse model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();


        notmember = findViewById(R.id.notmember);
        username = findViewById(R.id.etusernamelogin);
        password = findViewById(R.id.etpasswordlogin);
        login = findViewById(R.id.btnlogin);

        sensorGyro();

        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        if (token.equals(" ")) {

        } else {
            Intent intent = new Intent(LoginActivity.this, Dashboard.class);
            startActivity(intent);
            finish();
        }

        notmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });

    }

    private void sensorGyro() {

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                if (event.values[1] < 0) {
                    check();
                    finish();

                } else if (event.values[1] > 0) {

                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        if (sensor != null) {
            sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        } else {
            Toast.makeText(this, "No sensor found", Toast.LENGTH_SHORT).show();
        }
    }

    private void check() {

        String user = username.getText().toString();
        String pass = password.getText().toString();

        if (TextUtils.isEmpty(username.getText())) {
            username.setError("Enter username");
            return;

        } else if (TextUtils.isEmpty(password.getText())) {
            password.setError("Enter password");
            return;
        }


        StrictMode();
        checkUser checkUser = new checkUser(user, pass);
        model = checkUser.checkUser();
        if (checkUser.checkUser() != null) {

            Intent intent = new Intent(LoginActivity.this, Dashboard.class);
            startActivity(intent);

            SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("username", model.getResult().getUsername());
            editor.putString("id", model.getResult().getId());
            editor.putString("password", model.getResult().getPassword());
            editor.putString("address", model.getResult().getAddress());
            editor.putString("contact", model.getResult().getContact());
            editor.putString("gender", model.getResult().getGender());
            editor.putString("token", "Bearer " + model.getToken());
            editor.commit();

            notifiy();


            SharedPreferences sharedPreferences1 = getSharedPreferences("User", MODE_PRIVATE);
            String tk = sharedPreferences1.getString("token", "");
            Url.token = tk;
            finish();

        } else {
            Toast.makeText(this, "Invalid username and password", Toast.LENGTH_SHORT).show();

            Vibrator vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(2000);
        }

    }

    private void notifiy() {
        Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_add_shopping_cart_black_24dp)
                .setContentTitle("MyMart")
                .setContentText("Login success :" + username.getText().toString())
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to exit")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    BroadcastReciever broadcastReciever = new BroadcastReciever(this);

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastReciever, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(broadcastReciever);
    }

    private void StrictMode() {
        android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }
}


