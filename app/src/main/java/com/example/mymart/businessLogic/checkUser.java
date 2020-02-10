package com.example.mymart.businessLogic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.mymart.Dashboard;
import com.example.mymart.LoginActivity;
import com.example.mymart.StrictMode;

import java.io.IOException;

import Interface.Api;
import Url.Url;
import model.LoginRegisterResponse;
import model.Result;
import model.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class checkUser {

    private String username;
    private String password;
    private Context context;


    LoginRegisterResponse result;


    public checkUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginRegisterResponse checkUser() {

        Api api = Url.getInstance().create(Api.class);
        UserModel model = new UserModel(username, password);

        Call<LoginRegisterResponse> call = api.checkUser(model);


        try {
            Response<LoginRegisterResponse> loginRegisterResponseResponse = call.execute();
            if (!loginRegisterResponseResponse.isSuccessful()) {
                return null;
            } else {

                result = new LoginRegisterResponse(loginRegisterResponseResponse.body().getToken(),
                        loginRegisterResponseResponse.body().getResult());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
