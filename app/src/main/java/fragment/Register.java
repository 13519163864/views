package fragment;

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
