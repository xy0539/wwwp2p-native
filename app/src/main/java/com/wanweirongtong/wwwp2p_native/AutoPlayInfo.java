package com.wanweirongtong.wwwp2p_native;

/**
 * Created by xy on 2017/3/17.
 */
public class AutoPlayInfo {
    //轮播图片URL
    private String imageUrl;
    //轮播本地图片资源Id
    private int imageId;
    //链接
    private String adLinks;
    //图片对应的标题
    private String title;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getAdLinks() {
        return adLinks;
    }

    public void setAdLinks(String adLinks) {
        this.adLinks = adLinks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}