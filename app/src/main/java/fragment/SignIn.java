package fragment;

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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.zhuoxin.main.views.R;

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
                transaction2.replace( R.id.framlayout, new Register() );
                transaction2.commit();
                break;
            case R.id.forget_password:
                FragmentTransaction transaction3 = getActivity().getSupportFragmentManager().beginTransaction();
                transaction3.replace( R.id.framlayout, new ForgetPassword() );
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

    @Override
    public void getResponse(String message) {
        Log.e( "===", "message" + message );
    }
}
