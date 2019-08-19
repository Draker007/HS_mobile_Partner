package service.com.surebot.info.serviceperson.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.PartnerProfileRequest;
import service.com.surebot.info.serviceperson.ResponseClass.PartnerProfileResponse;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class PersonalDetailView extends AppCompatActivity {
    ImageView edit;
    @BindView(R.id.NameView)
            TextView gName;
    @BindView(R.id.NumberView)
    TextView gNumber;
    @BindView(R.id.EmailView)
    TextView gEmail;
    @BindView(R.id.GenderView)
    TextView gGender;

    @BindView(R.id.ProfessionView)
    TextView gProfession;


    @BindView(R.id.AddressView)
    TextView gAddress;

    @BindView(R.id.dobView)
    TextView gDob;

    @BindView(R.id.PerDetailImage)
    ImageView profileImage;


    TextView address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_detail_view);
        ButterKnife.bind(this);
        edit= findViewById(R.id.editProfIMG);
        address = findViewById(R.id.AddressView);
        address.setEnabled(false);

        getPersonalDetails();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalDetailView.this,AddPersonalDetailsActivity.class));
            }
        });
    }

    private void getPersonalDetails() {
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

                        ArrayList<PartnerProfileResponse.PartnerProfileRecords> partnerProfileRecords = new ArrayList<>(Arrays.asList(partnerProfileResponse.getPartner_profile_details()));
                        gName.setText(partnerProfileRecords.get(0).getUser_Name());
                        if(!partnerProfileRecords.get(0).getUser_Image_Path().equals("")) {
                            Glide.with(PersonalDetailView.this).load(partnerProfileRecords.get(0).getUser_Image_Path()).into(profileImage);
                        }
                            gEmail.setText(partnerProfileRecords.get(0).getUser_Email());
                            gGender.setText(partnerProfileRecords.get(0).getUser_Gender());
                            gNumber.setText(partnerProfileRecords.get(0).getUser_Contact_Number());
                            if (partnerProfileRecords.get(0).getUser_Full_Address() == null) {
                                gAddress.setTextColor(getResources().getColor(R.color.color_red));
                                gAddress.setText("* Your current Address is required");
                            } else {
                                gAddress.setTextColor(getResources().getColor(R.color.color_white));
                                gAddress.setText(partnerProfileRecords.get(0).getUser_Full_Address());
                            }



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


}
