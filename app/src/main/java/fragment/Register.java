package fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.zhuoxin.main.views.R;

import org.json.JSONException;
import org.json.JSONObject;

import entry.UriInfo;
import interFace.OnLoadResponseListener;
import utils.HttpUtils;

/**
 * Created by Administrator on 2016/11/1.
 */

public class Register extends Fragment implements OnLoadResponseListener, View.OnClickListener {
    EditText emil;
    EditText name;
    EditText password;
    Button sign;
    String Emil;
    String Name;
    String Password;
    public static final String REGISTER_INFO = "registerInfo";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.login, container, false );
    }

    RequestQueue requestQueue;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        emil = (EditText) view.findViewById( R.id.edt_mail );
        name = (EditText) view.findViewById( R.id.edt_name );
        password = (EditText) view.findViewById( R.id.edt_password );
        sign = (Button) view.findViewById( R.id.btn_signIn );
        sign.setOnClickListener( this );

        requestQueue = Volley.newRequestQueue( getActivity() );

    }

    @Override
    public void getResponse(String message) {
        Log.e( "===", "-------" + message );
        try {
            JSONObject jsonObject = new JSONObject( message );
            String mesage = jsonObject.getString( "message" );
            int status = jsonObject.getInt( "status" );
            Log.e( "===", "message" + mesage + "status" + status );
            JSONObject data = jsonObject.getJSONObject( "data" );
            Log.e( "====", "data===" + android.R.attr.data );
//            JSONObject object = android.R.attr.data.getJSONObject( 1 );
            int result = data.getInt( "result" );
            String token = data.getString( "token" );
            String explain = data.getString( "explain" );
            Log.e( "====", "result==" + result + "token==" + token + "explain==" + explain );
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences( "register", Context.MODE_PRIVATE );
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt( "result", result );
            edit.putString( "token", token );
            edit.putString( "explain", explain );
            Log.e( "===", "tianjial" );
            edit.commit();

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View view) {
        Emil = emil.getText().toString();
        Name = name.getText().toString();
        Password = password.getText().toString();
        new HttpUtils().SignIn( UriInfo.BaseUrl + UriInfo.SIGNIN, this, requestQueue, Emil, Name, Password );
        Log.e( "===", "注册按钮被点击了" );
    }
}
