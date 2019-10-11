package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import service.com.surebot.info.serviceperson.RequestClass.Partner_set_new_password_Request;
import service.com.surebot.info.serviceperson.RequestClass.Partner_set_new_password_otp_Request;
import service.com.surebot.info.serviceperson.ResponseClass.Partner_set_new_password_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Partner_set_new_password_otp_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class SetNewPasswordActivity extends AppCompatActivity  {

    String otp ,otp1 , otp2 , otp3 , otp4;


    @BindView(R.id.SetNewPassBack)
    ImageView back;

    @BindView(R.id.otp1)
    EditText gotp1;

    @BindView(R.id.otp2)
    EditText gotp2;

    @BindView(R.id.otp3)
    EditText gotp3;

    @BindView(R.id.otp4)
    EditText gotp4;

    @BindView(R.id.SetNewPassSendBTN)
    Button gSend;

    @BindView(R.id.ForgotPassSubmit)
    Button gSubmit;

    @BindView(R.id.ForgotPassNewPass)
    EditText gNewPassword;

    @BindView(R.id.ForgotPassConfirmPass)
    EditText gConfirmPassword;

    @BindView(R.id.OTPlayout)
    ConstraintLayout gOTPlayout;

    @BindView(R.id.newPasswordLayout)
    ConstraintLayout gnewPasswordLayout;
    String email;
    private Dialog progress;
    String gUserId_FromLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);
        ButterKnife.bind(this);
        progress = new Dialog(this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);

        gUserId_FromLogin= AppicationClass.getUserId_FromLogin();

        gotp1.addTextChangedListener(new SampleTextWatcherClass(gotp1));
        gotp2.addTextChangedListener(new SampleTextWatcherClass(gotp2));
        gotp3.addTextChangedListener(new SampleTextWatcherClass(gotp3));
        gotp4.addTextChangedListener(new SampleTextWatcherClass(gotp4));
         email = this.getIntent().getStringExtra("email");

        gSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!gNewPassword.getText().toString().isEmpty()) {
                    if (!gConfirmPassword.getText().toString().isEmpty()) {
                        if (gConfirmPassword.getText().toString().equals(gNewPassword.getText().toString())) {
                            ChangePasswordAPI();
                        } else {
                            gConfirmPassword.setError("Password Mismatch");
                            gConfirmPassword.requestFocus();
                        }
                    } else {
                        gConfirmPassword.setError("Enter Password Again here");
                        gConfirmPassword.requestFocus();
                    }
                }else{
                    gNewPassword.setError("Enter New Password");
                    gNewPassword.requestFocus();
                }
            }
        });
    gSend.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            if (gotp1.getText().toString().isEmpty()||gotp2.getText().toString().isEmpty()||gotp3.getText().toString().isEmpty()||gotp4.getText().toString().isEmpty()){
                Toast.makeText(SetNewPasswordActivity.this, "Enter 4 Digit OTP Sent To your E-mail or Phone Number", Toast.LENGTH_LONG).show();
                
            }
            else{
                otp = otp1+otp2+otp3+otp4;
                //call api to check OTP
                CheckOTPApi();
                Log.e("lola", "onClick: "+otp );
            }
        }
    });

    }

    //API for OTP
    public void CheckOTPApi(){

        try {
            System.out.println("In User Login Method 1");
            progress.show();
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            HttpLoggingInterceptor registrationInterceptor = new HttpLoggingInterceptor();
            registrationInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(registrationInterceptor);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("Constants.BASE_URL")
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            System.out.println("In User Login Method 2");
            ApiInterface request = retrofit.create(ApiInterface.class);
            Partner_set_new_password_otp_Request lservice_request = new Partner_set_new_password_otp_Request();


            lservice_request.setUser_ID(gUserId_FromLogin);
            lservice_request.setDocket(Constants.TOKEN);
            lservice_request.setOtp_code(otp);


            Call<Partner_set_new_password_otp_Response> call = request.ResetPassOTP(lservice_request);
            call.enqueue(new Callback<Partner_set_new_password_otp_Response>() {
                @Override
                public void onResponse(Call<Partner_set_new_password_otp_Response> call, Response<Partner_set_new_password_otp_Response> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getPartner_set_new_password_otp_response().equals(true)){
                            Toast.makeText(SetNewPasswordActivity.this, "OTP Correct Set New Password", Toast.LENGTH_SHORT).show();
                            gnewPasswordLayout.setVisibility(View.VISIBLE);
                            gOTPlayout.setVisibility(View.GONE);
                        }
                        else{
                            Toast.makeText(SetNewPasswordActivity.this, "OTP Entered is Incorrect Please Enter Correct OTP", Toast.LENGTH_SHORT).show();
                        }



                    }


                    progress.dismiss();
                }



                @Override
                public void onFailure(Call<Partner_set_new_password_otp_Response> call, Throwable t) {
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




    //Change Password Api call here
    public void ChangePasswordAPI(){

        try {
            System.out.println("In User Login Method 1");
            progress.show();
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            HttpLoggingInterceptor registrationInterceptor = new HttpLoggingInterceptor();
            registrationInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(registrationInterceptor);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("Constants.BASE_URL")
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            System.out.println("In User Login Method 2");
            ApiInterface request = retrofit.create(ApiInterface.class);
            Partner_set_new_password_Request lservice_request = new Partner_set_new_password_Request();


            lservice_request.setUser_ID(gUserId_FromLogin);
            lservice_request.setDocket(Constants.TOKEN);
            lservice_request.setUser_Email(email);



            Call<Partner_set_new_password_Response> call = request.SetNew_Password(lservice_request);
            call.enqueue(new Callback<Partner_set_new_password_Response>() {
                @Override
                public void onResponse(Call<Partner_set_new_password_Response> call, Response<Partner_set_new_password_Response> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getNew_Password_Response().equals("valid")){
                            Toast.makeText(SetNewPasswordActivity.this, "Password Changes SuccessFully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(SetNewPasswordActivity.this, response.body().getNew_Password_Response(), Toast.LENGTH_SHORT).show();
                        }



                    }


                    progress.dismiss();
                }



                @Override
                public void onFailure(Call<Partner_set_new_password_Response> call, Throwable t) {
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




    // Class for getting otp from EditText
    public class SampleTextWatcherClass implements TextWatcher{
        private View view;
        private SampleTextWatcherClass(View view)
        {
            this.view = view;
        }


        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String text = editable.toString();
            switch (view.getId()) {

                case R.id.otp1:
                    if (text.length() == 1)
                        gotp2.requestFocus();
                    otp1=text;
                    break;
                case R.id.otp2:
                    if (text.length() == 1)
                    {gotp3.requestFocus();
                    otp2 = text;
                    }
                    else if (text.length() == 0)
                        gotp1.requestFocus();
                    break;
                case R.id.otp3:
                    if (text.length() == 1)
                    { gotp4.requestFocus();
                        otp3=text;
                    }
                    else if (text.length() == 0)
                        gotp2.requestFocus();
                    break;
                case R.id.otp4:
                    if (text.length() == 0)
                        gotp3.requestFocus();
                    else
                        otp4=text;
                    break;

            }
        }
    }
}