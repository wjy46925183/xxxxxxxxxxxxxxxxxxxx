package com.dlg.personal.user.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.AudioAttributes;
import android.media.Image;
import android.media.ImageReader;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.utils.DialogUtils;
import com.common.utils.DimensUtils;
import com.dlg.personal.R;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.user.view.AutoFitTextureView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 作者：xiaoming
 * 主要功能：
 * 创建时间：2017/7/20 14:22
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class VerifyNamePtotoActivity extends BaseActivity {
    private RelativeLayout customcameraLayout;
    private AutoFitTextureView textureview;
    private RelativeLayout imgRe;
    private ImageView bgImg;
    private ImageView getImg;
    private TextView tipsText;
    private Button takepictureBtn;
    private RelativeLayout bottomBtnRe;
    private Button okBtn;
    private Button cancleBtn;
    private static final String TAG = "BindingBankCardCameraActivity";
    private static final String TIP = "身份证识别失败，请重新拍照！";
    private static final int SETIMAGE = 1;
    private static final int HANDLE_FAIL = 2;
    private static final int DISMISS_DIALOG = 3;
    private static final int CERTIFICATE_NUM = 0x60002;
    // 要申请的权限
    private String[] permissions = {Manifest.permission.CAMERA};
    private Dialog dialog;

    private int x;//宽
    private int y;// 长

    private String fileBank = "";
    private DisplayMetrics metrics = null;

    private Bitmap finalBitmap = null;
    Handler mHandler;
    Handler mUIHandler;
    ImageReader mImageReader;
    CaptureRequest.Builder mPreViewBuidler;
    CameraCaptureSession mCameraSession;
    CameraCharacteristics mCameraCharacteristics;
    Ringtone ringtone;
    //相机会话的监听器，通过他得到mCameraSession对象，这个对象可以用来发送预览和拍照请求
    private CameraCaptureSession.StateCallback mSessionStateCallBack;
    //打开相机时候的监听器，通过他可以得到相机实例，这个实例可以创建请求建造者
    private CameraDevice.StateCallback cameraOpenCallBack;
    private ImageReader.OnImageAvailableListener onImageAvaiableListener = new ImageReader.OnImageAvailableListener() {
        @Override
        public void onImageAvailable(ImageReader imageReader) {
            mHandler.post(new ImageSaver(imageReader.acquireNextImage()));
        }
    };
    private Size mPreViewSize;
    //预览图显示控件的监听器，可以监听这个surface的状态
    private TextureView.SurfaceTextureListener mSurfacetextlistener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_verify_name_camera);
        initView();
    }

    private void initView() {
        customcameraLayout = (RelativeLayout) findViewById(R.id.customcamera_layout);
        textureview = (AutoFitTextureView) findViewById(R.id.textureview);
        imgRe = (RelativeLayout) findViewById(R.id.img_re);
        bgImg = (ImageView) findViewById(R.id.bg_img);
        getImg = (ImageView) findViewById(R.id.get_img);
        tipsText = (TextView) findViewById(R.id.tips_text);
        takepictureBtn = (Button) findViewById(R.id.takepicture_btn);
        bottomBtnRe = (RelativeLayout) findViewById(R.id.bottom_btn_re);
        okBtn = (Button) findViewById(R.id.ok_btn);
        cancleBtn = (Button) findViewById(R.id.cancle_btn);
        mUIHandler = new Handler(new InnerCallBack());
        //初始化拍照的声音
        ringtone = RingtoneManager.getRingtone(this, Uri.parse("file:///system/media/audio/ui/camera_click.ogg"));
        AudioAttributes.Builder attr = new AudioAttributes.Builder();
        attr.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION);
        ringtone.setAudioAttributes(attr.build());

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        x = metrics.widthPixels;//获取了屏幕的宽度
        y = metrics.heightPixels;

        initOnClickListener();
        initViews();

        initmSurfacetextlistener();
    }
    private void initmSurfacetextlistener() {
        mSurfacetextlistener = new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
                // 当TextureView可用时，打开摄像头
                HandlerThread thread = new HandlerThread("Ceamera3");
                thread.start();
                mHandler = new Handler(thread.getLooper());
                //获取能打开的摄像头
                CameraManager manager = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);
                String cameraid = CameraCharacteristics.LENS_FACING_FRONT + "";
                try {
                    mCameraCharacteristics = manager.getCameraCharacteristics(cameraid);
                    StreamConfigurationMap map = mCameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);

                    mPreViewSize = getPictureSize(map.getOutputSizes
                            (SurfaceTexture.class), x);
                    if (mPreViewSize == null) {
                        mPreViewSize = new Size(x, y);
                    }
                    //创建读图片的对象
                    mImageReader = ImageReader.newInstance(mPreViewSize.getWidth(), mPreViewSize.getHeight(), ImageFormat.JPEG, 5);
                    mImageReader.setOnImageAvailableListener(onImageAvaiableListener, mHandler);

                    //初始化预览的图片比例
                    initTextureViewSize(width, height);

                    //打开摄像头,编译器强制加的。
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                            manager.openCamera(cameraid, cameraOpenCallBack, mHandler);
                        } else {
                            manager.openCamera(cameraid, cameraOpenCallBack, mHandler);
                        }
                    } else {
                        manager.openCamera(cameraid, cameraOpenCallBack, mHandler);
                    }

                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

            }
        };
        //初始化相机布局
        textureview.setSurfaceTextureListener(mSurfacetextlistener);
    }

    private void initViews() {
        bottomBtnRe.setVisibility(View.GONE);
        mToolBarHelper.setToolbarTitle("身份证识别");

        mSessionStateCallBack = new CameraCaptureSession.StateCallback() {
            @Override
            public void onConfigured(CameraCaptureSession cameraCaptureSession) {
                try {
                    mCameraSession = cameraCaptureSession;
                    mPreViewBuidler.set(CaptureRequest.CONTROL_AF_MODE,
                            CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);

                    cameraCaptureSession.setRepeatingRequest(mPreViewBuidler.build(), null, mHandler);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {

            }
        };

        cameraOpenCallBack = new CameraDevice.StateCallback() {
            @Override
            public void onOpened(CameraDevice cameraDevice) {
                try {
                    mPreViewBuidler = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);

                    SurfaceTexture texture = textureview.getSurfaceTexture();
                    Surface surface = new Surface(texture);
                    mPreViewBuidler.addTarget(surface);
                    cameraDevice.createCaptureSession(Arrays.asList(surface,
                            mImageReader.getSurface()), mSessionStateCallBack, mHandler);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onDisconnected(CameraDevice cameraDevice) {
            }

            @Override
            public void onError(CameraDevice cameraDevice, int i) {
            }
        };

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(x, y);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        lp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        textureview.setLayoutParams(lp);

        //设置背景的高宽
        RelativeLayout.LayoutParams bgLp = (RelativeLayout.LayoutParams) imgRe.getLayoutParams();
        bgLp.width = (int) getScreenWith(this);
        bgLp.height = bgLp.width * 776 / 750;
        imgRe.setLayoutParams(bgLp);
    }

    public void initTextureViewSize(int width, int height) {
        //设置预览大小.
        CameraManager manager = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);
        String cameraid = CameraCharacteristics.LENS_FACING_FRONT + "";
        // 获取指定摄像头的特性
        CameraCharacteristics characteristics = null;
        try {
            characteristics = manager.getCameraCharacteristics(cameraid);
            // 获取摄像头支持的配置属性
            StreamConfigurationMap map = characteristics.get(
                    CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            // 获取最佳的预览尺寸
            Size previewSize = chooseOptimalSize(map.getOutputSizes(
                    SurfaceTexture.class), width, height, mPreViewSize);

            // 根据选中的预览尺寸来调整预览组件（TextureView）的长宽比
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                textureview.setAspectRatio(previewSize.getWidth(), previewSize.
                        getHeight());
            } else {
                textureview.setAspectRatio(previewSize.getHeight(),
                        previewSize.getWidth());
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private static Size chooseOptimalSize(Size[] choices
            , int width, int height, Size aspectRatio) {
        // 收集摄像头支持的大过预览Surface的分辨率
        List<Size> bigEnough = new ArrayList<>();
        int w = aspectRatio.getWidth();
        int h = aspectRatio.getHeight();
        for (Size option : choices) {
            if (option.getHeight() == option.getWidth() * h / w &&
                    option.getWidth() >= width && option.getHeight() >= height) {
                bigEnough.add(option);
            }
        }
        // 如果找到多个预览尺寸，获取其中面积最小的
        if (bigEnough.size() > 0) {
            return Collections.min(bigEnough, new CompareSizesByArea());
        } else {
            return choices[0];
        }
    }



    // 为Size定义一个比较器Comparator
    @TargetApi(21)
    static  class CompareSizesByArea implements Comparator<Size> {
        @Override
        public int compare(Size lhs, Size rhs) {
            // 强转为long保证不会发生溢出
            return Long.signum((long) lhs.getWidth() * lhs.getHeight() -
                    (long) rhs.getWidth() * rhs.getHeight());
        }
    }

    private void initOnClickListener() {

        takepictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = DialogUtils.showLoadingDialog(mContext);
                try {
                    shootSound();
                    CaptureRequest.Builder builder = mCameraSession.getDevice().createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                    builder.addTarget(mImageReader.getSurface());
                    builder.set(CaptureRequest.CONTROL_AF_MODE,
                            CaptureRequest.CONTROL_AF_MODE_AUTO);
                    builder.set(CaptureRequest.CONTROL_AF_TRIGGER,
                            CameraMetadata.CONTROL_AF_TRIGGER_START);
                    builder.set(CaptureRequest.JPEG_ORIENTATION, 90);//
                    mCameraSession.capture(builder.build(), null, mHandler);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //裁剪拍照得到的图片。
                Bitmap bitmap = CropID(finalBitmap);
                if (bitmap != null) {
                    //保存图片，传送路径
                    File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsoluteFile();
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    File file = new File(dir, System.currentTimeMillis() + ".png");
                    if(!file.exists()){
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    String filepath = file.getAbsolutePath();
                    saveBitmap(bitmap,file);
                    //把图片带回到上一个页面
                    Intent intent = new Intent();
                    intent.putExtra("filepath",filepath);
                    VerifyNamePtotoActivity.this.setResult(Activity.RESULT_OK, intent);
                    VerifyNamePtotoActivity.this.finish();
//                    verifyID(finalBitmap);//调用支付宝的接口验证图片.
                }
            }
        });
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImg.setVisibility(View.GONE);
                textureview.setVisibility(View.VISIBLE);
                tipsText.setVisibility(View.VISIBLE);
                takepictureBtn.setVisibility(View.VISIBLE);
                bottomBtnRe.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCameraSession != null) {
            mCameraSession.getDevice().close();
            mCameraSession.close();
        }
        if (finalBitmap != null && !finalBitmap.isRecycled()) {
            finalBitmap.recycle();
            finalBitmap = null;
        }
    }
    /**
     * 获取屏幕的宽
     * @param context
     * @return
     */
    public static double getScreenWith(Activity context){
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }

    /**
     * 播放系统的拍照的声音
     */
    public void shootSound() {
        ringtone.stop();
        ringtone.play();
    }

    private class ImageSaver implements Runnable {
        Image reader;

        public ImageSaver(Image reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsoluteFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, System.currentTimeMillis() + ".png");
            fileBank = file.getPath();
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
                ByteBuffer buffer = reader.getPlanes()[0].getBuffer();
                byte[] buff = new byte[buffer.remaining()];
                buffer.get(buff);
                BitmapFactory.Options ontain = new BitmapFactory.Options();
                ontain.inSampleSize = 1;
                Bitmap bm = BitmapFactory.decodeByteArray(buff, 0, buff.length, ontain);

                //因为拍出来的图片很小，所以要对图片进行缩放
                bm = Bitmap.createScaledBitmap(bm, x, y, true);
                finalBitmap = ImageCropWithRect(bm);
                saveBitmap(finalBitmap, file);
                Message.obtain(mUIHandler, SETIMAGE).sendToTarget();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    reader.close();
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 保存方法
     */
    public void saveBitmap(Bitmap bm, File f) {
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class InnerCallBack implements Handler.Callback {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case SETIMAGE:
                    if (dialog != null && dialog.isShowing())
                        dialog.dismiss();

                    textureview.setVisibility(View.INVISIBLE);

                    getImg.setVisibility(View.VISIBLE);
                    if (finalBitmap != null) getImg.setImageBitmap(finalBitmap);
                    tipsText.setVisibility(View.GONE);
                    takepictureBtn.setVisibility(View.GONE);
                    bottomBtnRe.setVisibility(View.VISIBLE);
                    break;
                case HANDLE_FAIL:
                    if (dialog != null && dialog.isShowing())
                        dialog.dismiss();
                    break;
                case DISMISS_DIALOG:
                    if (dialog != null && dialog.isShowing())
                        dialog.dismiss();
                    break;
            }
            return false;
        }
    }

    public Size getPictureSize(Size[] list, int th) {

        for (Size s : list) {
            if ((s.getWidth() > th) && equalRate(s, 34 / 75f)) {
                Log.i("TAG", "最终设置图片尺寸:w = " + s.getWidth() + "h = " + s.getHeight());
                return s;
            }

        }
        return null;


    }

    private ArrayList<Size> getSizeList(Size[] list) {
        ArrayList<Size> sizeList = new ArrayList<>();
        if (list != null) {
            for (Size size : list) {
                sizeList.add(size);
            }
        }

        return sizeList;
    }

    public boolean equalRate(Size s, float rate) {
        float r = (float) (s.getWidth()) / (float) (s.getHeight());
        if (Math.abs(r - rate) <= 0.2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 按长方形裁切图片
     *
     * @param bitmap
     * @return
     */
    public Bitmap ImageCropWithRect(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }

        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();

        int nw, nh;     //新的bitmap的长宽
        int retX, retY; //新的bitmap的位置
        if (w > h) {
            nw = h / 2;
            nh = h;
            retX = (w - nw) / 2;
            retY = 0;
        } else {
            //由于我们的app强制竖屏，所以走这里的逻辑
            retX = 0;
            retY = DimensUtils.dip2px(mContext, 44) + DimensUtils.getStatusHeight(mContext);//标题栏+状态栏的高度
            nw = w;
            nh = w * 776 / 750;
        }

        // 下面这句是关键
        Bitmap bmp = Bitmap.createBitmap(bitmap, retX, retY, nw, nh, null,
                false);

        return bmp;
    }

    /**
     * 裁剪出银行卡的大小的图片
     *
     * @param bitmap
     * @return
     */
    public Bitmap CropID(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }

        int w = bitmap.getWidth();  // 得到图片的宽，高
        int h = bitmap.getHeight();

        int nw, nh;                 //新的bitmap的长宽
        int retX, retY;             //新的bitmap的位置
        if (w > h) {
            nw = h / 2;
            nh = h;
            retX = (w - nw) / 2;
            retY = 0;
        } else {
            //由于我们的app强制竖屏，所以走这里的逻辑
            retX = w * 2 / 750;
            retY = h * 185 / 776;
            nw = w * 707 / 750;
            nh = h * 408 / 750;
        }

        Bitmap bmp = Bitmap.createBitmap(bitmap, retX, retY, nw, nh, null, false);
        return bmp;
    }

}
