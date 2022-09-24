package com.fdac.macropayloginexample.controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.fdac.macropayloginexample.R;
import com.fdac.macropayloginexample.Utils.SharedPrefs;


public class Splash extends AppCompatActivity {
    SharedPrefs sharedPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        sharedPrefs= new SharedPrefs(this);
        LottieAnimationView anim_progress = findViewById(R.id.anim_progress);
        anim_progress.setAnimation(R.raw.loader);
        anim_progress.playAnimation();
        anim_progress.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Intent nuevo = new Intent(getApplicationContext(), Login.class);
                //if(sharedPrefs.CheckIsLogin())
                  //  nuevo = new Intent(getApplicationContext(), Home.class);

                startActivity(nuevo);
                finish();
            }
        });

    }

}
