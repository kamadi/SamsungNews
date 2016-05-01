package me.kamadi.samsungnewscrawler.network;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Madiyar on 01.05.2016.
 */
public class ApiFactory {
    private static final int CONNECT_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 60;
    private static final int TIMEOUT = 60;

    private static final OkHttpClient CLIENT = new OkHttpClient();
//
//    static {
//        CLIENT.setConnectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
//        CLIENT.setWriteTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
//        CLIENT.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
//
//    }

    @NonNull
    public static PostService getPostService() {
        return getRetrofit().create(PostService.class);
    }



    @NonNull
    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://news.samsung.com/global/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(CLIENT)
                .build();
    }
}
