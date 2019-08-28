package service.com.surebot.info.serviceperson.Fragment;

import android.annotation.SuppressLint;

import android.app.Dialog;
import android.content.Intent;
import android.content.Intent;
import android.media.Image;

import android.os.Bundle;
import android.text.Editable;
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

import service.com.surebot.info.serviceperson.utils.UserAddress_Location;

import service.com.surebot.info.serviceperson.RequestClass.Partner_package_Request;
import service.com.surebot.info.serviceperson.ResponseClass.Partner_package_Response;


public class MyTask_Fragment  extends Fragment implements  TodaysTask_Adapter.startservicelist_Communicator {


    @BindView(R.id.package_recyclerview)
    RecyclerView gPackage_recyclerview;

    @BindView(R.id.todaytask_recyclerview)
    RecyclerView gTodaytask_recyclerview;

    @BindView(R.id.notificationIcon)
    ImageView notification;



    ArrayList<Integer> gPackages_List;
    ArrayList<String> gUserName_List;

    LinearLayoutManager llm;
    ArrayList<Partner_package_Response.Partner_package_records> partner_package_response ;

    Dialog gEnterCode_Dialog;
    EditText lOtp_text1, lOtp_text2, lOtp_text3, lOtp_text4;
    String otp ,otp1 , otp2 , otp3 , otp4;
    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.mytask_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NotificationActivity.class));
            }
        });



        callListPackageAPI();
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

        ArrayList<String> gTimeslotArraylist = new ArrayList<>();
        gTimeslotArraylist.add(0,"37.0902, 95.7129");
        gTimeslotArraylist.add(1,"1.289545, 103.849972");
        gTimeslotArraylist.add(2,"56.1304, 106.3468");
        gTimeslotArraylist.add(3,"20.5937, 78.9629");
        UserAddress_Location lUserAddress_Location = new UserAddress_Location();
        /*lUserAddress_Location.setPosition(new LatLng(14.22262, 76.40038));
        lUserAddress_Location.setPosition(new LatLng(1.289545, 103.849972));
        lUserAddress_Location.setPosition(new LatLng(14.22262, 76.40038));
        lUserAddress_Location.setPosition(new LatLng(14.22262, 76.40038));*/


        //Todays Task List
        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        gTodaytask_recyclerview.setLayoutManager(llm);


        for(int i=0;i<gTimeslotArraylist.size();i++){
            lUserAddress_Location.setLatitutde(gTimeslotArraylist.get(i));

            items.add(i,lUserAddress_Location);


        }
        Log.e("cocacola", "onCreateView: "+items.get(1).getLatitutde() );

        TodaysTask_Adapter lTodaysTask_Adapter = new TodaysTask_Adapter(getActivity(),gTimeslotArraylist,gUserName_List);
        gTodaytask_recyclerview.setAdapter(lTodaysTask_Adapter);
        lTodaysTask_Adapter.setstartservicelist_Communicator(MyTask_Fragment.this);
        lTodaysTask_Adapter.notifyDataSetChanged();


           /* items.add(new UserAddress_Location(new LatLng(14.22262, 76.40038)));
        items.add(new UserAddress_Location(new LatLng(1.289545, 103.849972)));
        items.add(new UserAddress_Location(new LatLng(1.289545, 103.849972)));
        items.add(new UserAddress_Location(new LatLng(1.289545, 103.849972)));*/





        return view;

    }  //onCreateView Closed


    // // Get Packages Detials
    private void callListPackageAPI() {

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

//                        partner_package_response =new ArrayList<>(Arrays.asList(ListPackage.getPartner_package_response()));
//                        HomePackage_Adapter lHomePackage_Adapter = new HomePackage_Adapter(getActivity(),partner_package_response);
//                        gPackage_recyclerview.setAdapter(lHomePackage_Adapter);

                    }


                    // progress.dismiss();
                }



                @Override
                public void onFailure(Call<Partner_package_Response> call, Throwable t) {
                    System.out.println("In User Login Method 7");
                    //   progress.dismiss();
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
    public void startservice(String serviceid) {


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


                if (lOtp_text1.getText().toString().isEmpty()||lOtp_text2.getText().toString().isEmpty()||lOtp_text3.getText().toString().isEmpty()||lOtp_text4.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Enter 4 Digit Code Sent To User", Toast.LENGTH_LONG).show();

                }
                else{
                    otp = otp1+otp2+otp3+otp4;
                 System.out.println("Entered Otp " + otp);
                    startActivity(new Intent(getActivity(), serviceDetailsActivity.class));
                    gEnterCode_Dialog.dismiss();
                }
            }
        });

        gEnterCode_Dialog.setCancelable(false);
        gEnterCode_Dialog.show();


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


}
