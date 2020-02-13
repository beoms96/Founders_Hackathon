package com.android.samsung.codelab.guestbookdapp.contract;

public interface MapContract {
    interface View {
        void setLoading(boolean isLoading);

        void showReviewActivity();

        void toast(String message);

    }

    interface Presenter {
        void loadMap();

        void loadBalance();
    }
}
