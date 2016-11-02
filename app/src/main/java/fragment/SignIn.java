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

import com.zhuoxin.main.views.R;

/**
 * Created by Administrator on 2016/11/2.
 */

public class SignIn extends Fragment implements View.OnClickListener {

    Button mRegister;
    Button mForgetPassword;
//    Button mLogon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.logon, container, false );
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        mForgetPassword = (Button) view.findViewById( R.id.forget_password );
        mRegister = (Button) view.findViewById( R.id.btn_register );
        Log.e( "===", "-------" + mForgetPassword );
        mRegister.setOnClickListener( this );
        mForgetPassword.setOnClickListener( this );
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
        }
    }
}
