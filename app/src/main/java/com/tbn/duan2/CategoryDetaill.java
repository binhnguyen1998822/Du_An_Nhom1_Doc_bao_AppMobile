package com.tbn.duan2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.tbn.duan2.Adapter.PostAdapter;
import com.tbn.duan2.model.Latest.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetaill extends AppCompatActivity {
    ProgressBar processBar;
    SwipeRefreshLayout swipeRefreshLayout;
    private List<Post> data_list;
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_latest);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        processBar = (ProgressBar) findViewById(R.id.progressBar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });
        data_list = new ArrayList<>();
        getdatahd();
        recyclerView = findViewById(R.id.recycler_latest);
        postAdapter = new PostAdapter(data_list, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(postAdapter);

    }
    private void getdatahd() {

        try {
            Bundle b = new Bundle();
            b = getIntent().getExtras();
            String title = b.getString("title");
            setTitle(title);
            String post = b.getString("post");
            JSONArray jarray = new JSONArray(post);
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject object = jarray.getJSONObject(i);
                Post data = new Post(
                        object.getString("category_ID"),
                        object.getString("post_title"),
                        object.getString("post_content"),
                        object.getString("post_image"),
                        object.getString("post_title"),
                        object.getString("created_at"));
                data_list.add(data);


            }
            Log.d("logd", jarray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (data_list != null) {
            processBar.setVisibility(View.GONE);
        }

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
