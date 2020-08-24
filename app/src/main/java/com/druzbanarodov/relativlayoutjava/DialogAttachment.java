package com.druzbanarodov.relativlayoutjava;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class DialogAttachment extends DialogFragment {
    public static final int TAKE_PHOTO_FROM_CAMERA = 0;
    public static final int TAKE_PHOTO_FROM_GALLERY = 1;


    private OnDialogActionListener listener;

    public interface OnDialogActionListener {
        void onDialogAction(int action);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setItems(R.array.offer_tradition_fragment_dialog_list, (dialog, which) -> {
            switch (which) {
                case 0:
                    onDialogAction(TAKE_PHOTO_FROM_CAMERA);
                    break;
                case 1:
                    onDialogAction(TAKE_PHOTO_FROM_GALLERY);
                    break;
            }
        });

        return builder.create();
    }

    private void onDialogAction(int action) {
        if (listener != null) {
            listener.onDialogAction(action);
        }
    }

    public void setListener(OnDialogActionListener listener) {
        this.listener = listener;
    }


}