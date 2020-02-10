package com.example.mymart.businessLogic;

import java.io.IOException;

import Interface.Api;
import Url.Url;
import model.UpdateUser;
import model.UserRegModel;
import retrofit2.Call;
import retrofit2.Response;

public class UpdateLogic {

    String username;
    String address;
    String contact;
    String gender;
    boolean isSuccess = false;

    public UpdateLogic(String username, String address, String contact, String gender) {
        this.username = username;
        this.address = address;
        this.contact = contact;
        this.gender = gender;
    }

    public boolean update() {

        UpdateUser model = new UpdateUser(username, address, contact, gender);

        Api api = Url.getInstance().create(Api.class);

        Call<Void> voidCall = api.updateprofile(Url.token,52,model);

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
