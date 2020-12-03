package com.netflix.app.utlis;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.WindowManager;

import com.netflix.app.R;

import java.util.Objects;

class ProgressActivity extends Dialog {
    private static ProgressActivity progressDialog;

    private ProgressActivity(Context context) {
        super(context);
    }

    static void showDialog(final Context context) {
        progressDialog = new ProgressActivity(context);
        progressDialog.setContentView(R.layout.progress_dialog);
        Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        progressDialog.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                ((Activity) context).onBackPressed();
            }
        });
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    public static void dismissDialog() {
        if (progressDialog.isShowing()) {
            Objects.requireNonNull(progressDialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            progressDialog.dismiss();
        }
    }


}
