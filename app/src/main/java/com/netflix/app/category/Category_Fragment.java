package com.netflix.app.category;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netflix.app.R;
import com.netflix.app.category.adapter.CategoryAdapter;
import com.netflix.app.databinding.CFragmentCategoryBinding;
import com.netflix.app.utlis.DataSources;


public class Category_Fragment extends Fragment {
    private CFragmentCategoryBinding binding;
    private  View view;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.c_fragment_category,container,false);
        view = binding.getRoot();

        /*TODO Create iniAllCategory  for category data in recycler view*/

        iniAllCategory();

        return view;
    }
    private void iniAllCategory() {
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), DataSources.getmovie());
        binding.allcategoryRecyclerview.setAdapter(categoryAdapter);
        binding.allcategoryRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false));
    }

}
