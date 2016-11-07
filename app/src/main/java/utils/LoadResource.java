package utils;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import entry.ViewInfo;
import interFace.OnLoadResource;

/**
 * Created by Administrator on 2016/10/24.
 */

public class LoadResource extends AsyncTask<String, String, String> {
    InputStream mInPutStream;
    HttpURLConnection mConnection;

    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url = new URL(strings[0]);
            mConnection = (HttpURLConnection) url.openConnection();
            int responseCode = mConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                byte[] bytes = new byte[2 * 1024];
                int len = 0;
                mInPutStream = mConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                while ((len = mInPutStream.read(bytes)) != -1) {
                    buffer.append(new String(bytes, 0, len));
                }

                return buffer.toString();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (mConnection != null) {
                try {
                    mInPutStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            JSONObject object = new JSONObject(result);
            Log.e("===", "ascjzjk");
            String message = object.getString("message");
            int status = object.getInt("status");
            Log.e("===", "message" + message + "status" + status);
            JSONArray data = object.getJSONArray("data");
            Log.e("ads", "afsdsd");
            for (int i = 0; i < data.length(); i++) {
                JSONObject jsonObject = data.getJSONObject(i);
                Log.e("adsdf", "sdfdsd");
                String summary = jsonObject.getString("summary");
                String icon = jsonObject.getString("icon");
                String stamp = jsonObject.getString("stamp");
                String title = jsonObject.getString("title");
                int nid = jsonObject.getInt("nid");
                String link = jsonObject.getString("link");
                int type = jsonObject.getInt("type");
                if (null != onLoadResource) {
                    onLoadResource.setResource(new ViewInfo(summary, icon, stamp, title, nid, link, type));
                }

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public OnLoadResource onLoadResource;

    public void setOnLoadResource(OnLoadResource onLoadResource) {
        this.onLoadResource = onLoadResource;
    }

    ;
}
