package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

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
import service.com.surebot.info.serviceperson.RequestClass.Send_otp_mail_Request;
import service.com.surebot.info.serviceperson.ResponseClass.Send_otp_mail_Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    @BindView(R.id.EmailORNumber)
    EditText gEmailORNumber;

    @BindView(R.id.SendOTPbtn)
    EditText gSendOTPbtn;
        Dialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        progress = new Dialog(this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);
        gSendOTPbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gEmailORNumber.getText().toString().isEmpty()){
                    gEmailORNumber.setError("Enter Details");
                    gEmailORNumber.requestFocus();
                }
                else{
    //CallAPI to Get OTP

                }
                    }
        });
    }

    public void CallGetOTPAPI(){
        try {
            System.out.println("In User Login Method 1");
            progress.show();
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            HttpLoggingInterceptor registrationInterceptor = new HttpLoggingInterceptor();
            registrationInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(registrationInterceptor);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.82/services_at_home_test/Api/")
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            System.out.println("In User Login Method 2");
            ApiInterface request = retrofit.create(ApiInterface.class);
            Send_otp_mail_Request lservice_request = new Send_otp_mail_Request();


            lservice_request.setEmailId(gEmailORNumber.getText().toString());
            lservice_request.setDocket(Constants.TOKEN);


            Call<Send_otp_mail_Response> call = request.SendMailGetOTP(lservice_request);
            call.enqueue(new Callback<Send_otp_mail_Response>() {
                @Override
                public void onResponse(Call<Send_otp_mail_Response> call, Response<Send_otp_mail_Response> response) {



                    }


                 //   progress.dismiss();
               // }



                @Override
                public void onFailure(Call<Send_otp_mail_Response> call, Throwable t) {
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
