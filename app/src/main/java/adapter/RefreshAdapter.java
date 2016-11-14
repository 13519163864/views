package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuoxin.main.views.R;

import java.util.ArrayList;

import entry.Source;

/**
 * Created by Administrator on 2016/11/11.
 */

public class RefreshAdapter extends RecyclerView.Adapter<RefreshAdapter.Holdler> {
    ArrayList<Source> mList = new ArrayList<>();
    Context mContext;
    int mBack[] = {R.mipmap.ta, R.mipmap.tb, R.mipmap.tc, R.mipmap.td, R.mipmap.te, R.mipmap.tf, R.mipmap.tg, R.mipmap.th, R.mipmap.ti, R.mipmap.tj, R.mipmap.tk, R.mipmap.tl, R.mipmap.tm, R.mipmap.tn, R.mipmap.to, R.mipmap.tp, R.mipmap.tw, R.mipmap.tr, R.mipmap.tu, R.mipmap.tv};

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;

    }

    public RefreshAdapter(ArrayList<Source> mList, Context mContext) {
        mList.clear();
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public Holdler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( mContext ).inflate( R.layout.refresh, parent, false );

        return new Holdler( view );
    }

    @Override
    public void onBindViewHolder(final Holdler holder, int position) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick( holder.itemView, pos );
                }
            } );
            holder.itemView.setOnLongClickListener( new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick( holder.itemView, pos );
                    return false;
                }
            } );
        }
        Glide.with( mContext ).load( mList.get( position ).getIcon() ).into( holder.mIcon );
        holder.mTitle.setText( mList.get( position ).getTitle() );
        holder.mSummary.setText( mList.get( position ).getSummary() + "..." );
        holder.mStamp.setText( mList.get( position ).getStamp() + " " );
        holder.mLinear.setBackgroundResource( mBack[position % mBack.length] );
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class Holdler extends RecyclerView.ViewHolder {
        ImageView mIcon;
        TextView mTitle;
        TextView mStamp;
        TextView mSummary;
        LinearLayout mLinear;

        public Holdler(View itemView) {
            super( itemView );
            mIcon = (ImageView) itemView.findViewById( R.id.img_menu_adapter_icon_freash );
            mTitle = (TextView) itemView.findViewById( R.id.txt_menu_adapter_title_freash );
            mStamp = (TextView) itemView.findViewById( R.id.txt_menu_adapter_stamp_freash );
            mSummary = (TextView) itemView.findViewById( R.id.txt_menu_adapter_summary_freash );
            mLinear = (LinearLayout) itemView.findViewById( R.id.liner_freash );
        }
    }
}
