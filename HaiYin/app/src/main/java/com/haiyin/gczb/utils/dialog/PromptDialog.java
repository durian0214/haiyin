package com.haiyin.gczb.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiyin.gczb.R;

/**
 * Created
 * by durian
 * 2019/1/4.
 */
public class PromptDialog {
    private static PromptDialog intance = null;
    private Dialog dialog = null;

    public static PromptDialog getIntance() {
        synchronized (PromptDialog.class) {
            if (intance == null) {
                intance = new PromptDialog();

            }
        }
        return intance;
    }

    /**
     * 关闭dialog
     */
    public void disDialog() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                try {
                    dialog.dismiss();
                } catch (Exception e) {

                }
            }
        }
    }

    public void showPromptDialog(Context context , String title , String content, View.OnClickListener onclick) {
        dialog = new Dialog(context, R.style.myDialog);
        dialog.setContentView(R.layout.dialog);
        dialog.setCancelable(false);//点击框外取消
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ((TextView)dialog.findViewById(R.id.tv_dialog_title)).setText(title);
        ((TextView)dialog.findViewById(R.id.tv_dialog_content)).setText(content);
        dialog.findViewById(R.id.btn_dialog).setOnClickListener(onclick);
        dialog.show();
    }
}
