package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zhuoxin.main.views.R;

import java.util.ArrayList;

import entry.Source;
import interFace.OnResourceListener;
import utils.Task;
import views.Adapter_Menu;

/**
 * Created by Administrator on 2016/10/28.
 */

public class CenterFragment extends ListFragment implements OnResourceListener

{


    Adapter_Menu adapter = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initData();

    }

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
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick( l, v, position, id );
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

    }


    @Override
    public void getResource(ArrayList<Source> source) {

        list = source;

        Log.e( "===", "list1===" + list.size() );
        Log.e( "===", "jhjdh" + source );
    }


    private void initData() {
        Task task = new Task();
        task.setListener( this );
        task.execute( PATH );
    }


}
