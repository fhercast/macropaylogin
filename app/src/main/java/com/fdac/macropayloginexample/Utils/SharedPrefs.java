package com.fdac.macropayloginexample.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    public static final String name_preferences_user = "User_preferences";
    public static String token = "token";
    public static String userdata = "userdata";

    Context context;

    public SharedPrefs(Context context_) {
        context=context_;
    }

    public boolean CheckIsLogin()
    {
        if(getString(userdata).isEmpty())
            return false;
        else return true;

    }

    public  String getString(String Key){
        SharedPreferences preferences = context.
                getSharedPreferences(name_preferences_user, Context.MODE_PRIVATE);
        return preferences.getString(Key,"");
    }
    public void setString(String Key, String Data){
        SharedPreferences preferences = context.
                getSharedPreferences(name_preferences_user, Context.MODE_PRIVATE);
        SharedPreferences.Editor editPreferences = preferences.edit();
        editPreferences.putString(Key, Data);
        editPreferences.apply();
    }
}
