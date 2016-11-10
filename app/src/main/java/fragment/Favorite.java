package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zhuoxin.main.views.Activity_Favorite;
import com.zhuoxin.main.views.R;

import java.util.ArrayList;

import adapter.Adapter_Menu;
import entry.Source;
import utils.SqlUtils;

/**
 * Created by Administrator on 2016/11/7.
 * 收藏界面碎片
 */

public class Favorite extends Fragment implements AdapterView.OnItemClickListener {
    //数据源
    static ArrayList<Source> list;
    //加载组件
    ListView mLst;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局
        return LayoutInflater.from( getContext() ).inflate( R.layout.favorite, container, false );
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        mLst = (ListView) view.findViewById( R.id.lst_favorite );

//获取数据源,收藏界面的数据源是从数据库查询而来
        list = new SqlUtils( getContext() ).checkNews();
//        Log.e( "===", "list=======" + list.get( 0 ).getSummary() );
//加载适配器,此时适配器使用centerfragment的适配器
        Adapter_Menu adapter = new Adapter_Menu( list, getContext() );
        mLst.setAdapter( adapter );
        //listview设置监听事件
        mLst.setOnItemClickListener( this );

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //携带数据跳转
        Intent intent = new Intent( getActivity(), Activity_Favorite.class );
        intent.putExtra( "i", i );
        startActivity( intent );
    }

    //提供外部接口供下一页面获取数据
    public static ArrayList<Source> getList() {
        return list;
    }
}
