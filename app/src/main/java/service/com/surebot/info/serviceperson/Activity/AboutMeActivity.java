package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
import service.com.surebot.info.serviceperson.RequestClass.About_me_Request;
import service.com.surebot.info.serviceperson.RequestClass.AddAboutme_Request;
import service.com.surebot.info.serviceperson.ResponseClass.About_me_Response;
import service.com.surebot.info.serviceperson.ResponseClass.AddAboutme_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class AboutMeActivity extends AppCompatActivity {

    @BindView(R.id.boutMeIntro)
    EditText gIntoroduction_text;

    @BindView(R.id.boutmeHired)
    EditText hiredNo;

    @BindView(R.id.BoutmeExper)
    EditText gExperience_Text;

    @BindView(R.id.aboutBack)
    ConstraintLayout back;

    private Dialog progress;

    @BindView(R.id.save_button)
    Button gSave_button;
    ArrayList<About_me_Response.About_me_Records> about_me_Response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        ButterKnife.bind(this);
        progress = new Dialog(this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);
        getAboutmeData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        gSave_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!gIntoroduction_text.getText().toString().trim().equals("")){

                    if(!gExperience_Text.getText().toString().trim().equals("")){

                        add_AboutmeData();
                    }

                    else{
                        gExperience_Text.setError("Enter Experience");
                        gExperience_Text.requestFocus();
                    }
                }

                else{
                    gIntoroduction_text.setError("Enter Introduction");
                    gIntoroduction_text.requestFocus();
                }
            }
        });


    }

    //Add About me  Details

    private void add_AboutmeData() {
        try {
            System.out.println("In User Login Method 1");
            progress.show();
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            HttpLoggingInterceptor registrationInterceptor = new HttpLoggingInterceptor();
            registrationInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(registrationInterceptor);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            System.out.println("In User Login Method 2");
            ApiInterface request = retrofit.create(ApiInterface.class);
            AddAboutme_Request lAddAboutme_Request = new AddAboutme_Request();


            lAddAboutme_Request.setUser_ID(AppicationClass.getUserId_FromLogin());
            lAddAboutme_Request.setUser_Intro(gIntoroduction_text.getText().toString().trim());
            lAddAboutme_Request.setUser_Experience(gExperience_Text.getText().toString().trim());
            lAddAboutme_Request.setDocket(Constants.TOKEN);


            Call<AddAboutme_Response> call = request.add_Aboutme(lAddAboutme_Request);
            call.enqueue(new Callback<AddAboutme_Response>() {
                @Override
                public void onResponse(Call<AddAboutme_Response> call, Response<AddAboutme_Response> response) {
                    if (response.isSuccessful()) {

                        AddAboutme_Response aboutme_response = response.body();

                        if(aboutme_response.getAdd_about_me_response().equals("Valid")){
                            Toast.makeText(AboutMeActivity.this, "Addedd Successfully", Toast.LENGTH_SHORT).show();
                            finish();

                        }


                    }


                    progress.dismiss();
                }



                @Override
                public void onFailure(Call<AddAboutme_Response> call, Throwable t) {
                    Toast.makeText(AboutMeActivity.this, getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }
        catch (Exception e) {
            System.out.println("In User Login Method 8");
            e.printStackTrace();
            progress.dismiss();

        }
    }
    //Get About me Details
    private void getAboutmeData() {
            try {
                System.out.println("In User Login Method 1");
                progress.show();
                OkHttpClient.Builder client = new OkHttpClient.Builder();
                HttpLoggingInterceptor registrationInterceptor = new HttpLoggingInterceptor();
                registrationInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                client.addInterceptor(registrationInterceptor);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .client(client.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                System.out.println("In User Login Method 2");
                ApiInterface request = retrofit.create(ApiInterface.class);
                About_me_Request lservice_request = new About_me_Request();


                lservice_request.setUser_ID(AppicationClass.getUserId_FromLogin());
                lservice_request.setDocket(Constants.TOKEN);


                Call<About_me_Response> call = request.getAboutme(lservice_request);
                call.enqueue(new Callback<About_me_Response>() {
                    @Override
                    public void onResponse(Call<About_me_Response> call, Response<About_me_Response> response) {
                        if (response.isSuccessful()) {

                            About_me_Response aboutme_response = response.body();
                        if(!aboutme_response.getAbout_me_response().getUserAddInfoId().equals("No Results Found")){
                            gIntoroduction_text.setText(aboutme_response.getAbout_me_response().getUser_Intro());
                            gExperience_Text.setText(aboutme_response.getAbout_me_response().getUser_Experience());

                        }


                           }


                                progress.dismiss();
                        }



                    @Override
                    public void onFailure(Call<About_me_Response> call, Throwable t) {
                        Toast.makeText(AboutMeActivity.this, getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
                         progress.dismiss();
                    }
                });
            }
            catch (Exception e) {
            System.out.println("In User Login Method 8");
            e.printStackTrace();
            progress.dismiss();

        }
    }
}
