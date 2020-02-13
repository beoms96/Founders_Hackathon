package com.android.samsung.codelab.guestbookdapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.samsung.codelab.guestbookdapp.adapter.FeelIconAdapter;

public class FeelBottomSheetDialog extends BottomSheetDialogFragment {

    private OnDialogDismissListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_dialog_feel, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new FeelIconAdapter(this::dismiss));

        return view;

    }

    public void setDialogDismissListener(OnDialogDismissListener listener) {
        this.listener = listener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (listener != null) {
            listener.onDialogDismiss();
        }
    }

    public interface OnDialogDismissListener {
        void onDialogDismiss();
    }
}
