package com.durian.lib.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;

import com.durian.lib.R;

/**
 * Created
 * by durian
 * 2018/2/1.
 * loading
 */

public class LoadingUtil {
    private AlertDialog alertDialog;
    public void showLoadingDialog(Context mContext) {
        alertDialog = new AlertDialog.Builder(mContext).create();
        alertDialog.setContentView(R.layout.dialog_loading_alert);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        alertDialog.setCancelable(false);
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK)
                    return true;
                return false;
            }
        });
        alertDialog.show();
    }

    public void disLoadingDialog() {
        if (null != alertDialog && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }


}
