package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zhuoxin.main.views.R;

import java.util.ArrayList;

import adapter.Adapter_LeftFragment;
import entry.LeftFragmentInfo;

/**
 * Created by Administrator on 2016/10/28.
 */

public class LeftFragment extends Fragment{
    DrawerLayout drawerLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.left_fragment, container, false );
    }

    ArrayList<LeftFragmentInfo> mList = new ArrayList<>();
    ListView mLst;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        mLst = (ListView) view.findViewById( R.id.lst_left_menu );
        drawerLayout = (DrawerLayout) view.findViewById( R.id.draw_layout );
        initData();
        Adapter_LeftFragment adapter = new Adapter_LeftFragment( mList, getContext() );
        mLst.setAdapter( adapter );

    }

    public void initData() {
        mList.add( new LeftFragmentInfo( R.mipmap.biz_navigation_tab_news, "新闻", "NEWS" ) );
        mList.add( new LeftFragmentInfo( R.mipmap.biz_navigation_tab_read, "收藏", "FAVORITY" ) );
        mList.add( new LeftFragmentInfo( R.mipmap.biz_navigation_tab_local_news, "本地", "LOCAL" ) );
        mList.add( new LeftFragmentInfo( R.mipmap.biz_navigation_tab_ties, "跟帖", "COMMENT" ) );
        mList.add( new LeftFragmentInfo( R.mipmap.biz_navigation_tab_pics, "图片", "PHOTO" ) );
    }


}
