package service.com.surebot.info.serviceperson.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
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
import service.com.surebot.info.serviceperson.ResponseClass.AboutUs_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class AboutUsActivity extends AppCompatActivity {

    @BindView(R.id.aboutus_text)
    TextView gAboutus_text;


    private Dialog progress;

    ArrayList<AboutUs_Response.AboutUs_Record> gAboutUs_Arraylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        progress = new Dialog(this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);

        get_AboutUs();
    }
    //Get About Us

    private void get_AboutUs() {
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


            Call<AboutUs_Response> call = request.Get_AboutUs(lservice_request);
            call.enqueue(new Callback<AboutUs_Response>() {
                @Override
                public void onResponse(Call<AboutUs_Response> call, Response<AboutUs_Response> response) {
                    if (response.isSuccessful()) {

                        AboutUs_Response lAboutUs_Response = response.body();
                        gAboutUs_Arraylist = new ArrayList<>(Arrays.asList(lAboutUs_Response.getServices_response()));
                        gAboutus_text.setText(gAboutUs_Arraylist.get(0).getDetails());
                        progress.dismiss();
                    }


                    progress.dismiss();
                }



                @Override
                public void onFailure(Call<AboutUs_Response> call, Throwable t) {
                    Toast.makeText(AboutUsActivity.this, getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
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
