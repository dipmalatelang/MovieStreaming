package com.netflix.app.gallary;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netflix.app.R;
import com.netflix.app.databinding.FragmentGallaryBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class Gallary_Fragment extends Fragment {

private FragmentGallaryBinding binding;
private View view;
    public Gallary_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_gallary_,container,false);
        view = binding.getRoot();
        return view;
    }
}
