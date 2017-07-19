package com.j1adong.android_mjprogresshud.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.j1adong.android_mjprogresshud.MJProgressHUD;
import com.j1adong.android_mjprogresshud.listener.OnDismissListener;

public class MainActivity extends Activity {
    private MJProgressHUD mjProgressHUD;
    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mjProgressHUD = new MJProgressHUD(this);
        mjProgressHUD.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(MJProgressHUD hud) {
                // todo something, like: finish current activity
                Toast.makeText(getApplicationContext(), "dismiss", Toast.LENGTH_SHORT)
                        .show();
            }
        });

    }

    public void show(View view) {
        mjProgressHUD.enableBlur(false);
        mjProgressHUD.show();
    }

    public void showWithMaskType(View view) {
        mjProgressHUD.enableBlur(false);
        mjProgressHUD
                .showWithMaskType(MJProgressHUD.SVProgressHUDMaskType.None);
        // mjProgressHUD.showWithMaskType(MJProgressHUD.SVProgressHUDMaskType.Black);
        // mjProgressHUD.showWithMaskType(MJProgressHUD.SVProgressHUDMaskType.BlackCancel);
        // mjProgressHUD.showWithMaskType(MJProgressHUD.SVProgressHUDMaskType.Clear);
        // mjProgressHUD.showWithMaskType(MJProgressHUD.SVProgressHUDMaskType.ClearCancel);
        // mjProgressHUD.showWithMaskType(MJProgressHUD.SVProgressHUDMaskType.Gradient);
        // mjProgressHUD.showWithMaskType(MJProgressHUD.SVProgressHUDMaskType.GradientCancel);
    }

    public void showWithStatus(View view) {
        mjProgressHUD.enableBlur(false);
        mjProgressHUD.showWithStatus("loading...");
    }

    public void showInfoWithStatus(View view) {
        mjProgressHUD.enableBlur(false);
        mjProgressHUD
                .showInfoWithStatus("This is info", MJProgressHUD.SVProgressHUDMaskType.None);
    }

    public void showSuccessWithStatus(View view) {
        mjProgressHUD.enableBlur(false);
        mjProgressHUD.showSuccessWithStatus("Success！");
    }

    public void showErrorWithStatus(View view) {
        mjProgressHUD.enableBlur(false);
        mjProgressHUD
                .showErrorWithStatus("Error", MJProgressHUD.SVProgressHUDMaskType.GradientCancel);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    progress = progress + 5;
                    if (mjProgressHUD.getProgressBar()
                            .getMax() != mjProgressHUD.getProgressBar()
                            .getProgress()) {
                        mjProgressHUD.getProgressBar().setProgress(progress);
                        mjProgressHUD.setText("progress " + progress + "%");

                        mHandler.sendEmptyMessageDelayed(0, 500);
                    } else {
                        mjProgressHUD.dismiss();
                    }
                    break;
                case 1:
                    mjProgressHUD.enableBlur(false);
                    mjProgressHUD.showSuccessWithStatus("Success");
                    break;
                default:
                    break;
            }

        }
    };

    public void showWithProgress(View view) {
        mjProgressHUD.enableBlur(false);
        progress = 0;
        mjProgressHUD.getProgressBar().setProgress(progress);// 先重设了进度再显示，避免下次再show会先显示上一次的进度位置所以要先将进度归0
        mjProgressHUD.showWithProgress("progress " + progress
                + "%", MJProgressHUD.SVProgressHUDMaskType.Black);
        mHandler.sendEmptyMessageDelayed(0, 500);
    }

    public void showBlur(View view) {
        mjProgressHUD.enableBlur(true);
        mjProgressHUD.show();
    }

    public void showLoadingThenSuccess(View view) {
        mjProgressHUD.enableBlur(false);
        mjProgressHUD.show();
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (mjProgressHUD.isShowing()) {
                mjProgressHUD.dismiss();
                mHandler.removeCallbacksAndMessages(null);
                return false;
            }
        }

        return super.onKeyDown(keyCode, event);

    }

}
