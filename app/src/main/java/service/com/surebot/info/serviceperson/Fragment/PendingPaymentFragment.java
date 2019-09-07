package service.com.surebot.info.serviceperson.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.com.surebot.info.serviceperson.Adapter.paymentParentAdapter;
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.DataFiles.paymentChildData;
import service.com.surebot.info.serviceperson.DataFiles.paymentParentData;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.Partner_payment_Request;
import service.com.surebot.info.serviceperson.ResponseClass.Partner_payment_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Payment_completed_transaction_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class PendingPaymentFragment extends Fragment {
    RecyclerView r1;
    paymentParentAdapter adapter;
    View view;
    String TAG = "payment";
    private Dialog progress;
    TextView paymentText;
    List<Partner_payment_Response.Complete_transaction_record> Payment_details = new ArrayList<>();
    List<paymentChildData> paymentChildDataList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.payment_fragment, container, false);
        r1 = view.findViewById(R.id.paymentRecycler);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
        paymentText = view.findViewById(R.id.paymentText);
        //adapter = new paymentParentAdapter( paymentParentDataList);

        adapter = new paymentParentAdapter(Payment_details,getActivity());

        r1.setLayoutManager(lm);
        progress = new Dialog(getActivity(), android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);
        //HERE calling API for Completed Payment
        completedPaymentAPI();

        return view;
    }


    //Completed Payment API
    private void completedPaymentAPI() {
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
            System.out.println("In User Login Method 2");
            ApiInterface request = retrofit.create(ApiInterface.class);
            Partner_payment_Request lservice_request = new Partner_payment_Request();


            lservice_request.setUser_ID(AppicationClass.getUserId_FromLogin());
            lservice_request.setDocket(Constants.TOKEN);
            Log.e(TAG, "completedPaymentAPI: ");

            Call<Partner_payment_Response> call = request.PendingPayment(lservice_request);
            call.enqueue(new Callback<Partner_payment_Response>() {
                @Override
                public void onResponse(Call<Partner_payment_Response> call, Response<Partner_payment_Response> response) {
                    if (response.isSuccessful()) {

                        Partner_payment_Response aboutme_response = response.body();
                        ArrayList<Partner_payment_Response.Complete_transaction_record> pendingTrans = new ArrayList<>(Arrays.asList(aboutme_response.getPending_transaction_response()));
                        Log.e(TAG, "onResponse: "+pendingTrans.get(0).getTransaction_ID());

                        if (!pendingTrans.get(0).getTransaction_ID().equals("No Results Found")) {
                            r1.setVisibility(View.VISIBLE);
                            paymentText.setVisibility(View.GONE);
                            ArrayList<Partner_payment_Response.Complete_transaction_record> PendingPaymentResponse = new ArrayList<>(Arrays.asList(aboutme_response.getPending_transaction_response()));
                            adapter = new paymentParentAdapter(PendingPaymentResponse, getActivity());
                            r1.setAdapter(adapter);
                        }
                        else {
                            paymentText.setText("No Payment is Pending..");
                            r1.setVisibility(View.GONE);
                        }

                    }


                    progress.dismiss();
                }



                @Override
                public void onFailure(Call<Partner_payment_Response> call, Throwable t) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
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
}
