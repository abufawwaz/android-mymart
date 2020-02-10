package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import adapter.ItemAdapter;
import adapter.ViewPagerAdapter;
import model.ItemModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private RecyclerView recyclerView;
    private ViewPager viewPager;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        getItems();

        viewPager=view.findViewById(R.id.viewPager);

        ViewPagerAdapter imageAdapter = new ViewPagerAdapter(getActivity());
        viewPager.setAdapter(imageAdapter);

        return view;

}




    private void getItems() {
        Api api = Url.getInstance().create(Api.class);
        Call<List<ItemModel>> listCall = api.getItems();
        listCall.enqueue(new Callback<List<ItemModel>>() {
            @Override
            public void onResponse(Call<List<ItemModel>> call, Response<List<ItemModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getView().getContext(), "Token has expired, login again", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<ItemModel> modelList = response.body();
                ItemAdapter adapter = new ItemAdapter(getView().getContext(), modelList);

                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getView().getContext()));
            }

            @Override
            public void onFailure(Call<List<ItemModel>> call, Throwable t) {
                Toast.makeText(getView().getContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });



}


}
