package service.com.surebot.info.serviceperson.activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.ApplicationClass;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.fragments.PaymentCompletedFragment;
import service.com.surebot.info.serviceperson.fragments.PaymentPendingFragment;

public class PaymentActivity extends BaseActivity {

    @BindView(R.id.paymentTabLayout)
    TabLayout tabLayout;
    @BindView(R.id.paymentContainer)
    FrameLayout paymentContainer;
    @BindView(R.id.arrowIV)
    ImageView arrowIV;
    @BindView(R.id.paymentTV)
    TextView paymentTV;

    private PaymentCompletedFragment completedFragment;
    private PaymentPendingFragment pendingFragment;
    private PaymentActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_payment_fragment);
        ButterKnife.bind(this);
        context = PaymentActivity.this;
        setUpTabs();
        setTabSelectListener();
        replaceFragment(completedFragment);

        arrowIV.setOnClickListener(v -> {
            finish();
        });

        int categorySelection = ApplicationClass.getCategorySelection();
        int headerBackground = R.drawable.electrician_header_background;

        switch (categorySelection) {
            case 1:
            case 2: {
                headerBackground = R.drawable.salon_header_bg;
            }
            break;
            case 3: {
                headerBackground = R.drawable.electrician_header_background;
            }
            break;
            case 4: {
                headerBackground = R.drawable.plumber_header_bg;
            }
            break;
            case 5: {
                headerBackground = R.drawable.carpenter_header_bg;
            }
            break;
            case 6: {
                headerBackground = R.drawable.cleaning_header_bg;
            }
            break;
            case 7: {
                headerBackground = R.drawable.appliances_header_bg;
            }
            break;
            case 8: {
                headerBackground = R.drawable.pest_header_bg;
            }
            break;
            case 9: {
                headerBackground = R.drawable.paint_header_bg;
            }
            break;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            paymentTV.setBackground(ContextCompat.getDrawable(context, headerBackground));
        }
    }

    private void setTabSelectListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setCurrentTabFragment(int position) {
        if (position == 1) {
            replaceFragment(pendingFragment);
        } else {
            replaceFragment(completedFragment);
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = context.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.paymentContainer, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    private void setUpTabs() {
        completedFragment = new PaymentCompletedFragment();
        pendingFragment = new PaymentPendingFragment();
        tabLayout.addTab(tabLayout.newTab().setText("Completed"));
        tabLayout.addTab(tabLayout.newTab().setText("Pending"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }
}

