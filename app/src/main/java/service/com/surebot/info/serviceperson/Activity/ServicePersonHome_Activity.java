package service.com.surebot.info.serviceperson.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.Fragment.AllRequestList_Fragment;
import service.com.surebot.info.serviceperson.Fragment.MyTask_Fragment;
import service.com.surebot.info.serviceperson.R;

public class ServicePersonHome_Activity extends AppCompatActivity {

    BottomNavigationView navigation;
    Fragment fragment = null;

    int back = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_person_home);

        ButterKnife.bind(this);


        navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setItemIconTintList(null);
        fragment = new MyTask_Fragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();


    }  //Oncreate close

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_mytask:
                    fragment = new MyTask_Fragment();
                    loadFragment(fragment);
                    //   back = 0;
                    break;
                case R.id.navigation_profile:
                    fragment = new MyTask_Fragment();
                    loadFragment(fragment);
                    back = 0;
                    break;
                case R.id.navigation_allrequest:
                    fragment = new AllRequestList_Fragment();
                    loadFragment(fragment);
                    back = 0;
                    break;
                case R.id.navigation_more:
                    fragment = new MyTask_Fragment();
                    loadFragment(fragment);
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




