package com.android.samsung.codelab.guestbookdapp.contract;

public interface ReviewContract {

    interface View {
        void setLoading(boolean isLoading);

        void toast(String message);
    }

    interface Presenter {
        void loadReviews();

        void loadBalance();
    }

}
