package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

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
import service.com.surebot.info.serviceperson.ResponseClass.Account_details_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class AccountDetailsActivity extends AppCompatActivity {

    @BindView(R.id.bnkName)
    EditText gbnkName;

    @BindView(R.id.bnkBranchName)
    EditText gbnkBranchName;


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
                    progress.dismiss();


                    @Override
                    public void onFailure(Call<Account_details_Response> call, Throwable t) {
                        System.out.println("In User Login Method 7");
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
