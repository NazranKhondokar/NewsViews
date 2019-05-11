package com.nazran.newsviews.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nazran.newsviews.R;
import com.nazran.newsviews.adapters.CustomAdapter;
import com.nazran.newsviews.models.Article;
import com.nazran.newsviews.models.News;
import com.nazran.newsviews.network.GetDataService;
import com.nazran.newsviews.network.RetrofitClientInstance;
import com.nazran.newsviews.utils.CustomItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DrawerHome extends Fragment {

    private String token = "Bearer 35c2dd993e9d43c397096a16b7611a00";

    @BindView(R.id.customRecyclerView)
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    private CustomAdapter adapter;
    private List<Article> articleList = new ArrayList<>();

    public DrawerHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drawer_home, container, false);
        ButterKnife.bind(this, view);

        adapter = new CustomAdapter(getActivity(), articleList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<News> call = service.getAllArticle(token);
        call.enqueue(new Callback<News>() {

            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                progressDialog.dismiss();
                Log.e("Home", response.body().getStatus());
                if (response.body().getStatus().equals("ok")) {
                    generateDataList(response.body().getArticles());
                }
                Log.e("Home", "Articles: " + response.body().getArticles().size());
                Toast.makeText(getActivity(), "Data Fetched", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.e("Home", "" + t.getMessage());
            }
        });

        return view;
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(final List<Article> list) {
        adapter = new CustomAdapter(getActivity(), list, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                final Bundle args = new Bundle();
                args.putString("urlToImage", list.get(position).getUrlToImage());
                args.putString("description", list.get(position).getDescription());
                args.putString("url", list.get(position).getUrl());

                NewsPopUp newsPopUp = new NewsPopUp();
                newsPopUp.setArguments(args);
                newsPopUp.show(getActivity().getSupportFragmentManager(), "PopUp");
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
