package adapter;

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

import entry.CmtListInfo;

/**
 * Created by Administrator on 2016/11/9.
 * 评论内容的适配器
 */

public class AdapterCommit extends BaseAdapter {
    ArrayList<CmtListInfo> mList = new ArrayList<>();
    Context mContext;

    public AdapterCommit(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<CmtListInfo> mList) {
        this.mList = mList;
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
            view = LayoutInflater.from( mContext ).inflate( R.layout.commit_adapter, viewGroup, false );
            holdler.mPortrite = (ImageView) view.findViewById( R.id.img_commit_adapter_portrit );
            holdler.mUid = (TextView) view.findViewById( R.id.txt_commit_adapter_uid );
            holdler.mStamp = (TextView) view.findViewById( R.id.txt_commit_adapter_stamp );
            holdler.mContent = (TextView) view.findViewById( R.id.txt_commit_adapter_content );
            view.setTag( holdler );
        } else {
            holdler = (Holdler) view.getTag();
        }
        Glide.with( mContext ).load( mList.get( i ).getPortrait() ).into( holdler.mPortrite );
        holdler.mUid.setText( mList.get( i ).getUid() );
        holdler.mStamp.setText( mList.get( i ).getStamp() );
        holdler.mContent.setText( mList.get( i ).getContent() );
        return view;
    }

    static class Holdler {
        ImageView mPortrite;
        TextView mUid;
        TextView mStamp;
        TextView mContent;
    }
}
