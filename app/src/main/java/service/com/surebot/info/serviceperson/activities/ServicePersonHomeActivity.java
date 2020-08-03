package service.com.surebot.info.serviceperson.activities;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.ApplicationClass;
import service.com.surebot.info.serviceperson.Manager.CacheManager;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.fragments.MoreFragment;
import service.com.surebot.info.serviceperson.fragments.MyTaskFragment;
import service.com.surebot.info.serviceperson.fragments.ProfileFragment;
import service.com.surebot.info.serviceperson.fragments.RequestListFragment;

public class ServicePersonHomeActivity extends BaseActivity {

    BottomNavigationView navigation;
    int status = 1;
    String check = null;
    Fragment fragment = null;

    int back = 0;

    String HomeScreen_Flow;
    String gUserId_FromLogin, gUserName_FromLogin, gPremium_PartnerId, gCategoryId_FromLogin;
    private ServicePersonHomeActivity context;
    private int categorySelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_person_home);

        ButterKnife.bind(this);
        context = ServicePersonHomeActivity.this;
        categorySelection = ApplicationClass.getCategorySelection();
        Intent lintent = this.getIntent();
        HomeScreen_Flow = lintent.getStringExtra("HomeScreenFlow");

        System.out.println("Service Person screen flow is " + HomeScreen_Flow);

        SharedPreferences sp1 = ServicePersonHomeActivity.this.getSharedPreferences("User_Info", 0);
        gUserId_FromLogin = sp1.getString("User_Id", null);
        gUserName_FromLogin = sp1.getString("User_Name", null);
        gPremium_PartnerId = sp1.getString("Premium_PartnerId", null);
        gCategoryId_FromLogin = sp1.getString("Category_Id", null);

        ApplicationClass.setUserId_FromLogin(gUserId_FromLogin);
        ApplicationClass.setUserName_FromLogin(gUserName_FromLogin);
        ApplicationClass.setPremium_PartenerId(gPremium_PartnerId);
        ApplicationClass.setCategoryId_FromLogin(gCategoryId_FromLogin);
        HomeScreen_Flow = "fromlogin";

        check = getIntent().getStringExtra("status");
        if (check != null && check.equalsIgnoreCase("1")) {
            navigation.setSelectedItemId(R.id.navigationProfile);
        }

        CacheManager mCacheManager = new CacheManager(ServicePersonHomeActivity.this);

        Dialog progress = new Dialog(this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);
        navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        fragment = new MyTaskFragment();
        loadFragment(fragment);
        requestMultiplePermissions();
    }  //Oncreate close

    private void requestMultiplePermissions() {
        Dexter.withActivity(ServicePersonHomeActivity.this)
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
                            Toast.makeText(ServicePersonHomeActivity.this, "Request Denied By  User", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }).onSameThread()
                .check();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigationTask:
                fragment = new MyTaskFragment();
                break;
            case R.id.navigationProfile:
                fragment = new ProfileFragment();
                back = 0;
                break;
            case R.id.navigationRequest:
                fragment = new RequestListFragment();
                back = 0;
                break;
            case R.id.navigationMore:
                fragment = new MoreFragment();
                back = 0;
                break;
        }
        setBottomNavColor(item);
        loadFragment(fragment);
        return true;
    };

    private void setBottomNavColor(MenuItem selectedItem) {
        int bottomNavColor = R.color.colorBlue;
        switch (categorySelection) {
            case 1:
            case 2: {
                bottomNavColor = R.color.colorBlue;
            }
            break;
            case 3: {
                bottomNavColor = R.color.colorElectricianText;
            }
            break;
            case 4: {
                bottomNavColor = R.color.colorPrimaryBlue;
            }
            break;
            case 5: {
                bottomNavColor = R.color.colorBrown;
            }
            break;
            case 6:
            case 8: {
                bottomNavColor = R.color.colorGray;
            }
            break;
            case 7: {
                bottomNavColor = R.color.colorApplianceText;
            }
            break;
            case 9: {
                bottomNavColor = R.color.colorPaintText;
            }
            break;
        }

        int[][] states = new int[][]{
                new int[]{android.R.attr.state_checked}, // state_checked
                new int[]{}
        };

        int[] colors = new int[]{
                R.color.menuSelectedColor,
                bottomNavColor
        };

        ColorStateList myTextColorList = new ColorStateList(states, colors);
        navigation.setItemTextColor(myTextColorList);
        navigation.setItemIconTintList(myTextColorList);
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedItem.setIconTintList(ColorStateList.valueOf(ContextCompat.getColor(context, bottomNavColor)));
        }*/
    }

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
            navigation.setSelectedItemId(R.id.navigationTask);
            Fragment fragment = new MyTaskFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (back == 1) {
            finishAffinity();
            moveTaskToBack(true);
        }
    }
}




