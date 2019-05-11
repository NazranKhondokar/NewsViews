package com.nazran.newsviews.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nazran.newsviews.R;
import com.nazran.newsviews.activities.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsPopUp extends DialogFragment {

    @BindView(R.id.urlToImage)
    ImageView imageView;
    @BindView(R.id.description)
    TextView textView;
    @BindView(R.id.no)
    TextView no;
    @BindView(R.id.yes)
    TextView yes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_news_pop_up, container, false);
        ButterKnife.bind(this, view);

        String urlToImage = getArguments().getString("urlToImage");
        String description = getArguments().getString("description");
        final String url = getArguments().getString("url");

        Glide.with(getActivity())
                .load(urlToImage)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);

        textView.setText(description);

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("link", url);
                startActivity(intent);
                getDialog().dismiss();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
                getDialog().dismiss();
            }
        });

        return view;
    }
}
