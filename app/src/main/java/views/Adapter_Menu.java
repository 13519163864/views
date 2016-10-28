package views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuoxin.main.views.R;

import java.util.ArrayList;

import entry.Source;

/**
 * Created by Administrator on 2016/10/19.
 */

public class Adapter_Menu extends BaseAdapter {
    ArrayList<Source> mList;
    Context mContext;

    public Adapter_Menu(ArrayList<Source> list, Context mContext) {
        this.mList = list;
        this.mContext = mContext;

    }


    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
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
        Holdel holdel = null;
        if (null == view) {
            holdel = new Holdel();
            view = LayoutInflater.from( mContext ).inflate( R.layout.menu_adapter_layout, viewGroup, false );
            holdel.mIcon = (ImageView) view.findViewById( R.id.img_menu_adapter_icon );
            holdel.mTitle = (TextView) view.findViewById( R.id.txt_menu_adapter_title );
            holdel.mStamp = (TextView) view.findViewById( R.id.txt_menu_adapter_stamp );
            holdel.mSummary = (TextView) view.findViewById( R.id.txt_menu_adapter_summary );
            view.setTag( holdel );
        } else {
            holdel = (Holdel) view.getTag();
        }

        Glide.with( mContext ).load( mList.get( i ).getIcon() ).into( holdel.mIcon );
        holdel.mTitle.setText( mList.get( i ).getTitle() );
        holdel.mSummary.setText( mList.get( i ).getSummary() + "..." );
        holdel.mStamp.setText( mList.get( i ).getStamp() + " " );
        return view;
    }

    static class Holdel {
        ImageView mIcon;
        TextView mTitle;
        TextView mStamp;
        TextView mSummary;
    }

}
