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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
import service.com.surebot.info.serviceperson.RequestClass.Partner_package_Request;
import service.com.surebot.info.serviceperson.RequestClass.Select_service_partner_Request;
import service.com.surebot.info.serviceperson.ResponseClass.ListOfServices_Response;
import service.com.surebot.info.serviceperson.ResponseClass.ListOfSubServices_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Partner_package_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Select_service_partner_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class ServicesAdd_Activity extends AppCompatActivity implements  AddServicesList_Adapter.serviceslist_Communicator {

    @BindView(R.id.locationlist_recyclerview)
    RecyclerView gLocationlist_recyclerview;

    @BindView(R.id.serviceslist_recyclerview)
    RecyclerView gServiceslist_recyclerview;

    @BindView(R.id.PrimaryService)
    TextView gPrimaryService;

    @BindView(R.id.subserviceslist_recyclerview)
    RecyclerView gSubserviceslist_recyclerview;

    @BindView(R.id.NoSubServiceText)
    TextView gNoSubServiceText;


    @BindView(R.id.submitforapproval_button)
    Button gSubmitforapproval_button;

    LinearLayoutManager llm;

    LinearLayoutManager gServicelayoutmanager;
    LinearLayoutManager gSub_Servicelayoutmanager;

    ArrayList<String> gAreaName_List , subService;
    private Dialog progress;
    ArrayList<String > location_City= new ArrayList<>();
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
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        gLocationlist_recyclerview.setLayoutManager(llm);

        gAreaName_List = new ArrayList<String>();
        //Calling API for Location City
        location_list();




                        //For Service List
        gServicelayoutmanager = new LinearLayoutManager(ServicesAdd_Activity.this);
        gServicelayoutmanager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gServiceslist_recyclerview.setLayoutManager(gServicelayoutmanager);

                        //For Sub Service List

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


    //List Of Services  c
    public  void service_List(){
        try{
            progress.show();
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





                    }

                }

                @Override
                public void onFailure(Call<ListOfServices_Response> call, Throwable t) {
                    Toast.makeText(ServicesAdd_Activity.this, getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();

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


            lsubservice_request.setUser_ID(AppicationClass.getUserId_FromLogin());
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


                            // gSubServiceList_Adapter = new SubServiceList_Adapter(UserServiceListActivity.this,gSubServicesList_Arraylist);

                            AddSubServicesList_Adapter lAddSubServicesList_Adapter = new AddSubServicesList_Adapter(ServicesAdd_Activity.this,gSubServicesList_Arraylist);
                            gSubserviceslist_recyclerview.setAdapter(lAddSubServicesList_Adapter);
                                gSubmitforapproval_button.setVisibility(View.VISIBLE);

                            progress.dismiss();



                        }

                        else{
                            gSubserviceslist_recyclerview.setVisibility(View.GONE);
                            gNoSubServiceText.setVisibility(View.VISIBLE);
                            progress.dismiss();
                        }






                    }

                }

                @Override
                public void onFailure(Call<ListOfSubServices_Response> call, Throwable t) {
                    Toast.makeText(ServicesAdd_Activity.this, getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
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


    //List of Location
    public void location_list(){
        try{

            System.out.println("In User Login Method 1");

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
            Select_service_partner_Request locationRequest = new Select_service_partner_Request();


            locationRequest.setUser_ID("1");
            locationRequest.setCategory_ID("1");
            locationRequest.setDocket(Constants.TOKEN);


            Call<Select_service_partner_Response> call = request.SelectSeviceAndLocation(locationRequest);
            call.enqueue(new Callback<Select_service_partner_Response>() {
                @Override
                public void onResponse(Call<Select_service_partner_Response> call, Response<Select_service_partner_Response> response) {
                    if(response.isSuccessful()){

                        Select_service_partner_Response lservice_response = response.body();
                        ArrayList<Select_service_partner_Response.Location_Records> location_response = new ArrayList<>(Arrays.asList(lservice_response.getLocation_reponse()));
                        for (int i =0;i<location_response.size();i++){
                            location_City.add(location_response.get(i).getCity_Name());
                        }
                        Set<String> set = new HashSet<>(location_City);
                        location_City.clear();
                        location_City.addAll(set);
                        ArrayList<Select_service_partner_Response.Category_Records> Category = new ArrayList<>(Arrays.asList(lservice_response.getCategory_response()));
                        gPrimaryService.setText(Category.get(0).getCategory_Name());

                        AddServicesLocationlist_Adapter lAddServicesLocationlist_Adapter = new AddServicesLocationlist_Adapter(ServicesAdd_Activity.this,location_City,location_response);
                        gLocationlist_recyclerview.setAdapter(lAddServicesLocationlist_Adapter);



                        progress.dismiss();
                    }
                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<Select_service_partner_Response> call, Throwable t) {
                    Toast.makeText(ServicesAdd_Activity.this, getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
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
