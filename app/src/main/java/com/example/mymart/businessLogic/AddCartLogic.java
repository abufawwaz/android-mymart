package com.example.mymart.businessLogic;


import java.io.IOException;

import Interface.Api;
import Url.Url;
import model.CartModel;
import retrofit2.Call;
import retrofit2.Response;

public class AddCartLogic {
    String uid;
    String uname;
    String iid;
    String iname;
    String iprice;
    String icategory;
    String idescription;
    String city;
    String street;
    String quantity;

    boolean isSuccess = false;

    public AddCartLogic(String uid, String uname, String iid, String iname, String iprice, String icategory, String idescription, String city, String street, String quantity) {
        this.uid = uid;
        this.uname = uname;
        this.iid = iid;
        this.iname = iname;
        this.iprice = iprice;
        this.icategory = icategory;
        this.idescription = idescription;
        this.city = city;
        this.street = street;
        this.quantity = quantity;

    }

    public boolean addtocart() {

        CartModel cartModel = new CartModel(uname, uid, iid, iname, iprice, icategory, idescription, city, street, quantity);

        Api api = Url.getInstance().create(Api.class);
        Call<Void> voidCall = api.addtocart(Url.token, cartModel);

        try {
            Response<Void> voidResponse = voidCall.execute();
            if (voidResponse.isSuccessful()) {
                isSuccess = true;
            }
        } catch (
                IOException e) {

        }
        return isSuccess;
    }

}