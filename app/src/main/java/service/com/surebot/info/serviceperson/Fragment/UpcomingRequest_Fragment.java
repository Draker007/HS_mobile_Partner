package service.com.surebot.info.serviceperson.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

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
import service.com.surebot.info.serviceperson.Adapter.UpcomingRequest_Adapter;
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.UpcomingRequestList_Request;
import service.com.surebot.info.serviceperson.ResponseClass.UpcomingRequestList_Response;

public class UpcomingRequest_Fragment  extends Fragment {


    @BindView(R.id.newrequestlist_recyclerview)
    RecyclerView gNewrequestlist_recyclerview;
    @BindView(R.id.norequest_text)
    TextView gNorequest_text;


    LinearLayoutManager llm;
    ArrayList<String> gUserName_List;

    private Dialog progress;
    ArrayList<UpcomingRequestList_Response.UpcomingRequestList_Records> gUpcomingRequestList_Arraylist;
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
        get_UpcomingServiceRequestList();

        return view;
    }

    //Get Upcomiing Request List

    private void get_UpcomingServiceRequestList()  {
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
            UpcomingRequestList_Request lNewRequestList_Request = new UpcomingRequestList_Request();
            lNewRequestList_Request.setUser_ID("12");

            lNewRequestList_Request.setDocket(Constants.TOKEN);

            Call<UpcomingRequestList_Response> call = request.get_UpcomingServiceRequestList(lNewRequestList_Request);
            call.enqueue(new Callback<UpcomingRequestList_Response>() {


                @Override
                public void onResponse(Call<UpcomingRequestList_Response> call, Response<UpcomingRequestList_Response> response) {
                    if (response.isSuccessful()) {
                        UpcomingRequestList_Response lUpcomingRequestList_Response = response.body();

                        gUpcomingRequestList_Arraylist = new ArrayList<>(Arrays.asList(lUpcomingRequestList_Response.getPartner_requests_upcoming()));
                        UpcomingRequest_Adapter lUpcomingRequest_Adapter = new UpcomingRequest_Adapter(getActivity(),gUpcomingRequestList_Arraylist);
                        gNewrequestlist_recyclerview.setAdapter(lUpcomingRequest_Adapter);
                        progress.dismiss();
                    }
                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<UpcomingRequestList_Response> call, Throwable t) {
                    progress.dismiss();
                }
            });
        }catch (Exception e) {

            e.printStackTrace();
            progress.dismiss();

        }

    }

}
