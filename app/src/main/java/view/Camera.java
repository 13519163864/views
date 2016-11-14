package view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import interFace.OnLOadBitmapListener;

/**
 * Created by Administrator on 2016/11/14.
 * 自定义view通过
 */

public class Camera implements SurfaceHolder.Callback {
    android.hardware.Camera camera;
    OnLOadBitmapListener onLOadBitmapListener;

    public Camera(OnLOadBitmapListener onLOadBitmapListener) {
        this.onLOadBitmapListener = onLOadBitmapListener;
    }

    //获取相机数量
    public int getNumbers(Context context) {
        int numberOfCameras = android.hardware.Camera.getNumberOfCameras();
        //如果没有,吐司
        if (numberOfCameras == 0) {
            Toast.makeText( context, "相机不存在", Toast.LENGTH_SHORT ).show();
        }
        //一个,打开后置
        if (numberOfCameras == 1) {
            return 0;
        }
        android.hardware.Camera.CameraInfo cameraInfo = new android.hardware.Camera.CameraInfo();
        for (int i = 0; i < numberOfCameras; i++) {
            android.hardware.Camera.getCameraInfo( i, cameraInfo );//匹配
            if (cameraInfo.facing == android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK) {
                return i;
            }
        }
        return 1;

    }

    //设置焦距
    public void setZoom(int zoom) {
        android.hardware.Camera.Parameters parameters = camera.getParameters();
        parameters.setZoom( zoom );
        camera.setParameters( parameters );
    }

    //拿到最大焦距
    public int getMaxZoom() {
        return camera.getParameters().getMaxZoom();

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        //surface创建
        //打开相机,默认打开后置摄像头
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
        camera = android.hardware.Camera.open();
        //设置参数
        android.hardware.Camera.Parameters parameters = camera.getParameters();
        parameters.setJpegQuality( 80 );
        //设置持续聚焦
        parameters.setFocusMode( android.hardware.Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE );
        //设置闪光灯打开状态
        List<String> flashModes = camera.getParameters().getSupportedFlashModes();
        if (flashModes != null) {
            parameters.setFlashMode( android.hardware.Camera.Parameters.FLASH_MODE_ON );
        }
        parameters.setPictureFormat( ImageFormat.JPEG );
        //设置相机旋转角度
//        parameters.setRotation( 180 );
        camera.setDisplayOrientation( 90 );
        //保存参数
        camera.setParameters( parameters );
        try {
            camera.setPreviewDisplay( surfaceHolder );
        } catch (IOException e) {
            e.printStackTrace();
        }
        //开启预览
        camera.startPreview();
        //设置人脸识别
        camera.startFaceDetection();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
//surface改变
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
//surface销毁
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;

        }
    }

    public void takePicture() {
        camera.takePicture( null, null, new android.hardware.Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] bytes, android.hardware.Camera camera) {
                //字节数转为照片
                Bitmap bitmap = BitmapFactory.decodeByteArray( bytes, 0, bytes.length );
                //存照片
                String parent = Environment.getExternalStorageDirectory().getPath();
                File file = new File( parent + File.separator + System.currentTimeMillis() + ".jpg" );
                try {
                    FileOutputStream outputStream = new FileOutputStream( file );
                    //压缩照片
                    bitmap.compress( Bitmap.CompressFormat.JPEG, 90, outputStream );
                    outputStream.flush();
                    outputStream.close();
                    if (onLOadBitmapListener != null) {
                        onLOadBitmapListener.getBitmpa( bitmap );
                    }
                    //多次拍照
                    camera.startPreview();
//                    camera.release();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } );
//        camera.stopPreview();
    }
}
