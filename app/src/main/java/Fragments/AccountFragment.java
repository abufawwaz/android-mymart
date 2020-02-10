package Fragments;


import android.app.AlertDialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mymart.R;

import Interface.Api;
import Url.Url;
import createchannel.CreateChannel;
import model.UpdateUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private EditText username,address,contact,gender;
    private Button btn;

    private NotificationManagerCompat notificationManagerCompat;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_account, container, false);

        notificationManagerCompat= NotificationManagerCompat.from(getActivity());
        CreateChannel channel=new CreateChannel(getActivity());
        channel.createChannel();
        username=view.findViewById(R.id.nameupdate);
        address=view.findViewById(R.id.addressupdate);
        contact=view.findViewById(R.id.contactupdate);
        gender=view.findViewById(R.id.genderupdate);
        btn=view.findViewById(R.id.btnupdate);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                update();
                            }
                        })
                        .setNegativeButton("cancel",null);

                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

        display();
        return view;
    }

    private void update() {
        String u=username.getText().toString();
        String a=address.getText().toString();
        String c=contact.getText().toString();
        String g=gender.getText().toString();

        UpdateUser updateUser=new UpdateUser(u,a,c,g);

        Api api= Url.getInstance().create(Api.class);

        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("User", MODE_PRIVATE);
        String id = sharedPreferences.getString("id","");


        Call<Void> voidCall=api.updateprofile(Url.token,Integer.parseInt(id.toString()),updateUser);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getView().getContext(), "User Profile Updated succesfully", Toast.LENGTH_SHORT).show();
                    notifyupdate();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getView().getContext(), "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void notifyupdate() {
        Notification notification=new NotificationCompat.Builder(getActivity(), CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_add_shopping_cart_black_24dp)
                .setContentTitle("MyMart")
                .setContentText( "Profile Updated succesfully")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(5,notification);
    }

    private void display() {

        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("User", MODE_PRIVATE);
        String user = sharedPreferences.getString("username","");
        String add = sharedPreferences.getString("address","");
        String cont = sharedPreferences.getString("contact","");
        String gen = sharedPreferences.getString("gender","");

        username.setText(user);
        address.setText(add);
        contact.setText(cont);
        gender.setText(gen);
    }


    private void StrictMode(){
        android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }

}
