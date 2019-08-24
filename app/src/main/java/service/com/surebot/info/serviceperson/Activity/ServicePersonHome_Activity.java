package service.com.surebot.info.serviceperson.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.Fragment.AllRequestList_Fragment;
import service.com.surebot.info.serviceperson.Fragment.MyTask_Fragment;
import service.com.surebot.info.serviceperson.Fragment.Profile_Fragment;
import service.com.surebot.info.serviceperson.Manager.CacheManager;
import service.com.surebot.info.serviceperson.R;

public class ServicePersonHome_Activity extends AppCompatActivity {
   // ConstraintLayout moree,payment,myService,Logout,helpCenter,ChangePass;
    BottomNavigationView navigation;
    ImageView closeMore;
    // this will setup
    int status =1;
    Fragment fragment = null;
    @BindView(R.id.AboutUs)
    ConstraintLayout Aboutus;
    @BindView(R.id.myPayment)
    ConstraintLayout MyPayment;
    @BindView(R.id.myService)
    ConstraintLayout myService;
    @BindView(R.id.changePassword)
    ConstraintLayout changePass;
    @BindView(R.id.HelpCenter)
    ConstraintLayout HelpCenter;
    @BindView(R.id.Logout)
    ConstraintLayout logout;
    @BindView(R.id.moree)
    ConstraintLayout moree;
    private Dialog progress;

    private CacheManager mCacheManager;

    int back = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_person_home);

        ButterKnife.bind(this);

        mCacheManager = new CacheManager(ServicePersonHome_Activity.this);


        progress = new Dialog(this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);
        navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setItemIconTintList(null);
        fragment = new MyTask_Fragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();
       Listners();
        closeMore = findViewById(R.id.moreCLose);



    }  //Oncreate close

    private void Listners() {


        myService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServicePersonHome_Activity.this,ServicesAdd_Activity.class));
            }
        });

        MyPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServicePersonHome_Activity.this,PaymentActivity.class));
            }
        });
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServicePersonHome_Activity.this,changePasswordActivity.class));
            }
        });
        Aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServicePersonHome_Activity.this,AboutUsActivity.class));
            }
        });
        HelpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServicePersonHome_Activity.this,HelpCenterActivity.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: logout

                mCacheManager.setUserId("");
                mCacheManager.setUserRole("");
                mCacheManager.setFirstTimeLaunch(true);
                mCacheManager.setUserName("");
                startActivity(new Intent(ServicePersonHome_Activity.this, SplashActivity.class));
               finish();
            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_mytask:
                    status=1;
                    if (moree.getVisibility() == View.VISIBLE)
                    {
                        moree.setVisibility(View.GONE);
                    }
                    fragment = new MyTask_Fragment();
                    loadFragment(fragment);
                    //   back = 0;
                    break;
                case R.id.navigation_profile:
                    status=2;
                    if (moree.getVisibility() == View.VISIBLE)
                    {
                        moree.setVisibility(View.GONE);
                    }
                    fragment = new Profile_Fragment();
                    loadFragment(fragment);
                    back = 0;
                    break;
                case R.id.navigation_allrequest:
                    status=3;
                    if (moree.getVisibility() == View.VISIBLE)
                    {
                        moree.setVisibility(View.GONE);
                    }
                    fragment = new AllRequestList_Fragment();
                    loadFragment(fragment);
                    back = 0;
                    break;
                case R.id.navigation_more:

                    if (moree.getVisibility() == View.VISIBLE)
                    {
                            moree.setVisibility(View.GONE);

                    }
                    else
                    {
                        moree.setVisibility(View.VISIBLE);
                    }
                    closeMore.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            moree.setVisibility(View.GONE);
                            switch (status){
                                case 1: navigation.setSelectedItemId(R.id.navigation_mytask);
                                    break;
                                case 2: navigation.setSelectedItemId(R.id.navigation_profile);
                                    break;
                                case 3: navigation.setSelectedItemId(R.id.navigation_allrequest);
                                    break;
                            }
                        }
                    });
                    back = 0;
                    break;

            }
            return  true;
        }
    };


    //For Switching Fragments
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void onBackPressed() {



        if (back == 0) {
            back++;
            navigation.setSelectedItemId(R.id.navigation_mytask);
            Fragment fragment = new MyTask_Fragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (back == 1) {
            System.exit(0);
        }
    }
}




