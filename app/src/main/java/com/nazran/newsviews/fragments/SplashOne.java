package com.nazran.newsviews.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nazran.newsviews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashOne extends Fragment {


    public SplashOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_one, container, false);
    }

}
