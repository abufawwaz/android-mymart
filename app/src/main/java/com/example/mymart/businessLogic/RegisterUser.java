package com.example.mymart.businessLogic;

import java.io.IOException;

import Interface.Api;
import Url.Url;
import model.LoginRegisterResponse;
import model.UserModel;
import model.UserRegModel;
import retrofit2.Call;
import retrofit2.Response;

public class RegisterUser {

    String username;
    String password;
    String address;
    String contact;
    String gender;
    String userType = "User";
    boolean isSuccess = false;

    public RegisterUser(String username, String password, String address, String contact, String gender, String userType) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.contact = contact;
        this.gender = gender;
        this.userType = userType;
    }

    public boolean register() {

        UserRegModel userRegModel = new UserRegModel(username, password, address, contact, gender, userType);

        Api api = Url.getInstance().create(Api.class);

        Call<Void> voidCall = api.registerUser(userRegModel);

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
