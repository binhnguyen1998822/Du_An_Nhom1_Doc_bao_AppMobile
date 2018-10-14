package com.tbn.duan2.Frament;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tbn.duan2.Adapter.CategoryAdapter;
import com.tbn.duan2.NTB.Weblink;
import com.tbn.duan2.R;
import com.tbn.duan2.model.Latest.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    ProgressBar processBar;
    SwipeRefreshLayout swipeRefreshLayout;
    private List<Category> data_list;
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_category, container, false);
        processBar = (ProgressBar) view.findViewById(R.id.progressBar);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reload();
            }
        });
        getActivity().setTitle("Danh mục");
        data_list = new ArrayList<>();
        getdatahd();
        recyclerView = view.findViewById(R.id.recycler_category);
        categoryAdapter = new CategoryAdapter(data_list, getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(categoryAdapter);

        return view;
    }

    private void getdatahd() {

        StringRequest request = new StringRequest(com.android.volley.Request.Method.GET, Weblink.Category, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jarray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);
                        Category data = new Category(
                                object.getString("id"),
                                object.getString("category_name"),
                                object.getString("post"));
                        data_list.add(data);


                    }
                    Log.d("logd", jarray.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                categoryAdapter.notifyDataSetChanged();
                if (data_list != null) {
                    processBar.setVisibility(View.GONE);
                }


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(getContext());
        rQueue.add(request);

    }
    public void reload() {
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }
}
