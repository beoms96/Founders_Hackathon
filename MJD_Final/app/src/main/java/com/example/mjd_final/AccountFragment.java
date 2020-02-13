package com.example.mjd_final;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;

import com.example.mjd_final.adapter.ReviewsAdapter;
import com.example.mjd_final.contract.AccountContract;
import com.example.mjd_final.model.Review;


public class AccountFragment extends Fragment {

    private ObservableArrayList<Review> myReviewList;
    private ReviewsAdapter adapter;
    private AccountContract.Presenter contract;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        return inflater.inflate(R.layout.activity_account_fragment,container,false);
    }
}
