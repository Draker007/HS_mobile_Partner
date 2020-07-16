package service.com.surebot.info.serviceperson.utils;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class Utils {
    public static void startIntent(Activity activity, Class<?> tClass, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        activity.startActivity(intent);
    }

    public static void showHideView(boolean isShow, View... views) {
        for (View view : views) {
            if (view == null) return;
            if (isShow) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }
    }
}