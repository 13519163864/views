package interFace;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.widget.AdapterView;

/**
 * Created by Administrator on 2016/11/11.
 */

public class RecyclerViewClickListener implements RecyclerView.OnItemTouchListener {
    private int mLastDownX, getmLastDownY;
    //记录最小滑动距离
    private int touchSlop;
    private AdapterView.OnItemClickListener onItemClickListener;
    //是否单击事件
    private boolean isSingleTapUp=false;
    //是否长按事件
    private boolean isLongPressUp=false;
    private boolean isMove=false;



    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
