package service.com.surebot.info.serviceperson.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

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
import service.com.surebot.info.serviceperson.ApplicationClass;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.BuyPackage_Request;
import service.com.surebot.info.serviceperson.RequestClass.PartnerStartService_Request;
import service.com.surebot.info.serviceperson.ResponseClass.BuyPackage_Response;
import service.com.surebot.info.serviceperson.ResponseClass.PartnerStartService_Response;
import service.com.surebot.info.serviceperson.activities.PrimePackageActivity;
import service.com.surebot.info.serviceperson.activities.serviceDetailsActivity;
import service.com.surebot.info.serviceperson.adapters.HomePackage_Adapter;
import service.com.surebot.info.serviceperson.adapters.TodaysTaskAdapter;
import service.com.surebot.info.serviceperson.utils.Utils;


public class MyTaskFragment extends Fragment implements TodaysTaskAdapter.startservicelist_Communicator, HomePackage_Adapter.Buypackage_Communicator {


    @BindView(R.id.package_recyclerview)
    RecyclerView gPackage_recyclerview;

    @BindView(R.id.todaytask_recyclerview)
    RecyclerView gTodaytask_recyclerview;
    @BindView(R.id.headerLayoutCL)
    ConstraintLayout headerLayoutCL;
    @BindView(R.id.layoutServiceIV)
    ImageView layoutServiceIV;
    private PrimePackageActivity context;


    Dialog gEnterCode_Dialog;
    EditText lOtp_text1, lOtp_text2, lOtp_text3, lOtp_text4;
    String otp, otp1, otp2, otp3, otp4;

    private Dialog progress;


    String gUserId_FromLogin, gCategoryId_FromLogin, gPremiumPartner_Id;
    Dialog gCancel_Dialog;
    private static final String TAG = "MyTask_Fragment";
    String gPackagebuyedIdResponse_FromApi;

    @RequiresApi(api = Build.VERSION_CODES.M)
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

        int categorySelection = ApplicationClass.getCategorySelection();
        int headerLayout = R.drawable.salonlayout;
        int layoutServiceImage = R.drawable.electrician;

        switch (categorySelection) {
            case 1:
            case 2: {
                headerLayout = R.drawable.salonlayout;
                layoutServiceImage = R.drawable.salon;
            }
            break;
            case 3: {
                headerLayout = R.drawable.electrician_layout;
                layoutServiceImage = R.drawable.electrician;
            }
            break;
            case 4: {
                headerLayout = R.drawable.plumber_layout;
                layoutServiceImage = R.drawable.plumber;
            }
            break;
            case 5: {
                headerLayout = R.drawable.carpenter_layout;
                layoutServiceImage = R.drawable.carpenter;
            }
            break;
            case 6: {
                headerLayout = R.drawable.cleaning_layout;
                layoutServiceImage = R.drawable.cleaning;
            }
            break;
            case 7: {
                headerLayout = R.drawable.appliance_layout;
                layoutServiceImage = R.drawable.appliances;
            }
            break;
            case 8: {
                headerLayout = R.drawable.pest_layout;
                layoutServiceImage = R.drawable.pest;
            }
            break;
            case 9: {
                headerLayout = R.drawable.paint_layout;
                layoutServiceImage = R.drawable.paint;
            }
            break;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            headerLayoutCL.setBackground(ContextCompat.getDrawable(getActivity(), headerLayout));
            layoutServiceIV.setImageDrawable(ContextCompat.getDrawable(getActivity(), layoutServiceImage));
        }


        gUserId_FromLogin = ApplicationClass.getUserId_FromLogin();
        gCategoryId_FromLogin = ApplicationClass.getCategoryId_FromLogin();
        gPremiumPartner_Id = ApplicationClass.getPremium_PartenerId();

        TodaysTaskAdapter lTodaysTask_Adapter = new TodaysTaskAdapter(getActivity());
        gTodaytask_recyclerview.setAdapter(lTodaysTask_Adapter);
        HomePackage_Adapter lHomePackage_Adapter = new HomePackage_Adapter(getActivity());
        gPackage_recyclerview.setAdapter(lHomePackage_Adapter);

        return view;

    }  //onCreateView Closed

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
                                Log.e(TAG, "onResponse: was here length 0");
                            } else {
                                gPackagebuyedIdResponse_FromApi = ListPackageResponse.getBuy_partner_package_response();
                                Toast.makeText(getActivity(), "Package buyed successfuly", Toast.LENGTH_SHORT).show();


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
        } catch (Exception e) {
            System.out.println("In User Login Method 8");
            e.printStackTrace();
//            progress.dismiss();

        }
    }

    @Override
    public void startservice(String transactionid, String statusid, String username, String bookingdate, String bookingtime) {


        if (statusid.equals("1")) {

            Get_partner_start_servicecode(transactionid, statusid, username, bookingdate, bookingtime);
        }

        if (statusid.equals("0")) {
            gCancel_Dialog = new Dialog(getActivity(), R.style.dailogboxtheme);
            gCancel_Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            gCancel_Dialog.getWindow().setBackgroundDrawableResource(R.color.color_transparen);
            gCancel_Dialog.setContentView(R.layout.canceltodayrequest_popup);


            TextView lYes_text = gCancel_Dialog.findViewById(R.id.yes_text);
            TextView lNo_text = gCancel_Dialog.findViewById(R.id.no_text);

            lYes_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Get_partner_start_servicecode(transactionid, statusid, username, bookingdate, bookingtime);

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
        Toast.makeText(getActivity(), packageid, Toast.LENGTH_SHORT).show();

        buy_Package(packageid);

    }

// Class for getting otp from EditText

    public class SampleTextWatcherClass implements TextWatcher {
        private View view;

        private SampleTextWatcherClass(View view) {
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
                    otp1 = text;
                    break;
                case R.id.otp_text2:
                    if (text.length() == 1) {
                        lOtp_text3.requestFocus();
                        otp2 = text;
                    } else if (text.length() == 0)
                        lOtp_text1.requestFocus();
                    break;
                case R.id.otp_text3:
                    if (text.length() == 1) {
                        lOtp_text4.requestFocus();
                        otp3 = text;
                    } else if (text.length() == 0)
                        lOtp_text2.requestFocus();
                    break;
                case R.id.otp_text4:
                    if (text.length() == 0)
                        lOtp_text3.requestFocus();
                    else
                        otp4 = text;
                    break;

            }
        }

    }

    //Get Code For Start Service
    private void Get_partner_start_servicecode(String transactionId, String statusid, String username, String bookingdate, String bookingtime) {

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
                        if (!lPartnerStartService_Response.getPartner_start_service_get_otp_response().equals("Invalid")) {
                            if (!lPartnerStartService_Response.getPartner_start_service_get_otp_response().equals("valid")) {
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
                                            Toast.makeText(getActivity(), lPartnerStartService_Response.getPartner_start_service_get_otp_response(), Toast.LENGTH_SHORT).show();
                                            if (otp.equals(lPartnerStartService_Response.getPartner_start_service_get_otp_response())) {
                                                startActivity(new Intent(getActivity(), serviceDetailsActivity.class).putExtra("UserName", username).putExtra("BookingDate", bookingdate).putExtra("BookingTime", bookingtime));
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

                            if (lPartnerStartService_Response.getPartner_start_service_get_otp_response().equals("Valid")) {
                                Toast.makeText(getActivity(), "Cancelled Successfully", Toast.LENGTH_SHORT).show();
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
        } catch (Exception e) {
            System.out.println("In User Login Method 8");
            e.printStackTrace();
//            progress.dismiss();
        }
    }
}
