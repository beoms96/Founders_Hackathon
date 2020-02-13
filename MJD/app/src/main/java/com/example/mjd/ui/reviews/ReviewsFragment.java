package com.example.mjd.ui.reviews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mjd.R;

public class ReviewsFragment extends Fragment {

    private ReviewsViewModel reviewsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        reviewsViewModel =
                ViewModelProviders.of(this).get(ReviewsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_reviews, container, false);

        return root;
    }
}