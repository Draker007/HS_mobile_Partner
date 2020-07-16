package service.com.surebot.info.serviceperson.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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
import service.com.surebot.info.serviceperson.RequestClass.CustomerSupport_Request;
import service.com.surebot.info.serviceperson.ResponseClass.CustomerSupport_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class CustomerSupportActivity extends AppCompatActivity {

    @BindView(R.id.address_text)
    TextView gAdress_text;

    @BindView(R.id.emailid_text)
    TextView gEmailid_text;

    @BindView(R.id.requestcall_button)
    TextView gRequestcall_button;


    private Dialog progress;

    ArrayList<CustomerSupport_Response.CustomerSupport_Records> gCustomerSupportDetails_Arraylist;

    String gSupport_phoneNumber;
    String gUserId_FromLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_support);

        ButterKnife.bind(this);
        progress = new Dialog(CustomerSupportActivity.this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);

        gUserId_FromLogin = AppicationClass.getUserId_FromLogin();

        gRequestcall_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gSupport_phoneNumber!=null){

                    Intent i = new Intent(Intent.ACTION_DIAL);
                    String p = "tel:" + getResources().getString(R.string.supportnumber);
                    i.setData(Uri.parse(p));
                    startActivity(i);
                    System.out.println("Customer Supporrt Number " + gSupport_phoneNumber);
                }


            }
        });

        get_CustomerSupportDetails();


    } //On Create Close

    private void get_CustomerSupportDetails()  {
        try {

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

            ApiInterface request = retrofit.create(ApiInterface.class);
            CustomerSupport_Request lListofFaqs_Request = new CustomerSupport_Request();
            lListofFaqs_Request.setUser_ID(gUserId_FromLogin);
            lListofFaqs_Request.setDocket(Constants.TOKEN);

            Call<CustomerSupport_Response> call = request.get_CustomerSupportDetails(lListofFaqs_Request);
            call.enqueue(new Callback<CustomerSupport_Response>() {


                @Override
                public void onResponse(Call<CustomerSupport_Response> call, Response<CustomerSupport_Response> response) {
                    if (response.isSuccessful()) {
                        CustomerSupport_Response lCustomerSupport_Response = response.body();

                        gCustomerSupportDetails_Arraylist = new ArrayList<>(Arrays.asList(lCustomerSupport_Response.getServices_response()));
                        gEmailid_text.setText(gCustomerSupportDetails_Arraylist.get(0).getEmail_ID());
                       gAdress_text.setText(gCustomerSupportDetails_Arraylist.get(0).getOffice_Address());
                        gSupport_phoneNumber = gCustomerSupportDetails_Arraylist.get(0).getContact_NO();

                        progress.dismiss();
                    }
                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<CustomerSupport_Response> call, Throwable t) {
                    Toast.makeText(CustomerSupportActivity.this, getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }catch (Exception e) {

            e.printStackTrace();
            progress.dismiss();

        }

    }
}
