package com.netflix.app.category;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netflix.app.R;
import com.netflix.app.category.adapter.CategoryAdapter;
import com.netflix.app.utlis.DataSources;


public class Category_Fragment extends Fragment {
    RecyclerView allcategory_recyclerview;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.c_fragment_category, container, false);
        allcategory_recyclerview = view.findViewById(R.id.allcategory_recyclerview);


        iniAllCategory();

        return view;
    }
    private void iniAllCategory() {
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), DataSources.getmovie());
        allcategory_recyclerview.setAdapter(categoryAdapter);
        allcategory_recyclerview.setLayoutManager(new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false));
    }

}
