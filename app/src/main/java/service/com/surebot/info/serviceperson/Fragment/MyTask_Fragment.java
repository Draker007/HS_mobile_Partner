package service.com.surebot.info.serviceperson.Fragment;

import android.annotation.SuppressLint;

import android.app.Dialog;
import android.content.Intent;
import android.content.Intent;
import android.media.Image;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.ImageView;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import java.util.List;

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

import service.com.surebot.info.serviceperson.Activity.serviceDetailsActivity;

import service.com.surebot.info.serviceperson.Activity.NotificationActivity;

import service.com.surebot.info.serviceperson.Adapter.HomePackage_Adapter;
import service.com.surebot.info.serviceperson.Adapter.TodaysTask_Adapter;
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.R;

import service.com.surebot.info.serviceperson.RequestClass.BuyPackage_Request;
import service.com.surebot.info.serviceperson.RequestClass.PartnerStartService_Request;
import service.com.surebot.info.serviceperson.RequestClass.Partner_my_task_today_Request;
import service.com.surebot.info.serviceperson.ResponseClass.BuyPackage_Response;
import service.com.surebot.info.serviceperson.ResponseClass.PartnerStartService_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Partner_my_task_today_response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;
import service.com.surebot.info.serviceperson.utils.UserAddress_Location;

import service.com.surebot.info.serviceperson.RequestClass.Partner_package_Request;
import service.com.surebot.info.serviceperson.ResponseClass.Partner_package_Response;


public class MyTask_Fragment  extends Fragment implements  TodaysTask_Adapter.startservicelist_Communicator,HomePackage_Adapter.Buypackage_Communicator{


    @BindView(R.id.package_recyclerview)
    RecyclerView gPackage_recyclerview;

    @BindView(R.id.todaytask_recyclerview)
    RecyclerView gTodaytask_recyclerview;

    @BindView(R.id.notificationIcon)
    ImageView notification;

    @BindView(R.id.notask_header)
    TextView gNotask_header;

    @BindView(R.id.todyatask_header)
            TextView gTodyatask_header;

    @BindView(R.id.becomember_header)
    TextView gBecomember_header;


    ArrayList<Integer> gPackages_List;
    ArrayList<String> gUserName_List;

    LinearLayoutManager llm;
    ArrayList<Partner_package_Response.Partner_package_records> partner_package_response ;


    ArrayList<Partner_my_task_today_response.Partner_my_task_today_Records> lTodaysTask_Arraylist ;


    Dialog gEnterCode_Dialog;
    EditText lOtp_text1, lOtp_text2, lOtp_text3, lOtp_text4;
    String otp ,otp1 , otp2 , otp3 , otp4;

    private Dialog progress;


    String gUserId_FromLogin,gCategoryId_FromLogin,gPremiumPartner_Id;


    Dialog gCancel_Dialog;


    private static final String TAG = "MyTask_Fragment";
    String gPackagebuyedIdResponse_FromApi;

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.mytask_fragment_layout, container, false);
        ButterKnife.bind(this, view);

        progress = new Dialog(getActivity(), android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);

        gUserId_FromLogin= AppicationClass.getUserId_FromLogin();
        gCategoryId_FromLogin=AppicationClass.getCategoryId_FromLogin();
        gPremiumPartner_Id = AppicationClass.getPremium_PartenerId();


        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NotificationActivity.class));
            }
        });



        Package_Lists();


        gPackages_List = new ArrayList<Integer>();
        gPackages_List.add(R.mipmap.package1);
        gPackages_List.add(R.mipmap.package2);
        gPackages_List.add(R.mipmap.package3);



        gUserName_List = new ArrayList<String>();
        gUserName_List.add("Aditi");
        gUserName_List.add("Sahana");
        gUserName_List.add("Aditi");
        gUserName_List.add("Sahana");
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gPackage_recyclerview.setLayoutManager(layoutManager);




//        HomePackage_Adapter lHomePackage_Adapter = new HomePackage_Adapter(getActivity(),partner_package_response);
        //   gPackage_recyclerview.setAdapter(lHomePackage_Adapter);

      /*  HomePackage_Adapter lHomePackage_Adapter = new HomePackage_Adapter(getActivity(),partner_package_response);
        gPackage_recyclerview.setAdapter(lHomePackage_Adapter);*/




        List<UserAddress_Location> items = new ArrayList<>();

      /*  items.add(new UserAddress_Location("Item 1"));
        items.add(new UserAddress_Location("Item 2"));
        items.add(new UserAddress_Location(new LatLng(1.289545, 103.849972)));
        items.add(new UserAddress_Location("Item 3"));

        items.add(new UserAddress_Location("Item 4"));
        items.add(new UserAddress_Location("Item 5"));
        items.add(new UserAddress_Location("Item 6"));
        items.add(new UserAddress_Location("Item 7"));
        items.add(new UserAddress_Location("Item 8"));
        items.add(new UserAddress_Location("Item 9"));*/

      /*  ArrayList<String> gTimeslotArraylist = new ArrayList<>();
        gTimeslotArraylist.add(0,"37.0902, 95.7129");
        gTimeslotArraylist.add(1,"1.289545, 103.849972");
        gTimeslotArraylist.add(2,"56.1304, 106.3468");
        gTimeslotArraylist.add(3,"20.5937, 78.9629");
        UserAddress_Location lUserAddress_Location = new UserAddress_Location();*/
        /*lUserAddress_Location.setPosition(new LatLng(14.22262, 76.40038));
        lUserAddress_Location.setPosition(new LatLng(1.289545, 103.849972));
        lUserAddress_Location.setPosition(new LatLng(14.22262, 76.40038));
        lUserAddress_Location.setPosition(new LatLng(14.22262, 76.40038));*/


        //Todays Task List
        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        gTodaytask_recyclerview.setLayoutManager(llm);


   /*     for(int i=0;i<gTimeslotArraylist.size();i++){
            lUserAddress_Location.setLatitutde(gTimeslotArraylist.get(i));

            items.add(i,lUserAddress_Location);


        }*/
  //      Log.e("cocacola", "onCreateView: "+items.get(1).getLatitutde() );

       /* TodaysTask_Adapter lTodaysTask_Adapter = new TodaysTask_Adapter(getActivity(),gTimeslotArraylist,gUserName_List);
        gTodaytask_recyclerview.setAdapter(lTodaysTask_Adapter);
        lTodaysTask_Adapter.setstartservicelist_Communicator(MyTask_Fragment.this);
        lTodaysTask_Adapter.notifyDataSetChanged();*/


           /* items.add(new UserAddress_Location(new LatLng(14.22262, 76.40038)));
        items.add(new UserAddress_Location(new LatLng(1.289545, 103.849972)));
        items.add(new UserAddress_Location(new LatLng(1.289545, 103.849972)));
        items.add(new UserAddress_Location(new LatLng(1.289545, 103.849972)));*/


        Get_TodaysTaskList();


        return view;

    }  //onCreateView Closed


    // // Get Packages Detials
    private void Package_Lists() {

        try {
            System.out.println("In User Login Method 1");
            // progress.show();
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            HttpLoggingInterceptor registrationInterceptor = new HttpLoggingInterceptor();
            registrationInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(registrationInterceptor);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            System.out.println("asd1");
            ApiInterface request = retrofit.create(ApiInterface.class);
            Partner_package_Request lservice_request = new Partner_package_Request();
            lservice_request.setDocket(Constants.TOKEN);

            Call<Partner_package_Response> call = request.List_packages(lservice_request);
            call.enqueue(new Callback<Partner_package_Response>() {
                @Override
                public void onResponse(Call<Partner_package_Response> call, Response<Partner_package_Response> response) {
                    if (response.isSuccessful()) {
                        System.out.println("asd1");
                        Partner_package_Response ListPackage = response.body();
                        partner_package_response =new ArrayList<>(Arrays.asList(ListPackage.getPartner_package_response()));
                        if(!partner_package_response.get(0).getPackage_ID().equals("No Results Found")) {
                        HomePackage_Adapter lHomePackage_Adapter = new HomePackage_Adapter(getActivity(), partner_package_response);
                        gPackage_recyclerview.setAdapter(lHomePackage_Adapter);

                            gBecomember_header.setVisibility(View.VISIBLE);


                            lHomePackage_Adapter.setBuypackage_Communicator(MyTask_Fragment.this);
                            lHomePackage_Adapter.notifyDataSetChanged();
                        }
                    }


                     progress.dismiss();
                }



                @Override
                public void onFailure(Call<Partner_package_Response> call, Throwable t) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
                       progress.dismiss();
                }
            });
        }
        catch (Exception e) {
            System.out.println("In User Login Method 8");
            e.printStackTrace();
//            progress.dismiss();

        }
    }

    //Buy a package

    private void buy_Package(String packageid) {

        try {
            System.out.println("In User Login Method 1");
            // progress.show();
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            HttpLoggingInterceptor registrationInterceptor = new HttpLoggingInterceptor();
            registrationInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(registrationInterceptor);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            System.out.println("asd1");
            ApiInterface request = retrofit.create(ApiInterface.class);
            BuyPackage_Request lBuyPackage_Request = new BuyPackage_Request();
            lBuyPackage_Request.setDocket(Constants.TOKEN);
            lBuyPackage_Request.setUser_ID(gUserId_FromLogin);
            lBuyPackage_Request.setPackage_ID(packageid);

            Call<BuyPackage_Response> call = request.buy_Package(lBuyPackage_Request);
            call.enqueue(new Callback<BuyPackage_Response>() {
                @Override
                public void onResponse(Call<BuyPackage_Response> call, Response<BuyPackage_Response> response) {
                    if (response.isSuccessful()) {
                        System.out.println("asd1");
                        BuyPackage_Response ListPackageResponse = response.body();


                        //For Success Full Response
                        String text = ListPackageResponse.getBuy_partner_package_response();
                        boolean digitsOnly = TextUtils.isDigitsOnly(text);
                        if (digitsOnly) {
                            if (text.length() == 0) {
                                Log.e(TAG, "onResponse: was here length 0" );
                            } else {
                                gPackagebuyedIdResponse_FromApi = ListPackageResponse.getBuy_partner_package_response();
                                Toast.makeText(getActivity(),"Package buyed successfuly",Toast.LENGTH_SHORT).show();


                            }
                        } else {
                        }



                    }


                    progress.dismiss();
                }



                @Override
                public void onFailure(Call<BuyPackage_Response> call, Throwable t) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }
        catch (Exception e) {
            System.out.println("In User Login Method 8");
            e.printStackTrace();
//            progress.dismiss();

        }
    }

    @Override
    public void startservice(String transactionid,String statusid) {


if(statusid.equals("1")){

    Get_partner_start_servicecode(transactionid,statusid);
}

if(statusid.equals("0")){
            gCancel_Dialog = new Dialog(getActivity(), R.style.dailogboxtheme);
            gCancel_Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            gCancel_Dialog.getWindow().setBackgroundDrawableResource(R.color.color_transparen);
            gCancel_Dialog.setContentView(R.layout.canceltodayrequest_popup);


            TextView lYes_text = gCancel_Dialog.findViewById(R.id.yes_text);
            TextView lNo_text = gCancel_Dialog.findViewById(R.id.no_text);

            lYes_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Get_partner_start_servicecode(transactionid,statusid);

                }
            });

            lNo_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gCancel_Dialog.dismiss();

                }
            });

            gCancel_Dialog.setCancelable(false);
            gCancel_Dialog.show();


        }
    }

    @Override
    public void buypackage(String packageid) {
        Toast.makeText(getActivity(),packageid,Toast.LENGTH_SHORT).show();

        buy_Package(packageid);

    }


    // Class for getting otp from EditText

    public class SampleTextWatcherClass implements TextWatcher{
        private View view;
        private SampleTextWatcherClass(View view)
        {
            this.view = view;
        }


        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String text = editable.toString();
            switch (view.getId()) {

                case R.id.otp_text1:
                    if (text.length() == 1)
                        lOtp_text2.requestFocus();
                    otp1=text;
                    break;
                case R.id.otp_text2:
                    if (text.length() == 1)
                    {
                        lOtp_text3.requestFocus();
                        otp2 = text;
                    }
                    else if (text.length() == 0)
                        lOtp_text1.requestFocus();
                    break;
                case R.id.otp_text3:
                    if (text.length() == 1)
                    {
                        lOtp_text4.requestFocus();
                        otp3=text;
                    }
                    else if (text.length() == 0)
                        lOtp_text2.requestFocus();
                    break;
                case R.id.otp_text4:
                    if (text.length() == 0)
                        lOtp_text3.requestFocus();
                    else
                        otp4=text;
                    break;

            }
        }
    }


    //Get Todays Task List
    private void Get_TodaysTaskList() {

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
            System.out.println("asd1");
            ApiInterface request = retrofit.create(ApiInterface.class);
            Partner_my_task_today_Request lservice_request = new Partner_my_task_today_Request();
            lservice_request.setDocket(Constants.TOKEN);
            lservice_request.setUser_ID(gUserId_FromLogin);

            System.out.println("In Todays task Api" + gUserId_FromLogin);
            Call<Partner_my_task_today_response> call = request.Get_TodaysTaskList(lservice_request);
            call.enqueue(new Callback<Partner_my_task_today_response>() {
                @Override
                public void onResponse(Call<Partner_my_task_today_response> call, Response<Partner_my_task_today_response> response) {
                    if (response.isSuccessful()) {
                        System.out.println("asd12");
                        Partner_my_task_today_response ListPackage = response.body();
                        lTodaysTask_Arraylist = new ArrayList<>(Arrays.asList(ListPackage.getPartner_my_task_today_response()));

                        List<UserAddress_Location> items = new ArrayList<>();
                        if( !lTodaysTask_Arraylist.get(0).getTransaction_ID().equals("No Results Found") && !lTodaysTask_Arraylist.get(0).getUser_ID().equals("User Does Not Exists")){
                           gTodaytask_recyclerview.setVisibility(View.VISIBLE);
                           gNotask_header.setVisibility(View.GONE);
                           gTodyatask_header.setVisibility(View.VISIBLE);


                            TodaysTask_Adapter lTodaysTask_Adapter = new TodaysTask_Adapter(getActivity(),lTodaysTask_Arraylist);
                           gTodaytask_recyclerview.setAdapter(lTodaysTask_Adapter);
                           lTodaysTask_Adapter.setstartservicelist_Communicator(MyTask_Fragment.this);
                           lTodaysTask_Adapter.notifyDataSetChanged();
                       }
                       else{
                           gTodyatask_header.setVisibility(View.GONE);
                           gTodaytask_recyclerview.setVisibility(View.GONE);
                           gNotask_header.setVisibility(View.VISIBLE);
                       }

                        progress.dismiss();
                    }


                    progress.dismiss();
                }



                @Override
                public void onFailure(Call<Partner_my_task_today_response> call, Throwable t) {
                    Toast.makeText(getActivity(), "12"+getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
                      progress.dismiss();
                }
            });
        }
        catch (Exception e) {
            System.out.println("In User Login Method 8");
            e.printStackTrace();
//            progress.dismiss();

        }
    }


    //Get Code For Start Service
    private void Get_partner_start_servicecode(String transactionId,String statusid) {

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
            System.out.println("asd1");
            ApiInterface request = retrofit.create(ApiInterface.class);
            PartnerStartService_Request lservice_request = new PartnerStartService_Request();
            lservice_request.setDocket(Constants.TOKEN);
            lservice_request.setUser_ID(gUserId_FromLogin);
            lservice_request.setTransaction_ID(transactionId);
            lservice_request.setStatus_ID(statusid);

                  System.out.println("In Todays task Api Tart service" + gUserId_FromLogin + transactionId);
            Call<PartnerStartService_Response> call = request.Get_partner_start_servicecode(lservice_request);
            call.enqueue(new Callback<PartnerStartService_Response>() {
                @Override
                public void onResponse(Call<PartnerStartService_Response> call, Response<PartnerStartService_Response> response) {
                    if (response.isSuccessful()) {

                        PartnerStartService_Response lPartnerStartService_Response = response.body();
                        if(!lPartnerStartService_Response.getPartner_start_service_get_otp_response().equals("Invalid")) {
                            if(!lPartnerStartService_Response.getPartner_start_service_get_otp_response().equals("valid")){
                            Toast.makeText(getActivity(), lPartnerStartService_Response.getPartner_start_service_get_otp_response(), Toast.LENGTH_SHORT).show();
                            gEnterCode_Dialog = new Dialog(getActivity(), R.style.dailogboxtheme);
                            gEnterCode_Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            gEnterCode_Dialog.getWindow().setBackgroundDrawableResource(R.color.color_transparen);
                            gEnterCode_Dialog.setContentView(R.layout.entercodeforstartservice_popup);


                            TextView lOkay_text = gEnterCode_Dialog.findViewById(R.id.ok_text);
                            lOtp_text1 = gEnterCode_Dialog.findViewById(R.id.otp_text1);
                            lOtp_text2 = gEnterCode_Dialog.findViewById(R.id.otp_text2);
                            lOtp_text3 = gEnterCode_Dialog.findViewById(R.id.otp_text3);
                            lOtp_text4 = gEnterCode_Dialog.findViewById(R.id.otp_text4);

                            lOtp_text1.addTextChangedListener(new SampleTextWatcherClass(lOtp_text1));
                            lOtp_text2.addTextChangedListener(new SampleTextWatcherClass(lOtp_text2));
                            lOtp_text3.addTextChangedListener(new SampleTextWatcherClass(lOtp_text3));
                            lOtp_text4.addTextChangedListener(new SampleTextWatcherClass(lOtp_text4));


                            lOkay_text.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {


                                    if (lOtp_text1.getText().toString().isEmpty() || lOtp_text2.getText().toString().isEmpty() || lOtp_text3.getText().toString().isEmpty() || lOtp_text4.getText().toString().isEmpty()) {
                                        Toast.makeText(getActivity(), "Enter 4 Digit Code Sent To User", Toast.LENGTH_LONG).show();

                                    } else {
                                        otp = otp1 + otp2 + otp3 + otp4;
                                        System.out.println("Entered Otp " + otp);

                                        if (otp.equals(lPartnerStartService_Response.getPartner_start_service_get_otp_response())) {
                                            startActivity(new Intent(getActivity(), serviceDetailsActivity.class));
                                            gEnterCode_Dialog.dismiss();
                                        } else {
                                            Toast.makeText(getActivity(), "Invalid Otp", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                }
                            });

                            gEnterCode_Dialog.setCancelable(false);
                            gEnterCode_Dialog.show();
                        }

                            if( lPartnerStartService_Response.getPartner_start_service_get_otp_response().equals("Valid")){
                                   Toast.makeText(getActivity(),"Cancelled Successfully",Toast.LENGTH_SHORT).show();
                            }


                        }



                        progress.dismiss();
                    }


                    progress.dismiss();
                }



                @Override
                public void onFailure(Call<PartnerStartService_Response> call, Throwable t) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }
        catch (Exception e) {
            System.out.println("In User Login Method 8");
            e.printStackTrace();
//            progress.dismiss();

        }
    }

}
