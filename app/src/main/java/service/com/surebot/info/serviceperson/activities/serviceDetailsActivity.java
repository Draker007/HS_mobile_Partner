package service.com.surebot.info.serviceperson.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
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
import service.com.surebot.info.serviceperson.RequestClass.PaymentReceived_Request;
import service.com.surebot.info.serviceperson.ResponseClass.PaymentReceived_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class serviceDetailsActivity extends AppCompatActivity {
    @BindView(R.id.endservice_button)
    Button gEndservice_button;






    @BindView(R.id.username_text)
    TextView gUsername_text;

    @BindView(R.id.servicesdate_text)
    TextView gServicesDate_text;


    @BindView(R.id.servicestime_text)
    TextView gServicestime_text;

    private Dialog progress;
    ArrayList<PaymentReceived_Response.PaymentReceived_Records> gPaymentReceived_Arraylist;
     TextView gAmmountpaid_text,gServicelist_text,gService_date,gService_time;

    String gUser_Name;
    String gBooking_Date;
    String gBooking_Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);
        ButterKnife.bind(this);

        progress = new Dialog(serviceDetailsActivity.this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);

        Intent lintent = this.getIntent();
        gUser_Name = lintent.getStringExtra("UserName");
        gBooking_Date= lintent.getStringExtra("BookingDate");
        gBooking_Time= lintent.getStringExtra("BookingTime");


        gUsername_text.setText(gUser_Name);
        gServicesDate_text.setText(gBooking_Date);
        gServicestime_text.setText(gBooking_Time);


        gEndservice_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog paymentsuccessfull_dialog=new Dialog(serviceDetailsActivity.this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                paymentsuccessfull_dialog.setContentView(R.layout.paymentrecieved_popup);
                Button lpaymentReceived_Button = paymentsuccessfull_dialog.findViewById(R.id.paymentrcvd_button);
                gAmmountpaid_text= paymentsuccessfull_dialog.findViewById(R.id.ammountpaid_text);
                gServicelist_text= paymentsuccessfull_dialog.findViewById(R.id.servicelist_text);
                gService_date= paymentsuccessfull_dialog.findViewById(R.id.service_date);
                gService_time= paymentsuccessfull_dialog.findViewById(R.id.service_time);


                lpaymentReceived_Button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        startActivity(new Intent(serviceDetailsActivity.this,ServicePersonHome_Activity.class).putExtra("HomeScreenFlow","frompaymentreceived"));
                       // paymentsuccessfull_dialog.dismiss();
                        finish();
                    }
                });

                paymentsuccessfull_dialog.show();
                paymentsuccessfull_dialog.setCancelable(false);

            }
        });

    }


    //To Get Payment Received API

    private void get_CancelledServiceRequestList()  {
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
            PaymentReceived_Request lNewRequestList_Request = new PaymentReceived_Request();

            lNewRequestList_Request.setUser_ID(AppicationClass.getUserId_FromLogin());
            lNewRequestList_Request.setDocket(Constants.TOKEN);

            Call<PaymentReceived_Response> call = request.get_PaymentReceivedDetails(lNewRequestList_Request);
            call.enqueue(new Callback<PaymentReceived_Response>() {


                @Override
                public void onResponse(Call<PaymentReceived_Response> call, Response<PaymentReceived_Response> response) {
                    if (response.isSuccessful()) {
                        PaymentReceived_Response lPaymentReceived_Response = response.body();
                        gPaymentReceived_Arraylist = new ArrayList<>(Arrays.asList(lPaymentReceived_Response.getCompleted_payment_response()));
                    if(!gPaymentReceived_Arraylist.get(0).getTransaction_ID().equals("No Results Found")){
                        gAmmountpaid_text.setText(gPaymentReceived_Arraylist.get(0).getTotal_Amount());
                        gServicelist_text.setText(gPaymentReceived_Arraylist.get(0).getService());
                        gService_date.setText(gPaymentReceived_Arraylist.get(0).getBooking_Date());
                        gService_time.setText(gPaymentReceived_Arraylist.get(0).getBooking_Date());


                      /*  gServicesDate_text.setText(gPaymentReceived_Arraylist.get(0).getBooking_Date());
                        gServicestime_text.setText(gPaymentReceived_Arraylist.get(0).getBooking_Date());*/

                    }

                        progress.dismiss();
                    }
                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<PaymentReceived_Response> call, Throwable t) {

                    Toast.makeText(serviceDetailsActivity.this, getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }catch (Exception e) {

            e.printStackTrace();
            progress.dismiss();

        }

    }

}
