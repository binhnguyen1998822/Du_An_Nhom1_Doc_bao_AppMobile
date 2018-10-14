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

import com.squareup.picasso.Picasso;
import com.tbn.duan2.PostDetaill;
import com.tbn.duan2.NTB.Weblink;
import com.tbn.duan2.R;
import com.tbn.duan2.model.Latest.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.IteamViewHoder> {
    private List<Post> hdwallpapers;
    Context context;


    public PostAdapter(List<Post> hdwallpapers, Context context) {
        this.hdwallpapers = hdwallpapers;
        this.context = context;

    }



    @NonNull
    @Override
    public IteamViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_latest,null);

        return new IteamViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IteamViewHoder holder, int position) {
        final Post hdwallpaper = hdwallpapers.get(position);
        Picasso.with(context).load(hdwallpaper.getImage())
                .placeholder(R.drawable.error_images)
                .placeholder(R.drawable.error)
                .into(holder.imgLatest);
        holder.title.setText(hdwallpaper.getTitle());
        holder.des.setText(hdwallpaper.getDesperation());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, PostDetaill.class);
                intent.putExtra("img",hdwallpaper.getImage());
                intent.putExtra("title",hdwallpaper.getTitle());
                intent.putExtra("content",hdwallpaper.getContent());
                context.startActivities(new Intent[]{intent});
            }
        });
    }

    @Override
    public int getItemCount() {
        return hdwallpapers.size();
    }

    public  class IteamViewHoder extends RecyclerView.ViewHolder {
        private ImageView imgLatest;
        private TextView title;
        private TextView des;



        public IteamViewHoder(View itemView) {
            super(itemView);
            imgLatest = (ImageView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.title);
            des = (TextView) itemView.findViewById(R.id.des);
        }
    }
}
