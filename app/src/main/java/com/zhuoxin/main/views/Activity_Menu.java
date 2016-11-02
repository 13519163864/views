package com.zhuoxin.main.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import fragment.CenterFragment;
import fragment.LeftFragment;
import fragment.RightFragment;
//import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by Administrator on 2016/10/18.
 */

public class Activity_Menu extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    //    public static final String PATH = "http://118.244.212.82:9092/newsClient/path/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
//    ListView mLst;
//    ArrayList<Source> list = new ArrayList<>();
//    DrawerLayout drawerLayout;
    ImageView mHome;
    ImageView mShare;

//    ListView mLst;
    SlidingMenu slidingMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );
        initActinBar( "资讯" );
        slidingMenu = new SlidingMenu( this );
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace( R.id.framlayout_main, new CenterFragment() );
        transaction.commit();
        slidingMenu.setMode( SlidingMenu.LEFT_RIGHT );
        slidingMenu.setMenu( R.layout.left );

        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        transaction1.replace( R.id.framlayout_left, new LeftFragment() );
        transaction1.commit();
        slidingMenu.setSecondaryMenu( R.layout.right );
        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        transaction2.replace( R.id.framlayout_right, new RightFragment() );
        transaction2.commit();
        slidingMenu.setTouchModeAbove( SlidingMenu.TOUCHMODE_FULLSCREEN );
        slidingMenu.setFadeDegree( 0.35f );
        slidingMenu.setBehindOffset( 200 );
        slidingMenu.setShadowDrawable( R.color.colorPrimary );
        slidingMenu.setShadowWidth( 10 );
        slidingMenu.attachToActivity( this, SlidingMenu.SLIDING_CONTENT );
//        slidingMenu.toggle();

//        drawerLayout = (DrawerLayout) findViewById( R.id.draw_layout );
        mHome = (ImageView) findViewById( R.id.img_action_bar_home );
        mShare = (ImageView) findViewById( R.id.img_action_bar_share );

//        mLst = (ListView) findViewById( R.id.lst_left_menu );


//        mLogon = (Button) findViewById( R.id.btn_signIn );
//        mLogon.setOnClickListener( this );

        mHome.setOnClickListener( this );
        mShare.setOnClickListener( this );

//        initView();
//        initData();

//        Log.e("===","list==="+list.size());

//        mLst.setOnItemClickListener(this);
//        utils.LoadResource task = new LoadResource();
//        task.setOnLoadResource(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_action_bar_home:

                if (slidingMenu.isMenuShowing()) {
                    slidingMenu.showContent();
                } else {
                    slidingMenu.showMenu();
                }
//                if (drawerLayout.isDrawerOpen( Gravity.LEFT )) {
//                    drawerLayout.closeDrawer( Gravity.LEFT );
//                } else {
//                    drawerLayout.openDrawer( Gravity.LEFT );
//                }
                break;
            case R.id.img_action_bar_share:
                if (slidingMenu.isSecondaryMenuShowing()) {
                    slidingMenu.showContent();
                } else {
                    slidingMenu.showSecondaryMenu();
                }


//                if (drawerLayout.isDrawerOpen( Gravity.RIGHT )) {
//                    drawerLayout.closeDrawer( Gravity.RIGHT );
//                } else {
//                    drawerLayout.openDrawer( Gravity.RIGHT );
//                }
                break;


        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                slidingMenu.showContent();
                Toast.makeText( this, "新闻", Toast.LENGTH_SHORT ).show();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace( R.id.framlayout, new CenterFragment() );
                transaction.commit();
//                startActivity( new Intent( getActivity(), Activity_Menu.class ) );
                break;
        }
    }

//    private void initView() {
//        mLst = (ListView) findViewById(R.id.lst_menu);
//        mLst.setOnItemClickListener(this);
//    }
//
//    private void initData() {
//        Task task = new Task();
//        task.setListener(this);
//        task.execute(PATH);
//    }

//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Intent intent = new Intent(Activity_Menu.this, Activity_ViewsShow.class);
//        intent.putExtra("i", i);
//        Activity_Menu.this.startActivity(intent);
//    }


//    @Override
//    public void setResource(ViewInfo viewInfo) {
////        String icon = viewInfo.getIcon();
////        String summary = viewInfo.getSummary();
////        String link = viewInfo.getLink();
////        String nid = viewInfo.getNid();
////        String stamp = viewInfo.getStamp();
////        String title = viewInfo.getTitle();
////        int type = viewInfo.getType();
////        list.add(new ViewInfo(summary, icon, stamp, title, nid, link, type));
//        list.add(viewInfo);
//        Log.e("========","view======"+list);
//    }

//    @Override
//    public void getResource(ArrayList<Source> source) {
//        list = source;
//        initAdapter();
//        Log.e("===", "list1===" + list.size());
//        Log.e("===","jhjdh"+source);
//
//    }
//
//    private void initAdapter() {
//        Adapter_Menu adapter = new Adapter_Menu(list, this);
//        mLst.setAdapter(adapter);
//    }
}
