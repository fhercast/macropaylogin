package com.fdac.macropayloginexample.controller;

import static com.fdac.macropayloginexample.Utils.Base64Decoder.DecodeJWT;
import static com.fdac.macropayloginexample.Utils.Base64Decoder.getPayload;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.fdac.macropayloginexample.R;
import com.fdac.macropayloginexample.Utils.Base64Decoder;
import com.fdac.macropayloginexample.Utils.SharedPrefs;
import com.fdac.macropayloginexample.api.VolleyUtils;
import com.fdac.macropayloginexample.api.apiconfig;
import com.fdac.macropayloginexample.api.responsemodel.BaseDto;
import com.fdac.macropayloginexample.model.User;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;

public class Login extends AppCompatActivity {
    VolleyUtils api;
    Gson gson;
    SharedPrefs sharedPrefs;
    private EditText userNameEt;
    private EditText passwordEt;
    //private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        api = new VolleyUtils(this);
        gson = new Gson();
        sharedPrefs= new SharedPrefs(this);
        //user = new User();
        userNameEt = findViewById(R.id.user_name_et);
        passwordEt = findViewById(R.id.password_et);
        Button loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(userNameEt.getText().toString(), passwordEt.getText().toString());
            }
        });
    }



    private void login(String email, String password) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("password", password);

        api.Volley_POST(params, apiconfig.URL, true, "Iniciando sesión", new VolleyUtils.VolleyCallback() {
            @Override
            public void onSuccessResponse(String result) {

                BaseDto base = gson.fromJson(result, BaseDto.class);//Se recupera el objeto

                if(base.isSuccess())
                {
                    try {
                        sharedPrefs.setString(SharedPrefs.token,base.getToken());
                        //String[] data = DecodeJWT(base.getToken());
                        String json= getPayload(base.getToken());
                        sharedPrefs.setString(SharedPrefs.userdata,json);

                        //User user = gson.fromJson(json, User.class);//Set data del cliente
                        Intent nuevo = new Intent(getApplicationContext(), Home.class);
                        startActivity(nuevo);
                        finish();


                    }catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),base.getMsg(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onErrorResponse(String e) {

            }
        });

    }

}