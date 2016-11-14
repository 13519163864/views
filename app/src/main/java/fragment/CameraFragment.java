package fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.zhuoxin.main.views.R;
import com.zhuoxin.main.views.ShowActivity;

import interFace.OnLOadBitmapListener;
import view.Camera;

/**
 * Created by Administrator on 2016/11/14.
 * 自定义照相机碎片
 * 使用surfaceview  其内部嵌套了一个surface用于显示图像类的东西
 */

public class CameraFragment extends Fragment implements OnLOadBitmapListener, View.OnClickListener {
    Button take;
    SurfaceView surfaceView;
    SeekBar seekBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局
        return inflater.inflate( R.layout.camera, container, false );
    }

    Camera camera;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        take = (Button) view.findViewById( R.id.btn_camera );
        surfaceView = (SurfaceView) view.findViewById( R.id.suffaceview );
        seekBar = (SeekBar) view.findViewById( R.id.seekbar );
        SurfaceHolder holder = surfaceView.getHolder();
        //设置参数
        holder.setSizeFromLayout();
        //设置屏幕常亮
        holder.setKeepScreenOn( true );
        holder.setFormat( PixelFormat.JPEG );
        //SURFACE_TYPE_PUSH_BUFFERS设置surface中的数据来自于照相机
        holder.setType( SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS );
        //添加回调
        camera = new Camera( this );
        holder.addCallback( camera );
        seekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                camera.setZoom( i );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                int maxZoom = camera.getMaxZoom();
                seekBar.setMax( maxZoom );

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );
        take.setOnClickListener( this );

    }

    @Override
    public void getBitmpa(Bitmap bitmap) {
        Log.e( "====","跳转--------------------" );
        Intent intent = new Intent( getContext(), ShowActivity.class );
        intent.putExtra( "img", bitmap );
        startActivity( intent );
        Toast.makeText( getContext(), "OK", Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_camera:
                camera.takePicture();
                break;
        }
    }
}
