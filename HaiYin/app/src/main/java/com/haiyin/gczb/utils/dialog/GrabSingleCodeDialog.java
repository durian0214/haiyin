package com.haiyin.gczb.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.jungly.gridpasswordview.GridPasswordView;

import com.haiyin.gczb.R;
import com.haiyin.gczb.demandHall.presenter.ProjectPresenter;

/**
 * Created
 * by durian
 * 2019/1/4.
 */
public class GrabSingleCodeDialog {
    private static GrabSingleCodeDialog intance = null;
    private Dialog dialog = null;

    public static GrabSingleCodeDialog getIntance() {
        synchronized (GrabSingleCodeDialog.class) {
            if (intance == null) {
                intance = new GrabSingleCodeDialog();

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

    public void showGrabSingleCodeDialog(Context context, final ProjectPresenter projectPresenter, final String projectId) {
        dialog = new Dialog(context, R.style.myDialog);
        dialog.setContentView(R.layout.dialog_grab_single_code);
        dialog.setCancelable(true);//点击框外取消
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final GridPasswordView view = dialog.findViewById(R.id.pswView);
        view.togglePasswordVisibility();
        dialog.findViewById(R.id.btn_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projectPresenter.signProjectCheck("1", projectId, view.getPassWord());
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
