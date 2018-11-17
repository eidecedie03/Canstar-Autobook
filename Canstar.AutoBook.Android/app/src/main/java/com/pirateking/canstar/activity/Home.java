package com.pirateking.canstar.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pirateking.canstar.R;
import com.pirateking.canstar.helpers.SessionHelper;
import com.special.ResideMenu.ResideMenu;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Home extends Base {

//    ImageView imageView;
//    String URL = "https://s3.amazonaws.com/uifaces/faces/twitter/shadeed9/128.jpg";
    Context context = this;

    private ResideMenu mResideMenu;
    private SessionHelper mSession;
    private TextView tv_title;

    private RelativeLayout btn_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);
        InitializeVariales();

//         imageView = (ImageView) findViewById(R.id.image_load);
//        imageurl(URL);

        Log.d("test123","" + mSession.getFirstname().toString());


        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, UserProfile.class));
                finish();
            }
        });
        tv_title.setText("Home");
    }

    private void InitializeVariales(){
        tv_title = (TextView)findViewById(R.id.tv_title);
        mResideMenu = initMenu(this, R.id.content_hamburger);
        btn_profile = (RelativeLayout)findViewById(R.id.btn_profile);
        mSession = getSessionHelper();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    //    private void imageurl(String url){
//        Picasso.with(this).load(url).placeholder(R.mipmap.car_icon)
//                .error(R.mipmap.car_icon)
//                .into(imageView, new com.squareup.picasso.Callback() {
//                    @Override
//                    public void onSuccess() {
//
//                    }
//
//                    @Override
//                    public void onError() {
//
//                    }
//                });
//    }
}
