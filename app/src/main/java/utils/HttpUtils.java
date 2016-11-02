package utils;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import interFace.OnLoadResponseListener;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/11/1.
 */

public class HttpUtils {
    public void SignIn(String uri, final OnLoadResponseListener onLoadResponseListener, RequestQueue requestQueue, final String emil, final String name, final String password) {
        StringRequest request = new StringRequest( Request.Method.POST, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onLoadResponseListener.getResponse( response );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( "===", "----------" + error.getMessage() );
            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put( "ver", "1" );
                map.put( "uid", name );
                map.put( "email", emil );
                map.put( "pwd", password );
                return map;
            }
        };
        requestQueue.add( request );
    }

    public void Logon(String uri, String nane, String password, final OnLoadResponseListener onLoadResponseListener) {//okhttp同步post
        OkHttpClient client = new OkHttpClient();
        FormBody build = new FormBody.Builder().add( "ver", "1" ).add( "uid", nane ).add( "pwd", password ).add( "device", "0" ).build();
        final okhttp3.Request request = new okhttp3.Request.Builder().post( build ).url( uri ).build();
        final Call call = client.newCall( request );
        new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    okhttp3.Response response = call.execute();
                    if (response.isSuccessful()) {
                        onLoadResponseListener.getResponse( response.body().toString() );
                        Log.e( "=======", "登录-----" + response.body() );
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } ).start();

    }

    public void LogonIn(String uri, final OnLoadResponseListener onLoadResponseListener, RequestQueue requestQueue, final String name, final String password) {
        StringRequest request = new StringRequest( Request.Method.POST, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onLoadResponseListener.getResponse( response );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( "===", "----------" + error.getMessage() );
            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put( "ver", "1" );
                map.put( "uid", name );
                map.put( "pwd", password );
                map.put( "device", "0" );
                return map;
            }
        };
        requestQueue.add( request );
    }


}
