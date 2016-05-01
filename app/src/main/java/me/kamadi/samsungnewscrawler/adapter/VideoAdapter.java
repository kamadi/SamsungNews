package me.kamadi.samsungnewscrawler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.kamadi.samsungnewscrawler.R;
import me.kamadi.samsungnewscrawler.model.Video;

/**
 * Created by Madiyar on 21.09.2015.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.EventRecyclerViewHolder> {
    private LayoutInflater inflater;

    private List<Video> videos;
    private Context context;

    public VideoAdapter(Context context,List<Video> videos) {
        this.videos = videos;
        this.context = context;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public EventRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.list_item_video, parent, false);
        EventRecyclerViewHolder holder = new EventRecyclerViewHolder(root);
        return holder;
    }

    @Override
    public void onBindViewHolder(EventRecyclerViewHolder holder, int position) {
        Video video = videos.get(position);
        holder.titleView.setText(video.getTitle().toUpperCase());
        Picasso.with(context).load(video.getThumbnail()).into(holder.coverView);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public static class EventRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView titleView;
        ImageView coverView;


        public EventRecyclerViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.title);
            coverView = (ImageView) itemView.findViewById(R.id.cover_iv);
        }
    }
}
