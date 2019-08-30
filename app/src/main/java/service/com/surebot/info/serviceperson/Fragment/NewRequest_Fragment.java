package service.com.surebot.info.serviceperson.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import service.com.surebot.info.serviceperson.Adapter.NewRequests_Adapter;
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.NewRequestList_Request;
import service.com.surebot.info.serviceperson.RequestClass.SendQuotetoUser_Request;
import service.com.surebot.info.serviceperson.ResponseClass.Account_details_Response;
import service.com.surebot.info.serviceperson.ResponseClass.NewRequestList_Response;
import service.com.surebot.info.serviceperson.ResponseClass.SendQuotetoUser_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class NewRequest_Fragment  extends Fragment implements NewRequests_Adapter.ServiceList_Communicator {


    @BindView(R.id.newrequestlist_recyclerview)
    RecyclerView gNewrequestlist_recyclerview;
    @BindView(R.id.norequest_text)
    TextView gNorequest_text;

    LinearLayoutManager llm;




    private Dialog progress;

    ArrayList<NewRequestList_Response.NewRequestList_Response_Records> gNewRequestList_Arraylist;
    String gUserId_FromLogin;

    String gStatus_Id;

    String gFinalServicesMaping_Id,gFinalServices_Ammount;

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.newrequestlist_layout, container, false);
        ButterKnife.bind(this, view);

        progress = new Dialog(getActivity(), android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);


        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        gNewrequestlist_recyclerview.setLayoutManager(llm);

        gUserId_FromLogin = AppicationClass.getUserId_FromLogin();


        get_NewServiceRequestList();
        return view;
    }
    // First New Request List

    private void get_NewServiceRequestList()  {
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
            NewRequestList_Request lNewRequestList_Request = new NewRequestList_Request();

            lNewRequestList_Request.setUser_ID(gUserId_FromLogin);
            lNewRequestList_Request.setDocket(Constants.TOKEN);

            Call<NewRequestList_Response> call = request.get_NewServiceRequestList(lNewRequestList_Request);
            call.enqueue(new Callback<NewRequestList_Response>() {


                @Override
                public void onResponse(Call<NewRequestList_Response> call, Response<NewRequestList_Response> response) {
                    if (response.isSuccessful()) {
                        NewRequestList_Response lNewRequestList_Response = response.body();

                        gNewRequestList_Arraylist = new ArrayList<>(Arrays.asList(lNewRequestList_Response.getPartner_my_task_details_response()));
                        if(!gNewRequestList_Arraylist.get(0).getUser_ID().equals("No Results Found")){

                            gNewrequestlist_recyclerview.setVisibility(View.VISIBLE);
                            gNorequest_text.setVisibility(View.GONE);

                            NewRequests_Adapter lNewRequests_Adapter = new NewRequests_Adapter(getActivity(),gNewRequestList_Arraylist);
                            gNewrequestlist_recyclerview.setAdapter(lNewRequests_Adapter);
                            lNewRequests_Adapter.setServiceList_Communicator(NewRequest_Fragment.this);
                        }
                        else {
                            gNewrequestlist_recyclerview.setVisibility(View.GONE);
                            gNorequest_text.setVisibility(View.VISIBLE);
                        }

                        progress.dismiss();
                    }
                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<NewRequestList_Response> call, Throwable t) {
                    Toast.makeText(getActivity(), "new"+getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }catch (Exception e) {

            e.printStackTrace();
            progress.dismiss();

        }

    }



    //Send Or Reject Qoute to Use

    private void sendQuote_toUser()  {
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
            SendQuotetoUser_Request lNewRequestList_Request = new SendQuotetoUser_Request();

            lNewRequestList_Request.setUser_ID("11");
            lNewRequestList_Request.setQuote_amount("200,400");
            lNewRequestList_Request.setTransaction_Partner_Quote_ID("1,2");
            lNewRequestList_Request.setStatus_ID(gStatus_Id);
            lNewRequestList_Request.setDocket(Constants.TOKEN);

            Call<SendQuotetoUser_Response> call = request.sendQuote_toUser(lNewRequestList_Request);
            call.enqueue(new Callback<SendQuotetoUser_Response>() {


                @Override
                public void onResponse(Call<SendQuotetoUser_Response> call, Response<SendQuotetoUser_Response> response) {
                    if (response.isSuccessful()) {
                        SendQuotetoUser_Response lSendQuotetoUser_Response = response.body();
                    if(lSendQuotetoUser_Response.getRequest_partner_quote().equals("valid")){
                     Toast.makeText(getActivity(),"Quote Send Successfully",Toast.LENGTH_SHORT).show();
                    }

                        progress.dismiss();
                    }
                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<SendQuotetoUser_Response> call, Throwable t) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }catch (Exception e) {

            e.printStackTrace();
            progress.dismiss();

        }

    }

    @Override
    public void addquotationlist(ArrayList<String> finalmapingIdList, ArrayList<String> finalammountList,String StatusId) {
        gStatus_Id = StatusId;

        //sendQuote_toUser();

        StringBuilder gservicesId_builder = new StringBuilder();

        StringBuilder gservicesQuantity_builder = new StringBuilder();

        for(int i=0;i<finalmapingIdList.size();i++){

            gservicesId_builder.append(finalmapingIdList.get(i).toString()+",");
            gservicesQuantity_builder.append(finalammountList.get(i).toString()+",");

        }

        gFinalServicesMaping_Id = gservicesId_builder.toString();
        gFinalServices_Ammount = gservicesQuantity_builder.toString();

        if (gFinalServicesMaping_Id.endsWith(",")) {
            gFinalServicesMaping_Id = gFinalServicesMaping_Id.substring(0, gFinalServicesMaping_Id.length() - 1);
        }
        if (gFinalServices_Ammount.endsWith(",")) {
            gFinalServices_Ammount = gFinalServices_Ammount.substring(0, gFinalServices_Ammount.length() - 1);
        }

        System.out.println("Services Maping Id and Ammount is " + gFinalServicesMaping_Id  +  gFinalServices_Ammount + "and" + gStatus_Id);


    }
}
