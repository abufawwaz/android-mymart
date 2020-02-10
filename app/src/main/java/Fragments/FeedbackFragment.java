package Fragments;


import android.app.AlertDialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mymart.R;
import com.example.mymart.businessLogic.Feedbacklogic;

import createchannel.CreateChannel;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackFragment extends Fragment {


    private EditText etname,etphone,etemail,etmessage;
    private ImageView imageView;
    boolean isSuccess = false;
//    private Context context;
    private NotificationManagerCompat notificationManagerCompat;
    
    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        notificationManagerCompat= NotificationManagerCompat.from(getActivity());
        CreateChannel channel=new CreateChannel(getActivity());
        channel.createChannel();

        etname=view.findViewById(R.id.etnamefeedback);
        etphone=view.findViewById(R.id.etphonefeedback);
        etemail=view.findViewById(R.id.etemailfeedback);
        etmessage=view.findViewById(R.id.etmessagefeedback);
        imageView=view.findViewById(R.id.btnimage);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sendFeedback();
                            }
                        })
                        .setNegativeButton("cancel",null);

                AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }
        });

        
        return view;
    }

    public void sendFeedback(){
        String name=etname.getText().toString();
        String email=etemail.getText().toString();
        String phone=etphone.getText().toString();
        String message=etmessage.getText().toString();


        if (TextUtils.isEmpty(etname.getText())) {
            etname.setError("Enter name");
            return;
        }

        else if (TextUtils.isEmpty(etemail.getText())) {
            etemail.setError("Enter email");
            return;
        }
        else if (TextUtils.isEmpty(etphone.getText())) {
            etphone.setError("Enter phone");
            return;
        }  else if (TextUtils.isEmpty(etmessage.getText())) {
            etmessage.setError("Enter name");
            return;
        }
        Feedbacklogic feedbackLogic=new Feedbacklogic(name,email,phone,message);
        StrictMode();

        if (feedbackLogic.feedback()){
            Toast.makeText(getActivity(), "Feedback sent succesfully", Toast.LENGTH_SHORT).show();
            DisplayNotification();

        }
        else {
            Toast.makeText(getActivity(), "Feedback sent succesfully", Toast.LENGTH_SHORT).show();
            DisplayNotification();

        }



    }

    private void DisplayNotification() {
        Notification notification =new NotificationCompat.Builder(getContext(),CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_add_shopping_cart_black_24dp)
                .setContentTitle("MyMart")
                .setContentText("Feedback sent successfully")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(1,notification);
    }

    private void StrictMode(){
        android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }

}
