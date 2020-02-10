package com.example.mymart;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    ProgressBar progressBar;
//    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar=findViewById(R.id.progressbar);
//        textView=findViewById(R.id.percentage);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        progressAmimation();
//        Handler handler=new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        },2000);


    }

    private void progressAmimation() {

        ProgressBarAnimation progressBarAnimation=new ProgressBarAnimation(this,progressBar,0f,100f);
        progressBarAnimation.setDuration(3000);
        progressBar.setAnimation(progressBarAnimation);
    }
}
