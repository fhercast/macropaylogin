package com.fdac.macropayloginexample.api;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fdac.macropayloginexample.dialogs.dialo_messages;

import java.util.HashMap;
import java.util.Map;

public class VolleyUtils {

    Context context;

    public VolleyUtils(Context context_) {
        context=context_;
    }

    public interface VolleyCallback
    {
        void onSuccessResponse(String result);
        void onErrorResponse(String e);
    }

    public void Volley_POST(final Map<String, String> params, String url, boolean enabledialog, String textmsj, final VolleyCallback callback)
    {


        dialo_messages dialog_alerts = new dialo_messages(context);
        Dialog dialog = dialog_alerts.Loader(textmsj);
        if(enabledialog)
        {
            try{
                Activity act = (Activity) context;
                if(!act.isFinishing())
                    dialog.show();
            }catch (Exception e)
            {
            }
        }


        try
        {

            RequestQueue requestQueue = Volley.newRequestQueue(context);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response)
                {
                    dialog.dismiss();
                    callback.onSuccessResponse(response);

                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError e)
                {
                    callback.onErrorResponse("Sin acceso a internet");
                    dialog.dismiss();
                }
            })
            {
                /**
                 * Passing some request headers
                 * */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Content-Type","application/x-www-form-urlencoded");
                    return params;
                }

                /*@Override
                public String getBodyContentType() {
                    return " charset=utf-8";
                }*/

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return params;
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response)
                {

                    String responseString = "post_error";
                    if (response != null)
                    {
                        try
                        {
                            responseString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };
            stringRequest.setRetryPolicy(new
                    DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, 1,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            );
            requestQueue.add(stringRequest);
        }
        catch (NegativeArraySizeException n)
        {
            n.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
