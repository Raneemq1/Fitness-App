package com.example.fitnessapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.adapter.MentalRecyclerAdapter;
import com.example.fitnessapp.databinding.FragmentMentalBinding;
import com.example.fitnessapp.model.MentalGame;

public class MentalFragment extends Fragment {

    private FragmentMentalBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMentalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView recycler = binding.mentalRecycler;
        recycler.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
        /**
         Fill info before sent to recycler view
         */
        String[] captions = new String[MentalGame.mental_games.length];
        int[] ids = new int[MentalGame.mental_games.length];

        for (int i = 0; i < captions.length; i++) {
            captions[i] = MentalGame.mental_games[i].getName();
            ids[i] = MentalGame.mental_games[i].getImageID();
        }
        /**
         Assign recyclerview adapter
         */
        MentalRecyclerAdapter adapter = new MentalRecyclerAdapter(captions, ids);
        recycler.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}