package service.com.surebot.info.serviceperson.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.ApplicationClass;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.activities.AboutMeActivity;
import service.com.surebot.info.serviceperson.activities.IdentityVerificationActivity;
import service.com.surebot.info.serviceperson.activities.LicensesAndCertificatesActivity;
import service.com.surebot.info.serviceperson.activities.PersonalDetailsActivity;
import service.com.surebot.info.serviceperson.activities.PrimePackageActivity;
import service.com.surebot.info.serviceperson.utils.Utils;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private FragmentActivity context;

    @BindView(R.id.profileDetailsCL)
    ConstraintLayout profileDetailsCL;
    @BindView(R.id.identityVerificationCL)
    ConstraintLayout identityVerificationCL;
    @BindView(R.id.aboutMeCL)
    ConstraintLayout aboutMeCL;
    @BindView(R.id.licensesAndCertificatedCL)
    ConstraintLayout licensesAndCertificatedCL;
    @BindView(R.id.primePartnerTV)
    TextView primePartnerTV;
    @BindView(R.id.primePartnerCL)
    ConstraintLayout primePartnerCL;
    @BindView(R.id.profileHeaderCL)
    ConstraintLayout profileHeaderCL;
    @BindView(R.id.serviceLayoutIV)
    ImageView serviceLayoutIV;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        context = getActivity();

        int categorySelection = ApplicationClass.getCategorySelection();
        int headerLayout = R.drawable.salonlayout;
        int layoutServiceImage = R.drawable.electrician;

        switch (categorySelection) {
            case 1:
            case 2: {
                headerLayout = R.drawable.salonlayout;
                layoutServiceImage = R.drawable.salon;
            }
            break;
            case 3: {
                headerLayout = R.drawable.electrician_layout;
                layoutServiceImage = R.drawable.electrician;
            }
            break;
            case 4: {
                headerLayout = R.drawable.plumber_layout;
                layoutServiceImage = R.drawable.plumber;
            }
            break;
            case 5: {
                headerLayout = R.drawable.carpenter_layout;
                layoutServiceImage = R.drawable.carpenter;
            }
            break;
            case 6: {
                headerLayout = R.drawable.cleaning_layout;
                layoutServiceImage = R.drawable.cleaning;
            }
            break;
            case 7: {
                headerLayout = R.drawable.appliance_layout;
                layoutServiceImage = R.drawable.appliances;
            }
            break;
            case 8: {
                headerLayout = R.drawable.pest_layout;
                layoutServiceImage = R.drawable.pest;
            }
            break;
            case 9: {
                headerLayout = R.drawable.paint_layout;
                layoutServiceImage = R.drawable.paint;
            }
            break;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            profileHeaderCL.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getActivity()), headerLayout));
            serviceLayoutIV.setImageDrawable(ContextCompat.getDrawable(getActivity(), layoutServiceImage));
        }

        profileDetailsCL.setOnClickListener(this);
        identityVerificationCL.setOnClickListener(this);
        aboutMeCL.setOnClickListener(this);
        licensesAndCertificatedCL.setOnClickListener(this);
        primePartnerCL.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profileDetailsCL: {
                startActivity(new Intent(getActivity(), PersonalDetailsActivity.class));
            }
            break;
            case R.id.identityVerificationCL: {
                startActivity(new Intent(getActivity(), IdentityVerificationActivity.class));
            }
            break;
            case R.id.aboutMeCL: {
                startActivity(new Intent(getActivity(), AboutMeActivity.class));
            }
            break;
            case R.id.licensesAndCertificatedCL: {
                startActivity(new Intent(getActivity(), LicensesAndCertificatesActivity.class));
            }
            break;
            case R.id.primePartnerCL: {
                startActivity(new Intent(getActivity(), PrimePackageActivity.class));
            }
            break;
        }
    }
}
