package com.zhuoxin.main.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
//import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by Administrator on 2016/10/18.
 */

public class Activity_Menu extends AppCompatActivity {
//    public static final String PATH = "http://118.244.212.82:9092/newsClient/path/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
//    ListView mLst;
//    ArrayList<Source> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_menu );

//        initView();
//        initData();

//        Log.e("===","list==="+list.size());

//        mLst.setOnItemClickListener(this);
//        utils.LoadResource task = new LoadResource();
//        task.setOnLoadResource(this);

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
