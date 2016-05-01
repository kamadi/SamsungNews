package me.kamadi.samsungnewscrawler.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Madiyar on 29.04.2016.
 */
public class PostItem  {
    @SerializedName("post_info")
    private PostInfo postInfo;

    @SerializedName("thumb_info")
    private ThumbInfo thumbInfo;

    public PostInfo getPostInfo() {
        return postInfo;
    }

    public void setPostInfo(PostInfo postInfo) {
        this.postInfo = postInfo;
    }

    public ThumbInfo getThumbInfo() {
        return thumbInfo;
    }

    public void setThumbInfo(ThumbInfo thumbInfo) {
        this.thumbInfo = thumbInfo;
    }
}
