package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
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
import service.com.surebot.info.serviceperson.RequestClass.Account_details_Request;
import service.com.surebot.info.serviceperson.RequestClass.Add_account_details_Request;
import service.com.surebot.info.serviceperson.ResponseClass.Account_details_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Add_account_details_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class AccountDetailsActivity extends AppCompatActivity {

    @BindView(R.id.bnkName)
    EditText gbnkName;

    @BindView(R.id.bnkBranchName)
    EditText gbnkBranchName;

    @BindView(R.id.accountDetailsBack)
    ConstraintLayout gaccountDetailsBack;

    @BindView(R.id.AccHolderName)
    EditText gAccHolderName;


    @BindView(R.id.AccNumber)
    EditText gAccNumber;


    @BindView(R.id.AccIFSCcode)
    EditText gAccIFSCcode;


    @BindView(R.id.AccConfirmBTN)
    Button btnConfirm;

    private Dialog progress;

    ArrayList<Account_details_Response.account_details_records> account_details_records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        ButterKnife.bind(this);
        progress = new Dialog(this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);
        gaccountDetailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!gbnkName.getText().toString().trim().isEmpty()){
                    if (!gbnkBranchName.getText().toString().trim().isEmpty()){
                        if (!gAccHolderName.getText().toString().trim().isEmpty()){
                            if (!gAccNumber.getText().toString().trim().isEmpty()) {
                                if (!gAccIFSCcode.getText().toString().trim().isEmpty()) {
                                    setAccountDetails();
                                }else {
                                    gAccIFSCcode.setError("Fill IFSC Code");
                                    gAccIFSCcode.requestFocus();
                                }
                            }else {
                                gAccNumber.setError("Fill Account Number");
                                gAccNumber.requestFocus();
                            }}else {
                            gAccHolderName.setError("Fill Account Holder Name");
                            gAccHolderName.requestFocus();
                        }}
                    else {
                        gbnkBranchName.setError("Fill Branch Name");
                        gbnkBranchName.requestFocus();
                    }}else {
                    gbnkName.setError("Fill Branch Name");
                    gbnkName.requestFocus();
                }
            }
        });

    }


        public void getAccountDetails(){
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
                Account_details_Request lservice_request = new Account_details_Request ();


                lservice_request.setUser_ID(AppicationClass.getUserId_FromLogin());
                lservice_request.setDocket(Constants.TOKEN);


                Call<Account_details_Response> call = request.AccountDetails(lservice_request);
                call.enqueue(new Callback<Account_details_Response>() {
                    @Override
                    public void onResponse(Call<Account_details_Response> call, Response<Account_details_Response> response) {
                        if (response.isSuccessful()) {

                            Account_details_Response account_details_response = response.body();

                            account_details_records = new ArrayList<>(Arrays.asList(account_details_response.getAccount_details_records()));
                           //TODO have to set Validation
                            gbnkName.setText(account_details_records.get(0).getBank_Name());
                            gAccHolderName.setText(account_details_records.get(0).getAccount_Holder_Name());
                            gAccIFSCcode.setText(account_details_records.get(0).getIFSC_Code());
                            gAccNumber.setText(account_details_records.get(0).getAccount_Number());
                            gbnkBranchName.setText(account_details_records.get(0).getBank_Branch_Name());


                        }


                        progress.dismiss();
                    }



                    @Override
                    public void onFailure(Call<Account_details_Response> call, Throwable t) {

                        Toast.makeText(AccountDetailsActivity.this, getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();

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


    public void setAccountDetails(){
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
            Add_account_details_Request lservice_request = new Add_account_details_Request ();


            lservice_request.setUser_ID(AppicationClass.getUserId_FromLogin());
            lservice_request.setDocket(Constants.TOKEN);
            lservice_request.setAccount_Number(gAccNumber.getText().toString());
            lservice_request.setAccount_Holder_Name(gAccHolderName.getText().toString());
            lservice_request.setBank_Branch_Name(gbnkBranchName.getText().toString());
            lservice_request.setBank_Name(gbnkName.getText().toString());
            lservice_request.setIFSC_Code(gAccIFSCcode.getText().toString());

            Call<Add_account_details_Response> call = request.Add_account_details(lservice_request);
            call.enqueue(new Callback<Add_account_details_Response>() {
                @Override
                public void onResponse(Call<Add_account_details_Response> call, Response<Add_account_details_Response> response) {
                    if (response.isSuccessful()) {

                        Add_account_details_Response account_details_response = response.body();
                        if (account_details_response.getAccount_details_response().equals("true")) {
                            Toast.makeText(AccountDetailsActivity.this, "Updated Account Details ", Toast.LENGTH_SHORT).show();
                            //TODO setup response

                        }
                    }

                    progress.dismiss();
                }



                @Override
                public void onFailure(Call<Add_account_details_Response> call, Throwable t) {
                    Toast.makeText(AccountDetailsActivity.this, getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();

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
