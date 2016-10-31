package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zhuoxin.main.views.Activity_ViewsShow;
import com.zhuoxin.main.views.R;

import java.util.ArrayList;

import adapter.Adapter_Menu;
import entry.Source;
import interFace.OnResourceListener;
import utils.Task;

/**
 * Created by Administrator on 2016/10/28.
 */

public class CenterFragment extends Fragment implements OnResourceListener, AdapterView.OnItemClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.menu, container, false );
        return view;
    }

    public static final String PATH = "http://118.244.212.82:9092/newsClient/path/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
    ListView mLst;
    ArrayList<Source> list = new ArrayList<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated( view, savedInstanceState );
        mLst = (ListView) view.findViewById( R.id.lst_menu );
        initData();
        mLst.setOnItemClickListener( this );

    }

    private void initData() {
        Task task = new Task();
        task.setListener( this );
        task.execute( PATH );
    }

    @Override
    public void getResource(ArrayList<Source> source) {
        list = source;
        Log.e( "===", "list" + list.size() );
        Adapter_Menu adapter = new Adapter_Menu( list, getContext() );
        mLst.setAdapter( adapter );
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent( getActivity(), Activity_ViewsShow.class );
        intent.putExtra( "i", i );
        startActivity( intent );
    }
}
