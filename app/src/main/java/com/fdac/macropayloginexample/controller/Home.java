package com.fdac.macropayloginexample.controller;

import static com.fdac.macropayloginexample.Utils.CodeGenerator.CodeBar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.fdac.macropayloginexample.R;
import com.fdac.macropayloginexample.Utils.SharedPrefs;
import com.fdac.macropayloginexample.model.User;
import com.google.gson.Gson;

public class Home extends AppCompatActivity{
    SharedPrefs sharedPrefs;
    TextView txtwelcome,txttoken;
    ImageView codebar;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        sharedPrefs= new SharedPrefs(this);
        gson = new Gson();
        txtwelcome = findViewById(R.id.txtwelcome);
        txttoken = findViewById(R.id.txttoken);
        codebar = findViewById(R.id.codebar);
        SetData();


    }

    void SetData()
    {
        User user = gson.fromJson(sharedPrefs.getString(SharedPrefs.userdata), User.class);//Set data del cliente
        txtwelcome.setText( String.format("Bienvenido, %s",user.getTitular()));

        //
        txttoken.setText(sharedPrefs.getString(SharedPrefs.token));
        Bitmap bitcode =CodeBar(sharedPrefs.getString(SharedPrefs.token));
        if(bitcode!=null)
        {
            codebar.setImageBitmap(bitcode);
        }
    }
}
