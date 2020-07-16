package service.com.surebot.info.serviceperson.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import service.com.surebot.info.serviceperson.adapters.AddServicesList_Adapter;
import service.com.surebot.info.serviceperson.adapters.AddSubServicesList_Adapter;
import service.com.surebot.info.serviceperson.adapters.CityAdapter;
import service.com.surebot.info.serviceperson.adapters.RadiusAdapter;
import service.com.surebot.info.serviceperson.adapters.SpinnerWithCheckBoxAdapter;
import service.com.surebot.info.serviceperson.adapters.SpinnerWithCheckBoxStates_Adapter;
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.GetAllZipCode_Request;
import service.com.surebot.info.serviceperson.RequestClass.GetCityList_Request;
import service.com.surebot.info.serviceperson.RequestClass.GetListofCountry_Request;
import service.com.surebot.info.serviceperson.RequestClass.GetStateList_Request;
import service.com.surebot.info.serviceperson.RequestClass.ListOfServices_Request;
import service.com.surebot.info.serviceperson.RequestClass.ListOfSubServices_Request;
import service.com.surebot.info.serviceperson.RequestClass.PartnerApprovalStatus_Request;
import service.com.surebot.info.serviceperson.RequestClass.Select_service_partner_Request;
import service.com.surebot.info.serviceperson.RequestClass.SubmitForApproval_Request;
import service.com.surebot.info.serviceperson.ResponseClass.GetAllZipCode_Response;
import service.com.surebot.info.serviceperson.ResponseClass.GetCityList_Response;
import service.com.surebot.info.serviceperson.ResponseClass.GetListofCountry_Response;
import service.com.surebot.info.serviceperson.ResponseClass.GetStateList_Response;
import service.com.surebot.info.serviceperson.ResponseClass.GetSubServicesByZipcode_Response;
import service.com.surebot.info.serviceperson.ResponseClass.ListOfServices_Response;
import service.com.surebot.info.serviceperson.ResponseClass.ListOfSubServices_Response;
import service.com.surebot.info.serviceperson.ResponseClass.PartnerApprovalStatus_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Select_service_partner_Response;
import service.com.surebot.info.serviceperson.ResponseClass.SubmitForApproval_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;
import service.com.surebot.info.serviceperson.utils.spinnerData;

public class ServicesAdd_Activity extends AppCompatActivity implements
        AddServicesList_Adapter.serviceslist_Communicator, RadiusAdapter.radiuslistremove_Communicator {


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


    @BindView(R.id.submitforapproval_button_radius)
    Button gSubmitforapproval_button_Radius;

    @BindView(R.id.waitingfor_approval_button)
    Button gWaitingfor_Approval_Button;



   /* @BindView(R.id.submitforapproval_button_citywise)
    Button gSubmitforapproval_button_Citywise;

    @BindView(R.id.submitforapproval_button_statewise)
    Button gSubmitforapproval_button_Statewise;*/

    @BindView(R.id.waitingforapproval_button)
    Button gWaitingforapproval_button;

    @BindView(R.id.spinner1)
    Spinner gCountry_Spinner;

    @BindView(R.id.selectionheadertext)
    TextView gSelectionheadertext;



   /* @BindView(R.id.nocitiesfound)
    TextView gCities_NotFound ;*/


    Spinner Spinnerstates;
    Spinner Spinnercities;


    String gSelectedCountryId;

    @BindView(R.id.StateSpinnerCheck)
    Spinner gStates_withCheckbox;
    ArrayList<String> gRadius = new ArrayList<>();
    ArrayList<String> gZipcodeId = new ArrayList<>();
    ArrayList<String> gKms = new ArrayList<>();
    ArrayList<String> finalPriceList = new ArrayList<>();
    ArrayList<String> finalmapingIdList = new ArrayList<>();
    LinearLayoutManager llm;

    LinearLayoutManager gServicelayoutmanager;
    LinearLayoutManager gSub_Servicelayoutmanager;

    ArrayList<String> gAreaName_List, subService;
    ArrayList<String> location_City = new ArrayList<>();
    ArrayList<ListOfServices_Response.ListOfServices_Records> gServicesList_Arraylist;
    ArrayList<ListOfSubServices_Response.ListOfSubServices_Records> gSubServicesList_Arraylist;
    //Get Sub Services by Zipcode
    ArrayList<GetSubServicesByZipcode_Response.GetSubServicesByZipcode_Details> gSubServicesListArraylist_Zipcode;
    //For Country
    ArrayList<GetListofCountry_Response.GetListofCountry_List> gGetCountry_ArrayList;
    ArrayList<String> gCountryArrayList;
    ArrayList<String> gCountryID_ArrayList = new ArrayList<>();
    //For States
    ArrayList<GetStateList_Response.GetStateList_Details> gGetStates_ArrayList;
    ArrayList<String> gStateArrayList = new ArrayList<>();
    ArrayList<String> gStateID_ArrayList = new ArrayList<>();
    ArrayList<String> gOnlyStateStatus_ArrayList = new ArrayList<>();
    //For Cities
    ArrayList<GetCityList_Response.GetCityList_Details> gGetCities_ArrayList;
    ArrayList<String> gCityArrayList;
    ArrayList<String> gCityID_ArrayList = new ArrayList<>();
    ArrayList<String> gPartnerStatusForCityID_ArrayList = new ArrayList<>();
    //For ZipCodes
    ArrayList<GetAllZipCode_Response.GetAllZipCode_Details> gGetZipcode_ArrayList;
    ArrayList<String> gZipCodeArrayList;
    ArrayList<String> gZipCodeId_ArrayList = new ArrayList<>();
    //For Getting Partner status
    ArrayList<PartnerApprovalStatus_Response.PartnerApprovalStatus> gGet_PartnerApprovalStatus;
    SpinnerWithCheckBoxAdapter myAdapter;
    String kms = "", gStatenamewith_Cities;
    String gServiceId_FromService;
    String SelectedLocationID = "";
    String SelectedSubServiceID = "";
    String SelectedSubServiceAmmout = "";
    String SelectedSubServiceID_new = "";
    String SelectedSubServiceAmmout_new = "";
    String gUserId_FromLogin, gCategoryId_FromLogin, gPremiumPartner_Id;
    AutoCompleteTextView lZipCode_AutoEditText;
    ArrayList<String> noofCoties = new ArrayList<>();
    ArrayList<String> Statelist = new ArrayList<>();
    Spinner lRadiusKms;
    ArrayList<String> gCountryName_Arraylist;
    String gFinalZipcode_toApi = "";


    //For Radius
    String gFinalKms_toApi = "";
    String gCityId_toApi = "";

    //For City
    String gStateId_toApi = "";


    //For State
    Dialog gConfirmation_Dialog;
    TextView laddressproofpendingtext;
    TextView lbankdetailspendingtext;
    TextView ldocumentspendingtext;
    ImageView laddressproof_Image;
    ImageView lbankdetails_Image;
    ImageView ldocuments_Image;
    String statelist_value = "Radiusclicked";
    Boolean gRadiusClicked_values = false;
    Boolean gCityClicked_values = false;
    Boolean gStateClicked_values = false;
    Boolean gPartner_Status = false;
    String gAdminApproval_Status;
    String gUser_TypeFromCountryList;
    private Dialog progress;

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
        System.out.println("Cities arraylist size in ocreate 11111111 is " + AppicationClass.addlocationservicecities.size());
        AppicationClass.addlocationservicecities.clear();
        System.out.println("Cities arraylist size in ocreate 222 is " + AppicationClass.addlocationservicecities.size());
        gUserId_FromLogin = AppicationClass.getUserId_FromLogin();
        gCategoryId_FromLogin = AppicationClass.getCategoryId_FromLogin();
        gPremiumPartner_Id = AppicationClass.getPremium_PartenerId();

        getPartnerApproval_Status();


        // verification status popup

        gConfirmation_Dialog = new Dialog(ServicesAdd_Activity.this, R.style.dailogboxtheme);
        gConfirmation_Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        gConfirmation_Dialog.getWindow().setBackgroundDrawableResource(R.color.color_transparen);
        gConfirmation_Dialog.setContentView(R.layout.verificationstatus_popup);

        Button lOkay_Button = gConfirmation_Dialog.findViewById(R.id.okbutton);
        laddressproofpendingtext = gConfirmation_Dialog.findViewById(R.id.addressproofpendingtext);
        lbankdetailspendingtext = gConfirmation_Dialog.findViewById(R.id.bankdetailspendingtext);
        ldocumentspendingtext = gConfirmation_Dialog.findViewById(R.id.documentspendingtext);


        laddressproof_Image = gConfirmation_Dialog.findViewById(R.id.statusimage_one);
        lbankdetails_Image = gConfirmation_Dialog.findViewById(R.id.statusimage_two);
        ldocuments_Image = gConfirmation_Dialog.findViewById(R.id.statusimage_three);


        lOkay_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gPartner_Status) {

                   gConfirmation_Dialog.dismiss();

                    System.out.println("Partner status is in If " + gPartner_Status);

                } else {

                    System.out.println("Partner status is in Else" + gPartner_Status);
                    gConfirmation_Dialog.dismiss();
                    Toast.makeText(ServicesAdd_Activity.this, ServicesAdd_Activity.this.getString(R.string.profilestatusnote), Toast.LENGTH_SHORT).show();
                }
            }
        });


        gConfirmation_Dialog.setCancelable(false);
        gConfirmation_Dialog.show();


        //get the spinner from the xml.


        //SoftKeyboard Enabling
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        System.out.println("User id and Category id in Add Service " + gUserId_FromLogin + gCategoryId_FromLogin);
        gAreaName_List = new ArrayList<String>();
        //Calling API for Location City
        //    location_list();

        //Calling Api for Country List
        getCountry_List();

        getZipCodes_List();

        // getStates_List();


        //for Location Radius
        LinearLayoutManager lm = new LinearLayoutManager(ServicesAdd_Activity.this);
        gRadiusRecycler.setLayoutManager(lm);


        lZipCode_AutoEditText = findViewById(R.id.autoZipRadius);
        lRadiusKms = findViewById(R.id.radiusSpinner);
       /* lZipCode_AutoEditText.setThreshold(1);//will start working from first character
        lZipCode_AutoEditText.setAdapter(adapter12);*/
        String[] items2 = new String[]{"Kms", "5", "10", "15", "20"};

        ArrayAdapter adapter2 = new ArrayAdapter<>(ServicesAdd_Activity.this, android.R.layout.simple_spinner_dropdown_item, items2);
        lRadiusKms.setAdapter(adapter2);


        lRadiusKms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kms = items2[i];
                /*if (!lRadiusKms.getSelectedItem().toString().equals("Kms")) {
                    kms = items2[i];
                }
                else{

                }*/

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //for Location City
        LinearLayoutManager lm1 = new LinearLayoutManager(ServicesAdd_Activity.this);
        gCityRecycler.setLayoutManager(lm1);


        String[] cityitems = new String[]{"Select cities", "Benglore", "Mysore", "Chitradurga", "Davanagere", "Chikkamangalore"};

        Spinnercities = findViewById(R.id.spinnerCities);
        Spinnerstates = findViewById(R.id.spinnerStates);

       /* ArrayList<spinnerData> listVOs = new ArrayList<>();
        for (int i = 0; i < cityitems.length; i++) {
            spinnerData stateVO = new spinnerData();
            stateVO.setTitle(cityitems[i]);
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }*/
        String[] items1 = new String[]{"Select State", "Karnatka", "Uttrakhand", "Tamil Nadu", "Book Of Zeref"};
      /*  SpinnerWithCheckBoxAdapter myAdapter = new SpinnerWithCheckBoxAdapter(ServicesAdd_Activity.this, 0,
                listVOs);
        Spinnercities.setAdapter(myAdapter);*/


      /*  ArrayAdapter cityadapter = new ArrayAdapter<>(ServicesAdd_Activity.this, android.R.layout.simple_spinner_dropdown_item, items1);
        Spinnerstates.setAdapter(cityadapter);*/
       /* Spinnerstates.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gStatenamewith_Cities = items1[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/


        //for Location

    /*    ArrayList<spinnerData> listVOs1 = new ArrayList<>();
        for (int i = 0; i < items1.length; i++) {
            spinnerData stateVO = new spinnerData();
            stateVO.setTitle(items1[i]);
            stateVO.setSelected(false);
            listVOs1.add(stateVO);
        }

        SpinnerWithCheckBoxAdapter myAdapter1 = new SpinnerWithCheckBoxAdapter(ServicesAdd_Activity.this, 0,
                listVOs1);
        states.setAdapter(myAdapter1);*/


        //For Service List
        gServicelayoutmanager = new LinearLayoutManager(ServicesAdd_Activity.this);
        gServicelayoutmanager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gServiceslist_recyclerview.setLayoutManager(gServicelayoutmanager);

        //For Sub Service List

        gSub_Servicelayoutmanager = new LinearLayoutManager(ServicesAdd_Activity.this);
        gSub_Servicelayoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        gSubserviceslist_recyclerview.setLayoutManager(gSub_Servicelayoutmanager);


        System.out.println("Km in Add more text " + kms);
        //Adding Listners for location buttons
        //  if(!kms.equals("") || kms.equals("Kms")){


        //Add More location by radius

        gAddmoreRadiustxt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                  /*   gRadius.clear();
                        gKms.clear();*/
                if (!lZipCode_AutoEditText.getText().toString().trim().equals("")) {

                    if (!kms.equals("") && !kms.equals("Kms")) {
/*if(gRadius.contains(lZipCode_AutoEditText.getText().toString().trim())){


}*/
                        System.out.println("Radius arraylis size is " + gRadius.size());
                        gKms.add(kms);
                        gRadius.add(lZipCode_AutoEditText.getText().toString().trim());


                        for (int i = 0; i < gZipCodeArrayList.size(); i++) {
                            if (gRadius.contains(gZipCodeArrayList.get(i).toString().trim())) {
                                gZipcodeId.add(gZipCodeId_ArrayList.get(i).toString());
                                System.out.println("Zipcode id in Add more text " + gZipCodeId_ArrayList.get(i).toString());
                            }

                        }


                        System.out.println("Code id Zipcode id in Add more text " + gZipcodeId.size());


                        gRadiusClicked_values = true;
                        RadiusAdapter radiusAdapter = new RadiusAdapter(ServicesAdd_Activity.this, gRadius, gKms);
                        gRadiusRecycler.setAdapter(radiusAdapter);
                        kms = "";
                        lZipCode_AutoEditText.getText().clear();
                        // gZipcodeId.clear();
                      /*  gRadius.clear();
                        gKms.clear();*/
                        lRadiusKms.setAdapter(adapter2);

                        System.out.println("Added Radius and kms arrays are " + gRadius.size() + " and " + gKms.size());


                    } else {

                        Toast.makeText(ServicesAdd_Activity.this, "Enter Kms", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(ServicesAdd_Activity.this, "Enter Zip Code", Toast.LENGTH_SHORT).show();
                }
            }
        });

       /* }

        else{
            Toast.makeText(ServicesAdd_Activity.this,"Select Kms",Toast.LENGTH_SHORT).show();
        }*/


        gAddmoreCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (myAdapter.getCheckedItems().size() > 0) {
                    System.out.println("Statename with city size is " + myAdapter.getCheckedItems().size());
                    int i = myAdapter.getCheckedItems().size();
                    Log.e("hmhm", "onClick: " + myAdapter.getCheckedItems() + gStatenamewith_Cities);

                    if (i != 0 && !gStatenamewith_Cities.equals("")) {
                        noofCoties.add(String.valueOf(i));
                        Statelist.add(gStatenamewith_Cities.trim());


                        CityAdapter cityAdapter = new CityAdapter(ServicesAdd_Activity.this, Statelist, noofCoties);
                        gCityRecycler.setAdapter(cityAdapter);

                        gStatenamewith_Cities = "";
                        Spinnercities.setAdapter(myAdapter);
                        Spinnerstates.setSelection(0);

                        System.out.println("Number of cities Added arrays are " + noofCoties);
                    }


                } else {
                    Toast.makeText(ServicesAdd_Activity.this, "First Select Cities", Toast.LENGTH_SHORT).show();
                }

            }
        });


        gRadiusBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (!gCountry_Spinner.getSelectedItem().toString().equals(getResources().getString(R.string.selectcountry))) {
                   /* String  gSelectedCategoryId = gCountryID_ArrayList.get(item);

                    Toast.makeText(ServicesAdd_Activity.this,gSelectedCategoryId,Toast.LENGTH_SHORT).show();*/
                    gSubmitforapproval_button_Radius.setVisibility(View.VISIBLE);
                    //gSubmitforapproval_button_Citywise.setVisibility(View.GONE);
                    //gSubmitforapproval_button_Statewise.setVisibility(View.GONE);

                    statelist_value = "Radiusclicked";

                    gSelectionheadertext.setText("Select zipcodes and Kms");

                    gStatebtn.setBackgroundResource(R.drawable.round_btn_gray);
                    gStatebtn.setTextColor(Color.BLACK);
                    gCityBtn.setBackgroundResource(R.drawable.round_btn_gray);
                    gCityBtn.setTextColor(Color.BLACK);
                    gRadiusBtn.setBackgroundResource(R.drawable.buttonblue_background);
                    gRadiusBtn.setTextColor(Color.WHITE);
                    gStateLayout.setVisibility(View.GONE);
                    gCitylayout.setVisibility(View.GONE);
                    gRadiusLayout.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(ServicesAdd_Activity.this, getResources().getString(R.string.selectcountry), Toast.LENGTH_SHORT).show();

                }


            }
        });

        gCityBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (!gCountry_Spinner.getSelectedItem().toString().equals(getResources().getString(R.string.selectcountry))) {
                    statelist_value = "CityClicked";


                    System.out.println("Cities arraylist size on click on city" + AppicationClass.addlocationservicecities.size());

                    //   gSubmitforapproval_button_Citywise.setVisibility(View.VISIBLE);

                    // gSubmitforapproval_button_Radius.setVisibility(View.GONE);
                    //gSubmitforapproval_button_Statewise.setVisibility(View.GONE);


                    gStatebtn.setBackgroundResource(R.drawable.round_btn_gray);
                    gStatebtn.setTextColor(Color.BLACK);
                    gRadiusBtn.setBackgroundResource(R.drawable.round_btn_gray);
                    gRadiusBtn.setTextColor(Color.BLACK);
                    gCityBtn.setBackgroundResource(R.drawable.buttonblue_background);
                    gCityBtn.setTextColor(Color.WHITE);
                    gStateLayout.setVisibility(View.GONE);
                    gCitylayout.setVisibility(View.VISIBLE);
                    gRadiusLayout.setVisibility(View.GONE);

                    AppicationClass.addlocationservicecities.clear();

                    getStates_List();
                } else {
                    Toast.makeText(ServicesAdd_Activity.this, getResources().getString(R.string.selectcountry), Toast.LENGTH_SHORT).show();

                }


            }
        });

        gStatebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                if (!gCountry_Spinner.getSelectedItem().toString().equals(getResources().getString(R.string.selectcountry))) {
                    statelist_value = "Stateclicked";


                    //  gSubmitforapproval_button_Citywise.setVisibility(View.GONE);
                    // gSubmitforapproval_button_Radius.setVisibility(View.GONE);

                    //gSubmitforapproval_button_Statewise.setVisibility(View.VISIBLE);


                    gRadiusBtn.setBackgroundResource(R.drawable.round_btn_gray);
                    gRadiusBtn.setTextColor(Color.BLACK);
                    gCityBtn.setBackgroundResource(R.drawable.round_btn_gray);
                    gCityBtn.setTextColor(Color.BLACK);
                    gStatebtn.setBackgroundResource(R.drawable.buttonblue_background);
                    gStatebtn.setTextColor(Color.WHITE);
                    gStateLayout.setVisibility(View.VISIBLE);
                    gCitylayout.setVisibility(View.GONE);
                    gRadiusLayout.setVisibility(View.GONE);
                    //  AppicationClass.addlocationservicestates.clear();


                    getStates_List();

                } else {
                    Toast.makeText(ServicesAdd_Activity.this, getResources().getString(R.string.selectcountry), Toast.LENGTH_SHORT).show();

                }


            }
        });


//Radius wise
        gSubmitforapproval_button_Radius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //For Getting Sub Services id for free partner
                if (AppicationClass.getPremium_PartenerId().equals("0")) {
                    StringBuilder gSubServices_builder = new StringBuilder();

System.out.println("In submit button " + AppicationClass.addservicemapingid.size());

if(AppicationClass.addservicemapingid.contains(AppicationClass.multipleaddservicemapingid)){
    System.out.println("In submit button  if");
}

else{
    System.out.println("In submit button  else");
}
                    for (int i = 0; i < AppicationClass.addservicemapingid.size(); i++) {
                        gSubServices_builder.append(AppicationClass.addservicemapingid.get(i).toString() + ",");


                    }

                    SelectedSubServiceID_new = gSubServices_builder.toString();

                    if (SelectedSubServiceID_new.endsWith(",")) {
                        SelectedSubServiceID_new = SelectedSubServiceID_new.substring(0, SelectedSubServiceID_new.length() - 1);
                    }
                }


                System.out.println("Service id in submit button " + SelectedSubServiceID_new);


                //Sub Service ammount

                if (AppicationClass.getPremium_PartenerId().equals("1")) {


//******************** new try *********************

                    ArrayList<String> id = new ArrayList<>();
                    ArrayList<String> price = new ArrayList<>();


                    for (int p = 0; p < AppicationClass.addnewserviceammount.size(); p++) {
                        String[] list = AppicationClass.addnewserviceammount.get(p).split(",");
                        id.add(list[0]);
                        price.add(list[1]);
                        System.out.println("In new request test arraylist value is " + AppicationClass.addnewserviceammount.get(p));

                    }
                    Log.e("lola", "onClick: " + id + "  " + price);

                    for (int a = 0; a < id.size(); a++) {
                        System.out.println("saprate Id and price in first adapter is " + id.get(a).toString() + "\n" + price.get(a).toString());
                    }


                    ArrayList<String> finalPriceList = new ArrayList<>();
                    ArrayList<String> finalmapingIdList = new ArrayList<>();

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


                    System.out.println("New Amount and id size is " + finalmapingIdList.size() + " and " + finalPriceList.size());

                    for (int i = 0; i < finalPriceList.size(); i++) {
                        System.out.println("New Amount and id values are " + finalmapingIdList.get(i).toString() + " and " + finalPriceList.get(i).toString());
                    }


//******************** new try *********************



                   /* for(int i=0;i<AppicationClass.addserviceammount.size();i++){
                        gSubServiceamount_builder.append(AppicationClass.addserviceammount.get(i).toString()+",");


                    }

                    SelectedSubServiceAmmout= gSubServiceamount_builder.toString();

                    if (SelectedSubServiceAmmout.endsWith(",")) {
                        SelectedSubServiceAmmout = SelectedSubServiceAmmout.substring(0, SelectedSubServiceAmmout.length() - 1);
                    }*/
                    //Removing dupicate ids
                    for (int i = 0; i < AppicationClass.addserviceammount.size(); i++) {

                        //  if(!finalPriceList.contains(AppicationClass.addserviceammount.get(i).toString())){
                        System.out.println("In Submit method entering into not contains !!!!!");
                        finalPriceList.add(AppicationClass.addserviceammount.get(i).toString());

                        //   }

/*else{
                            System.out.println("In Submit method entering into not contains @@@@@@@@");
                        }*/

                    }

                    for (int i = 0; i < AppicationClass.addservicemapingid.size(); i++) {

                        if (!finalmapingIdList.contains(AppicationClass.addservicemapingid.get(i).toString())) {
                            finalmapingIdList.add(AppicationClass.addservicemapingid.get(i).toString());

                        } else {

                            finalPriceList.remove(AppicationClass.addserviceammount.get(i).toString());
                        }


                    }






                    /* Final Mapping id and Final service ammount is */


                    StringBuilder gSubServiceamount_builder_new = new StringBuilder();

                    for (int i = 0; i < finalPriceList.size(); i++) {
                        gSubServiceamount_builder_new.append(finalPriceList.get(i).toString() + ",");
                        System.out.println("Final service ammount array values are " + finalPriceList.get(i).toString() + " and  " + finalPriceList.size());


                    }

                    SelectedSubServiceAmmout_new = gSubServiceamount_builder_new.toString();

                    if (SelectedSubServiceAmmout_new.endsWith(",")) {
                        SelectedSubServiceAmmout_new = SelectedSubServiceAmmout_new.substring(0, SelectedSubServiceAmmout_new.length() - 1);
                    }

                    StringBuilder gSubServiceId_builder_new = new StringBuilder();

                    for (int i = 0; i < finalmapingIdList.size(); i++) {
                        gSubServiceId_builder_new.append(finalmapingIdList.get(i).toString() + ",");


                    }


                    SelectedSubServiceID_new = gSubServiceId_builder_new.toString();

                    if (SelectedSubServiceID_new.endsWith(",")) {
                        SelectedSubServiceID_new = SelectedSubServiceID_new.substring(0, SelectedSubServiceID_new.length() - 1);
                    }


                }


                System.out.println("Final mapping id and ammount is " + SelectedSubServiceAmmout_new + "and" + SelectedSubServiceID_new);


                // if(statelist_value.equals("Radiusclicked")) {
                //Zipcode id wise

                StringBuilder gZipcode_builder = new StringBuilder();
                System.out.println("In Submit button Zipcode Size iss " + gZipcodeId.size());

                for (int i = 0; i < gZipcodeId.size(); i++) {
                    gZipcode_builder.append(gZipcodeId.get(i).toString() + ",");

                    System.out.println("In Submit button Zipcode id is " + gZipcodeId.get(i).toString());
                }

                gFinalZipcode_toApi = gZipcode_builder.toString();

                if (gFinalZipcode_toApi.endsWith(",")) {
                    gFinalZipcode_toApi = gFinalZipcode_toApi.substring(0, gFinalZipcode_toApi.length() - 1);
                }

                StringBuilder gKms_builder = new StringBuilder();


                for (int i = 0; i < gKms.size(); i++) {
                    gKms_builder.append(gKms.get(i).toString() + ",");


                }

                gFinalKms_toApi = gKms_builder.toString();

                if (gFinalKms_toApi.endsWith(",")) {
                    gFinalKms_toApi = gFinalKms_toApi.substring(0, gFinalKms_toApi.length() - 1);
                }

                //        }


                // if(statelist_value.equals("CityClicked")) {

                //City Wise
                System.out.println("city arraylist size is " + AppicationClass.addlocationservicecities.size());
                if (AppicationClass.addlocationservicecities.size() > 0) {


                    StringBuilder gCity_builder = new StringBuilder();


                    for (int i = 0; i < AppicationClass.addlocationservicecities.size(); i++) {
                        gCity_builder.append(AppicationClass.addlocationservicecities.get(i).toString() + ",");


                    }

                    gCityId_toApi = gCity_builder.toString();

                    if (gCityId_toApi.endsWith(",")) {
                        gCityId_toApi = gCityId_toApi.substring(0, gCityId_toApi.length() - 1);
                    }


                    gCityClicked_values = true;
                } else {

                    gCityClicked_values = false;
                }


                //    }


                //  if(statelist_value.equals("Stateclicked")) {

                //State wise

                if (AppicationClass.addlocationservicestates.size() > 0) {

                    StringBuilder gState_builder = new StringBuilder();

                    for (int i = 0; i < AppicationClass.addlocationservicestates.size(); i++) {
                        gState_builder.append(AppicationClass.addlocationservicestates.get(i).toString() + ",");


                    }

                    gStateId_toApi = gState_builder.toString();

                    if (gStateId_toApi.endsWith(",")) {
                        gStateId_toApi = gStateId_toApi.substring(0, gStateId_toApi.length() - 1);
                    }

                    gStateClicked_values = true;
                } else {
                    gStateClicked_values = false;
                }

                //     }

                // System.out.println("Values in submit api " + gFinalZipcode_toApi + "and" + gFinalZipcode_toApi.length() );


//System.out.println("Values of buttons are " + gRadiusClicked_values +" and " + gCityClicked_values + " and "+ gStateClicked_values );
                if ((gRadiusClicked_values)) {
                    System.out.println("Value of Submit is " + "radius");
                }

                if ((gCityClicked_values)) {
                    System.out.println("Value of Submit is " + "city");
                }

                if ((gStateClicked_values)) {
                    System.out.println("Value of Submit is " + "state");
                }
                if ((gRadiusClicked_values) && (gCityClicked_values)) {
                    System.out.println("Value of Submit is " + "radius" + "city");

                }
                if ((gRadiusClicked_values) && (gStateClicked_values)) {
                    System.out.println("Value of Submit is " + "radius" + "state");
                }

                if ((gCityClicked_values) && (gStateClicked_values)) {
                    System.out.println("Value of Submit is " + "city" + "state");
                }

                if ((gRadiusClicked_values) && (gCityClicked_values) && (gStateClicked_values)) {
                    System.out.println("Value of Submit is " + "radius" + "city" + "state");

                }


                if ((gRadiusClicked_values) || (gCityClicked_values) || (gStateClicked_values)) {
                    System.out.println("Value of Submit is " + "any one is clicked");

                    System.out.println("In Submit buttoon id and amount are " + SelectedSubServiceID_new + " and " + SelectedSubServiceAmmout_new);

                 submitFor_Approval();


                } else {
                    Toast.makeText(ServicesAdd_Activity.this, "Select Location", Toast.LENGTH_SHORT).show();
                }


                //  submitFor_Approval();


                System.out.println("Service id in submit is idddddddd " + AppicationClass.addservicemapingid.size());

                System.out.println("Service id in submit is ammountttttt " + AppicationClass.addserviceammount.size());


             /*   if(statelist_value.equals("Radiusclicked")){

                   //if(gRadius.size()<=0 && gKms.size()<=0){
                        if (!lZipCode_AutoEditText.getText().toString().trim().equals("")) {

                            if (!kms.equals("") && !kms.equals("Kms")) {
                                gRadius.add(lZipCode_AutoEditText.getText().toString());
                                gKms.add(kms);

                                System.out.println("In Services Add Activity zipcode arraylsit !!!!!!!!" + gRadius.size() + "and" + gKms.size());
                            } else {

                                Toast.makeText(ServicesAdd_Activity.this, "Enter Kms", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ServicesAdd_Activity.this, "Enter Zip Code", Toast.LENGTH_SHORT).show();
                        }
                   *//* }
                    else {
                        System.out.println("In Services Add Activity zipcode arraylsit @@@@@@@@" + gRadius.size() + "and" + gKms.size());

                    }*//*
                }


                */


                //  System.out.println("On click on Submit city arraylist is " + AppicationClass.addlocationservicecities.size());

           /* System.out.println("In Radius submit array size is " + gRadius.size());

            if(gRadius.size()>0){
                addby_Radius();
            }
               else{
                Toast.makeText(ServicesAdd_Activity.this,"Select Zipcode",Toast.LENGTH_SHORT).show();

            }*/


            }
        });


    }  //On Create close


    //List Of Services  c
    public void service_List() {
        try {
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
                    if (response.isSuccessful()) {

                        ListOfServices_Response lservice_response = response.body();

                        gServicesList_Arraylist = new ArrayList<>(Arrays.asList(lservice_response.getServices_response()));
                        if (!gServicesList_Arraylist.get(0).getService_Mapping_ID().equals("No Results Found")) {

                            AddServicesList_Adapter lAddServicesList_Adapter = new AddServicesList_Adapter(ServicesAdd_Activity.this, gServicesList_Arraylist);
                            gServiceslist_recyclerview.setAdapter(lAddServicesList_Adapter);

                            gServiceId_FromService = gServicesList_Arraylist.get(0).getService_ID();

                            subService_List(gServiceId_FromService);

                            //     subServiceList_ByZipcode(gServiceId_FromService);
                        } else {


                        }


                    }

                }

                @Override
                public void onFailure(Call<ListOfServices_Response> call, Throwable t) {
                    Toast.makeText(ServicesAdd_Activity.this, getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();

                    progress.dismiss();
                }
            });


        } catch (Exception e) {
            System.out.println("In User Login Method 8");
            e.printStackTrace();
            progress.dismiss();

        }
    }


    //List Of Sub Services

    public void subService_List(String serviceId) {
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

            ApiInterface request = retrofit.create(ApiInterface.class);
            ListOfSubServices_Request lsubservice_request = new ListOfSubServices_Request();


            lsubservice_request.setUser_ID(gUserId_FromLogin);
            lsubservice_request.setCategory_ID(gCategoryId_FromLogin);
            lsubservice_request.setDocket(Constants.TOKEN);
            lsubservice_request.setService_ID(serviceId);
            Log.e("hihi", "subService_List: " + gCategoryId_FromLogin);


            Call<ListOfSubServices_Response> call = request.SubServices_list(lsubservice_request);
            call.enqueue(new Callback<ListOfSubServices_Response>() {
                @Override
                public void onResponse(Call<ListOfSubServices_Response> call, Response<ListOfSubServices_Response> response) {
                    if (response.isSuccessful()) {

                        ListOfSubServices_Response lsubservice_response = response.body();


                        gSubServicesList_Arraylist = new ArrayList<>(Arrays.asList(lsubservice_response.getSub_services_response()));
                        if (!gSubServicesList_Arraylist.get(0).getService_Mapping_ID().equals("No Results Found")) {
                            gSubserviceslist_recyclerview.setVisibility(View.VISIBLE);
                            //     gSubmitforapproval_button_Radius.setVisibility(View.GONE);

                            // gSubServiceList_Adapter = new SubServiceList_Adapter(UserServiceListActivity.this,gSubServicesList_Arraylist);
                            AppicationClass.addserviceammount.clear();
                            for (int i = 0; i < gSubServicesList_Arraylist.size(); i++) {
                                if (gSubServicesList_Arraylist.get(i).getService_Is_There().equals("Exists")) {

                                    if (AppicationClass.getUserType_FromCountryList().equals("1")) {
                                        AppicationClass.addserviceammount.add(gSubServicesList_Arraylist.get(i).getService_Amount());


                                    }

                                    if (AppicationClass.getUserType_FromCountryList().equals("0")) {
                                        AppicationClass.addservicemapingid.add(gSubServicesList_Arraylist.get(i).getService_Mapping_ID());


                                    }

                                }

                            }
                            AddSubServicesList_Adapter lAddSubServicesList_Adapter = new AddSubServicesList_Adapter(ServicesAdd_Activity.this, gSubServicesList_Arraylist,gAdminApproval_Status);
                            gSubserviceslist_recyclerview.setAdapter(lAddSubServicesList_Adapter);


                            progress.dismiss();


                        } else {
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


        } catch (Exception e) {
            System.out.println("In User Login Method 8");
            e.printStackTrace();
            progress.dismiss();

        }
    }


    //List of Location
    public void location_list() {
        try {

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
                    if (response.isSuccessful()) {

                        Select_service_partner_Response lservice_response = response.body();
                        ArrayList<Select_service_partner_Response.Service_Records> service_response = new ArrayList<>(Arrays.asList(lservice_response.getServices_response()));
                        if (service_response.get(0).getService_Mapping_ID().equals("1")) {

                            ArrayList<Select_service_partner_Response.Location_Records> location_response = new ArrayList<>(Arrays.asList(lservice_response.getLocation_reponse()));
                            for (int i = 0; i < location_response.size(); i++) {
                                location_City.add(location_response.get(i).getCity_Name());
                            }
                            Set<String> set = new HashSet<>(location_City);
                            location_City.clear();
                            location_City.addAll(set);
                            ArrayList<Select_service_partner_Response.Category_Records> Category = new ArrayList<>(Arrays.asList(lservice_response.getCategory_response()));
                            gPrimaryService.setText(Category.get(0).getCategory_Name());


                        } else
                            Toast.makeText(ServicesAdd_Activity.this, service_response.get(0).getService_Mapping_ID(), Toast.LENGTH_SHORT).show();

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


        } catch (Exception e) {
            System.out.println("In User Login Method 8");
            e.printStackTrace();
            progress.dismiss();

        }
    }

//Submit For Approval

    public void submitFor_Approval() {
        try {
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
            // lservice_request.setUser_ID("2");
            lservice_request.setService_Mapping_ID(SelectedSubServiceID_new);
            System.out.println("In api submit mathod id is " + SelectedSubServiceID_new);
            //SelectedLocationID

         /*   if(statelist_value.equals("Radiusclicked")) {
                lservice_request.setZipcode(gFinalZipcode_toApi);
                lservice_request.setRadius_kms(gFinalKms_toApi);
            }
            if(statelist_value.equals("CityClicked")) {
                lservice_request.setCity(gCityId_toApi);

            }
            if(statelist_value.equals("Stateclicked")) {
                lservice_request.setState(gStateId_toApi);
            }*/


            //Location ids,Optional keys


            if ((gRadiusClicked_values)) {

                lservice_request.setZipcode(gFinalZipcode_toApi);
                lservice_request.setRadius_kms(gFinalKms_toApi);

                System.out.println("In Submit Value of Submit is " + "radius");
            }

            if ((gCityClicked_values)) {
                lservice_request.setCity(gCityId_toApi);
                System.out.println("In Submit Value of Submit is " + "city");
            }

            if ((gStateClicked_values)) {
                lservice_request.setState(gStateId_toApi);
                System.out.println("In Submit Value of Submit is " + "state");
            }

            if ((gRadiusClicked_values) && (gCityClicked_values)) {
                lservice_request.setZipcode(gFinalZipcode_toApi);
                lservice_request.setRadius_kms(gFinalKms_toApi);

                lservice_request.setCity(gCityId_toApi);

                System.out.println("In Submit Value of Submit is " + "radius" + "city");

            }
            if ((gRadiusClicked_values) && (gStateClicked_values)) {
                lservice_request.setZipcode(gFinalZipcode_toApi);
                lservice_request.setRadius_kms(gFinalKms_toApi);

                lservice_request.setState(gStateId_toApi);

                System.out.println("In Submit Value of Submit is " + "radius" + "state");
            }

            if ((gCityClicked_values) && (gStateClicked_values)) {
                lservice_request.setCity(gCityId_toApi);
                lservice_request.setState(gStateId_toApi);
                System.out.println("In Submit Value of Submit is " + "city" + "state");
            }

            if ((gRadiusClicked_values) && (gCityClicked_values) && (gStateClicked_values)) {

                lservice_request.setZipcode(gFinalZipcode_toApi);
                lservice_request.setRadius_kms(gFinalKms_toApi);


                lservice_request.setCity(gCityId_toApi);
                lservice_request.setState(gStateId_toApi);

                System.out.println("In Submit Value of Submit is " + "radius" + "city" + "state");

            }





            /*if(statelist_value){

            }*/

            lservice_request.setDocket(Constants.TOKEN);


            //lservice_request.setService_Amount("");lservice_request.setDocket(Constants.TOKEN);


            Log.e("hihi", "submitFor_Approval: " + SelectedSubServiceID);
            if (AppicationClass.getPremium_PartenerId().equals("1")) {
                // lservice_request.setService_Amount(SelectedSubServiceAmmout);

                System.out.println("In api submit mathod amount is " + SelectedSubServiceAmmout_new);
                lservice_request.setService_Amount(SelectedSubServiceAmmout_new);

                // lservice_request.setService_Mapping_ID(SelectedSubServiceID);
            }

            Call<SubmitForApproval_Response> call = request.submitFor_Approval(lservice_request);
            call.enqueue(new Callback<SubmitForApproval_Response>() {
                @Override
                public void onResponse(Call<SubmitForApproval_Response> call, Response<SubmitForApproval_Response> response) {
                    if (response.isSuccessful()) {

                        SubmitForApproval_Response lservice_response = response.body();

                        if (lservice_response.getSubmit_services_for_approval_response().equals("valid")) {
                            Toast.makeText(ServicesAdd_Activity.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                            //subService_List(gServiceId_FromService);
                            //   subServiceList_ByZipcode(gServiceId_FromService);
                            gWaitingforapproval_button.setVisibility(View.VISIBLE);
                            gSubmitforapproval_button_Radius.setVisibility(View.GONE);
                            AppicationClass.addLocation.clear();
                            AppicationClass.addserviceammount.clear();
                            AppicationClass.addservicemapingid.clear();
                            AppicationClass.multipleaddservicemapingid.clear();
                            startActivity(new Intent(ServicesAdd_Activity.this, ServicePersonHome_Activity.class).putExtra("HomeScreenFlow", "fromserviceadd"));
                            finish();
                        } else {
                            subService_List(gServiceId_FromService);
                            // subServiceList_ByZipcode(gServiceId_FromService);
                            AppicationClass.addLocation.clear();
                            AppicationClass.addserviceammount.clear();
                            AppicationClass.addservicemapingid.clear();
                            AppicationClass.multipleaddservicemapingid.clear();
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


        } catch (Exception e) {
            System.out.println("In User Login Method 8");
            e.printStackTrace();
            progress.dismiss();

        }
    }

    @Override
    public void servicesslist(String serviceid) {

        subService_List(serviceid);
        //  subServiceList_ByZipcode(serviceid);


    }

    //Get Partner approval status
    private void getPartnerApproval_Status() {

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
            PartnerApprovalStatus_Request lGetListofCountry_Request = new PartnerApprovalStatus_Request();

            lGetListofCountry_Request.setDocket(Constants.TOKEN);
            lGetListofCountry_Request.setUser_ID(gUserId_FromLogin);

            System.out.println("Values of userid is " + gUserId_FromLogin);

            Call<PartnerApprovalStatus_Response> call = request.Get_PartnerApprovalStatus(lGetListofCountry_Request);
            call.enqueue(new Callback<PartnerApprovalStatus_Response>() {
                @Override
                public void onResponse(Call<PartnerApprovalStatus_Response> call, Response<PartnerApprovalStatus_Response> response) {
                    if (response.isSuccessful()) {


                        PartnerApprovalStatus_Response lPartnerApprovalStatus_Response = response.body();
                        gCountry_Spinner.setVisibility(View.VISIBLE);
                        gGet_PartnerApprovalStatus = new ArrayList<>(Arrays.asList(lPartnerApprovalStatus_Response.getPartner_approval_status()));


                        laddressproofpendingtext.setText(gGet_PartnerApprovalStatus.get(0).getAddress_proof_status());
                        lbankdetailspendingtext.setText(gGet_PartnerApprovalStatus.get(0).getBank_account_status());
                        ldocumentspendingtext.setText(gGet_PartnerApprovalStatus.get(0).getIdentity_Verfication_proof_status());

                        if (!gGet_PartnerApprovalStatus.get(0).getAddress_proof_status().equals("Approved") || !gGet_PartnerApprovalStatus.get(0).getIdentity_Verfication_proof_status().equals("Approved") || !gGet_PartnerApprovalStatus.get(0).getBank_account_status().equals("Approved")) {

                            // Toast.makeText(ServicesAdd_Activity.this,"Need to take approval for bank details",Toast.LENGTH_SHORT).show();


                            if (gGet_PartnerApprovalStatus.get(0).getAddress_proof_status().equals("Approved")) {

                                laddressproofpendingtext.setTextColor(getResources().getColor(R.color.color_green));
                                laddressproof_Image.setVisibility(View.VISIBLE);

                                //  Toast.makeText(ServicesAdd_Activity.this,"Need to take approval for personal details",Toast.LENGTH_SHORT).show();
                            }
                            if (gGet_PartnerApprovalStatus.get(0).getIdentity_Verfication_proof_status().equals("Approved")) {
                                ldocumentspendingtext.setTextColor(getResources().getColor(R.color.color_green));
                                ldocuments_Image.setVisibility(View.VISIBLE);

                                //  Toast.makeText(ServicesAdd_Activity.this,"Need to take approval for documents",Toast.LENGTH_SHORT).show();
                            }
                            if (gGet_PartnerApprovalStatus.get(0).getBank_account_status().equals("Approved")) {
                                lbankdetailspendingtext.setTextColor(getResources().getColor(R.color.color_green));
                                lbankdetails_Image.setVisibility(View.VISIBLE);
                                //  Toast.makeText(ServicesAdd_Activity.this,"Need to take approval for bank details",Toast.LENGTH_SHORT).show();
                            }


                            if (gGet_PartnerApprovalStatus.get(0).getAddress_proof_status().equals("Waiting_For_Approval")) {

                                laddressproofpendingtext.setTextColor(getResources().getColor(R.color.orange));
                                laddressproof_Image.setVisibility(View.GONE);
                                //  Toast.makeText(ServicesAdd_Activity.this,"Need to take approval for personal details",Toast.LENGTH_SHORT).show();
                            }
                            if (gGet_PartnerApprovalStatus.get(0).getIdentity_Verfication_proof_status().equals("Waiting_For_Approval")) {
                                ldocumentspendingtext.setTextColor(getResources().getColor(R.color.orange));
                                ldocuments_Image.setVisibility(View.GONE);

                                //  Toast.makeText(ServicesAdd_Activity.this,"Need to take approval for documents",Toast.LENGTH_SHORT).show();
                            }
                            if (gGet_PartnerApprovalStatus.get(0).getBank_account_status().equals("Waiting_For_Approval")) {
                                lbankdetailspendingtext.setTextColor(getResources().getColor(R.color.orange));
                                lbankdetails_Image.setVisibility(View.GONE);
                                //  Toast.makeText(ServicesAdd_Activity.this,"Need to take approval for bank details",Toast.LENGTH_SHORT).show();
                            }


                            if (gGet_PartnerApprovalStatus.get(0).getAddress_proof_status().equals("Rejected")) {

                                laddressproofpendingtext.setTextColor(getResources().getColor(R.color.color_red));
                                laddressproof_Image.setVisibility(View.GONE);
                                //  Toast.makeText(ServicesAdd_Activity.this,"Need to take approval for personal details",Toast.LENGTH_SHORT).show();
                            }
                            if (gGet_PartnerApprovalStatus.get(0).getIdentity_Verfication_proof_status().equals("Rejected")) {
                                ldocumentspendingtext.setTextColor(getResources().getColor(R.color.color_red));
                                ldocuments_Image.setVisibility(View.GONE);

                                //  Toast.makeText(ServicesAdd_Activity.this,"Need to take approval for documents",Toast.LENGTH_SHORT).show();
                            }
                            if (gGet_PartnerApprovalStatus.get(0).getBank_account_status().equals("Rejected")) {
                                lbankdetailspendingtext.setTextColor(getResources().getColor(R.color.color_red));
                                lbankdetails_Image.setVisibility(View.GONE);
                                //  Toast.makeText(ServicesAdd_Activity.this,"Need to take approval for bank details",Toast.LENGTH_SHORT).show();
                            }


                            gPartner_Status = false;
                            System.out.println("In partner approval status in if");
                            //   gConfirmation_Dialog.dismiss();
                        } else {

                            System.out.println("In partner approval status in else");

                            laddressproofpendingtext.setTextColor(getResources().getColor(R.color.color_green));
                            ldocumentspendingtext.setTextColor(getResources().getColor(R.color.color_green));
                            lbankdetailspendingtext.setTextColor(getResources().getColor(R.color.color_green));

                            laddressproof_Image.setVisibility(View.VISIBLE);
                            lbankdetails_Image.setVisibility(View.VISIBLE);
                            ldocuments_Image.setVisibility(View.VISIBLE);
                            gPartner_Status = true;
                            // gConfirmation_Dialog.dismiss();
                        }

                        if (gGet_PartnerApprovalStatus.get(0).getAddress_proof_status().equals("Approved") && gGet_PartnerApprovalStatus.get(0).getIdentity_Verfication_proof_status().equals("Approved") && gGet_PartnerApprovalStatus.get(0).getBank_account_status().equals("Approved")) {

                            System.out.println("In partner approval status in second if");

                            laddressproofpendingtext.setTextColor(getResources().getColor(R.color.color_green));
                            ldocumentspendingtext.setTextColor(getResources().getColor(R.color.color_green));
                            lbankdetailspendingtext.setTextColor(getResources().getColor(R.color.color_green));

                            laddressproof_Image.setVisibility(View.VISIBLE);
                            lbankdetails_Image.setVisibility(View.VISIBLE);
                            ldocuments_Image.setVisibility(View.VISIBLE);


                            gPartner_Status = true;


                        }


                        progress.dismiss();

                    }

                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<PartnerApprovalStatus_Response> call, Throwable t) {
                    System.out.println("In User Login Method 7");
                    progress.dismiss();
                }
            });
        } catch (Exception e) {
            progress.dismiss();
            e.printStackTrace();


        }

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
            GetListofCountry_Request lGetListofCountry_Request = new GetListofCountry_Request();

            lGetListofCountry_Request.setDocket(Constants.TOKEN);
            lGetListofCountry_Request.setUser_ID(gUserId_FromLogin);

            Call<GetListofCountry_Response> call = request.Get_CountryList(lGetListofCountry_Request);
            call.enqueue(new Callback<GetListofCountry_Response>() {
                @Override
                public void onResponse(Call<GetListofCountry_Response> call, Response<GetListofCountry_Response> response) {
                    if (response.isSuccessful()) {


                        GetListofCountry_Response lGetListofCountry_Response = response.body();
                        gCountry_Spinner.setVisibility(View.VISIBLE);
                        gGetCountry_ArrayList = new ArrayList<>(Arrays.asList(lGetListofCountry_Response.getGet_country_list_response()));
                        System.out.println("Arrayfrom Db Size is  " + gGetCountry_ArrayList.size());
                        gAdminApproval_Status = lGetListofCountry_Response.getService_status();


                        gUser_TypeFromCountryList = lGetListofCountry_Response.getUser_type();

                        AppicationClass.setUserType_FromCountryList(gUser_TypeFromCountryList);
                        service_List();
                        if (gAdminApproval_Status.equals("Newly_Registered") || gAdminApproval_Status.equals("Rejected") || gAdminApproval_Status.equals("Approved")) {
                            onApprovedStatus();
                        }


                        if (gAdminApproval_Status.equals("Waiting_For_Approval")) {

                            onWaitingStatus();
                        }


                        gCountryArrayList = new ArrayList<>();
                        gCountryArrayList.add(0, getResources().getString(R.string.selectcountry));
                        gCountryID_ArrayList.add(0, getResources().getString(R.string.selectcountryid));
                        for (int i = 0; i < gGetCountry_ArrayList.size(); i++) {


                            gCountryArrayList.add(1, gGetCountry_ArrayList.get(i).getCountry_Name());
                            gCountryID_ArrayList.add(1, gGetCountry_ArrayList.get(i).getCountry_ID());

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
                                    gSelectedCountryId = gCountryID_ArrayList.get(item);

                                  //  Toast.makeText(ServicesAdd_Activity.this, gSelectedCountryId, Toast.LENGTH_SHORT).show();

                                } else {

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
        } catch (Exception e) {
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
            GetStateList_Request lGetStateList_Request = new GetStateList_Request();

            lGetStateList_Request.setDocket(Constants.TOKEN);
            lGetStateList_Request.setUser_ID(gUserId_FromLogin);
            //   lGetStateList_Request.setCountry_ID(gSelectedCountryId);
            lGetStateList_Request.setCountry_ID("102");

            Call<GetStateList_Response> call = request.Get_StatesList(lGetStateList_Request);
            call.enqueue(new Callback<GetStateList_Response>() {
                @Override
                public void onResponse(Call<GetStateList_Response> call, Response<GetStateList_Response> response) {
                    if (response.isSuccessful()) {


                        GetStateList_Response lGetStateList_Response = response.body();

                        gGetStates_ArrayList = new ArrayList<>(Arrays.asList(lGetStateList_Response.getGet_states_list_response()));

                        if (!gGetStates_ArrayList.get(0).getState_ID().equals("No Results Found")) {
  /*  gStateArrayList=new ArrayList<>();
    gStateID_ArrayList=new ArrayList<>();
    gOnlyStateStatus_ArrayList=new ArrayList<>();

    gStateArrayList.add(0,getResources().getString(R.string.selectstate));
    gStateID_ArrayList.add(0,getResources().getString(R.string.selectstateid));
    gOnlyStateStatus_ArrayList.add(0,getResources().getString(R.string.selectstatestatus));
    for(int i=0;i<gGetStates_ArrayList.size();i++){


        gStateArrayList.add(1,gGetStates_ArrayList.get(i).getState_Name());
        gStateID_ArrayList.add(1,gGetStates_ArrayList.get(i).getState_ID());
        gOnlyStateStatus_ArrayList.add(1,gGetStates_ArrayList.get(i).getPartner_alloted());
    }
*/
                            if (statelist_value.equals("CityClicked")) {

                                gStateArrayList = new ArrayList<>();
                                gStateID_ArrayList = new ArrayList<>();
                                gOnlyStateStatus_ArrayList = new ArrayList<>();

                                gStateArrayList.add(0, getResources().getString(R.string.selectstate));
                                gStateID_ArrayList.add(0, getResources().getString(R.string.selectstateid));
                                gOnlyStateStatus_ArrayList.add(0, getResources().getString(R.string.selectstatestatus));
                                for (int i = 0; i < gGetStates_ArrayList.size(); i++) {


                                    gStateArrayList.add(1, gGetStates_ArrayList.get(i).getState_Name());
                                    gStateID_ArrayList.add(1, gGetStates_ArrayList.get(i).getState_ID());
                                    gOnlyStateStatus_ArrayList.add(1, gGetStates_ArrayList.get(i).getPartner_alloted());
                                }


                                System.out.println("In Get State list enter into city button");
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ServicesAdd_Activity.this, android.R.layout.simple_spinner_dropdown_item, gStateArrayList);
                                //set the spinners adapter to the previously created one.
                                Spinnerstates.setAdapter(adapter);


                                Spinnerstates.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                        int item = Spinnerstates.getSelectedItemPosition();
                                        Spinnerstates.setSelection(item, false);
                                        if (!Spinnerstates.getSelectedItem().toString().equals(getResources().getString(R.string.selectstate))) {

                                            gStatenamewith_Cities = gStateArrayList.get(item);
                                            String gSelectedStatesId = gStateID_ArrayList.get(item);

                                    //        Toast.makeText(ServicesAdd_Activity.this, gSelectedStatesId, Toast.LENGTH_SHORT).show();

                                            getCities_List(gSelectedStatesId);
                                        }

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });
                            }


                            if (statelist_value.equals("Stateclicked")) {
                                gStateArrayList = new ArrayList<>();
                                gStateID_ArrayList = new ArrayList<>();
                                gOnlyStateStatus_ArrayList = new ArrayList<>();

                                gStateArrayList.add(0, getResources().getString(R.string.selectstate));
                                gStateID_ArrayList.add(0, getResources().getString(R.string.selectstateid));
                                gOnlyStateStatus_ArrayList.add(0, getResources().getString(R.string.selectstatestatus));
                                for (int i = 0; i < gGetStates_ArrayList.size(); i++) {


                                    gStateArrayList.add(1, gGetStates_ArrayList.get(i).getState_Name());
                                    gStateID_ArrayList.add(1, gGetStates_ArrayList.get(i).getState_ID());
                                    gOnlyStateStatus_ArrayList.add(1, gGetStates_ArrayList.get(i).getPartner_alloted());
                                }
                                System.out.println("In Get State list enter into state button");
                                //Add According to only States

                                ArrayList<spinnerData> listVOs1 = new ArrayList<>();
                                AppicationClass.addlocationservicestates.clear();
                                for (int i = 0; i < gStateArrayList.size(); i++) {
                                    spinnerData stateVO = new spinnerData();
                                    stateVO.setTitle(gStateArrayList.get(i).toString());
                                    stateVO.setStateId(gStateID_ArrayList.get(i).toString());
                                    stateVO.setState_status(gOnlyStateStatus_ArrayList.get(i).toString());
                                    stateVO.setSelected(false);
                                    if (gOnlyStateStatus_ArrayList.get(i).toString().equals("1")) {
                                        if (!AppicationClass.addlocationservicestates.contains(gStateID_ArrayList.get(i).toString())) {
                                            AppicationClass.addlocationservicestates.add(gStateID_ArrayList.get(i).toString());
                                        }
                                    }
                                    listVOs1.add(stateVO);
                                }

                                SpinnerWithCheckBoxStates_Adapter myAdapter1 = new SpinnerWithCheckBoxStates_Adapter(ServicesAdd_Activity.this, 0,
                                        listVOs1);
                                gStates_withCheckbox.setAdapter(myAdapter1);

                            }


                        } else {
                            Toast.makeText(ServicesAdd_Activity.this, "No States found", Toast.LENGTH_SHORT).show();


                        }


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
        } catch (Exception e) {
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
            GetCityList_Request lGetStateList_Request = new GetCityList_Request();

            lGetStateList_Request.setDocket(Constants.TOKEN);
            lGetStateList_Request.setUser_ID(gUserId_FromLogin);
            //StateId
            lGetStateList_Request.setState_ID("424");

            Call<GetCityList_Response> call = request.Get_CityList(lGetStateList_Request);
            call.enqueue(new Callback<GetCityList_Response>() {
                @Override
                public void onResponse(Call<GetCityList_Response> call, Response<GetCityList_Response> response) {
                    if (response.isSuccessful()) {


                        GetCityList_Response lGetCityList_Response = response.body();

                        gGetCities_ArrayList = new ArrayList<>(Arrays.asList(lGetCityList_Response.getGet_cities_list_response()));
                        if (!gGetCities_ArrayList.get(0).getCity_ID().equals("No Results Found")) {

                            Spinnercities.setVisibility(View.VISIBLE);
                            //  gCities_NotFound.setVisibility(View.GONE);
                            gCityArrayList = new ArrayList<>();
                            gCityArrayList.add(0, getResources().getString(R.string.selectcity));
                            gCityID_ArrayList.add(0, getResources().getString(R.string.selectcityid));
                            gPartnerStatusForCityID_ArrayList.add(0, getResources().getString(R.string.selectcityidpartnerstatus));
                            for (int i = 0; i < gGetCities_ArrayList.size(); i++) {


                                gCityArrayList.add(1, gGetCities_ArrayList.get(i).getCity_Name());
                                gCityID_ArrayList.add(1, gGetCities_ArrayList.get(i).getCity_ID());
                                gPartnerStatusForCityID_ArrayList.add(1, gGetCities_ArrayList.get(i).getPartner_alloted());
                            }


                            ArrayList<spinnerData> listVOs = new ArrayList<>();
                            AppicationClass.addlocationservicecities.clear();
                            for (int i = 0; i < gCityArrayList.size(); i++) {
                                spinnerData stateVO = new spinnerData();
                                stateVO.setTitle(gCityArrayList.get(i).toString());
                                stateVO.setCityId(gCityID_ArrayList.get(i).toString());
                                stateVO.setPartnerStatus(gPartnerStatusForCityID_ArrayList.get(i).toString());
                                System.out.println("City Name is " + gCityArrayList.get(i).toString() + gCityID_ArrayList.get(i).toString());
                                stateVO.setSelected(false);

                                if (gPartnerStatusForCityID_ArrayList.get(i).toString().equals("1")) {
                                    if (!AppicationClass.addlocationservicecities.contains(gCityID_ArrayList.get(i).toString())) {
                                        AppicationClass.addlocationservicecities.add(gCityID_ArrayList.get(i).toString());
                                    }
                                }


                                listVOs.add(stateVO);
                            }


                            myAdapter = new SpinnerWithCheckBoxAdapter(ServicesAdd_Activity.this, 0,
                                    listVOs);
                            Spinnercities.setAdapter(myAdapter);



                           /* Spinnercities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                            });*/

                            gAddmoreCity.setVisibility(View.VISIBLE);


                        } else {
                            //  Spinnercities.setVisibility(View.GONE);
                            // gCities_NotFound.setVisibility(View.VISIBLE);
                            Toast.makeText(ServicesAdd_Activity.this, "No Cities Found", Toast.LENGTH_SHORT).show();


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
        } catch (Exception e) {
            progress.dismiss();
            e.printStackTrace();


        }

    }

    //Get ZipCode List

    private void getZipCodes_List() {

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
            GetAllZipCode_Request lGetStateList_Request = new GetAllZipCode_Request();

            lGetStateList_Request.setDocket(Constants.TOKEN);
            lGetStateList_Request.setUser_ID(gUserId_FromLogin);
            lGetStateList_Request.setCountry_ID("102");

            Call<GetAllZipCode_Response> call = request.Get_AllZipCodeList(lGetStateList_Request);
            call.enqueue(new Callback<GetAllZipCode_Response>() {
                @Override
                public void onResponse(Call<GetAllZipCode_Response> call, Response<GetAllZipCode_Response> response) {
                    if (response.isSuccessful()) {


                        GetAllZipCode_Response lGetZipcodeList_Response = response.body();

                        gGetZipcode_ArrayList = new ArrayList<>(Arrays.asList(lGetZipcodeList_Response.getList_zipcode_response()));
                        if (!gGetZipcode_ArrayList.get(0).getZipcode_ID().equals("No Results Found")) {


                            gZipCodeArrayList = new ArrayList<>();


                          /*  gZipCodeArrayList.add(0,getResources().getString(R.string.selectcity));
                            gZipCodeId_ArrayList.add(0,getResources().getString(R.string.selectcityid));
                            for(int i=0;i<gGetZipcode_ArrayList.size();i++){


                                gZipCodeArrayList.add(1,gGetZipcode_ArrayList.get(i).getZipcode());
                                gZipCodeId_ArrayList.add(1,gGetZipcode_ArrayList.get(i).getZipcode_ID());

                            }*/

                            for (int i = 0; i < gGetZipcode_ArrayList.size(); i++) {
                                gZipCodeArrayList.add(gGetZipcode_ArrayList.get(i).getZipcode());
                                gZipCodeId_ArrayList.add(gGetZipcode_ArrayList.get(i).getZipcode_ID());
                            }


                            // if (!gCountry_Spinner.getSelectedItem().toString().equals(getResources().getString(R.string.selectcountry))) {
                            ArrayAdapter<String> adapter12 = new ArrayAdapter<String>
                                    (ServicesAdd_Activity.this, android.R.layout.select_dialog_item, gZipCodeArrayList);

                            lZipCode_AutoEditText.setThreshold(1);//will start working from first character
                            lZipCode_AutoEditText.setAdapter(adapter12);

                            //  gZipcodeId.clear();
                            //   }

                            /*else{
                                Toast.makeText(ServicesAdd_Activity.this, getResources().getString(R.string.selectcountry), Toast.LENGTH_SHORT).show();
                            }*/


                        } else {
                            //  Spinnercities.setVisibility(View.GONE);
                            // gCities_NotFound.setVisibility(View.VISIBLE);
                            Toast.makeText(ServicesAdd_Activity.this, "No Cities Found", Toast.LENGTH_SHORT).show();


                        }


                        progress.dismiss();

                    }

                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<GetAllZipCode_Response> call, Throwable t) {
                    System.out.println("In User Login Method 7");
                    progress.dismiss();
                }
            });
        } catch (Exception e) {
            progress.dismiss();
            e.printStackTrace();


        }

    }

    public void onApprovedStatus() {
        gSubmitforapproval_button_Radius.setVisibility(View.VISIBLE);
        gWaitingfor_Approval_Button.setVisibility(View.GONE);

        gAddmoreRadiustxt.setEnabled(true);
        gAddmoreRadiustxt.setFocusable(true);
        gAddmoreRadiustxt.setClickable(true);


        lZipCode_AutoEditText.setEnabled(true);
        lZipCode_AutoEditText.setFocusable(true);
        lZipCode_AutoEditText.setClickable(true);
        lZipCode_AutoEditText.setKeyListener(lZipCode_AutoEditText.getKeyListener());


        gRadiusBtn.setClickable(true);
        gCityBtn.setClickable(true);
        gStatebtn.setClickable(true);

        lRadiusKms.setClickable(true);
        gCountry_Spinner.setClickable(true);

        lRadiusKms.setEnabled(true);
        gCountry_Spinner.setEnabled(true);
    }

    public void onWaitingStatus() {
        gSubmitforapproval_button_Radius.setVisibility(View.GONE);
        gWaitingfor_Approval_Button.setVisibility(View.VISIBLE);


        gAddmoreRadiustxt.setEnabled(false);
        gAddmoreRadiustxt.setFocusable(false);
        gAddmoreRadiustxt.setClickable(false);


        lZipCode_AutoEditText.setEnabled(false);
        lZipCode_AutoEditText.setFocusable(false);
        lZipCode_AutoEditText.setClickable(false);
        lZipCode_AutoEditText.setKeyListener(lZipCode_AutoEditText.getKeyListener());

        gRadiusBtn.setClickable(false);
        gCityBtn.setClickable(false);
        gStatebtn.setClickable(false);

        lRadiusKms.setClickable(false);
        gCountry_Spinner.setClickable(false);

        lRadiusKms.setEnabled(false);
        gCountry_Spinner.setEnabled(false);

    }

    @Override
    public void removeradiuslist(String zipcode, String kms, int position) {


        gRadiusClicked_values = false;

        System.out.println("Clicked on remove" + gRadiusClicked_values);
    }
}
