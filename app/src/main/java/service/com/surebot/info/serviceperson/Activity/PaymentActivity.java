package service.com.surebot.info.serviceperson.Activity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import service.com.surebot.info.serviceperson.Adapter.paymentFragmentAdapter;
import service.com.surebot.info.serviceperson.Fragment.PendingPaymentFragment;
import service.com.surebot.info.serviceperson.Fragment.completedPaymentFragment;
import service.com.surebot.info.serviceperson.R;
public class PaymentActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    paymentFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        tabLayout = findViewById(R.id.paymentTab);
        tabLayout.setTabTextColors(Color.WHITE,Color.BLACK);
        viewPager =findViewById(R.id.paymentViewer);
        adapter = new paymentFragmentAdapter(getSupportFragmentManager());

        adapter.AddFragment(new completedPaymentFragment(),"Completed");
        adapter.AddFragment(new PendingPaymentFragment(),"Pending");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}
