package com.android.samsung.codelab.guestbookdapp;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.samsung.codelab.guestbookdapp.contract.WriteFeedContract;
import com.android.samsung.codelab.guestbookdapp.databinding.ActivityWriteFeedBinding;
import com.android.samsung.codelab.guestbookdapp.model.UserInfo;
import com.android.samsung.codelab.guestbookdapp.presenter.WriteFeedPresenter;

public class WriteFeedActivity extends AppCompatActivity implements WriteFeedContract.ViewContract {

    private static final String TAG = WriteFeedActivity.class.getSimpleName();
    WriteFeedContract.PresenterContract contract;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        ActivityWriteFeedBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_write_feed);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        binding.setFeed(UserInfo.getInstance().getFeedToWrite());

        WriteFeedPresenter presenter = new WriteFeedPresenter(this);
        binding.setWriteFeedPresenter(presenter);
        contract = presenter;

        setEmojiBottomSheet();

        EditText commentEditText = findViewById(R.id.txt_user_comment);
        commentEditText.addTextChangedListener(new TextWatcher() {
            String previousString = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                previousString = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (commentEditText.getLineCount() > 3) {
                    commentEditText.setText(previousString);
                    commentEditText.setSelection(commentEditText.length());
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_write_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_send) {
            contract.actionSend();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setLoadingProgress(boolean isLoading) {

        runOnUiThread(() -> {
            ProgressBar bar = findViewById(R.id.progress_bar);
            EditText editTextName = findViewById(R.id.txt_user_name);
            EditText editTextComment = findViewById(R.id.txt_user_comment);

            bar.setVisibility(isLoading ? View.VISIBLE : View.INVISIBLE);
            editTextName.setEnabled(!isLoading);
            editTextComment.setEnabled(!isLoading);
        });
    }

    @Override
    public void toastMessage(String message) {
        runOnUiThread(() -> Toast.makeText(this, message, Toast.LENGTH_LONG).show());
    }

    @Override
    public void finishActivity() {
        runOnUiThread(this::finish);
    }

    @Override
    public void setEmojiBottomSheet() {
        EditText userName = findViewById(R.id.txt_user_name);

        FeelBottomSheetDialog dialog = new FeelBottomSheetDialog();
        dialog.show(getSupportFragmentManager(), "bottomSheet");
        dialog.setDialogDismissListener(() -> {
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                userName.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(userName, InputMethodManager.SHOW_IMPLICIT);
            }, 500);
        });
    }

}
