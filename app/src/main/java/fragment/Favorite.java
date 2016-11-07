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
 */

public class Favorite extends Fragment implements AdapterView.OnItemClickListener {
  static   ArrayList<Source> list;
    ListView mLst;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from( getContext() ).inflate( R.layout.favorite, container, false );
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        mLst = (ListView) view.findViewById( R.id.lst_favorite );


        list = new SqlUtils( getContext() ).checkNews();
//        Log.e( "===", "list=======" + list.get( 0 ).getSummary() );

        Adapter_Menu adapter = new Adapter_Menu( list, getContext() );
        mLst.setAdapter( adapter );
        mLst.setOnItemClickListener( this );

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent( getActivity(), Activity_Favorite.class );
        intent.putExtra( "i", i );
        startActivity( intent );
    }
    public static ArrayList<Source> getList(){
        return list;
    }
}
