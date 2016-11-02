package view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhuoxin.main.views.R;

/**
 * Created by Administrator on 2016/11/1.
 */

public class ActionBarView extends LinearLayout {
    TextView mContent;

    public ActionBarView(Context context) {
        this( context, null );
    }

    public ActionBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super( context, attrs, defStyleAttr );
        initView( context );
    }


    public ActionBarView(Context context, AttributeSet attrs) {
        this( context, attrs, 0 );
    }

    private void initView(Context context) {
        inflate( context, R.layout.action_bar_view, this );
        mContent= (TextView) findViewById( R.id.txt_action_bar );
    }

    public void setListener(String content) {
        mContent.setText( content );
    }


}
