package Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mymart.R;

import java.util.List;

import Interface.Api;
import Url.Url;
import adapter.CartAdapter;
import adapter.ItemAdapter;
import model.CartModel;
import model.ItemModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {

    private RecyclerView rv;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_cart, container, false);

        rv = view.findViewById(R.id.recyclerviewcart);

        getCart();
        return view;
    }

    private void getCart() {
       Api api=Url.getInstance().create(Api.class);

       SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("User",MODE_PRIVATE);
       String uid = sharedPreferences.getString("id","");
       Call<List<CartModel>> listCall=api.getbyid(Url.token,uid);

       listCall.enqueue(new Callback<List<CartModel>>() {
           @Override
           public void onResponse(Call<List<CartModel>> call, Response<List<CartModel>> response) {
               if (!response.isSuccessful()){
                   Toast.makeText(getView().getContext(), response.toString(), Toast.LENGTH_SHORT).show();
                   return;
               }

            final List<CartModel> cartModels=response.body();
               CartAdapter adapter = new CartAdapter(getView().getContext(), cartModels);

               rv.setAdapter(adapter);
               rv.setLayoutManager(new LinearLayoutManager(getView().getContext()));
           }

           @Override
           public void onFailure(Call<List<CartModel>> call, Throwable t) {
               Toast.makeText(getView().getContext(),"error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
           }
       });

    }
    }

