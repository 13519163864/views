package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.zhuoxin.main.views.Activity_ViewsShow;
import com.zhuoxin.main.views.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import adapter.Adapter_Menu;
import entry.Source;
import interFace.OnResourceListener;
import me.maxwin.view.XListView;
import utils.Task;

/**
 * Created by Administrator on 2016/10/28.
 */

public class CenterFragment extends Fragment implements XListView.IXListViewListener, OnResourceListener, AdapterView.OnItemClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.menu, container, false );
        return view;
    }

    Adapter_Menu adapter;
    public static final String PATH = "http://118.244.212.82:9092/newsClient/path/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321";
    public static final String PATH_BEF = "http://118.244.212.82:9092/newsClient/path/news_list?ver=1&subid=1&dir=2&nid=1&stamp=20140321";
    XListView mLst;

    static ArrayList<Source> list = new ArrayList<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated( view, savedInstanceState );

        handler = new Handler();
        mLst = (XListView) view.findViewById( R.id.lst_menu );

        Task task = new Task();
        task.setListener( this );
        task.execute( PATH );


        //上拉加载
        mLst.setPullLoadEnable( true );
        //下拉刷新
        mLst.setPullRefreshEnable( true );
        //设置监听

        mLst.setXListViewListener( this );

        mLst.setOnItemClickListener( this );

    }


//    @Override
//    public void getResource(ArrayList<Source> source) {
//        list = source;
//        Log.e( "===", "list" + list.size() );
//        Adapter_Menu adapter = new Adapter_Menu( list, getContext() );
//        mLst.setAdapter( adapter );
//
//    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent( getActivity(), Activity_ViewsShow.class );
        intent.putExtra( "i", i );
        startActivity( intent );
    }

    @Override
    public void onRefresh() {
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {
                stop();
            }
        }, 2000 );
    }

    @Override
    public void onLoadMore() {
        Task task = new Task();
        task.setListener( this );
        task.execute( PATH_BEF );
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {
                stop();
            }
        }, 2000 );
    }

    public void stop() {
        mLst.stopLoadMore();
        mLst.stopRefresh();
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        String date = format.format( new Date( System.currentTimeMillis() ) );
        mLst.setRefreshTime( date );
    }

    Handler handler;


    @Override
    public void getAllData(Source source) {
        list.add( source );
        adapter = new Adapter_Menu( list, getContext() );
        mLst.setAdapter( adapter );
        adapter.notifyDataSetChanged();
        Log.e( "====","++++++++source"+list.size() );
    }

    @Override
    public void getResource(Source source) {


    }


    public ArrayList<Source> getList() {
        return list;
    }
}
