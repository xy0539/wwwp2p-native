package com.wanweirongtong.wwwp2p_native;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**2017.6.2 by xy
 */
public class HomeFragment extends Fragment implements ViewSwitcher.ViewFactory {
    private AutoPlayingViewPager mAutoPlayingViewPager;
    /**
     * 模拟网络请求获取的图片URL
     */
    private String [] imageUrl = new String[] {
            "http://g.hiphotos.baidu.com/image/pic/item/d0c8a786c9177f3e117088eb75cf3bc79e3d568b.jpg",
            "http://upload.cebnet.com.cn/2014/1217/1418776413348.jpg",
            "http://n.sinaimg.cn/transform/20150917/7DDk-fxhytwp5363222.jpg",
            "http://n.sinaimg.cn/transform/20151019/YtA_-fxivsce6931363.jpg"
    };

    private String [] imageTitle = new String [] {
            "赵丽颖","高圆圆","王鸥","唐嫣"};

    private List<AutoPlayInfo> mAutoPlayInfoList;


    private TextSwitcher textSwitcher;
    private Timer timer;
    //private Timer timer;
    // 要显示的文本
    String[] infoStrs = new String[]
            {
                    "万维融通原生版上线啦！",
                    "万维融通原生版上线啦！！",
                    "万维融通原生版上线啦！！！"
            };
    private int curStr;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textSwitcher= (TextSwitcher) getActivity().findViewById(R.id.info);
        textSwitcher.setFactory(this);

        mAutoPlayingViewPager = (AutoPlayingViewPager) getActivity().findViewById(R.id.auto_play_viewpager);
        mAutoPlayInfoList=new ArrayList<>();
        for(int i = 0 ; i < imageUrl.length ; i ++){
            //把模拟的数据添加到集合里面
            AutoPlayInfo autoPlayInfo = new AutoPlayInfo();
            autoPlayInfo.setImageUrl(imageUrl[i]);
            autoPlayInfo.setAdLinks("");
            autoPlayInfo.setTitle(imageTitle[i]);
            mAutoPlayInfoList.add(autoPlayInfo);

        }
        //通过这个方法把集合传进去，并设置图片的点击事件，可以做跳转
        mAutoPlayingViewPager.initialize(mAutoPlayInfoList).build();
        mAutoPlayingViewPager.setOnPageItemClickListener(onPageItemClickListener);

        //定时器循环显示字符串

       timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        textSwitcher.setText(infoStrs[curStr]);
                        if(curStr==infoStrs.length-1){
                            curStr=0;
                        }
                        else {
                            curStr++;
                        }

                    }
                });
            }
        };
        if(infoStrs.length>0){
            curStr=0;
            timer.schedule(timerTask,1,3000);
        }

    }


    @Override
    public void onDestroy(){

        if (timer != null) {

            timer.cancel( );

            timer = null;

        }
        super.onDestroy();
    }

    @Override
    public View makeView() {
        TextView textView = new TextView(this.getActivity());
        //textView.setTextSize(36);
        return textView;
    }



    private AutoPlayingViewPager.OnPageItemClickListener onPageItemClickListener = new AutoPlayingViewPager.OnPageItemClickListener() {

        @Override
        public void onPageItemClick(int position, String adLink) {

            //Toast.makeText(MainActivity.this,"第"+position, Toast.LENGTH_LONG).show();
        }

    };


    @Override
    public void onStart() {   //当Activity onRestart();还要执行，
        //没有数据时不执行startPlaying,避免执行几次导致轮播混乱
        if(mAutoPlayInfoList != null && !mAutoPlayInfoList.isEmpty()){
            mAutoPlayingViewPager.startPlaying();
        }
        super.onStart();

    }

    @Override
    public void onPause() {
        mAutoPlayingViewPager.stopPlaying();
        super.onPause();
    }



    public static HomeFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}