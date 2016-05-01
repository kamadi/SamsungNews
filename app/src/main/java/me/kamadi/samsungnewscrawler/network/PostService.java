package me.kamadi.samsungnewscrawler.network;

import java.util.Map;

import me.kamadi.samsungnewscrawler.model.ApiResponse;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Madiyar on 01.05.2016.
 */
public interface PostService {
    @FormUrlEncoded
    @POST("wp-admin/admin-ajax.php")
    Call<ApiResponse> getPosts(@FieldMap Map<String, String> names);
}
