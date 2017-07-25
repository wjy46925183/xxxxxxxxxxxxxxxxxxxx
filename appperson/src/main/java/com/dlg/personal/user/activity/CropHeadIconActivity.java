package com.dlg.personal.user.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.common.utils.headcrop.ClipImageLayout;
import com.common.utils.headcrop.ImageTools;
import com.dlg.personal.R;

import java.io.File;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class CropHeadIconActivity extends Activity implements View.OnClickListener{
    private ClipImageLayout mClipImageLayout;
    private String path;
    private ProgressDialog loadingDialog;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_crop_head_icon);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
    }

    private void initView() {
        mClipImageLayout = (ClipImageLayout) findViewById(R.id.id_clipImageLayout);
        btn = (Button)findViewById(R.id.ensure);
        btn.setOnClickListener(this);
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setTitle("请稍后...");
        path = getIntent().getStringExtra("path");
        if (TextUtils.isEmpty(path) || !(new File(path).exists())) {
            Toast.makeText(this, "图片加载失败", Toast.LENGTH_SHORT).show();
            return;
        }
        Bitmap bitmap = ImageTools.convertToBitmap(path, 600, 600);
        if (bitmap == null) {
            Toast.makeText(this, "图片加载失败", Toast.LENGTH_SHORT).show();
            return;
        }
        mClipImageLayout.setBitmap(bitmap);
    }


    private File getCacheDir(Context context, String cacheName) {
        File cacheDir = context.getExternalCacheDir();
        if (cacheDir == null) {
            cacheDir = context.getCacheDir();
        }
        cacheDir = new File(cacheDir, cacheName);
        return cacheDir;
    }

    @Override
    public void onClick(View v) {
        loadingDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = mClipImageLayout.clip();
                File file = getCacheDir(CropHeadIconActivity.this, System.currentTimeMillis() + ".png");
                ImageTools.savePhotoToSDCard(bitmap, file);
                Luban.with(CropHeadIconActivity.this).load(file).setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {
                        loadingDialog.dismiss();
                        Intent intent = new Intent();
                        intent.setData(Uri.fromFile(file));
                        setResult(RESULT_OK, intent);
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadingDialog.dismiss();
                    }
                }).launch();
            }
        }).start();
    }
}
