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
import service.com.surebot.info.serviceperson.ResponseClass.Account_details_Response;
import service.com.surebot.info.serviceperson.ResponseClass.NewRequestList_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class NewRequest_Fragment  extends Fragment {


    @BindView(R.id.newrequestlist_recyclerview)
    RecyclerView gNewrequestlist_recyclerview;
    @BindView(R.id.norequest_text)
    TextView gNorequest_text;

    LinearLayoutManager llm;


    ArrayList<String> gUserName_List;

    private Dialog progress;

    ArrayList<NewRequestList_Response.NewRequestList_Response_Records> gNewRequestList_Arraylist;


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



        gUserName_List = new ArrayList<String>();
        gUserName_List.add("Aditi");
        gUserName_List.add("Sahana");
        gUserName_List.add("Aditi");


        llm = new LinearLayoutManager(getActivity());
         llm.setOrientation(LinearLayoutManager.VERTICAL);
        gNewrequestlist_recyclerview.setLayoutManager(llm);


       /* NewRequests_Adapter lTodaysTask_Adapter = new NewRequests_Adapter(getActivity(),gUserName_List);
        gNewrequestlist_recyclerview.setAdapter(lTodaysTask_Adapter);*/

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
            lNewRequestList_Request.setUser_ID("11");

            lNewRequestList_Request.setDocket(Constants.TOKEN);

            Call<NewRequestList_Response> call = request.get_NewServiceRequestList(lNewRequestList_Request);
            call.enqueue(new Callback<NewRequestList_Response>() {


                @Override
                public void onResponse(Call<NewRequestList_Response> call, Response<NewRequestList_Response> response) {
                    if (response.isSuccessful()) {
                        NewRequestList_Response lNewRequestList_Response = response.body();

                        gNewRequestList_Arraylist = new ArrayList<>(Arrays.asList(lNewRequestList_Response.getPartner_my_task_details_response()));
                        NewRequests_Adapter lNewRequests_Adapter = new NewRequests_Adapter(getActivity(),gNewRequestList_Arraylist);
                        gNewrequestlist_recyclerview.setAdapter(lNewRequests_Adapter);
                        progress.dismiss();
                    }
                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<NewRequestList_Response> call, Throwable t) {
                    progress.dismiss();
                }
            });
        }catch (Exception e) {

            e.printStackTrace();
            progress.dismiss();

        }

    }

}
