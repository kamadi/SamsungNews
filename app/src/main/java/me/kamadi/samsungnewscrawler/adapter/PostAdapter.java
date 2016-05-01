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

import butterknife.Bind;
import butterknife.ButterKnife;
import me.kamadi.samsungnewscrawler.R;
import me.kamadi.samsungnewscrawler.model.PostItem;

/**
 * Created by Madiyar on 01.05.2016.
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostRecyclerViewHolder> {
    public OnItemClickListener listener;
    private Context context;
    private List<PostItem> postItems;
    private LayoutInflater inflater;

    public PostAdapter(Context context, List<PostItem> postItems) {
        this.context = context;
        this.postItems = postItems;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public PostRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.list_item_post, parent, false);
        PostRecyclerViewHolder viewHolder = new PostRecyclerViewHolder(rootView,listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PostRecyclerViewHolder holder, int position) {
        PostItem item = postItems.get(position);
        holder.title.setText(item.getPostInfo().getTitle());
        Picasso.with(context).load(item.getThumbInfo().getSrc()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class PostRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClickListener listener;

        @Bind(R.id.title)
        TextView title;


        @Bind(R.id.thumbnail)
        ImageView thumbnail;

        public PostRecyclerViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.listener = listener;
//            title.setOnClickListener(this);
//            description.setOnClickListener(this);
//            thumbnail.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }
}
