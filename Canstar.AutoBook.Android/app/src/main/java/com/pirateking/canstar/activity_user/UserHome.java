package com.pirateking.canstar.activity_user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.pirateking.canstar.R;
import com.pirateking.canstar.activity.Base;
import com.special.ResideMenu.ResideMenu;

public class UserHome extends Base {

    private ResideMenu mResideMenu;
    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user_home);
        InitializeVariales();

        tv_title.setText("Home");
    }

    private void InitializeVariales(){
        tv_title = (TextView)findViewById(R.id.tv_title);
        mResideMenu = initMenu(this, R.id.content_hamburger);
    }
}
