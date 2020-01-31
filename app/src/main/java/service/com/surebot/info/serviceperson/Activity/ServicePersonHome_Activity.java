package service.com.surebot.info.serviceperson.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.app.Application;

import android.Manifest;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.Fragment.AllRequestList_Fragment;
import service.com.surebot.info.serviceperson.Fragment.MyTask_Fragment;
import service.com.surebot.info.serviceperson.Fragment.Profile_Fragment;
import service.com.surebot.info.serviceperson.Manager.CacheManager;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class ServicePersonHome_Activity extends AppCompatActivity {
   // ConstraintLayout moree,payment,myService,Logout,helpCenter,ChangePass;
    BottomNavigationView navigation;
    ImageView closeMore;
    // this will setup
    int status =1;
    String check=null;
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

    String HomeScreen_Flow;

    String gUserId_FromLogin,gUserName_FromLogin,gPremium_PartnerId,gCategoryId_FromLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_person_home);

        ButterKnife.bind(this);
        Intent lintent = this.getIntent();
        HomeScreen_Flow = lintent.getStringExtra("HomeScreenFlow");

        System.out.println("Service Person screen flow is " + HomeScreen_Flow);

        SharedPreferences sp1 =ServicePersonHome_Activity.this.getSharedPreferences("User_Info", 0);
        gUserId_FromLogin = sp1.getString("User_Id", null);
        gUserName_FromLogin= sp1.getString("User_Name", null);
        gPremium_PartnerId =  sp1.getString("Premium_PartnerId", null);
        gCategoryId_FromLogin=  sp1.getString("Category_Id", null);

        AppicationClass.setUserId_FromLogin(gUserId_FromLogin);
        AppicationClass.setUserName_FromLogin(gUserName_FromLogin);
        AppicationClass.setPremium_PartenerId(gPremium_PartnerId);
        AppicationClass.setCategoryId_FromLogin(gCategoryId_FromLogin);

        if(HomeScreen_Flow.equals("fromlogin")){

            System.out.println("In Homesceen entering into fromlogin");
            fragment = new MyTask_Fragment();
        }


        if(HomeScreen_Flow.equals("frombecomeprime")){

            System.out.println("In Homesceen entering into frombecomeprime");
            fragment = new MyTask_Fragment();
        }

        if(HomeScreen_Flow.equals("fromcreateprofile")){
            System.out.println("In Homesceen entering into fromcreateprofile");
            fragment = new Profile_Fragment();
        }

        if(HomeScreen_Flow.equals("fromserviceadd")){
            System.out.println("In Homesceen entering into Service Add");
            fragment = new MyTask_Fragment();
        }


        check = getIntent().getStringExtra("status");
        if(check=="1"){
            navigation.setSelectedItemId(R.id.navigation_profile);
        }

        mCacheManager = new CacheManager(ServicePersonHome_Activity.this);

        progress = new Dialog(this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);
        navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setItemIconTintList(null);




        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();
       Listners();
        requestMultiplePermissions();
        closeMore = findViewById(R.id.moreCLose);



    }  //Oncreate close



    private void  requestMultiplePermissions(){
        Dexter.withActivity(ServicePersonHome_Activity.this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            Toast.makeText(ServicePersonHome_Activity.this, "Request Denied By  User", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }).onSameThread()
                .check();
    }
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
            finishAffinity();
            System.exit(0);
        }
    }
}




