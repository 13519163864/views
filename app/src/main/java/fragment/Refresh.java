package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.zhuoxin.main.views.Activity_ViewsShow;
import com.zhuoxin.main.views.R;

import java.util.ArrayList;

import adapter.RefreshAdapter;
import entry.Source;
import interFace.OnResourceListener;
import utils.Task;

/**
 * Created by Administrator on 2016/11/11.
 */

public class Refresh extends Fragment implements OnResourceListener {

    static ArrayList<Source> list = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    public static final String PATH = "http://118.244.212.82:9092/newsClient/path/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321";
    public static final String PATH_BEF = "http://118.244.212.82:9092/newsClient/path/news_list?ver=1&subid=1&dir=2&nid=1&stamp=20140321";
    RefreshAdapter adapter;
    Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.refesh, container, false );
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById( R.id.swipe_layout );
        recyclerView = (RecyclerView) view.findViewById( R.id.recy_layout );
        handler = new Handler();

        recyclerView.setLayoutManager( new StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL ) );
        Task task = new Task();
        task.setListener( this );
        task.execute( PATH );
        adapter = new RefreshAdapter( list, getContext() );
        recyclerView.setAdapter( adapter );
        adapter.notifyDataSetChanged();

//        //设置进度圈的大小
//        swipeRefreshLayout.setSize( SwipeRefreshLayout.LARGE );
//        //设置进度动画的颜色
//        swipeRefreshLayout.setColorSchemeResources( R.color.colorAccent, R.color.colorPrimaryDark );
//        //设置进度圈的背景色
////        swipeRefreshLayout.setProgressBackgroundColor( Color.parseColor( "#949" ) );
//        swipeRefreshLayout.setProgressViewEndTarget( true
//                , 100 );
//
        swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing( false );
                        initData();
                        adapter.notifyDataSetChanged();
                    }
                }, 2000 );
            }
        } );

        adapter.setOnItemClickListener( new RefreshAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent( getActivity(), Activity_ViewsShow.class );
                intent.putExtra( "position", position );
                startActivity( intent );
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Animation animation = AnimationUtils.loadAnimation( getContext(), R.anim.lode );
                animation.setDuration( 2000 );
                view.startAnimation( animation );
            }
        } );


    }


// {
//        @Override
//        public void handleMessage (Message msg){
//            super.handleMessage( msg );
//            switch (msg.what) {
//                case 1:
//                    swipeRefreshLayout.setRefreshing( false );
//                    adapter.notifyDataSetChanged();
//                    break;
//                default:
//                    break;
//            }
//        }
//    }

    ;

    public ArrayList<Source> getList() {
        return list;
    }

    public void initData() {
        Task task = new Task();
        task.setListener( this );
        task.execute( PATH_BEF );
    }

    @Override
    public void getResource(Source source) {

    }

    @Override
    public void getAllData(Source source) {
        list.add( source );

    }
}
