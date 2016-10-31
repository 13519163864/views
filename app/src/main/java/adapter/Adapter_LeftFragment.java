package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuoxin.main.views.R;

import java.util.ArrayList;

import entry.LeftFragmentInfo;

/**
 * Created by Administrator on 2016/10/31.
 */

public class Adapter_LeftFragment extends BaseAdapter {
    ArrayList<LeftFragmentInfo> mList;
    Context mContext;

    public Adapter_LeftFragment(ArrayList<LeftFragmentInfo> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get( i );
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holdler holdler = null;
        if (view == null) {
            holdler = new Holdler();
            view = LayoutInflater.from( mContext ).inflate( R.layout.left_fragment_show, viewGroup, false );
            holdler.mImg = (ImageView) view.findViewById( R.id.img_left_fragment_menu );
            holdler.mName = (TextView) view.findViewById( R.id.txt_left_fragment_name );
            holdler.mEng = (TextView) view.findViewById( R.id.txt_left_fragment_eng );
            view.setTag( holdler );
        } else {
            holdler = (Holdler) view.getTag();
        }
        holdler.mImg.setImageResource( mList.get( i ).getIcon() );
        holdler.mName.setText( mList.get( i ).getName() );
        holdler.mEng.setText( mList.get( i ).getEng() );
        return view;
    }

    static class Holdler {
        ImageView mImg;
        TextView mName;
        TextView mEng;
    }
}
