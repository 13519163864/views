package fragment;

import android.content.Context;
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
            edit.putString( "message", mesage );
            edit.putInt( "status", status );
            edit.putInt( "result", result );
            edit.putString( "token", token );
            edit.putString( "explain", explain );
            Log.e( "===", "tianjial" );
            edit.commit();
            int result1 = sharedPreferences.getInt( "result", 0 );
            String explain1 = sharedPreferences.getString( "explain", null );
            if (result1 == 0) {
                Toast.makeText( getActivity(), explain1 + "请登录", Toast.LENGTH_SHORT ).show();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace( R.id.framlayout_main, new SignIn() );
                transaction.commit();
            } else if (result1 == -1) {
                Toast.makeText( getActivity(), explain1, Toast.LENGTH_SHORT ).show();

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace( R.id.framlayout_main, new CenterFragment() );
                transaction.commit();
            } else if (result1 == -2) {
                Toast.makeText( getActivity(), explain1 + "请重新输入", Toast.LENGTH_SHORT ).show();
                name.setText( "" );
            } else if (result1 == -3) {
                Toast.makeText( getActivity(), explain1 + "请重新输入", Toast.LENGTH_SHORT ).show();
                emil.setText( "" );

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void getCmtList(String data) {

    }


    /**
     * 合法E-mail地址：
     * 1. 必须包含一个并且只有一个符号“@”
     * 2. 第一个字符不得是“@”或者“.”
     * 3. 不允许出现“@.”或者.@
     * 4. 结尾不得是字符“@”或者“.”
     * 5. 允许“@”前的字符中出现“＋”
     * 6. 不允许“＋”在最前面，或者“＋@”
     * <p>
     * <p>
     * <p>
     * 字符描述：
     * ^ ：匹配输入的开始位置。
     * \：将下一个字符标记为特殊字符或字面值。
     * ：匹配前一个字符零次或几次。
     * + ：匹配前一个字符一次或多次。
     * (pattern) 与模式匹配并记住匹配。
     * x|y：匹配 x 或 y。
     * [a-z] ：表示某个范围内的字符。与指定区间内的任何字符匹配。
     * \w ：与任何单词字符匹配，包括下划线。
     * $ ：匹配输入的结尾。
     */

    @Override
    public void onClick(View view) {
        Emil = emil.getText().toString();
        Name = name.getText().toString();
        Password = password.getText().toString();
        if (!Name.matches( "[a-zA-Z]{6,10}" )) {
            Toast.makeText( getContext(), "用户名格式不正确,请重新输入", Toast.LENGTH_SHORT ).show();
        } else if (!Emil.matches( "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$" )) {
            Toast.makeText( getContext(), "邮箱格式不正确,请重新输入", Toast.LENGTH_SHORT ).show();
        } else {

            new HttpUtils().SignIn( UriInfo.BaseUrl + UriInfo.SIGNIN, this, requestQueue, Emil, Name, Password );
            Log.e( "===", "注册按钮被点击了" );
        }


    }
}
