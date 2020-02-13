package com.example.mjd_final;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mjd_final.contract.IntroContract;
import com.example.mjd_final.model.UserInfo;
import com.example.mjd_final.util.PrefsHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.samsung.android.sdk.coldwallet.ScwCoinType;
import com.samsung.android.sdk.coldwallet.ScwService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IntroContract.View {

    private FragmentManager fragmentManager = getSupportFragmentManager();

    private MapFragment mapFragment = new MapFragment();
    private ReviewFragment reviewFragment = new ReviewFragment();
    private AccountFragment accountFragment = new AccountFragment();

    private static final String TAG = MainActivity.class.getSimpleName();
    private IntroContract.Presenter contract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ConnectKeyStore
        PrefsHelper.initialize(getApplicationContext());
        ConnectKeyStore connectKeyStore = new ConnectKeyStore(this);
        contract = connectKeyStore;

        String cachedSeedHash = PrefsHelper.getInstance().getCachedSeedHash();

        boolean alreadyInit = (!TextUtils.isEmpty(cachedSeedHash));

        if (alreadyInit) {
            final Handler handler = new Handler();
            handler.postDelayed(() -> contract.initializeKeyStore(), 800);
        }

        String hdPath = ScwService.getHdPath(ScwCoinType.ETH, 0);
        ArrayList<String> hdPathList = new ArrayList<>();
        hdPathList.add(hdPath);
        String publicAddress;
        ScwService.getInstance().getAddressList(new ScwService.ScwGetAddressListCallback() {
            @Override
            public void onSuccess(List<String> addressList) {
                String publicAddress = addressList.get(0);
                Log.d(TAG, "public address: " + publicAddress);
            }

            @Override
            public void onFailure(int errorCode, @Nullable String s) {

            }
        }, hdPathList);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout,mapFragment).commitAllowingStateLoss();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (menuItem.getItemId()){
                    case R.id.navigation_map:{
                        transaction.replace(R.id.frame_layout,
                                mapFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_reviews:{
                        transaction.replace(R.id.frame_layout,
                                reviewFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_account:{
                        transaction.replace(R.id.frame_layout,
                                accountFragment).commitAllowingStateLoss();
                        break;
                    }
                }
                return true;
            }
        });
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
