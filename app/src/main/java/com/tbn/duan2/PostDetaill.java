package com.tbn.duan2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PostDetaill extends AppCompatActivity {
    private ImageView imgDisplay;
    TextView tvtitle;
    String image,title,content;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detaill);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgDisplay = (ImageView) findViewById(R.id.image);
        tvtitle = (TextView) findViewById(R.id.title);
        webView= (WebView)findViewById(R.id.webview);
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        image = b.getString("img");
        title = b.getString("title");
        content = b.getString("content");
        tvtitle.setText(title);
        final WebSettings webSettings = webView.getSettings();
        webSettings.setDefaultFontSize(40);
        webView.setInitialScale(1);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
        Picasso.with(this).load(image)
                .placeholder(R.drawable.error_images)
                .placeholder(R.drawable.error)
                .into(imgDisplay);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
