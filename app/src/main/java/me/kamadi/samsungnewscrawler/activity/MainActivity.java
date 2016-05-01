package me.kamadi.samsungnewscrawler.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.kamadi.samsungnewscrawler.App;
import me.kamadi.samsungnewscrawler.R;
import me.kamadi.samsungnewscrawler.adapter.PostAdapter;
import me.kamadi.samsungnewscrawler.adapter.VideoAdapter;
import me.kamadi.samsungnewscrawler.listener.RecyclerViewItemClickListener;
import me.kamadi.samsungnewscrawler.model.ApiResponse;
import me.kamadi.samsungnewscrawler.model.Category;
import me.kamadi.samsungnewscrawler.model.PostItem;
import me.kamadi.samsungnewscrawler.model.Video;
import me.kamadi.samsungnewscrawler.network.ApiFactory;
import me.kamadi.samsungnewscrawler.network.NetworkHelper;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecyclerViewItemClickListener.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
    ImageView ivProfile;
    List<PostItem> postItems;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    String currentCategory = "";
    int currentIndex = 0;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    LinearLayoutManager mLayoutManager;
    private PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addOnItemTouchListener(
                new RecyclerViewItemClickListener(this, this));
//        recyclerView.addOnScrollListener(new EndlessScrollListener(mLayoutManager) {
//            @Override
//            public boolean onLoadMore(int page, int totalItemsCount) {
//                return false;
//            }
//        });
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                getPosts();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void getPosts() {
        swipeRefreshLayout.setRefreshing(true);
        Call<ApiResponse> call = ApiFactory.getPostService().getPosts(NetworkHelper.getParams(currentIndex, currentCategory));
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ApiResponse apiResponse = response.body();
                List<PostItem>currentPostItems = apiResponse.getContent().getPostItems();
                swipeRefreshLayout.setRefreshing(false);
                if(currentIndex == 0) {
                    postItems = currentPostItems;
                    adapter = new PostAdapter(MainActivity.this, postItems);
                    recyclerView.setAdapter(adapter);
                }else{
                    postItems.addAll(currentPostItems);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }

    private void getVideos() {
        swipeRefreshLayout.setRefreshing(true);

        List<Video> videos  = NetworkHelper.getVideos();

        swipeRefreshLayout.setRefreshing(false);
        VideoAdapter videoAdapter = new VideoAdapter(MainActivity.this, videos);
        recyclerView.setAdapter(videoAdapter);
    }

    private void createAdapter() {

    }


    @Override
    public void onItemClick(View view, int position) {
        if (currentCategory.equals("video")) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(NetworkHelper.getVideos().get(position).getUrl())));
        } else {
            Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
            App.getInstance().setPostItem(postItems.get(position));

            Pair imagePair = new Pair<>(view.findViewById(R.id.thumbnail), DetailActivity.IMAGE_TRANSITION_NAME);
            Pair titlePair = new Pair<>(view.findViewById(R.id.title), DetailActivity.TITLE_TRANSITION_NAME);

            ActivityOptionsCompat transitionActivityOptions =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                            MainActivity.this, imagePair, titlePair);

            ActivityCompat.startActivity(MainActivity.this,
                    detailIntent, transitionActivityOptions.toBundle());
        }
    }

    @OnClick(R.id.fab)
    public void onFabClick(View view) {
        Intent intent = new Intent(this, CategoryListActivity.class);


        Pair imagePair = new Pair<>(view, CategoryListActivity.IMAGE_TRANSITION_NAME);


        ActivityOptionsCompat transitionActivityOptions =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        MainActivity.this, imagePair);

        ActivityCompat.startActivityForResult(MainActivity.this,
                intent, CategoryListActivity.REQUEST_CODE, transitionActivityOptions.toBundle());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == CategoryListActivity.REQUEST_CODE) {
            Category category = data.getParcelableExtra("category");
            recyclerView.setAdapter(null);
            currentCategory = category.getParam();
            currentIndex = 0;
            if (currentCategory.equals("video")) {
                getVideos();
            } else {
                getPosts();
            }

        }
    }

    @Override
    public void onRefresh() {
        if (currentCategory.equals("video")) {
            getVideos();
        } else {
            getPosts();
        }
    }


    private class VideoFetcher extends AsyncTask<Void, Void, List<Video>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected List<Video> doInBackground(Void... params) {
//            List<Video> videos = new ArrayList<>();
//            try {
//                videos = NetworkHelper.getVideos();
//                Log.e(LOG_TAG, videos.size() + "");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Video> videos) {
            swipeRefreshLayout.setRefreshing(false);
            VideoAdapter videoAdapter = new VideoAdapter(MainActivity.this, videos);
            recyclerView.setAdapter(videoAdapter);
        }
    }
}
