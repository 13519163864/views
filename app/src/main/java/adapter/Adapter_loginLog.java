package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhuoxin.main.views.R;

import java.util.ArrayList;

import entry.LoginLog;

/**
 * Created by Administrator on 2016/11/4.
 */

public class Adapter_loginLog extends BaseAdapter {
    ArrayList<LoginLog> mList;
    Context mContext;

    public Adapter_loginLog(ArrayList<LoginLog> mList, Context context) {
        this.mList = mList;
        this.mContext = context;
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
        if (null == view) {
            holdler = new Holdler();
            view = LayoutInflater.from( mContext ).inflate( R.layout.login_log, viewGroup, false );
            holdler.mTime = (TextView) view.findViewById( R.id.txt_login_log_time );
            holdler.mAddress = (TextView) view.findViewById( R.id.txt_login_log_address );
            holdler.mDevice = (TextView) view.findViewById( R.id.txt_login_log_device );
            view.setTag( holdler );
        } else {
            holdler = (Holdler) view.getTag();
        }
        holdler.mTime.setText( mList.get( i ).getTime() );
        holdler.mAddress.setText( mList.get( i ).getAddress() );
        holdler.mDevice.setText( mList.get( i ).getDevice()+"");
        return view;

    }

    static class Holdler {
        TextView mTime;
        TextView mAddress;
        TextView mDevice;
    }
}
