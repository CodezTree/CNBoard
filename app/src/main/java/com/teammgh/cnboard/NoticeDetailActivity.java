package com.teammgh.cnboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class NoticeDetailActivity extends AppCompatActivity {

    PhotoView detailImage;

    LinearLayout linearLayout;

    PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);

        detailImage = findViewById(R.id.iv_imageView);
        linearLayout = findViewById(R.id.notice_detail_layout);

        final Intent intent = getIntent();

        final Callback imageLoadedCallback = new Callback() {
            @Override
            public void onSuccess() {
                if(mAttacher!=null){
                    mAttacher.update();
                }else{
                    Log.d("test","Attached");
                    mAttacher = new PhotoViewAttacher(detailImage);
                    mAttacher.setMinimumScale(mAttacher.getScale());
                }
            }
            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                // TODO Auto-generated method stub
            }
        };

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("test", Integer.toString(linearLayout.getWidth()));
                Picasso.get().load("http://45.32.49.247/notice/" + intent.getStringExtra("URL")).fit().centerInside().into(detailImage, imageLoadedCallback);
            }
        }, 100);
    }
}
