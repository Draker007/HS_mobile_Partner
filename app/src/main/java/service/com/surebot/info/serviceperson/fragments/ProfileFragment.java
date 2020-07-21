package service.com.surebot.info.serviceperson.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.activities.AboutMeActivity;
import service.com.surebot.info.serviceperson.activities.AwardsAndCertificateActivity;
import service.com.surebot.info.serviceperson.activities.IdentityVerificationActivity;
import service.com.surebot.info.serviceperson.activities.PersonalDetailView;
import service.com.surebot.info.serviceperson.activities.PrimePartnerActivity;

public class ProfileFragment extends Fragment {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        context = getActivity();

        profileDetailsCL.setOnClickListener(view1 -> startActivity(new Intent(getActivity(), PersonalDetailView.class)));
        identityVerificationCL.setOnClickListener(view12 -> startActivity(new Intent(getActivity(),
                IdentityVerificationActivity.class)));
        aboutMeCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AboutMeActivity.class));
            }
        });
        licensesAndCertificatedCL.setOnClickListener(view13 -> startActivity(new Intent(getActivity(),
                AwardsAndCertificateActivity.class)));
        /*accDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AccountDetailsActivity.class));
            }
        });*/
        primePartnerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PrimePartnerActivity.class));
            }
        });
        return view;
    }
}
