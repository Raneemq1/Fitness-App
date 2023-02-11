package com.example.fitnessapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fitnessapp.databinding.FragmentFoodBinding;


public class FoodFragment extends Fragment {

    private FragmentFoodBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ;

        binding = FragmentFoodBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.txtfood;

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}