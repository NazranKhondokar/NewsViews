package com.nazran.newsviews.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nazran.newsviews.R;
import com.nazran.newsviews.models.News;
import com.nazran.newsviews.network.GetDataService;
import com.nazran.newsviews.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DrawerHome extends Fragment {

    private String token = "Bearer 35c2dd993e9d43c397096a16b7611a00";
    public DrawerHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<News> call = service.getAllArticle(token);
        call.enqueue(new Callback<News>() {

            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                //generateDataList(response.body());
                Log.e("Home", response.body().getStatus());
                Log.e("Home", "Articles: " + response.body().getArticles().size());
                Toast.makeText(getActivity(), "Data Fetched", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.e("Home", "" + t.getMessage());
            }
        });

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drawer_home, container, false);
    }

}
