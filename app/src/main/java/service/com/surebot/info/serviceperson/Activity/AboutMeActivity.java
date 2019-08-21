package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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
import service.com.surebot.info.serviceperson.ResponseClass.About_me_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class AboutMeActivity extends AppCompatActivity {

    @BindView(R.id.boutMeIntro)
    EditText intro;

    @BindView(R.id.boutmeHired)
    EditText hiredNo;

    @BindView(R.id.BoutmeExper)
    EditText experience;

    @BindView(R.id.aboutBack)
    ImageView back;

    private Dialog progress;

    @BindView(R.id.btnBoutmeSave)
    Button savebtn;
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
    }

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

                            about_me_Response =new ArrayList<>(Arrays.asList(aboutme_response.getAbout_me_response()));
                            if(!about_me_Response.get(0).getAbout_ID().equals("Invalid")){
                                experience.setText(about_me_Response.get(0).getExperience());
                                intro.setText(about_me_Response.get(0).getIntroduction());
                                //TODO have to set for No of time hired
                            }


                           }


                            progress.dismiss();
                        }



                    @Override
                    public void onFailure(Call<About_me_Response> call, Throwable t) {
                        System.out.println("In User Login Method 7");
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
