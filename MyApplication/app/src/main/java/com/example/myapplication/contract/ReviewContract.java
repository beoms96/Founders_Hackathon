package com.example.myapplication.contract;

import androidx.databinding.ObservableArrayList;

import com.example.myapplication.model.Review;
import com.example.myapplication.remote.ReviewLoader;

public interface ReviewContract {
    interface View {
        void setLoading(boolean isLoading);

        void showWriteFeedActivity();

        void toast(String message);
    }

    interface Presenter {
        void loadFeed(ReviewLoader reviewLoader, ObservableArrayList<Review> feeds);

        void loadBalance();
    }


}
