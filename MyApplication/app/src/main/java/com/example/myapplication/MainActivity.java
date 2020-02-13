package com.example.myapplication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.provider.FontRequest;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;


import com.example.myapplication.contract.IntroContract;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.presenter.IntroPresenter;
import com.example.myapplication.util.PrefsHelper;

public class MainActivity extends AppCompatActivity implements IntroContract.View {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private IntroContract.Presenter contract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setLoading(false);

        IntroPresenter presenter = new IntroPresenter(this);
        contract = presenter;

        binding.setIntroPresenter(presenter);

        String cachedSeedHash = PrefsHelper.getInstance().getCachedSeedHash();

        ConstraintLayout initLayout = findViewById(R.id.cl_init);
        ConstraintLayout splashLayout = findViewById(R.id.cl_splash);

        boolean alreadyInit = (!TextUtils.isEmpty(cachedSeedHash));

        initLayout.setVisibility(alreadyInit ? View.GONE : View.VISIBLE);
        splashLayout.setVisibility(alreadyInit ? View.VISIBLE : View.GONE);

        if (alreadyInit) {
            final Handler handler = new Handler();
            handler.postDelayed(() -> contract.initializeKeystore(), 800);
        }
    }

    @Override
    public void setLoading(boolean isLoading) {
        binding.setIsLoading(isLoading);
    }


    @Override
    public void toastMessage(String message) {
        runOnUiThread(() -> Toast.makeText(this, message, Toast.LENGTH_LONG).show());
    }

    @Override
    public void showDialog(String title, String btnText, String message, IntroContract.DialogOnClickListener callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(btnText, (dialog, which) -> callback.dialogOnClick())
                .show();
    }

    @Override
    public void launchDeepLink(String deepLink) {
        Uri uri = Uri.parse(deepLink);
        Intent displayIntent = new Intent(Intent.ACTION_VIEW, uri);
        displayIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
