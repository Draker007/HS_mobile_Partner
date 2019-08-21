package service.com.surebot.info.serviceperson.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.com.surebot.info.serviceperson.Activity.AboutMeActivity;
import service.com.surebot.info.serviceperson.Activity.AccountDetailsActivity;
import service.com.surebot.info.serviceperson.Activity.IdentityVerificationActivity;
import service.com.surebot.info.serviceperson.Activity.PersonalDetailView;
import service.com.surebot.info.serviceperson.Activity.PrimePartnerActivity;
import service.com.surebot.info.serviceperson.Activity.AwardsAndCertificateActivity;
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.DataFiles.awardsData;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.PartnerProfileRequest;
import service.com.surebot.info.serviceperson.ResponseClass.PartnerProfileResponse;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class Profile_Fragment extends Fragment {
View v;
    CircleImageView profImge;
    CardView cardView;
    String image;
    PopupWindow pop;
    List<awardsData> awardsDatas = new ArrayList<>();
    ConstraintLayout constraintLayout;

    @BindView(R.id.FragProName)
    TextView name;

    @BindView(R.id.FragproEmail)
    TextView email;

    @BindView(R.id.FragproNumber)
    TextView number;
    ArrayList<PartnerProfileResponse.PartnerProfileRecords> partnerProfileRecords ;

    Button IdentVerf,personalDetails,aboutME,awards,accDetails,primePartner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v  = inflater.inflate(R.layout.profile_fragment_layout, container, false);
        ButterKnife.bind(this, v);
        callProfileAPI();
        Initialize();
        Listners();

        return v;
        //constraintLayout = v.findViewById(R.id.CL1);

    }

    private void callProfileAPI() {

        try {
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            HttpLoggingInterceptor registrationInterceptor = new HttpLoggingInterceptor();
            registrationInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(registrationInterceptor);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiInterface request = retrofit.create(ApiInterface.class);
            PartnerProfileRequest partnerLandingPage = new PartnerProfileRequest();
//            // lbannerimage_request.getUserLandingRequest().setUserid("1");
            partnerLandingPage.setDocket(Constants.TOKEN);
            partnerLandingPage.setUser_ID(AppicationClass.getUserId_FromLogin());
            Log.e("lol", "onResponse: "+AppicationClass.getUserId_FromLogin());

            // Log.e("lol", "user_SignUp: "+ partnerLandingPage.getCategory_ID()+partnerLandingPage.getDocket()+partnerLandingPage.getUser_Contact_Number()+partnerLandingPage.getUser_Email()+partnerLandingPage.getUser_Gender()+partnerLandingPage.getUser_Password() );
            Call<PartnerProfileResponse> call = request.profileDetails(partnerLandingPage);
            call.enqueue(new Callback<PartnerProfileResponse>() {
                @Override
                public void onResponse(Call<PartnerProfileResponse> call, Response<PartnerProfileResponse> response) {
                    if (response.isSuccessful()) {


                        PartnerProfileResponse partnerProfileResponse = response.body();

                        partnerProfileRecords = new ArrayList<>(Arrays.asList(partnerProfileResponse.getPartner_profile_details()));
                     //   Log.e("lola", "onResponse: "+partnerUserDetails  );
                        //Log.e("lol", "onResponse: " +response.body().getUser_Contact_Number());

                            name.setText(partnerProfileRecords.get(0).getUser_Name());
                            email.setText(partnerProfileRecords.get(0).getUser_Email());
                            number.setText(partnerProfileRecords.get(0).getUser_ID());

                    }


                }

            @Override
            public void onFailure(Call<PartnerProfileResponse> call, Throwable t) {
                System.out.println("In User Login Method 7");
            }
        });
    }catch (Exception e) {

        e.printStackTrace();


    }

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

    private void Initialize() {pop=new PopupWindow(getContext());
        profImge = v.findViewById(R.id.EditImageProf);
        cardView = v.findViewById(R.id.cardView101);
        IdentVerf = v.findViewById(R.id.btnIdenVerf);
        aboutME = v.findViewById(R.id.btnAbout);
        awards = v.findViewById(R.id.btnAward);
        accDetails = v.findViewById(R.id.accDetailsBTN);
        primePartner = v.findViewById(R.id.btnPrimePartner);

        //Buttons
        personalDetails = v.findViewById(R.id.btnPersonaldetails);




        cardView.setBackgroundResource(R.drawable.card_round);
        profImge.bringToFront();
    }
}
