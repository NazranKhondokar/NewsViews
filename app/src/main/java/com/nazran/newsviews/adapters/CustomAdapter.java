package com.nazran.newsviews.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nazran.newsviews.R;
import com.nazran.newsviews.models.Article;
import com.nazran.newsviews.utils.CustomItemClickListener;

import java.util.List;

/**
 * Adapter for Article List
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<Article> dataList;
    private Context context;
    CustomItemClickListener customItemClickListener;

    public CustomAdapter(Context context,List<Article> dataList, CustomItemClickListener listener){
        this.context = context;
        this.dataList = dataList;
        this.customItemClickListener = listener;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView title;
        TextView name;
        private ImageView urlToImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            title = mView.findViewById(R.id.title);
            name = mView.findViewById(R.id.name);
            urlToImage = mView.findViewById(R.id.urlToImage);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        final CustomViewHolder mViewHolder = new CustomViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customItemClickListener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.name.setText(dataList.get(position).getSource().getName());
        holder.title.setText(dataList.get(position).getTitle());

        Glide.with(context)
                .load(dataList.get(position).getUrlToImage())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.urlToImage);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}