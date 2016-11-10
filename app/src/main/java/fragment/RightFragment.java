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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuoxin.main.views.Activity_Menu;
import com.zhuoxin.main.views.Activity_UserCenter;
import com.zhuoxin.main.views.R;

/**
 * Created by Administrator on 2016/10/28.
 * slidingmenu右侧界面,用于登录,此处需根据登录状态设置显示内容
 */

public class RightFragment extends Fragment implements View.OnClickListener {
    ImageView mSignIn;
    TextView mTxtSign;
    int result;
    int status;
    String token;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局
        return inflater.inflate( R.layout.right_fragment, container, false );
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        initView( view );
        //获取SharedPreferences判断用户登录状态以及服务器响应数据
        SharedPreferences signIn = getActivity().getSharedPreferences( "signIn", Context.MODE_PRIVATE );
        SharedPreferences userInfo = getActivity().getSharedPreferences( "UserInfo", Context.MODE_PRIVATE );
        //登录状态
        result = signIn.getInt( "result", 0 );
        Log.e( "===", "result+++" + result );
        //响应状态
        status = userInfo.getInt( "status", 0 );
        Log.e( "===", "status+++" + status );
        //用户令牌
        token = signIn.getString( "token", null );

        String portrait = userInfo.getString( "portrait", null );
        Log.e( "===", "portrait+++" + portrait );

        String uid = userInfo.getString( "uid", null );
        Log.e( "===", "uid+++" + uid );

        if (result == 0 && token != null && status == 0) {
            //如果响应正确登录成功并且令牌存在,则设置内容为用户名以及头像
            Glide.with( getActivity() ).load( portrait ).into( mSignIn );
            mTxtSign.setText( uid );
        } else {
            //否则为默认
            mSignIn.setImageResource( R.mipmap.biz_pc_main_info_profile_avatar_bg_dark );
            mTxtSign.setText( "立即登录" );
        }

    }

    private void initView(View view) {
        mSignIn = (ImageView) view.findViewById( R.id.img_sign );
        mTxtSign = (TextView) view.findViewById( R.id.txt_sign );
        mTxtSign.setOnClickListener( this );
        mSignIn.setOnClickListener( this );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_sign:
                //若响应成功,则进入用户中心
                if (result == 0 && token != null && status == 0) {
                    startActivity( new Intent( getActivity(), Activity_UserCenter.class ) );
                } else {
                    //否则进行登录操作
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace( R.id.framlayout_main, new SignIn() );
                    transaction.commit();
//设置slidingmenu自动识别开关状态
                    new Activity_Menu().slidingMenu.toggle();
                }
//                drawerLayout.closeDrawer( Gravity.RIGHT );
                break;
            //同上
            case R.id.txt_sign:
                if (result == 0 && status == 0 && token != null) {
                    startActivity( new Intent( getActivity(), Activity_UserCenter.class ) );
                } else {
                    FragmentTransaction transaction1 = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction1.replace( R.id.framlayout_main, new SignIn() );
                    transaction1.commit();
                    new Activity_Menu().showConten();
                }

//                drawerLayout.closeDrawer( Gravity.RIGHT );
                break;

        }
    }

}
