package service.com.surebot.info.serviceperson.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
import service.com.surebot.info.serviceperson.RequestClass.ChangePasswordRequest;
import service.com.surebot.info.serviceperson.ResponseClass.ChangePasswordResponse;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class changePasswordActivity extends AppCompatActivity {

    private Dialog progress;
    @BindView(R.id.CPcurrent)
    EditText gCurrentPass;

    @BindView(R.id.CPconfirm)
    EditText gConfirmPass;

    @BindView(R.id.CPnew)
    EditText gNewPass;

    @BindView(R.id.CPchangePass)
    Button changePassbtn;

    @BindView(R.id.CPback)
    ImageView gBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        progress = new Dialog(this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);
        gBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        changePassbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!gCurrentPass.getText().toString().trim().isEmpty()){
                    if (!gNewPass.getText().toString().trim().isEmpty()) {
                        if (gNewPass.length() >= 5) {
                        if (!gConfirmPass.getText().toString().toString().isEmpty()) {
                            if (gConfirmPass.getText().toString().equals(gNewPass.getText().toString())) {
                                changePasswrdAPI();
                            } else {
                                Toast.makeText(changePasswordActivity.this, "Confirm Password and New Password should be same", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            gConfirmPass.setError("Confirm Password");
                            gConfirmPass.requestFocus();
                        }
                    } else {
                            gNewPass.setError("Password should be more than 5 characters");
                            gNewPass.requestFocus();
                        }
                    }else{
                        gNewPass.setError("NewPasswrd");
                        gNewPass.requestFocus();
                    }
                    }else{
                    gCurrentPass.setError("Current Password");
                    gCurrentPass.requestFocus();
                }
            }
        });

    }

    //here calling change password api
    public void changePasswrdAPI(){

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
            ChangePasswordRequest lservice_request = new ChangePasswordRequest();


            lservice_request.setUser_ID(AppicationClass.getUserId_FromLogin());
            lservice_request.setCurrent_Password(gCurrentPass.getText().toString());
            lservice_request.setPassword(gNewPass.getText().toString());
            lservice_request.setDocket(Constants.TOKEN);


            Call<ChangePasswordResponse> call = request.ChangePassword(lservice_request);
            call.enqueue(new Callback<ChangePasswordResponse>() {
                @Override
                public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                    if (response.isSuccessful()) {

                        ChangePasswordResponse aboutme_response = response.body();


                            if(aboutme_response.getPartner_change_password_response().equals("valid")){
                                Toast.makeText(changePasswordActivity.this, "Password Changed", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(changePasswordActivity.this, response.body().getPartner_change_password_response(), Toast.LENGTH_SHORT).show();
                            }



                    }


                    progress.dismiss();
                }



                @Override
                public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                    Toast.makeText(changePasswordActivity.this, getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }
        catch (Exception e) {
            System.out.println("In User Login Method 8");
            e.printStackTrace();
            //progress.dismiss();

        }
    }
}
