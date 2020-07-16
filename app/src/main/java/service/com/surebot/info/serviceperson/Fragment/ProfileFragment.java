package service.com.surebot.info.serviceperson.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import service.com.surebot.info.serviceperson.activities.AboutMeActivity;
import service.com.surebot.info.serviceperson.activities.AccountDetailsActivity;
import service.com.surebot.info.serviceperson.activities.AwardsAndCertificateActivity;
import service.com.surebot.info.serviceperson.DataFiles.awardsData;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.ResponseClass.PartnerProfileResponse;
import service.com.surebot.info.serviceperson.activities.IdentityVerificationActivity;
import service.com.surebot.info.serviceperson.activities.PersonalDetailView;
import service.com.surebot.info.serviceperson.activities.PrimePartnerActivity;

public class ProfileFragment extends Fragment {
View v;
    CircleImageView profImge;
    CardView cardView;
    String image;
    PopupWindow pop;
    List<awardsData> awardsDatas = new ArrayList<>();
    ConstraintLayout constraintLayout;



    Dialog progress;

    ArrayList<PartnerProfileResponse.PartnerProfileRecords> partnerProfileRecords ;

    Button IdentVerf,personalDetails,aboutME,awards,accDetails,primePartner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v  = inflater.inflate(R.layout.profile_fragment_layout, container, false);
        ButterKnife.bind(this, v);
        progress = new Dialog(getActivity(), android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);

        Listners();

        return v;
        //constraintLayout = v.findViewById(R.id.CL1);

    }



    private void Listners() {
        personalDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PersonalDetailView.class));
            }

        });
        IdentVerf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), IdentityVerificationActivity.class));
            }
        });
        aboutME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AboutMeActivity.class));
            }
        });
        awards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AwardsAndCertificateActivity.class));
            }
        });
        accDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AccountDetailsActivity.class));
            }
        });
        primePartner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PrimePartnerActivity.class));
            }
        });

    }


}