package com.wanweirongtong.wwwp2p_native;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<View> views;
    private GuideViewPagerAdapter adapter;
    private ImageView[] point;      //放所有的点
    private Button start;        //启动主页面的按钮
    private int currentIndex = 0;   //用来判断当前的点
    private int[] guidePics = { R.drawable.start1, R.drawable.start2,R.drawable.start3 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        init();
        initImageResource();
        initpoint();
    }

    private void init() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        views = new ArrayList<View>();
        adapter = new GuideViewPagerAdapter(views);
        start = (Button) findViewById(R.id.button_Start);
        assert start != null;
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                 //点击进入主页
            }
        });
    }
    private void initImageResource() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        for (int i = 0; i < guidePics.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);      //设置布局
            imageView.setImageResource(guidePics[i]);   //设置资源
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);   //设置拉伸变换
            views.add(imageView);
        }
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurDot(position);
                isStartShow(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initpoint() {
        /*LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout_point);
        point = new ImageView[guidePics.length];
        for (int i = 0; i < point.length; i++) {
            point[i] = (ImageView) linearLayout.getChildAt(i);
            point[i].setEnabled(true);
        }
        point[currentIndex].setEnabled(false);*/


        LinearLayout layout = (LinearLayout)findViewById(R.id.linearLayout_point);
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mParams.setMargins(10, 0, 10, 0);//设置小圆点左右之间的间隔

        point = new ImageView[guidePics.length];

        for(int i = 0; i < point.length; i++)
        {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(mParams);
            imageView.setImageResource(R.drawable.selector_point);
            if(i== 0)
            {
                imageView.setEnabled(false);//默认启动时，选中第一个小圆点
            }
            else {
                imageView.setEnabled(true);
            }
            point[i] = imageView;//得到每个小圆点的引用，用于滑动页面时，（onPageSelected方法中）更改它们的状态。
            layout.addView(imageView);//添加到布局里面显示
        }


    }


    private void setCurDot(int position) {
        point[position].setEnabled(false);
        point[currentIndex].setEnabled(true);
        currentIndex = position;
    }

    private void isStartShow(int position) {
        if (position == (guidePics.length - 1)) {
            start.setVisibility(View.VISIBLE);
        } else {
            start.setVisibility(View.GONE);
        }
    }
}
