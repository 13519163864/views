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

    //    public void Logon(String uri, String nane, String password, final OnLoadResponseListener onLoadResponseListener) {//okhttp同步post
//        OkHttpClient client = new OkHttpClient();
//        FormBody build = new FormBody.Builder().add( "ver", "1" ).add( "uid", nane ).add( "pwd", password ).add( "device", "0" ).build();
//        final okhttp3.Request request = new okhttp3.Request.Builder().post( build ).url( uri ).build();
//        final Call call = client.newCall( request );
//        new Thread( new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    okhttp3.Response response = call.execute();
//
//
//
//
//
//
//
////
////                        JSONObject jsonObject = new JSONObject( message );
////                        String mesage = jsonObject.getString( "message" );
////                        int status = jsonObject.getInt( "status" );
////                        Log.e( "===", "message" + mesage + "status" + status );
////                        JSONObject data = jsonObject.getJSONObject( "data" );
////                        Log.e( "====", "data===" + android.R.attr.data );
//////            JSONObject object = android.R.attr.data.getJSONObject( 1 );
////                        int result = data.getInt( "result" );
////                        String token = data.getString( "token" );
////                        String explain = data.getString( "explain" );
////                        Log.e( "====", "result==" + result + "token==" + token + "explain==" + explain );
////                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences( "signIn", Context.MODE_PRIVATE );
////                        SharedPreferences.Editor edit = sharedPreferences.edit();
////                        edit.putInt( "result", result );
////                        edit.putString( "token", token );
////                        edit.putString( "explain", explain );
////                        Log.e( "===", "tianjial" );
////                        edit.commit();
//
//
//
//                        Log.e( "=======", "登录-----" + response.body() );
//                    }else {
//
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        } ).start();
//
//    }

    public void LogonIn(String uri, final OnLoadResponseListener onLoadResponseListener, RequestQueue requestQueue, final String name, final String password) {
        StringRequest request = new StringRequest( Request.Method.POST, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                onLoadResponseListener.getResponse( response );
                Log.e( "========", "解析前" + response );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( "===", "error=======" + error.getMessage() );
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

    public void UserCenter(String uri, final OnLoadResponseListener onLoadResponseListener, RequestQueue requestQueue, final String token) {
        StringRequest request = new StringRequest( Request.Method.POST, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                onLoadResponseListener.getResponse( response );
                Log.e( "========", "解析前" + response );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( "===", "error=======" + error.getMessage() );
            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put( "ver", "1" );
                map.put( "imei", "1" );
                map.put( "token", token );
                return map;
            }
        };
        requestQueue.add( request );
    }


    public void CmtNum(String uri, final OnLoadResponseListener onLoadResponseListener, RequestQueue requestQueue, final String nid) {
        StringRequest request = new StringRequest( Request.Method.POST, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                onLoadResponseListener.getResponse( response );
                Log.e( "========", "解析前" + response );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( "===", "error=======" + error.getMessage() );
            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put( "ver", "1" );
                map.put( "nid", nid );
                return map;
            }
        };
        requestQueue.add( request );
    }

    public void CmtList(String uri, final OnLoadResponseListener onLoadResponseListener, RequestQueue requestQueue, final String nid, final String dir, final String cid) {
        StringRequest request = new StringRequest( Request.Method.POST, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                onLoadResponseListener.getCmtList( response );
                Log.e( "========", "解析前" + response );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( "===", "error=======" + error.getMessage() );
            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put( "ver", "0000000" );
                map.put( "nid", nid );
                map.put( "type", "1" );
                map.put( "stamp", "yyyyMMdd" );
                map.put( "cid", cid );
                map.put( "dir", dir );
                return map;
            }
        };
        requestQueue.add( request );
    }

    public void CmtPublish(String uri, final OnLoadResponseListener onLoadResponseListener, RequestQueue requestQueue, final String nid, final String token, final String ctx) {
        StringRequest request = new StringRequest( Request.Method.POST, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                onLoadResponseListener.getCmtList( response );
                Log.e( "========", "解析前" + response );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( "===", "error=======" + error.getMessage() );
            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put( "ver", "1" );
                map.put( "nid", nid );
                map.put( "token", token );
                map.put( "imei", "10" );
                map.put( "ctx", ctx );
                map.put( "dir", "0" );
                return map;
            }
        };
        requestQueue.add( request );
    }


}
