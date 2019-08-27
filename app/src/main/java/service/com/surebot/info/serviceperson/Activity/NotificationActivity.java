package service.com.surebot.info.serviceperson.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.com.surebot.info.serviceperson.Adapter.NotificationAdapter;
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.DataFiles.NotificationData;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.Notification_Request;
import service.com.surebot.info.serviceperson.ResponseClass.Notification_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class NotificationActivity extends AppCompatActivity {
    RecyclerView r1;
    NotificationAdapter adapter;
    private Dialog progress;
    TextView noNotification;
    ImageView back;
    List<NotificationData> notificationDataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        r1 = findViewById(R.id.notificationRecycler);
        noNotification = findViewById(R.id.NoNotification);
        back = findViewById(R.id.NotificationGoBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        progress = new Dialog(this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);
        adapter = new NotificationAdapter(notificationDataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NotificationActivity.this);
        r1.setLayoutManager(layoutManager);
        getNotification();

    }


    void getNotification(){
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
            Notification_Request lservice_request = new Notification_Request();
        lservice_request.setUser_ID("4");
            lservice_request.setDocket(Constants.TOKEN);


            Call<Notification_Response> call = request.NotificationCall(lservice_request);
            call.enqueue(new Callback<Notification_Response>() {
                @Override
                public void onResponse(Call<Notification_Response> call, Response<Notification_Response> response) {
                    if (response.isSuccessful()) {

                        Notification_Response account_details_response = response.body();

                        ArrayList<Notification_Response.partner_services_notification_records> notification_Response = new ArrayList<>(Arrays.asList(account_details_response.getPartner_services_notification_response()));
                       if(!notification_Response.get(0).getUser_ID().equals("No Results Found")){
                        for(int i=0 ; i<notification_Response.size();i++) {
                            NotificationData c = new NotificationData(notification_Response.get(i).getUser_Name(), notification_Response.get(i).getUser_Full_Address(), notification_Response.get(i).getUser_Contact_Number(), notification_Response.get(i).getBooking_Id(), notification_Response.get(i).getBooking_date(), notification_Response.get(i).getBooking_Start_Time());
                            notificationDataList.add(c);
                            noNotification.setVisibility(View.GONE);
                        }
                        r1.setAdapter(adapter);
                    }else
                       {    r1.setVisibility(View.GONE);
                           noNotification.setVisibility(View.VISIBLE);
                       }
                    }


                    progress.dismiss();
                }



                @Override
                public void onFailure(Call<Notification_Response> call, Throwable t) {
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
