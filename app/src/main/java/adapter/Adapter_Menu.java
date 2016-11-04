package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuoxin.main.views.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import entry.Source;

/**
 * Created by Administrator on 2016/10/19.
 */

public class Adapter_Menu extends BaseAdapter {
    ArrayList<Source> mList = new ArrayList<>();
    Context mContext;
    int mBack[] = {R.mipmap.backa, R.mipmap.backb, R.mipmap.backc, R.mipmap.backd, R.mipmap.backe, R.mipmap.backf, R.mipmap.backg, R.mipmap.backh, R.mipmap.backi, R.mipmap.backj, R.mipmap.backl, R.mipmap.backm, R.mipmap.backn, R.mipmap.backo, R.mipmap.backp, R.mipmap.backq, R.mipmap.backr, R.mipmap.backs, R.mipmap.backt, R.mipmap.backu};
    int mResId[] = {R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};
    int mBlack[] = {R.mipmap.blacka, R.mipmap.blackd, R.mipmap.blacke, R.mipmap.blackf, R.mipmap.blackh, R.mipmap.blackg, R.mipmap.blacki, R.mipmap.blackj};

    public Adapter_Menu(ArrayList<Source> list, Context mContext) {
        mList.clear();
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
            holdel.mIcon = (CircleImageView) view.findViewById( R.id.img_menu_adapter_icon );
            holdel.mTitle = (TextView) view.findViewById( R.id.txt_menu_adapter_title );
            holdel.mStamp = (TextView) view.findViewById( R.id.txt_menu_adapter_stamp );
            holdel.mSummary = (TextView) view.findViewById( R.id.txt_menu_adapter_summary );
            holdel.mLinear = (LinearLayout) view.findViewById( R.id.liner );
            view.setTag( holdel );
        } else {
            holdel = (Holdel) view.getTag();
        }

        Glide.with( mContext ).load( mList.get( i ).getIcon() ).into( holdel.mIcon );
        holdel.mTitle.setText( mList.get( i ).getTitle() );
        holdel.mSummary.setText( mList.get( i ).getSummary() + "..." );
        holdel.mStamp.setText( mList.get( i ).getStamp() + " " );
        holdel.mLinear.setBackgroundResource( mResId[i % mResId.length] );


        //给inageview添加渐变动画
        Animation loadAnimation = AnimationUtils.loadAnimation( mContext, R.anim.alpha );
        //设置动画时间
        loadAnimation.setDuration( 1000 );
        //设置动画播放次数
        loadAnimation.setRepeatCount( 2);
        //开启动画
        holdel.mIcon.startAnimation( loadAnimation );


//        holdel.mLinear.setBackgroundDrawable( R.mipmap.ki );
        return view;
    }

    static class Holdel {
        CircleImageView mIcon;
        TextView mTitle;
        TextView mStamp;
        TextView mSummary;
        LinearLayout mLinear;
    }

}
