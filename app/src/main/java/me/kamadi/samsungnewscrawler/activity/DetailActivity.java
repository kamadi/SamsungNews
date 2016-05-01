package me.kamadi.samsungnewscrawler.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.kamadi.samsungnewscrawler.App;
import me.kamadi.samsungnewscrawler.R;
import me.kamadi.samsungnewscrawler.helper.PicassoImageGetter;
import me.kamadi.samsungnewscrawler.model.PostItem;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_PARAM = "extra_production";
    public static final String IMAGE_TRANSITION_NAME = "image_transition";
    public static final String TITLE_TRANSITION_NAME = "title_transition";
    private static final String LOG_TAG = DetailActivity.class.getSimpleName();

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.description_text_view)
    TextView descriptionTextView;

    @Bind(R.id.poster_image_view)
    ImageView posterImageView;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    private PostItem postItem;


//    @Bind(R.id.title_text_view)
//    TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_full);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        postItem = App.getInstance().getPostItem();

        getSupportActionBar().setTitle(postItem.getPostInfo().getTitle());

        ViewCompat.setTransitionName(posterImageView, IMAGE_TRANSITION_NAME);
//        ViewCompat.setTransitionName(titleTextView, TITLE_TRANSITION_NAME);

        Picasso.with(this)
                .load(postItem.getThumbInfo().getSrc())
                .into(posterImageView);

//        titleTextView.setText(postItem.getPostInfo().getTitle());
        NewFetcherTask newFetcherTask = new NewFetcherTask(postItem);
        newFetcherTask.execute();
    }

    private void displayDescription(String result) {
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        Spanned spanned = Html.fromHtml(result, new PicassoImageGetter(descriptionTextView, getResources(), Picasso.with(this), width), null);
        progressBar.setVisibility(View.GONE);
        descriptionTextView.setVisibility(View.VISIBLE);
        descriptionTextView.setText(spanned);
//        webview.loadDataWithBaseURL("", data, "text/html", "UTF-8", "");
    }

    @OnClick(R.id.fab)
    public void onFabClicked(View view) {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, postItem.getPostInfo().getTitle());
        share.putExtra(Intent.EXTRA_TEXT, postItem.getPostInfo().getLink());

        startActivity(Intent.createChooser(share, "Share link!"));
    }

    private class NewFetcherTask extends AsyncTask<Void, Void, String> {

        PostItem postItem;

        public NewFetcherTask(PostItem postItem) {
            this.postItem = postItem;
        }

        @Override
        protected String doInBackground(Void... params) {
            Log.e(LOG_TAG, "fetching started");
            Document document = null;
            String result = "";
            try {
                document = Jsoup.connect(postItem.getPostInfo().getLink()).get();
                Log.e(LOG_TAG, document.text());
                result = document.getElementsByClass("text_cont").get(0).html();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {

            displayDescription(s);
        }
    }
}
