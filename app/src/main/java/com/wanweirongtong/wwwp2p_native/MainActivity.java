package com.wanweirongtong.wwwp2p_native;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    private ArrayList<Fragment> fragments;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //底部导航栏
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.ic_trending_up_white_24dp, "投资"))
                .addItem(new BottomNavigationItem(R.drawable.ic_person_white_24dp, "我的"))
                .addItem(new BottomNavigationItem(R.drawable.ic_more_horiz_white_24dp, "更多"))
                .initialise();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.menu_icon);//设置导航栏图标
        //toolbar.setLogo(R.mipmap.ic_launcher);//设置app logo
        //toolbar.setTitle("万维融通原生版");//设置主标题
        toolbar.setTitleTextColor(Color.WHITE);
        //toolbar.setSubtitle("Subtitle");//设置子标题

        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
        
    }



    /** * 设置默认的 */
    private void setDefaultFragment() {

        if (fragments != null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.layFrame, fragments.get(0));
            transaction.commit();
        }

    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("Home"));
        fragments.add(InvestFragment.newInstance("Invest"));
        fragments.add(UserFragment.newInstance("User"));
        fragments.add(MoreFragment.newInstance("More"));
        return fragments;
    }

    @Override
    public void onTabSelected(int position) {

        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                if (fragment.isAdded()) {
                    ft.show(fragment);
                    //Log.d("myinfo", "re"+position);
                } else {
                    ft.add(R.id.layFrame, fragment);
                    //Log.d("myinfo", "add"+position);
                }
                ft.commitAllowingStateLoss();
            }
        }

    }

    @Override
    public void onTabUnselected(int position) {

        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.hide(fragment);
               // Log.d("myinfo", "hide"+position);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }

}
