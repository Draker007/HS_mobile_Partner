package service.com.surebot.info.serviceperson.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
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
import service.com.surebot.info.serviceperson.Adapter.UpcomingRequest_Adapter;
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.UpcomingRequestList_Request;
import service.com.surebot.info.serviceperson.ResponseClass.UpcomingRequestList_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class UpcomingRequest_Fragment  extends Fragment {


    @BindView(R.id.newrequestlist_recyclerview)
    RecyclerView gUpcomingrequestlist_recyclerview;
    @BindView(R.id.norequest_text)
    TextView gNorequest_text;


    LinearLayoutManager llm;


    private Dialog progress;
    ArrayList<UpcomingRequestList_Response.UpcomingRequestList_Records> gUpcomingRequestList_Arraylist;
    String gUserId_FromLogin;

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

        gUserId_FromLogin = AppicationClass.getUserId_FromLogin();


        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        gUpcomingrequestlist_recyclerview.setLayoutManager(llm);
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
            lNewRequestList_Request.setUser_ID(gUserId_FromLogin);

            lNewRequestList_Request.setDocket(Constants.TOKEN);

            Call<UpcomingRequestList_Response> call = request.get_UpcomingServiceRequestList(lNewRequestList_Request);
            call.enqueue(new Callback<UpcomingRequestList_Response>() {


                @Override
                public void onResponse(Call<UpcomingRequestList_Response> call, Response<UpcomingRequestList_Response> response) {
                    if (response.isSuccessful()) {
                        UpcomingRequestList_Response lUpcomingRequestList_Response = response.body();

                        gUpcomingRequestList_Arraylist = new ArrayList<>(Arrays.asList(lUpcomingRequestList_Response.getPartner_requests_upcoming()));
                       if(!gUpcomingRequestList_Arraylist.get(0).getUser_ID().equals("No Results Found")){
                           gNorequest_text.setVisibility(View.GONE);
                           gUpcomingrequestlist_recyclerview.setVisibility(View.VISIBLE);
                           UpcomingRequest_Adapter lUpcomingRequest_Adapter = new UpcomingRequest_Adapter(getActivity(),gUpcomingRequestList_Arraylist);
                           gUpcomingrequestlist_recyclerview.setAdapter(lUpcomingRequest_Adapter);
                       }
                       else {
                           Log.e("lol1", "onResponse: was here" );
                           gNorequest_text.setVisibility(View.VISIBLE);
                           gUpcomingrequestlist_recyclerview.setVisibility(View.GONE);
                       }

                        progress.dismiss();
                    }
                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<UpcomingRequestList_Response> call, Throwable t) {
                    Toast.makeText(getActivity(), "hihi"+getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
                    Log.e("lol1", "onFailure: "+t );
                    progress.dismiss();
                }
            });
        }catch (Exception e) {

            e.printStackTrace();
            progress.dismiss();

        }

    }

}
