package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuoxin.main.views.Activity_Menu;
import com.zhuoxin.main.views.R;

/**
 * Created by Administrator on 2016/10/28.
 */

public class RightFragment extends Fragment implements View.OnClickListener {
    ImageView mSignIn;
    TextView mTxtSign;

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_sign:
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace( R.id.framlayout_main, new SignIn() );
                transaction.commit();

                new Activity_Menu().slidingMenu.toggle();
//                drawerLayout.closeDrawer( Gravity.RIGHT );
                break;
            case R.id.txt_sign:
                FragmentTransaction transaction1 = getActivity().getSupportFragmentManager().beginTransaction();
                transaction1.replace( R.id.framlayout_main, new SignIn() );
                transaction1.commit();
                new Activity_Menu().showConten();


//                drawerLayout.closeDrawer( Gravity.RIGHT );
                break;
        }
    }

}
