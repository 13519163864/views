package utils;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import entry.Data;
import entry.Source;
import interFace.OnResourceListener;

/**
 * Created by Administrator on 2016/10/24.
 */

public class Task extends AsyncTask<String, String, String> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    InputStream inputStream;
    HttpURLConnection urlConnection;

    @Override
    protected String doInBackground(String... strings) {
        String path = strings[0];
        try {
            URL url = new URL( path );
            urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == 200) {
                Log.e( "===", "code==" + urlConnection.getResponseCode() );
                inputStream = urlConnection.getInputStream();
                byte[] bytes = new byte[2 * 1024];
                int len = 0;
                StringBuffer buffer = new StringBuffer();
                while ((len = inputStream.read( bytes )) != -1) {
                    buffer.append( new String( bytes, 0, len ) );
                }
                return buffer.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != urlConnection) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.e( "===", "sdfhb" + result );
        if (null != result && null != onResourceListener) {

            Gson gson = new Gson();
            Type type = new TypeToken<Data>() {
            }.getType();

            Data data = gson.fromJson( result, type );

            Log.e( "===", "message" + data.getMessage() );

            Log.e( "===", "message" + data.getStatus() );

            ArrayList<Source> data1 = data.getData();
            Log.e( "===", "data1==" + data1.get( 0 ).getSummary() );

            for (int i = 0; i < data1.size(); i++) {
                onResourceListener.getAllData( data1.get( i ) );
            }

            Log.e( "===", "监听完成" );
        }


    }

    public OnResourceListener onResourceListener;

    public void setListener(OnResourceListener onResourceListener) {
        this.onResourceListener = onResourceListener;

    }
}
