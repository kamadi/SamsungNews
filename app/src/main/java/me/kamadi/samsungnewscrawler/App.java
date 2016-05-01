package me.kamadi.samsungnewscrawler;

import android.app.Application;

import me.kamadi.samsungnewscrawler.model.PostItem;

/**
 * Created by Madiyar on 01.05.2016.
 */
public class App extends Application {
    private static  App app;
    private PostItem postItem;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public synchronized static App getInstance(){
        return app;
    }


    public PostItem getPostItem() {
        return postItem;
    }

    public void setPostItem(PostItem postItem) {
        this.postItem = postItem;
    }
}
