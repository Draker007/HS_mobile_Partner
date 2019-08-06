package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

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
import service.com.surebot.info.serviceperson.Adapter.AddServicesList_Adapter;
import service.com.surebot.info.serviceperson.Adapter.AddServicesLocationlist_Adapter;
import service.com.surebot.info.serviceperson.Adapter.AddSubServicesList_Adapter;
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.ListOfServices_Request;
import service.com.surebot.info.serviceperson.RequestClass.ListOfSubServices_Request;
import service.com.surebot.info.serviceperson.ResponseClass.ListOfServices_Response;
import service.com.surebot.info.serviceperson.ResponseClass.ListOfSubServices_Response;

public class ServicesAdd_Activity extends AppCompatActivity implements  AddServicesList_Adapter.serviceslist_Communicator {

    @BindView(R.id.locationlist_recyclerview)
    RecyclerView gLocationlist_recyclerview;

    @BindView(R.id.serviceslist_recyclerview)
    RecyclerView gServiceslist_recyclerview;


    @BindView(R.id.subserviceslist_recyclerview)
    RecyclerView gSubserviceslist_recyclerview;


    @BindView(R.id.nosubservice_text)
    TextView gNosubservice_text;

    @BindView(R.id.submitforapproval_button)
    Button gSubmitforapproval_button;

    LinearLayoutManager llm;

    LinearLayoutManager gServicelayoutmanager;
    LinearLayoutManager gSub_Servicelayoutmanager;

    ArrayList<String> gAreaName_List;
    private Dialog progress;

    ArrayList<ListOfServices_Response.ListOfServices_Records> gServicesList_Arraylist;

    ArrayList<ListOfSubServices_Response.ListOfSubServices_Records> gSubServicesList_Arraylist;

    String gServiceId_FromService;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_add);
        ButterKnife.bind(this);


        progress = new Dialog(ServicesAdd_Activity.this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);

        llm = new LinearLayoutManager(ServicesAdd_Activity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        gLocationlist_recyclerview.setLayoutManager(llm);

        gAreaName_List = new ArrayList<String>();
        gAreaName_List.add("Indiranagar");
        gAreaName_List.add("Vijayanagara");
        gAreaName_List.add("Banashankari");
        gAreaName_List.add("Rajaji Nagara");



        AddServicesLocationlist_Adapter lAddServicesLocationlist_Adapter = new AddServicesLocationlist_Adapter(ServicesAdd_Activity.this,gAreaName_List);
        gLocationlist_recyclerview.setAdapter(lAddServicesLocationlist_Adapter);


        gServicelayoutmanager = new LinearLayoutManager(ServicesAdd_Activity.this);
        gServicelayoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        gServiceslist_recyclerview.setLayoutManager(gServicelayoutmanager);



        gSub_Servicelayoutmanager = new LinearLayoutManager(ServicesAdd_Activity.this);
        gSub_Servicelayoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        gSubserviceslist_recyclerview.setLayoutManager(gSub_Servicelayoutmanager);


        service_List();


        gSubmitforapproval_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ServicesAdd_Activity.this,ServicePersonHome_Activity.class));
            }
        });
    }  //On Create close


    //List Of Services
    public  void service_List(){
        try{

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
            ListOfServices_Request lservice_request = new ListOfServices_Request();


            lservice_request.setUser_ID("1");
            lservice_request.setCategory_ID("1");
            lservice_request.setDocket(Constants.TOKEN);


            Call<ListOfServices_Response> call = request.Services_list(lservice_request);
            call.enqueue(new Callback<ListOfServices_Response>() {
                @Override
                public void onResponse(Call<ListOfServices_Response> call, Response<ListOfServices_Response> response) {
                    if(response.isSuccessful()){

                        ListOfServices_Response lservice_response = response.body();

                        gServicesList_Arraylist= new ArrayList<>(Arrays.asList(lservice_response.getServices_response()));

                        AddServicesList_Adapter lAddServicesList_Adapter = new AddServicesList_Adapter(ServicesAdd_Activity.this,gServicesList_Arraylist);
                        gServiceslist_recyclerview.setAdapter(lAddServicesList_Adapter);

                        gServiceId_FromService=gServicesList_Arraylist.get(0).getService_ID();

                        subService_List(gServiceId_FromService);




                        progress.dismiss();
                    }
                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<ListOfServices_Response> call, Throwable t) {
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


    //List Of Sub Services

    public  void subService_List(String serviceId){
        try{

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

            ApiInterface request = retrofit.create(ApiInterface.class);
            ListOfSubServices_Request lsubservice_request = new ListOfSubServices_Request();


            lsubservice_request.setUser_ID("1");
            lsubservice_request.setCategory_ID("1");
            lsubservice_request.setDocket(Constants.TOKEN);
            lsubservice_request.setService_ID(serviceId);




            Call<ListOfSubServices_Response> call = request.SubServices_list(lsubservice_request);
            call.enqueue(new Callback<ListOfSubServices_Response>() {
                @Override
                public void onResponse(Call<ListOfSubServices_Response> call, Response<ListOfSubServices_Response> response) {
                    if(response.isSuccessful()){

                        ListOfSubServices_Response lsubservice_response = response.body();



                        gSubServicesList_Arraylist= new ArrayList<>(Arrays.asList(lsubservice_response.getSub_services_response()));
                        if(!gSubServicesList_Arraylist.get(0).getService_Mapping_ID().equals("No Results Found")){
                            gSubserviceslist_recyclerview.setVisibility(View.VISIBLE);
                            gNosubservice_text.setVisibility(View.GONE);

                            // gSubServiceList_Adapter = new SubServiceList_Adapter(UserServiceListActivity.this,gSubServicesList_Arraylist);

                            AddSubServicesList_Adapter lAddSubServicesList_Adapter = new AddSubServicesList_Adapter(ServicesAdd_Activity.this,gSubServicesList_Arraylist);
                            gSubserviceslist_recyclerview.setAdapter(lAddSubServicesList_Adapter);






                        }

                        else{
                            gSubserviceslist_recyclerview.setVisibility(View.GONE);
                            gNosubservice_text.setVisibility(View.VISIBLE);
                        }





                        progress.dismiss();
                    }
                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<ListOfSubServices_Response> call, Throwable t) {
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

    @Override
    public void servicesslist(String serviceid) {

        subService_List(serviceid);

    }
}
