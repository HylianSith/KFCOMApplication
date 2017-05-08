package com.mycompany.kfcomapplication.fragments;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mycompany.kfcomapplication.R;
import com.mycompany.kfcomapplication.activities.Home;

/**
 * Created by jason_000 on 11/29/2016.
 */

public class HomeFragment extends Fragment {
    LinearLayout mLinearLayout;
    public static HomeFragment getInstance() {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_view, container, false);

        return rootView;
    }
}
