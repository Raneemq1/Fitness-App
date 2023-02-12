package com.example.fitnessapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.adapter.PhysicalRecyclerAdapter;
import com.example.fitnessapp.databinding.FragmentPhysicalBinding;
import com.example.fitnessapp.model.PhysicalExercise;

public class PhysicalFragment extends Fragment {

    private FragmentPhysicalBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentPhysicalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView recycler = binding.physicalRecycler;
        recycler.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));

        PhysicalRecyclerAdapter adapter = new PhysicalRecyclerAdapter(PhysicalExercise.physicalExercises);
        recycler.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}