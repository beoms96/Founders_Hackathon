package com.example.mjd_final;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mjd_final.contract.AccountContract;
import com.example.mjd_final.model.UserInfo;

public class AccountActivity extends AppCompatActivity implements AccountContract {

    private static final String TAG = AccountActivity.class.getSimpleName();
    AccountContract.Presenter contract;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_account_fragment);

        TextView nickName = findViewById(R.id.nickname);
        nickName.setText(UserInfo.getInstance().getAddress());
        Log.d(TAG, "nickName: " + nickName);

        Button myReviews = findViewById(R.id.myReviews);
        myReviews.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

            }
        });

    }

}
