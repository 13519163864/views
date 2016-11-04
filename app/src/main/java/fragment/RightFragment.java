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
 */

public class RightFragment extends Fragment implements View.OnClickListener {
    ImageView mSignIn;
    TextView mTxtSign;
    int result;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.right_fragment, container, false );
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        mSignIn = (ImageView) view.findViewById( R.id.img_sign );
        mTxtSign = (TextView) view.findViewById( R.id.txt_sign );
        mTxtSign.setOnClickListener( this );
        mSignIn.setOnClickListener( this );
        SharedPreferences signIn = getActivity().getSharedPreferences( "signIn", Context.MODE_PRIVATE );
        SharedPreferences userInfo = getActivity().getSharedPreferences( "UserInfo", Context.MODE_PRIVATE );
        result = signIn.getInt( "result", 0 );
        Log.e( "===", "result+++" + result );
        String portrait = userInfo.getString( "portrait", null );
        Log.e( "===", "portrait+++" + portrait );

        String uid = userInfo.getString( "uid", null );
        Log.e( "===", "uid+++" + uid );

        if (result == 0) {
            Glide.with( getActivity() ).load( portrait ).into( mSignIn );
            mTxtSign.setText( uid );
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_sign:
                if (result == 0) {
                    startActivity( new Intent( getActivity(), Activity_UserCenter.class ) );
                } else {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace( R.id.framlayout_main, new SignIn() );
                    transaction.commit();

                    new Activity_Menu().slidingMenu.toggle();
                }
//                drawerLayout.closeDrawer( Gravity.RIGHT );
                break;
            case R.id.txt_sign:
                if (result == 0) {
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
