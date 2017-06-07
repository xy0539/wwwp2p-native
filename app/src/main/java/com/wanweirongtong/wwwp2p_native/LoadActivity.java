package com.wanweirongtong.wwwp2p_native;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.WindowManager;


public class LoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.RGBA_8888);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_load);
        //getSupportActionBar().hide();  去掉标题栏

       //判断是否第一次打开APP
        PackageManager packageManager = this.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(this.getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int currentVersion=packInfo.versionCode;
        SharedPreferences sharedPreferences = getSharedPreferences("AppInfo", Context.MODE_PRIVATE);
        int lastVersion = sharedPreferences.getInt("lastVersion",0);

        if(lastVersion<currentVersion){
            Log.d("myinfo", "diyici"+Integer.toString(lastVersion));
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt("lastVersion", currentVersion);
            edit.apply();    //提交修改

            Intent intent = new Intent(this,GuideActivity.class);
            startActivity(intent);
            finish();

        } else {
            new Handler().postDelayed(new Runnable(){
                public void run() {
                    Intent intent = new Intent(LoadActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);

        }


    }
}
