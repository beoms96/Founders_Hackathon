package com.android.samsung.codelab.guestbookdapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.samsung.codelab.guestbookdapp.contract.MapContract;

public class MapActivity extends AppCompatActivity implements MapContract.View {

    private MapContract.Presenter contract;

    protected void OnCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void showReviewActivity() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(getApplicationContext(), ReviewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @Override
    public void setLoading(boolean isLoading) {
        runOnUiThread(() -> {

        });
    }

    @Override
    public void toast(String message) {
        runOnUiThread(() -> Toast.makeText(this, message, Toast.LENGTH_LONG).show());
    }
}
