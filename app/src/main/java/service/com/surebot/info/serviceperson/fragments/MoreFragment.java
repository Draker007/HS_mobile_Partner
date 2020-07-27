package service.com.surebot.info.serviceperson.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.ApplicationClass;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.activities.AboutUsActivity;
import service.com.surebot.info.serviceperson.activities.ChangePasswordActivity;
import service.com.surebot.info.serviceperson.activities.HelpCenterActivity;
import service.com.surebot.info.serviceperson.activities.PaymentActivity;
import service.com.surebot.info.serviceperson.activities.PrimePackageActivity;
import service.com.surebot.info.serviceperson.utils.Utils;

public class MoreFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.paymentLL)
    LinearLayout paymentLL;
    @BindView(R.id.helpCenterLL)
    LinearLayout helpCenterLL;
    @BindView(R.id.aboutUsLL)
    LinearLayout aboutUsLL;
    @BindView(R.id.primePackagesLL)
    LinearLayout primePackagesLL;
    @BindView(R.id.changePasswordLL)
    LinearLayout changePasswordLL;
    @BindView(R.id.headerRL)
    RelativeLayout headerRL;
    private FragmentActivity context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.more_fragment, container, false);
        ButterKnife.bind(this, view);
        context = getActivity();

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
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            headerRL.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getActivity()), headerBackground));
        }

        paymentLL.setOnClickListener(this);
        aboutUsLL.setOnClickListener(this);
        helpCenterLL.setOnClickListener(this);
        primePackagesLL.setOnClickListener(this);
        changePasswordLL.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.paymentLL: {
                Utils.startIntent(context, PaymentActivity.class, false);
            }
            break;
            case R.id.aboutUsLL: {
                Utils.startIntent(context, AboutUsActivity.class, false);
            }
            break;
            case R.id.helpCenterLL: {
                Utils.startIntent(context, HelpCenterActivity.class, false);
            }
            break;
            case R.id.primePackagesLL: {
                Utils.startIntent(context, PrimePackageActivity.class, false);
            }
            break;
            case R.id.changePasswordLL: {
                Utils.startIntent(context, ChangePasswordActivity.class, false);
            }
            break;
        }
    }
}
