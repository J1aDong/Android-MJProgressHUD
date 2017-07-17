package com.j1adong.android_mjprogresshud;

import android.view.Gravity;

/**
 * Created by Sai on 15/8/16.
 */
public class MJProgressHUDAnimateUtil {
    private static final int INVALID = -1;

    static int getAnimationResource(int gravity, boolean isInAnimation) {
        switch (gravity) {
            case Gravity.TOP:
                return isInAnimation ? R.anim.mjslide_in_top : R.anim.mjslide_out_top;
            case Gravity.BOTTOM:
                return isInAnimation ? R.anim.mjslide_in_bottom : R.anim.mjslide_out_bottom;
            case Gravity.CENTER:
                return isInAnimation ? R.anim.mjfade_in_center : R.anim.mjfade_out_center;
            default:
                // This case is not implemented because we don't expect any other gravity at the moment
        }
        return INVALID;
    }
}
