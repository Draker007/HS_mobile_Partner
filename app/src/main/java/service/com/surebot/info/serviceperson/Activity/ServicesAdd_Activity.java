package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
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
import service.com.surebot.info.serviceperson.Adapter.CityAdapter;
import service.com.surebot.info.serviceperson.Adapter.RadiusAdapter;
import service.com.surebot.info.serviceperson.Adapter.SpinnerWithCheckBoxAdapter;
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.GetCityList_Request;
import service.com.surebot.info.serviceperson.RequestClass.GetListofCountry_Request;
import service.com.surebot.info.serviceperson.RequestClass.GetStateList_Request;
import service.com.surebot.info.serviceperson.RequestClass.ListOfServices_Request;
import service.com.surebot.info.serviceperson.RequestClass.ListOfSubServices_Request;
import service.com.surebot.info.serviceperson.RequestClass.Partner_package_Request;
import service.com.surebot.info.serviceperson.RequestClass.Select_service_partner_Request;
import service.com.surebot.info.serviceperson.RequestClass.SubmitForApproval_Request;
import service.com.surebot.info.serviceperson.ResponseClass.GetCityList_Response;
import service.com.surebot.info.serviceperson.ResponseClass.GetListofCountry_Response;
import service.com.surebot.info.serviceperson.ResponseClass.GetStateList_Response;
import service.com.surebot.info.serviceperson.ResponseClass.ListOfServices_Response;
import service.com.surebot.info.serviceperson.ResponseClass.ListOfSubServices_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Partner_package_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Select_service_partner_Response;
import service.com.surebot.info.serviceperson.ResponseClass.SubmitForApproval_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;
import service.com.surebot.info.serviceperson.utils.spinnerData;

public class ServicesAdd_Activity extends AppCompatActivity implements  AddServicesList_Adapter.serviceslist_Communicator {


    @BindView(R.id.serviceslist_recyclerview)
    RecyclerView gServiceslist_recyclerview;

    @BindView(R.id.radiusRecycler)
    RecyclerView gRadiusRecycler;

    @BindView(R.id.cityRecycler)
    RecyclerView gCityRecycler;

    @BindView(R.id.radiusBtn)
    Button gRadiusBtn;

    @BindView(R.id.cityBtn)
    Button gCityBtn;

    @BindView(R.id.statebtn)
    Button gStatebtn;

    @BindView(R.id.StateLayout)
    ConstraintLayout gStateLayout;

    @BindView(R.id.Citylayout)
    ConstraintLayout gCitylayout;

    @BindView(R.id.RadiusLayout)
    ConstraintLayout gRadiusLayout;

    @BindView(R.id.addmoreCity)
    TextView gAddmoreCity;

    @BindView(R.id.addmoreRadiustxt)
    TextView gAddmoreRadiustxt;

    @BindView(R.id.subserviceslist_recyclerview)
    RecyclerView gSubserviceslist_recyclerview;

    @BindView(R.id.NoSubServiceText)
    TextView gNoSubServiceText;

    @BindView(R.id.addServiceCategory)
    TextView gPrimaryService;


    @BindView(R.id.submitforapproval_button)
    Button gSubmitforapproval_button;

    @BindView(R.id.waitingforapproval_button)
    Button gWaitingforapproval_button;

    @BindView(R.id.spinner1)
    Spinner gCountry_Spinner ;

   /* @BindView(R.id.nocitiesfound)
    TextView gCities_NotFound ;*/



    Spinner Spinnerstates ;
    Spinner Spinnercities;




    @BindView(R.id.StateSpinnerCheck)
    Spinner states ;
    ArrayList<String> gRadius = new ArrayList<>();
    ArrayList<String> gKms = new ArrayList<>();
    ArrayList<String> finalPriceList = new ArrayList<>();
    ArrayList<String> finalmapingIdList = new ArrayList<>();
    LinearLayoutManager llm;

    LinearLayoutManager gServicelayoutmanager;
    LinearLayoutManager gSub_Servicelayoutmanager;

    ArrayList<String> gAreaName_List , subService;
    private Dialog progress;
    ArrayList<String > location_City= new ArrayList<>();
    ArrayList<ListOfServices_Response.ListOfServices_Records> gServicesList_Arraylist;

    ArrayList<ListOfSubServices_Response.ListOfSubServices_Records> gSubServicesList_Arraylist;
//For Country
    ArrayList<GetListofCountry_Response.GetListofCountry_List> gGetCountry_ArrayList;
    ArrayList<String>  gCountryArrayList;
    ArrayList<String> gCountryID_ArrayList = new ArrayList<>();
    //For States
    ArrayList<GetStateList_Response.GetStateList_Details> gGetStates_ArrayList;
    ArrayList<String>  gStateArrayList;
    ArrayList<String> gStateID_ArrayList = new ArrayList<>();

    //For Cities
    ArrayList<GetCityList_Response.GetCityList_Details> gGetCities_ArrayList;
    ArrayList<String>  gCityArrayList;
    ArrayList<String> gCityID_ArrayList = new ArrayList<>();

    SpinnerWithCheckBoxAdapter myAdapter;


    String kms , Citywisestate;
    String gServiceId_FromService ;
    String SelectedLocationID ="";
    String SelectedSubServiceID="";
    String SelectedSubServiceAmmout="";
    String gUserId_FromLogin,gCategoryId_FromLogin,gPremiumPartner_Id;
    AutoCompleteTextView lZipCode ;
    ArrayList<String > noofCoties= new ArrayList<>();
    ArrayList<String > Statelist= new ArrayList<>();
    Spinner lRadiusKms;

    ArrayList<String> gCountryName_Arraylist;

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

        //get the spinner from the xml.

//create a list of items for the spinner.
        String[] items = new String[]{"Select Your Country", "India", "Japan", "Canada","Brazil","Russia"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.



        String data[]={"560064", "560063", "560062", "560061", "560060", "560065", "650064", "520064", "510064", "500064"};

        ArrayAdapter<String> adapter12 = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, data);




        gUserId_FromLogin= AppicationClass.getUserId_FromLogin();
        gCategoryId_FromLogin=AppicationClass.getCategoryId_FromLogin();
        gPremiumPartner_Id = AppicationClass.getPremium_PartenerId();


        System.out.println("User id and Category id in Add Service " + gUserId_FromLogin + gCategoryId_FromLogin );
        gAreaName_List = new ArrayList<String>();
        //Calling API for Location City
        location_list();

        //Calling Api for Country List
        getCountry_List();

       // getStates_List();



        //for Location Radius
        LinearLayoutManager lm = new LinearLayoutManager(ServicesAdd_Activity.this);
        gRadiusRecycler.setLayoutManager(lm);


        lZipCode = findViewById(R.id.autoZipRadius);
        lRadiusKms=findViewById(R.id.radiusSpinner);
        lZipCode.setThreshold(1);//will start working from first character
        lZipCode.setAdapter(adapter12);
        String[] items2 = new String[]{"Kms", "5", "10", "15","20"};

        ArrayAdapter adapter2 = new ArrayAdapter<>(ServicesAdd_Activity.this, android.R.layout.simple_spinner_dropdown_item, items2);
        lRadiusKms.setAdapter(adapter2);
        lRadiusKms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kms = items2[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //for Location City
        LinearLayoutManager lm1 = new LinearLayoutManager(ServicesAdd_Activity.this);
        gCityRecycler.setLayoutManager(lm1);


        String[] cityitems = new String[]{"Select cities", "Benglore", "Mysore", "Chitradurga","Davanagere","Chikkamangalore"};

        Spinnercities = findViewById(R.id.spinnerCities);
        Spinnerstates = findViewById(R.id.spinnerStates);

       /* ArrayList<spinnerData> listVOs = new ArrayList<>();
        for (int i = 0; i < cityitems.length; i++) {
            spinnerData stateVO = new spinnerData();
            stateVO.setTitle(cityitems[i]);
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }*/
        String[] items1 = new String[]{"Select State", "Karnatka", "Uttrakhand", "Tamil Nadu","Book Of Zeref"};
      /*  SpinnerWithCheckBoxAdapter myAdapter = new SpinnerWithCheckBoxAdapter(ServicesAdd_Activity.this, 0,
                listVOs);
        Spinnercities.setAdapter(myAdapter);*/


      /*  ArrayAdapter cityadapter = new ArrayAdapter<>(ServicesAdd_Activity.this, android.R.layout.simple_spinner_dropdown_item, items1);
        Spinnerstates.setAdapter(cityadapter);*/
       /* Spinnerstates.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Citywisestate = items1[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/



        //for Location

        ArrayList<spinnerData> listVOs1 = new ArrayList<>();
        for (int i = 0; i < items1.length; i++) {
            spinnerData stateVO = new spinnerData();
            stateVO.setTitle(items1[i]);
            stateVO.setSelected(false);
            listVOs1.add(stateVO);
        }

        SpinnerWithCheckBoxAdapter myAdapter1 = new SpinnerWithCheckBoxAdapter(ServicesAdd_Activity.this, 0,
                listVOs1);
        states.setAdapter(myAdapter1);


        //For Service List
        gServicelayoutmanager = new LinearLayoutManager(ServicesAdd_Activity.this);
        gServicelayoutmanager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gServiceslist_recyclerview.setLayoutManager(gServicelayoutmanager);

                        //For Sub Service List

        gSub_Servicelayoutmanager = new LinearLayoutManager(ServicesAdd_Activity.this);
        gSub_Servicelayoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        gSubserviceslist_recyclerview.setLayoutManager(gSub_Servicelayoutmanager);


        service_List();

        //Adding Listners for location buttons

        gAddmoreRadiustxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!lZipCode.getText().toString().trim().equals("")){
                    if(!kms.equals("")){
                        gKms.add(kms);
                        gRadius.add(lZipCode.getText().toString().trim());
                        RadiusAdapter radiusAdapter = new RadiusAdapter(ServicesAdd_Activity.this,gRadius,gKms);
                        gRadiusRecycler.setAdapter(radiusAdapter);
                        kms="";
                        lZipCode.getText().clear();
                    }
                }

            }
        });



        gAddmoreCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int i= myAdapter.getCheckedItems().size();
                Log.e("hmhm", "onClick: "+myAdapter.getCheckedItems() +Citywisestate);
                if(i!=0 && !Citywisestate.equals("")){
                noofCoties.add(String.valueOf(i));
                Statelist.add(Citywisestate.trim());
                CityAdapter cityAdapter = new CityAdapter(ServicesAdd_Activity.this,Statelist,noofCoties);
                gCityRecycler.setAdapter(cityAdapter);
                Citywisestate="";
                    Spinnercities.setAdapter(myAdapter);
                    Spinnerstates.setSelection(0);
            }
            }
        });




        gRadiusBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                gStatebtn.setBackgroundResource(R.drawable.round_btn_gray);
                gStatebtn.setTextColor(Color.BLACK);
                gCityBtn.setBackgroundResource(R.drawable.round_btn_gray);
                gCityBtn.setTextColor(Color.BLACK);
                gRadiusBtn.setBackgroundResource(R.drawable.buttonblue_background);
                gRadiusBtn.setTextColor(Color.WHITE);
                gStateLayout.setVisibility(View.GONE);
                gCitylayout.setVisibility(View.GONE);
                gRadiusLayout.setVisibility(View.VISIBLE);

            }
        });

        gCityBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                gStatebtn.setBackgroundResource(R.drawable.round_btn_gray);
                gStatebtn.setTextColor(Color.BLACK);
                gRadiusBtn.setBackgroundResource(R.drawable.round_btn_gray);
                gRadiusBtn.setTextColor(Color.BLACK);
                gCityBtn.setBackgroundResource(R.drawable.buttonblue_background);
                gCityBtn.setTextColor(Color.WHITE);
                gStateLayout.setVisibility(View.GONE);
                gCitylayout.setVisibility(View.VISIBLE);
                gRadiusLayout.setVisibility(View.GONE);



                getStates_List();

            }
        });

        gStatebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                gRadiusBtn.setBackgroundResource(R.drawable.round_btn_gray);
                gRadiusBtn.setTextColor(Color.BLACK);
                gCityBtn.setBackgroundResource(R.drawable.round_btn_gray);
                gCityBtn.setTextColor(Color.BLACK);
                gStatebtn.setBackgroundResource(R.drawable.buttonblue_background);
                gStatebtn.setTextColor(Color.WHITE);
                gStateLayout.setVisibility(View.VISIBLE);
                gCitylayout.setVisibility(View.GONE);
                gRadiusLayout.setVisibility(View.GONE);

            }
        });



        gSubmitforapproval_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    if(AppicationClass.addLocation.isEmpty()) {
                        Toast.makeText(ServicesAdd_Activity.this, "Please Select Location before procedding", Toast.LENGTH_SHORT).show();
                    }else {
                        for (int i = 0; i < AppicationClass.addLocation.size(); i++) {
                            if (i == AppicationClass.addLocation.size() - 1) {
                                SelectedLocationID = SelectedLocationID + AppicationClass.addLocation.get(i);
                            } else {
                                SelectedLocationID = SelectedLocationID + AppicationClass.addLocation.get(i) + ",";
                            }
                        }

                        if (AppicationClass.getPremium_PartenerId().equals("0")) {
                            if(AppicationClass.addserviceammount.isEmpty()){
                                Toast.makeText(ServicesAdd_Activity.this, "Fill Service before proceeding", Toast.LENGTH_SHORT).show();
                            }
                            Log.e("lol1", "onClick: " + AppicationClass.addserviceammount);
                            Log.e("lol1", "onClick: " + AppicationClass.addLocation);
                            for (int i = 0; i < AppicationClass.addserviceammount.size(); i++) {
                                if (i == AppicationClass.addserviceammount.size() - 1) {
                                    SelectedSubServiceID = SelectedSubServiceID + AppicationClass.addserviceammount.get(i);
                                } else {
                                    SelectedSubServiceID = SelectedSubServiceID + AppicationClass.addserviceammount.get(i) + ",";
                                }
                            }

                            Log.e("lol1", "onClick: " + SelectedSubServiceID);
                            Log.e("lol1", "onClick: " + SelectedLocationID);
                            submitFor_Approval();
                        }   else {
                            if( AppicationClass.addservicemapingid.isEmpty()){
                                Toast.makeText(ServicesAdd_Activity.this, "Fill Service before proceeding", Toast.LENGTH_SHORT).show();
                            }else{
                            Toast.makeText(ServicesAdd_Activity.this, "", Toast.LENGTH_SHORT).show();
                    Log.e("lol1", "onClick: " + AppicationClass.addservicemapingid);
                    ArrayList<String> id = new ArrayList<>();
                    ArrayList<String> price = new ArrayList<>();

                    for (int p = 0; p < AppicationClass.addservicemapingid.size(); p++) {
                        String[] list = AppicationClass.addservicemapingid.get(p).split(",");
                        Log.e("hihi", "onClick: "+price +id);
                        id.add(list[0]);
                        price.add(list[1]);
                    }
                    Log.e("lola", "onClick: " + id + "  " + price);


                    int items = 0;
                    for (int j = 0; j < price.size(); j++) {
                        int i = 1;
                        for (int check = 0; check < finalmapingIdList.size(); check++) {
                            if (!id.get(j).equals(finalmapingIdList.get(check))) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                        }
                        if (i == 1) {
                            for (int o = id.size() - 1; o >= 0; o--) {
                                if (id.get(j).equals(id.get(o))) {

                                    Log.e("asdasd", "onClick: " + id.get(o));
                                    finalmapingIdList.add(items, id.get(o));
                                    finalPriceList.add(items, price.get(o));
                                    o = 0;

                                }

                            }
                            items++;
                        }

                    }
                    Log.e("lol1", "onClick: " + finalmapingIdList + finalPriceList);
                    for (int i = 0;i<finalmapingIdList.size();i++){
                        if (i == finalmapingIdList.size() - 1){
                            SelectedSubServiceID=finalmapingIdList.get(i);
                        }else{
                            SelectedSubServiceID=finalmapingIdList.get(i)+",";
                        }}
                    for (int i = 0;i<finalPriceList.size();i++){
                        if (i == finalPriceList.size() - 1){
                            SelectedSubServiceAmmout=finalPriceList.get(i);
                        }else{
                            SelectedSubServiceAmmout=finalPriceList.get(i)+",";
                        }}
                    AppicationClass.addservicemapingid.clear();
                            if( finalmapingIdList.isEmpty()){
                                Toast.makeText(ServicesAdd_Activity.this, "Fill Service before proceeding", Toast.LENGTH_SHORT).show();
                            }
                    // communicator.addquotationlist(finalmapingIdList,finalPriceList,"1");
                    System.out.println("In Submit  Button in Add Service List " + SelectedSubServiceID + SelectedSubServiceAmmout);
                    submitFor_Approval();
                }
                        }

               // startActivity(new Intent(ServicesAdd_Activity.this,ServicePersonHome_Activity.class));
            }}
        });


              /*gCountry_Spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                int item = gCountry_Spinner.getSelectedItemPosition();
                                if (!gCountry_Spinner.getSelectedItem().toString().equals("Select Category")) {
                                    String gCountry_Id= gGetCountry_ArrayList.get(item).getCountry_ID();

                                    Toast.makeText(ServicesAdd_Activity.this,gCountry_Id,Toast.LENGTH_SHORT).show();
                                }
                            }
                        });*/

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


          /*  lservice_request.setUser_ID(gUserId_FromLogin);
            lservice_request.setCategory_ID(gCategoryId_FromLogin);*/

            lservice_request.setUser_ID(gUserId_FromLogin);
            lservice_request.setCategory_ID(gCategoryId_FromLogin);
            lservice_request.setDocket(Constants.TOKEN);


            Call<ListOfServices_Response> call = request.Services_list(lservice_request);
            call.enqueue(new Callback<ListOfServices_Response>() {
                @Override
                public void onResponse(Call<ListOfServices_Response> call, Response<ListOfServices_Response> response) {
                    if(response.isSuccessful()){

                        ListOfServices_Response lservice_response = response.body();

                        gServicesList_Arraylist= new ArrayList<>(Arrays.asList(lservice_response.getServices_response()));
                if(!gServicesList_Arraylist.get(0).getService_Mapping_ID().equals("No Results Found")){

                    AddServicesList_Adapter lAddServicesList_Adapter = new AddServicesList_Adapter(ServicesAdd_Activity.this,gServicesList_Arraylist);
                    gServiceslist_recyclerview.setAdapter(lAddServicesList_Adapter);

                    gServiceId_FromService=gServicesList_Arraylist.get(0).getService_ID();

                    subService_List(gServiceId_FromService);
                }

                else
                {


                }





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


            lsubservice_request.setUser_ID(gUserId_FromLogin);
            lsubservice_request.setCategory_ID(gCategoryId_FromLogin);
            lsubservice_request.setDocket(Constants.TOKEN);
            lsubservice_request.setService_ID(serviceId);
            Log.e("hihi", "subService_List: "+gCategoryId_FromLogin );




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


            locationRequest.setUser_ID(gUserId_FromLogin);
            locationRequest.setCategory_ID(gCategoryId_FromLogin);
            locationRequest.setDocket(Constants.TOKEN);


            Call<Select_service_partner_Response> call = request.SelectSeviceAndLocation(locationRequest);
            call.enqueue(new Callback<Select_service_partner_Response>() {
                @Override
                public void onResponse(Call<Select_service_partner_Response> call, Response<Select_service_partner_Response> response) {
                    if(response.isSuccessful()){

                        Select_service_partner_Response lservice_response = response.body();
                        ArrayList<Select_service_partner_Response.Service_Records> service_response = new ArrayList<>(Arrays.asList(lservice_response.getServices_response()));
                            if(service_response.get(0).getService_Mapping_ID().equals("1")){

                                ArrayList<Select_service_partner_Response.Location_Records> location_response = new ArrayList<>(Arrays.asList(lservice_response.getLocation_reponse()));
                                for (int i = 0; i < location_response.size(); i++) {
                                    location_City.add(location_response.get(i).getCity_Name());
                                }
                                Set<String> set = new HashSet<>(location_City);
                                location_City.clear();
                                location_City.addAll(set);
                                ArrayList<Select_service_partner_Response.Category_Records> Category = new ArrayList<>(Arrays.asList(lservice_response.getCategory_response()));
                                gPrimaryService.setText(Category.get(0).getCategory_Name());


                            }else
                                Toast.makeText(ServicesAdd_Activity.this,service_response.get(0).getService_Mapping_ID() , Toast.LENGTH_SHORT).show();

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

//Submit For Approval

    public  void submitFor_Approval(){
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
            SubmitForApproval_Request lservice_request = new SubmitForApproval_Request();


            lservice_request.setUser_ID(gUserId_FromLogin);
            lservice_request.setService_Mapping_ID(SelectedSubServiceID);
            lservice_request.setLocation_ID(SelectedLocationID);
            lservice_request.setDocket(Constants.TOKEN);
            Log.e("hihi", "submitFor_Approval: "+SelectedSubServiceID );
            if (AppicationClass.getPremium_PartenerId().equals("1")){
                lservice_request.setService_Amount(SelectedSubServiceAmmout);
                lservice_request.setService_Mapping_ID(SelectedSubServiceID);
            }

            Call<SubmitForApproval_Response> call = request.submitFor_Approval(lservice_request);
            call.enqueue(new Callback<SubmitForApproval_Response>() {
                @Override
                public void onResponse(Call<SubmitForApproval_Response> call, Response<SubmitForApproval_Response> response) {
                    if(response.isSuccessful()){

                        SubmitForApproval_Response lservice_response = response.body();

                      if(lservice_response.getSubmit_services_for_approval_response().equals("valid")){
                          Toast.makeText(ServicesAdd_Activity.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                          subService_List(gServiceId_FromService);

                          gWaitingforapproval_button.setVisibility(View.VISIBLE);
                          gSubmitforapproval_button.setVisibility(View.GONE);
                          AppicationClass.addLocation.clear();
                          AppicationClass.addserviceammount.clear();
                          AppicationClass.addservicemapingid.clear();
                          startActivity(new Intent(ServicesAdd_Activity.this,ServicePersonHome_Activity.class));
                          finish();
                      }
              else{
                          subService_List(gServiceId_FromService);
                          AppicationClass.addLocation.clear();
                          AppicationClass.addserviceammount.clear();
                          AppicationClass.addservicemapingid.clear();
                          SelectedSubServiceID = "";
                          SelectedSubServiceAmmout = "";
                          SelectedLocationID = "";
                      }




                    }

                }

                @Override
                public void onFailure(Call<SubmitForApproval_Response> call, Throwable t) {
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

//Getting Country List
    private void getCountry_List() {

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
            GetListofCountry_Request lGetListofCountry_Request= new GetListofCountry_Request();

            lGetListofCountry_Request.setDocket(Constants.TOKEN);
            lGetListofCountry_Request.setUser_ID("1");

            Call<GetListofCountry_Response> call = request.Get_CountryList(lGetListofCountry_Request);
            call.enqueue(new Callback<GetListofCountry_Response>() {
                @Override
                public void onResponse(Call<GetListofCountry_Response> call, Response<GetListofCountry_Response> response) {
                    if (response.isSuccessful()) {


                        GetListofCountry_Response lGetListofCountry_Response = response.body();
                        gCountry_Spinner.setVisibility(View.VISIBLE);
                        gGetCountry_ArrayList = new ArrayList<>(Arrays.asList(lGetListofCountry_Response.getGet_country_list_response()));
                        System.out.println("Arrayfrom Db Size is  " + gGetCountry_ArrayList.size());
                        gCountryArrayList=new ArrayList<>();
                        gCountryArrayList.add(0,getResources().getString(R.string.selectcountry));
                        gCountryID_ArrayList.add(0,getResources().getString(R.string.selectcountryid));
                        for(int i=0;i<gGetCountry_ArrayList.size();i++){


                            gCountryArrayList.add(1,gGetCountry_ArrayList.get(i).getCountry_Name());
                            gCountryID_ArrayList.add(1,gGetCountry_ArrayList.get(i).getCountry_ID());

                        }
                        System.out.println("Country arraylist size is " + gGetCountry_ArrayList.get(0).getCountry_Name());
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ServicesAdd_Activity.this, android.R.layout.simple_spinner_dropdown_item, gCountryArrayList);
                        //set the spinners adapter to the previously created one.
                        gCountry_Spinner.setAdapter(adapter);


                        gCountry_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                int item = gCountry_Spinner.getSelectedItemPosition();
                                gCountry_Spinner.setSelection(item, false);
                                if (!gCountry_Spinner.getSelectedItem().toString().equals(getResources().getString(R.string.selectcountry))) {
                                  String  gSelectedCategoryId = gCountryID_ArrayList.get(item);

                                    Toast.makeText(ServicesAdd_Activity.this,gSelectedCategoryId,Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });



             //With Hint
               /*         for(int i=0;i<gGetCountry_ArrayList.size();i++) {

                            //     System.out.println("From DB Country arraylist size is " + gGetCountry_ArrayList.size());


                            gCountryArrayList.add(0, gGetCountry_ArrayList.get(0).getSelectcountryhint());
                          //  gCountryID_ArrayList.add(0, gGetCountry_ArrayList.get(0).getCountry_ID());


                            for (int j = 0; j <= i; j++) {

                                gCountryArrayList.add(1, gGetCountry_ArrayList.get(j).getCountry_Name());
                                //gCountryID_ArrayList.add(1, gGetCountry_ArrayList.get(j).getCountry_ID());


                            }
                        }
                            System.out.println("Country arraylist size is " + gCountryArrayList.size());
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ServicesAdd_Activity.this, android.R.layout.simple_spinner_dropdown_item, gCountryArrayList);
                            //set the spinners adapter to the previously created one.
                            gCountry_Spinner.setAdapter(adapter);
*/











                        progress.dismiss();

                    }

                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<GetListofCountry_Response> call, Throwable t) {
                    System.out.println("In User Login Method 7");
                    progress.dismiss();
                }
            });
        }catch (Exception e) {
            progress.dismiss();
            e.printStackTrace();


        }

    }

    //Getting State List

    private void getStates_List() {

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
            GetStateList_Request lGetStateList_Request= new GetStateList_Request();

            lGetStateList_Request.setDocket(Constants.TOKEN);
            lGetStateList_Request.setUser_ID("1");
            lGetStateList_Request.setCountry_ID("1");

            Call<GetStateList_Response> call = request.Get_StatesList(lGetStateList_Request);
            call.enqueue(new Callback<GetStateList_Response>() {
                @Override
                public void onResponse(Call<GetStateList_Response> call, Response<GetStateList_Response> response) {
                    if (response.isSuccessful()) {


                        GetStateList_Response lGetStateList_Response = response.body();

                        gGetStates_ArrayList = new ArrayList<>(Arrays.asList(lGetStateList_Response.getGet_states_list_response()));


                        gStateArrayList=new ArrayList<>();
                        gStateArrayList.add(0,getResources().getString(R.string.selectstate));
                        gStateID_ArrayList.add(0,getResources().getString(R.string.selectstateid));
                        for(int i=0;i<gGetStates_ArrayList.size();i++){


                            gStateArrayList.add(1,gGetStates_ArrayList.get(i).getState_Name());
                            gStateID_ArrayList.add(1,gGetStates_ArrayList.get(i).getState_ID());

                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ServicesAdd_Activity.this, android.R.layout.simple_spinner_dropdown_item, gStateArrayList);
                        //set the spinners adapter to the previously created one.
                        Spinnerstates.setAdapter(adapter);


                        Spinnerstates.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                int item = Spinnerstates.getSelectedItemPosition();
                                Spinnerstates.setSelection(item, false);
                                if (!Spinnerstates.getSelectedItem().toString().equals(getResources().getString(R.string.selectstate))) {
                                    String  gSelectedStatesId = gStateID_ArrayList.get(item);

                                    Toast.makeText(ServicesAdd_Activity.this,gSelectedStatesId,Toast.LENGTH_SHORT).show();

                                    getCities_List(gSelectedStatesId);
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });



                        progress.dismiss();

                    }

                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<GetStateList_Response> call, Throwable t) {
                    System.out.println("In User Login Method 7");
                    progress.dismiss();
                }
            });
        }catch (Exception e) {
            progress.dismiss();
            e.printStackTrace();


        }

    }

    //Get City List
    private void getCities_List(String StateId) {

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
            GetCityList_Request lGetStateList_Request= new GetCityList_Request();

            lGetStateList_Request.setDocket(Constants.TOKEN);
            lGetStateList_Request.setUser_ID("1");
            lGetStateList_Request.setState_ID(StateId);

            Call<GetCityList_Response> call = request.Get_CityList(lGetStateList_Request);
            call.enqueue(new Callback<GetCityList_Response>() {
                @Override
                public void onResponse(Call<GetCityList_Response> call, Response<GetCityList_Response> response) {
                    if (response.isSuccessful()) {


                        GetCityList_Response lGetCityList_Response = response.body();

                        gGetCities_ArrayList = new ArrayList<>(Arrays.asList(lGetCityList_Response.getGet_cities_list_response()));
if(!gGetCities_ArrayList.get(0).getCity_ID().equals("No Results Found")){

    Spinnercities.setVisibility(View.VISIBLE);
  //  gCities_NotFound.setVisibility(View.GONE);
    gCityArrayList=new ArrayList<>();
    gCityArrayList.add(0,getResources().getString(R.string.selectcity));
    gCityID_ArrayList.add(0,getResources().getString(R.string.selectcityid));
    for(int i=0;i<gGetCities_ArrayList.size();i++){


        gCityArrayList.add(1,gGetCities_ArrayList.get(i).getCity_Name());
        gCityID_ArrayList.add(1,gGetCities_ArrayList.get(i).getState_ID());

    }



    ArrayList<spinnerData> listVOs = new ArrayList<>();
    for (int i = 0; i < gCityArrayList.size(); i++) {
        spinnerData stateVO = new spinnerData();
        stateVO.setTitle(gCityArrayList.get(i).toString());

        System.out.println("City Name is " + gCityArrayList.get(i).toString());
        stateVO.setSelected(false);
        listVOs.add(stateVO);
    }


    myAdapter = new SpinnerWithCheckBoxAdapter(ServicesAdd_Activity.this, 0,
            listVOs);
    Spinnercities.setAdapter(myAdapter);



    Spinnercities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            int item = Spinnercities.getSelectedItemPosition();
            Spinnercities.setSelection(item, false);
            if (!Spinnercities.getSelectedItem().toString().equals(getResources().getString(R.string.selectcity))) {
                String  gSelectedCitiesId = gCityID_ArrayList.get(item);

                Toast.makeText(ServicesAdd_Activity.this,gSelectedCitiesId,Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });




}

else {
  //  Spinnercities.setVisibility(View.GONE);
   // gCities_NotFound.setVisibility(View.VISIBLE);
    Toast.makeText(ServicesAdd_Activity.this,"No Cities Found",Toast.LENGTH_SHORT).show();


}




                        progress.dismiss();

                    }

                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<GetCityList_Response> call, Throwable t) {
                    System.out.println("In User Login Method 7");
                    progress.dismiss();
                }
            });
        }catch (Exception e) {
            progress.dismiss();
            e.printStackTrace();


        }

    }

}
