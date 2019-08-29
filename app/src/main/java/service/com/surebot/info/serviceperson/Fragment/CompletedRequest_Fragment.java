package service.com.surebot.info.serviceperson.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
import service.com.surebot.info.serviceperson.Adapter.CompletedRequest_Adapter;
import service.com.surebot.info.serviceperson.Adapter.UpcomingRequest_Adapter;
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.UpcomingRequestList_Request;
import service.com.surebot.info.serviceperson.ResponseClass.CompletedRequestList_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class CompletedRequest_Fragment  extends Fragment {

    @BindView(R.id.newrequestlist_recyclerview)
    RecyclerView gCompletedrequestlist_recyclerview;
    @BindView(R.id.norequest_text)
    TextView gNorequest_text;


    LinearLayoutManager llm;
String gUserId_FromLogin;

    ArrayList<CompletedRequestList_Response.CompletedRequestList_Records> gCompeltedRequestList_Arraylist;
    private Dialog progress;
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
        gCompletedrequestlist_recyclerview.setLayoutManager(llm);


        get_CompletedServiceRequestList();

        return view;
    }


    private void get_CompletedServiceRequestList()  {
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

            Call<CompletedRequestList_Response> call = request.get_CompletedServiceRequestList(lNewRequestList_Request);
            call.enqueue(new Callback<CompletedRequestList_Response>() {


                @Override
                public void onResponse(Call<CompletedRequestList_Response> call, Response<CompletedRequestList_Response> response) {
                    if (response.isSuccessful()) {
                        CompletedRequestList_Response lCompletedRequestList_Response = response.body();

                        gCompeltedRequestList_Arraylist = new ArrayList<>(Arrays.asList(lCompletedRequestList_Response.getPartner_requests_upcoming()));

                       if(!gCompeltedRequestList_Arraylist.get(0).getUser_ID().equals("No Results Found")){
                           gNorequest_text.setVisibility(View.GONE);
                           gCompletedrequestlist_recyclerview.setVisibility(View.VISIBLE);
                           CompletedRequest_Adapter lCompletedRequest_Adapter = new CompletedRequest_Adapter(getActivity(),gCompeltedRequestList_Arraylist);
                           gCompletedrequestlist_recyclerview.setAdapter(lCompletedRequest_Adapter);
                       }

                       else {
                           gNorequest_text.setVisibility(View.VISIBLE);
                           gCompletedrequestlist_recyclerview.setVisibility(View.GONE);
                       }

                        progress.dismiss();
                    }
                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<CompletedRequestList_Response> call, Throwable t) {

                    Toast.makeText(getActivity(), getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }catch (Exception e) {

            e.printStackTrace();
            progress.dismiss();

        }

    }

}
