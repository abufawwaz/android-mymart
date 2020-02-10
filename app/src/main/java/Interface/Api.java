package Interface;

import java.util.List;

import model.CartModel;
import model.FeedbackModel;
import model.ItemModel;
import model.LoginRegisterResponse;
import model.UpdateUser;
import model.UserModel;
import model.UserRegModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {


    @POST("v1/feedback")
    Call<Void> feedback(@Header("Authorization") String token,@Body FeedbackModel model);


    @POST("v1/addtocart")
    Call<Void> addtocart(@Header("Authorization") String token,@Body CartModel cartModel);

    @PUT("v1/updatemydetail/{userId}")
    Call<Void> updateprofile(@Header("Authorization") String token,@Path("userId") int userId, @Body UpdateUser updateUser);

    @GET("v1/viewbooking/{uid}")
    Call<List<CartModel>> getbyid(@Header("Authorization") String token,@Path("uid") String uid);

    @DELETE("v1/deletecart/{iid}")
    Call<Void> deleteItems(@Header("Authorization") String token,@Path("iid") String iid);

    @POST("v1/users/register")
    Call<Void> registerUser(@Body UserRegModel model);

    @GET("v1/viewItems")
    Call<List<ItemModel>> getItems();

    @POST("v1/users/login")
    Call<LoginRegisterResponse> checkUser(@Body UserModel model);

}
