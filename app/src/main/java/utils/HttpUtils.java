package utils;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import interFace.OnLoadResponseListener;

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

    public void Logon() {

    }
}
