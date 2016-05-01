package me.kamadi.samsungnewscrawler.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.kamadi.samsungnewscrawler.R;
import me.kamadi.samsungnewscrawler.listener.RecyclerViewItemClickListener;
import me.kamadi.samsungnewscrawler.adapter.CategoryAdapter;
import me.kamadi.samsungnewscrawler.model.Category;
import me.kamadi.samsungnewscrawler.model.CategoryList;

public class CategoryListActivity extends AppCompatActivity implements RecyclerViewItemClickListener.OnItemClickListener {

    public static final String IMAGE_TRANSITION_NAME = "image_transition";
    public static final int REQUEST_CODE = 111;
    public static final int RESULT_CATEGORY_SELECTION_CANCELED = 17;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        ButterKnife.bind(this);
        ViewCompat.setTransitionName(fab, IMAGE_TRANSITION_NAME);

        CategoryAdapter adapter = new CategoryAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(this, this));
    }

    @Override
    public void onItemClick(View view, int position) {

        Category category = CategoryList.getCategories().get(position);
        Intent data = new Intent();
        data.putExtra("category", category);
        setResult(RESULT_OK,data);
        ActivityCompat.finishAfterTransition(CategoryListActivity.this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        close();
    }

    private void close() {
        setResult(RESULT_CATEGORY_SELECTION_CANCELED);
        ActivityCompat.finishAfterTransition(CategoryListActivity.this);
    }

    @OnClick(R.id.fab)
    public void onFabClick(View view){
        close();
    }
}
