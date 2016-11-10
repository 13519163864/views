package fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.zhuoxin.main.views.Activity_UserCenter;
import com.zhuoxin.main.views.R;

import org.json.JSONException;
import org.json.JSONObject;

import entry.UriInfo;
import interFace.OnLoadResponseListener;
import utils.HttpUtils;

/**
 * Created by Administrator on 2016/11/2.
 */

public class SignIn extends Fragment implements View.OnClickListener, OnLoadResponseListener {

    Button mRegister;
    Button mForgetPassword;
    Button mLogon;
    EditText name;
    EditText password;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.logon, container, false );
    }

    RequestQueue requestQueue;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        mForgetPassword = (Button) view.findViewById( R.id.forget_password );
        mRegister = (Button) view.findViewById( R.id.btn_register );
        mLogon = (Button) view.findViewById( R.id.logon );
        mLogon.setOnClickListener( this );
        name = (EditText) view.findViewById( R.id.edt_logon_name );
        password = (EditText) view.findViewById( R.id.edt_logon_password );
        Log.e( "===", "-------" + mForgetPassword );
        mRegister.setOnClickListener( this );
        mForgetPassword.setOnClickListener( this );
        requestQueue = Volley.newRequestQueue( getActivity() );
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                FragmentTransaction transaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                transaction2.replace( R.id.framlayout_main, new Register() );
                transaction2.commit();

                break;
            case R.id.forget_password:
                FragmentTransaction transaction3 = getActivity().getSupportFragmentManager().beginTransaction();
                transaction3.replace( R.id.framlayout_main, new ForgetPassword() );
                transaction3.commit();
                break;
            case R.id.logon:
                String Name = name.getText().toString();
                String Password = password.getText().toString();
//                new HttpUtils().Logon( UriInfo.BaseUrl + UriInfo.LOGON, Name, Password, this );
                new HttpUtils().LogonIn( UriInfo.BaseUrl + UriInfo.LOGON, this, requestQueue, Name, Password );
                Log.e( "===", "登录按钮被点击了" );
                break;
        }
    }

    static SharedPreferences sharedPreferences;

    @Override
    public void getResponse(String message) {
        try {
            sharedPreferences = getActivity().getSharedPreferences( "signIn", Context.MODE_PRIVATE );
            SharedPreferences.Editor edit = sharedPreferences.edit();
            JSONObject jsonObject = new JSONObject( message );
            String mesage = jsonObject.getString( "message" );
            edit.putString( "message", mesage );
            int status = jsonObject.getInt( "status" );
            edit.putInt( "status", status );
            edit.commit();
            String message1 = sharedPreferences.getString( "message", null );
            int status1 = sharedPreferences.getInt( "status", 0 );
            Log.e( "---", "message1=====" + mesage );
//            if ("登录成功".equals( expl )) {
            if (status1==0) {
                startActivity( new Intent( getActivity(), Activity_UserCenter.class ) );
                getActivity().overridePendingTransition( R.anim.set, R.anim.exit );
                getActivity().finish();
            } else {

                Log.e( "====", "登录失败" );
                Toast.makeText( getActivity(), message1, Toast.LENGTH_SHORT ).show();
//                name.setText( "" );
                password.setText( "" );
            }
            Log.e( "===", "message" + mesage + "status" + status );
            JSONObject data = jsonObject.getJSONObject( "data" );
            Log.e( "====", "data===" + android.R.attr.data );
//            JSONObject object = android.R.attr.data.getJSONObject( 1 );
            int result = data.getInt( "result" );
            String token = data.getString( "token" );
            String explain = data.getString( "explain" );
            Log.e( "====", "result==" + result + "token==" + token + "explain==" + explain );


            edit.putInt( "result", result );
            edit.putString( "token", token );
            edit.putString( "explain", explain );

            Log.e( "===", "tianjial" );
            edit.commit();


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e( "===", "message" + message );
    }

    @Override
    public void getCmtList(String data) {

    }

}
