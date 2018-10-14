package com.tbn.duan2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tbn.duan2.CategoryDetaill;
import com.tbn.duan2.R;
import com.tbn.duan2.model.Latest.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHoder> {
    private List<Category> hdwallpaperList;
    private Context context;

    public CategoryAdapter(List<Category> hdwallpaperList, Context context) {
        this.hdwallpaperList = hdwallpaperList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, null);

        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        final Category hdwallpaper = hdwallpaperList.get(position);
        holder.name.setText(hdwallpaper.getCat_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, CategoryDetaill.class);
                intent.putExtra("post", hdwallpaper.getPost());
                intent.putExtra("title",hdwallpaper.getCat_name());
                context.startActivities(new Intent[]{intent});
            }
        });

    }

    @Override
    public int getItemCount() {
        return hdwallpaperList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        private ImageView imgLatest;
        private TextView name;


        public ViewHoder(View itemView) {
            super(itemView);
            imgLatest = (ImageView) itemView.findViewById(R.id.imgCategory);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
