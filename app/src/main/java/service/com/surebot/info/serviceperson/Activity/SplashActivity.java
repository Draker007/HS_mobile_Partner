package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.Manager.CacheManager;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.PartnerApprovalStatus_Request;
import service.com.surebot.info.serviceperson.ResponseClass.PartnerApprovalStatus_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

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

           // Intent userHomeIntent = new Intent(getApplicationContext(),CreateProfileActivity.class);
            userHomeIntent.putExtra("HomeScreenFlow","fromlogin");
            startActivity(userHomeIntent);

            finish();



        }
        else {
            Intent loginIntent = new Intent(getApplicationContext(),Login_Activity.class);
            startActivity(loginIntent);
            finish();
        }
    }  // On create close




}
