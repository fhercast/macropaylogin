package com.fdac.macropayloginexample.Utils;


import android.os.Build;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Decoder {
  /*  public static String Decode(String base64)
    {
        String result = "";
        byte[] decrypt= Base64.decode(base64, Base64.DEFAULT);
        try {
            result = new String(decrypt, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }*/

    private static String decode(String encodedString) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new String(Base64.getUrlDecoder().decode(encodedString));
        }
        return "";
    }

    public static String[] DecodeJWT(String token) throws JSONException {
        String[] parts = token.split("\\.");

        JSONObject header = new JSONObject(decode(parts[0]));
        JSONObject payload = new JSONObject(decode(parts[1]));
        String signature = decode(parts[2]);

        return parts;
    }

    public static String getPayload(String token) throws JSONException
    {
        String[] parts = token.split("\\.");
        JSONObject payload = new JSONObject(decode(parts[1]));
        return payload.toString();
    }

}
