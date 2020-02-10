package com.example.mymart;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import Fragments.AccountFragment;
import Fragments.CartFragment;
import Fragments.FeedbackFragment;
import Fragments.HelpFragment;
import Fragments.HomeFragment;
import Fragments.LocationFragment;
import Fragments.MoreFragment;
import broadcast.BroadcastReciever;

public class Dashboard extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;


    private HomeFragment homeFragment;
    private AccountFragment accountFragment;
    private FeedbackFragment feedbackFragment;
    private HelpFragment helpFragment;
    private MoreFragment moreFragment;

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mMainNav=findViewById(R.id.main_nav);
        mMainFrame=findViewById(R.id.main_frame);

        homeFragment=new HomeFragment();
        accountFragment=new AccountFragment();
        feedbackFragment=new FeedbackFragment();
        helpFragment=new HelpFragment();

        sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
        String token = sharedPreferences.getString("token","");

        if (token.equals(" ")){
            Intent intent= new Intent(Dashboard.this,LoginActivity.class);
            startActivity(intent);
        }
        setFragment(homeFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.navigation_cart:
                        setFragment(new CartFragment());
                        return true;
                    case R.id.navigation_account:
                        setFragment(accountFragment);
                        return true;
                    case R.id.navigation_more:
                        setFragment(new MoreFragment());
                        return true;

                    case R.id.navigation_location:
//                        setFragment(helpFragment);
//                        return true;

                        startActivity(new Intent(Dashboard.this,MapsActivity.class));
                        return true;
                        default:
                            return false;
                }

            }
        });
    }



    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to exit")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

}
