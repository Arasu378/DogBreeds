package com.kyros.technologies.dogbreeds.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyros.technologies.dogbreeds.R;

/**
 * Created by kyros on 23-08-2017.
 */

public class ByBreedFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_bybreed,container,false);
        return view;
    }
}
