package me.kamadi.samsungnewscrawler.model;

/**
 * Created by Madiyar on 01.05.2016.
 */
public class Video {
    private String url;
    private String title;
    private String thumbnail;

    public Video(String url, String title, String thumbnail) {
        this.url = url;
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
