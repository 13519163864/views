package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhuoxin.main.views.R;

/**
 * Created by Administrator on 2016/10/31.
 */

public class TextAdap extends BaseAdapter {
    String[] str;
    Context mContext;

    public TextAdap(String[] str, Context mContext) {
        this.str = str;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return str.length;
    }

    @Override
    public Object getItem(int i) {
        return getItem( i );
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = LayoutInflater.from( mContext ).inflate( R.layout.texr, viewGroup, false );
        TextView textView = (TextView) view1.findViewById( R.id.txt_tex );
        textView.setText( str[i] );
        return view1;
    }
}
