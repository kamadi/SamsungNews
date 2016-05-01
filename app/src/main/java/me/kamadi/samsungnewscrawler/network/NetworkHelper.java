package me.kamadi.samsungnewscrawler.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.kamadi.samsungnewscrawler.model.Video;

/**
 * Created by Madiyar on 01.05.2016.
 */
public class NetworkHelper {

    private static final String LOG_TAG = NetworkHelper.class.getSimpleName();

    public static HashMap<String, String> getParams(int page, String category) {
        HashMap<String, String> params = new HashMap<>();
        params.put("paged", page + "");
        params.put("category", category);
        params.put("orderby", "date");
        params.put("action", "post_lists");
        params.put("posttype", "post");
        params.put("postperpage", "10");
        params.put("from", "main");
        return params;
    }

    public static List<Video> getVideos(){
        List<Video> videos = new ArrayList<>();
//        Document doc = Jsoup.connect("http://news.samsung.com/global/").get();
//        Element videoSection = doc.getElementsByClass("board_video").first();
//        Elements videosList = videoSection.getElementsByTag("ul").first().getElementsByTag("li");
////        Log.e(LOG_TAG, videosList.html());
//        Video video;
//        for (Element videoElement : videosList) {
//            video = new Video();
//            video.setTitle(videoElement.getElementsByClass("title").first().text());
//            Log.e(LOG_TAG,videoElement.getElementsByTag("img").first().html());
//            video.setThumbnail(videoElement.getElementsByTag("img").first().attr("src"));
//            video.setThumbnail(videoElement.getElementsByTag("a").first().attr("href"));
//            videos.add(video);
//        }
        videos.add(new Video("https://youtu.be/kCHL-4ZJMLI","Galaxy S7 edge | S7 - Water and Dust Resistance Test","https://img.global.news.samsung.com/global/wp-content/uploads/2016/03/FacesOfInnoation_Part2_IP68_Thumb704_2.jpg"));
        videos.add(new Video("https://youtu.be/gdFBJoJGVmE","World's Largest Baseball LED Scoreboard in SK Wyverns Munhak Stadium, Incheon, Korea","https://img.global.news.samsung.com/global/wp-content/uploads/2016/04/LED_Scoreboard_Thumb704-704x334.jpg"));
        return videos;
    }

}
