package com.pirateking.canstar.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.afollestad.materialdialogs.MaterialDialog;
import com.pirateking.canstar.R;
import com.pirateking.canstar.helpers.SessionHelper;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import java.lang.reflect.Field;

public class Base extends AppCompatActivity {



    // For Side Menu Contents
    private String[] mMenuTitles = {"Home","Edit Profile","Logout" };
    private Integer[] mMenuIcons = {R.mipmap.icon_user1,R.mipmap.icon_logout,R.mipmap.icon_logout};
    private Toast toast;
    private Thread thread;

    public MaterialDialog.Builder mMaterialDialog;

    public ResideMenu initMenu(final Activity activity, final int toolbarId) {
        final ResideMenu resideMenu = new ResideMenu(activity);
        // setNac
        resideMenu.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_gray));
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        resideMenu.attachToActivity(activity);

        for (int x = 0; x < mMenuTitles.length; x++) {
            ResideMenuItem menuItem = new ResideMenuItem(this, mMenuIcons[x], mMenuTitles[x]);
            try {
                Field privateTextView = ResideMenuItem.class.getDeclaredField("tv_title");
                privateTextView.setAccessible(true);
                TextView tv = (TextView) privateTextView.get(menuItem);
                tv.setTextColor(Color.WHITE);
                tv.setTextSize(15);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            final int index = x;
            menuItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Class classTo = null;
//                    if (index == 0) classTo = Main.class;
//                    if (index == 1) classTo = Plants.class;
//                    if (index == 2) classTo = Diseases.class;
//                    if (index == 3) classTo = HowtoPlant.class;
//                    if (index == 4) classTo = AboutUs.class;
//                    if (index == 3) classTo = Task.class;
//                    if (index == 4) classTo = SendReport.class;
//                    if (index == 8) logoutUser();

                    if (classTo != null) {
                        if (!classTo.getName().equals(activity.getClass().getName())) {
                            if (!activity.getClass().equals(Home.class)) finish();
                            startActivity(new Intent(v.getContext(), classTo));
                        }
                        else resideMenu.closeMenu();
                    }
                }
            });

            resideMenu.addMenuItem(menuItem, ResideMenu.DIRECTION_LEFT);
        }
        activity.findViewById(toolbarId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toolbarId == R.id.content_hamburger)
                    resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
//                else if (toolbarId == R.id.back) {
//                    activity.finish();
//                }
            }
        });

        return resideMenu;
    }
    // local db
//    public DBHelper getLocalDB() {
//        return new DBHelper(getApplicationContext());
//    }

    public void showErrorDialog(Context context, String errorMessage){
        new MaterialDialog.Builder(context)
                .title("Error")
                .negativeText(errorMessage)
                .showListener(dialog -> showToast("onShow"))
                .cancelListener(dialog -> showToast("onCancel"))
                .dismissListener(dialog -> showToast("onDismiss"))
                .show();
    }

    private void showToast(String message) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }


    MaterialDialog loadingdialog;
    public void showLoadingDialog(Context context){
      loadingdialog =  new MaterialDialog.Builder(context)
                .title("test loading")
                .content("please wait")
                .progress(true, 0)
                .show();
    }

    public void dismissDialog(){
        loadingdialog.dismiss();
    }




    // for login sync
//    public void showLoadingDialog(Context context, int responseLength){
//        new MaterialDialog.Builder(context)
//                .title("Progress Dialog")
//                .content("Please wait, Don't Touch anything")
//                .contentGravity(GravityEnum.CENTER)
//                .progress(false, responseLength, true)
//                .cancelListener(
//                        dialog -> {
//                            if (thread != null) {
//                                thread.interrupt();
//                                Toast.makeText(context, "cancelled", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                .showListener(
//                        dialogInterface -> {
//                            final MaterialDialog dialog = (MaterialDialog) dialogInterface;
//                            startThread(
//                                    () -> {
//                                        while (dialog.getCurrentProgress() != dialog.getMaxProgress()
//                                                && !Thread.currentThread().isInterrupted()) {
//                                            if (dialog.isCancelled()) {
////                                                dialog.setContent("Cancelled");
//
//                                                break;
//                                            }
//                                            try {
//                                                Thread.sleep(50);
//                                            } catch (InterruptedException e) {
//                                                e.getStackTrace();
//                                                break;
//                                            }
//                                            dialog.incrementProgress(10);
//                                        }
//                                        runOnUiThread(
//                                                () -> {
//                                                    thread = null;
//                                                    dialog.setContent("Done");
//                                                    Toast.makeText(context, "Wala na, finish na", Toast.LENGTH_SHORT).show();
//                                                    dialog.dismiss();
//                                                });
//                                    });
//                        })
//                .show();
//    }


    public void showMessageDialog(Context context, String status, String message){
        if(status.equals("Success")) {
            new MaterialDialog.Builder(context).content("Success").contentColor(getResources().getColor(R.color.colorPrimary)).positiveText("OK").show();
        }
        else if(status.equals("Error")){
            new MaterialDialog.Builder(context).content("Error").contentColor(getResources().getColor(R.color.colorPrimaryDark)).negativeText("Dismiss").show();
        }

    }

    private void startThread(Runnable run) {
        if (thread != null) {
            thread.interrupt();
        }
        thread = new Thread(run);
        thread.start();
    }



    public SessionHelper getSessionHelper() {
        return new SessionHelper(this);
    }

    public String getApiUrl() {
        return getSharedPreferences("API_URL", Context.MODE_PRIVATE).getString("API_URL", "192.168.21.28");
    }

}
