package service.com.surebot.info.serviceperson.activities;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import service.com.surebot.info.serviceperson.Manager.CacheManager;
import service.com.surebot.info.serviceperson.R;

public class SplashActivity extends BaseActivity {

    private CacheManager mCacheManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mCacheManager = new CacheManager(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {

            if (!mCacheManager.isFirstTimeLaunch()) {
                Intent userHomeIntent = new Intent(getApplicationContext(), ServicePersonHomeActivity.class);
                // Intent userHomeIntent = new Intent(getApplicationContext(),CreateProfileActivity.class);
                userHomeIntent.putExtra("HomeScreenFlow", "fromlogin");
                startActivity(userHomeIntent);
                finish();
            } else {
                Intent loginIntent = new Intent(getApplicationContext(), PreLoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        },1000);

    }  // On create close




}
