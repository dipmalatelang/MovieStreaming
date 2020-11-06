package com.netflix.app.home.ui;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.netflix.app.R;
import com.netflix.app.databinding.FragmentEpisodesBinding;
import com.netflix.app.home.adapter.SeasonAdapter;
import com.netflix.app.utlis.DataSources;


public class EpisodesFragment extends Fragment  {
    private FragmentEpisodesBinding binding;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_episodes,container,false);
        view = binding.getRoot();

        /* ToDo recycler view for season */
        inisesosn();
        return view;
    }
    private void inisesosn() {
        SeasonAdapter seasonAdapter = new SeasonAdapter(getContext(), DataSources.getmovie());
        binding.recyclerviewSeason.setAdapter(seasonAdapter);
        binding.recyclerviewSeason.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }}
