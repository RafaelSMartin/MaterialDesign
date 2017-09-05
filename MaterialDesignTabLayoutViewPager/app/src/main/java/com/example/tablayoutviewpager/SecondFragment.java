package com.example.tablayoutviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Indogroup02 on 04/09/2017.
 */

public class SecondFragment extends Fragment {

    // Store instance variables
    private String title;
    private int page;

    // newInstance Constructor con argumentos
    public static SecondFragment newInstance(int page, String title) {
        SecondFragment fragmentSecond = new SecondFragment();
        Bundle args = new Bundle();
        args.putInt("PageNumber", page);
        args.putString("PageTitle", title);
        fragmentSecond.setArguments(args);
        return fragmentSecond;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("PageNumber", 0);
        title = getArguments().getString("PageTitle");

    }


    // Infla la vista del fragment con el XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        return view;
    }
}
