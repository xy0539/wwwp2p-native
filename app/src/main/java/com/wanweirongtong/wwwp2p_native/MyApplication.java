package com.wanweirongtong.wwwp2p_native;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by xy on 2017/3/30.
 */
public class MyApplication extends Application {
    private static MyApplication singleton;

    public static MyApplication getInstance(){
        return singleton;
    }

    @Override
    public final void onCreate() {
        super.onCreate();
        initImageLoader();
    }

    public void initImageLoader() {
        DisplayImageOptions.Builder options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)// 内存缓存
                .cacheOnDisk(true)// 磁盘缓存
                .showImageOnFail(R.drawable.ic_fail)//加载失败显示的图片
                .considerExifParams(true)// 是否考虑EXIF信息，比如拍照方向
                .displayer(new FadeInBitmapDisplayer(300));//淡入动画
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(getApplicationContext());
        // 取消缓存多张尺寸不同的同一张图片
        config.denyCacheImageMultipleSizesInMemory();
        // 设置显示选项
        config.defaultDisplayImageOptions(options.build());
        // 生成缓存文件的生成器，保证唯一的文件名，可以不设置，默认使用hash算法，也是可以保证不重名的
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        // 磁盘缓存大小
        config.diskCacheSize(100 * 1024 * 1024); // 100 MB
        // 内存缓存大小
        config.memoryCacheSize((int) (Runtime.getRuntime().freeMemory() / 4));
        // 任务处理顺序，默认是FIFO 先进先出， LIFO 后进先出
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        // 打印调试日志
        config.writeDebugLogs(); // Remove for release app
        ImageLoader.getInstance().init(config.build());
    }
}