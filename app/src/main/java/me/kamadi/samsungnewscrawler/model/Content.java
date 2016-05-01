package me.kamadi.samsungnewscrawler.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Madiyar on 01.05.2016.
 */
public class Content {
    @SerializedName("total_page")
    private int totalPage;

    @SerializedName("list")
    private List<PostItem>postItems;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<PostItem> getPostItems() {
        return postItems;
    }

    public void setPostItems(List<PostItem> postItems) {
        this.postItems = postItems;
    }
}
