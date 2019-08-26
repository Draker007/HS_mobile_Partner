package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.Manager.CacheManager;
import service.com.surebot.info.serviceperson.R;

public class SplashActivity extends AppCompatActivity {

    private CacheManager mCacheManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);


        mCacheManager = new CacheManager(this);

        if (!mCacheManager.isFirstTimeLaunch()) {
            Intent userHomeIntent = new Intent(getApplicationContext(),ServicePersonHome_Activity.class);
            startActivity(userHomeIntent);
            finish();
        }
        else {
            Intent loginIntent = new Intent(getApplicationContext(),Login_Activity.class);
            startActivity(loginIntent);
            finish();
        }
    }
}
