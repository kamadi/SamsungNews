package me.kamadi.samsungnewscrawler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.kamadi.samsungnewscrawler.R;
import me.kamadi.samsungnewscrawler.model.Category;
import me.kamadi.samsungnewscrawler.model.CategoryList;

/**
 * Created by Madiyar on 01.05.2016.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.PostRecyclerViewHolder> {
    private LayoutInflater inflater;

    public CategoryAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public PostRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.list_item_category, parent, false);
        PostRecyclerViewHolder viewHolder = new PostRecyclerViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PostRecyclerViewHolder holder, int position) {
        Category category = CategoryList.getCategories().get(position);
        holder.title.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return CategoryList.getCategories().size();
    }

    public class PostRecyclerViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.category_name_tv)
        TextView title;

        @Bind(R.id.category_dot_color)
        ImageView dot;

        public PostRecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
