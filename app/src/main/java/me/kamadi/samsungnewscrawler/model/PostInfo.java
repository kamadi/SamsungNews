package me.kamadi.samsungnewscrawler.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Madiyar on 29.04.2016.
 */
public class PostInfo {
    private int ID;

    @SerializedName("date_text")
    private String dateText;
    private String title;
    private String link;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDateText() {
        return dateText;
    }

    public void setDateText(String dateText) {
        this.dateText = dateText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
