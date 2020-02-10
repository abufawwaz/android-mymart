package com.example.mymart.businessLogic;

import android.content.SharedPreferences;

import java.io.IOException;

import Interface.Api;
import Url.Url;
import model.FeedbackModel;
import retrofit2.Call;
import retrofit2.Response;

public class Feedbacklogic {
    private String name;
    private String email;
    private String phone;
    private String message;

    boolean isSuccess = false;

    public Feedbacklogic(String name, String email, String phone, String message) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.message = message;
    }

    public boolean feedback() {

        Api api= Url.getInstance().create(Api.class);

        FeedbackModel model=new FeedbackModel(name,email,phone,message);

        Call<Void> voidCall = api.feedback(Url.token,model);


        try {
            Response<Void> voidResponse = voidCall.execute();
            if (voidResponse.isSuccessful()) {
                isSuccess = true;
            }
        } catch (IOException e) {

        }
        return isSuccess;

    }
}
