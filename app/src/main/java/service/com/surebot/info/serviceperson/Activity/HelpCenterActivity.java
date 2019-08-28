package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

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
import service.com.surebot.info.serviceperson.Adapter.faqAdapter;
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.ListofFaqs_Request;
import service.com.surebot.info.serviceperson.ResponseClass.ListofFaqs_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class HelpCenterActivity extends AppCompatActivity {

    @BindView(R.id.HelpcenterfaqRecycler)
    RecyclerView recyclerView;

    @BindView(R.id.CustomerSupportbtn)
    Button CustomerSupport;

    @BindView(R.id.TermOfUseBTN)
            Button Termofuse;

    faqAdapter adapter;

    private Dialog progress;


    ArrayList<ListofFaqs_Response.ListofFaqs_Records> gListofFaqs_Arraylist;

    LinearLayoutManager llm;

    String gUserId_FromLogin;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        ButterKnife.bind(this);

        progress = new Dialog(HelpCenterActivity.this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);

        gUserId_FromLogin = AppicationClass.getUserId_FromLogin();



        llm = new LinearLayoutManager(HelpCenterActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


        //recyclerView.setAdapter(adapter);
       /* ArrayList<String> gFAQquestionList = new ArrayList<>();
        ArrayList<String> gFAQanswerList = new ArrayList<>();
        gFAQquestionList.add("asdasdasdasdsadasdasdsad");

        gFAQanswerList.add("dddedqqwdqwdaasdgfsadbaskdbah asjsshdg assmdgjasbv djalsgg a kahsdjhhaj, kshdfkasdf hflkhs dfasdf ahsdf asd.1");
        gFAQquestionList.add("asdasdasdasdsadasdasdsad");
        gFAQanswerList.add("dddedqqwdqwdaasdgfsadbaskdbah asjsshdg assmdgjasbv djalsgg a kahsdjhhaj, kshdfkasdf hflkhs dfasdf ahsdf asd.2");
        gFAQquestionList.add("asdasdasdasdsadasdasdsad");
        gFAQanswerList.add("dddedqqwdqwdaasdgfsadbaskdbah asjsshdg assmdgjasbv djalsgg a kahsdjhhaj, kshdfkasdf hflkhs dfasdf ahsdf asd.3");
        gFAQquestionList.add("asdasdasdasdsadasdasdsad");
        gFAQanswerList.add("dddedqqwdqwdaasdgfsadbaskdbah asjsshdg assmdgjasbv djalsgg a kahsdjhhaj, kshdfkasdf hflkhs dfasdf ahsdf asd.4");
        gFAQquestionList.add("asdasdasdasdsadasdasdsad");
        gFAQanswerList.add("dddedqqwdqwdaasdgfsadbaskdbah asjsshdg assmdgjasbv djalsgg a kahsdjhhaj, kshdfkasdf hflkhs dfasdf ahsdf asd.5");
        gFAQquestionList.add("asdasdasdasdsadasdasdsad");
        gFAQanswerList.add("dddedqqwdqwdaasdgfsadbaskdbah asjsshdg assmdgjasbv djalsgg a kahsdjhhaj, kshdfkasdf hflkhs dfasdf ahsdf asd.6");
        gFAQquestionList.add("asdasdasdasdsadasdasdsad");
        gFAQanswerList.add("dddedqqwdqwdaasdgfsadbaskdbah asjsshdg assmdgjasbv djalsgg a kahsdjhhaj, kshdfkasdf hflkhs dfasdf ahsdf asd.7");
        adapter = new faqAdapter(this,gFAQquestionList,gFAQanswerList);
        recyclerView.setAdapter(adapter);*/

        CustomerSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HelpCenterActivity.this,CustomerSupportActivity.class));
            }
        });
        Termofuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HelpCenterActivity.this,Terms.class));
            }
        });
        get_FaqList();

    } //On create Close


    //Get List Of Faqs

    private void get_FaqList()  {
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
            ListofFaqs_Request lListofFaqs_Request = new ListofFaqs_Request();
            lListofFaqs_Request.setUser_ID("1");
            lListofFaqs_Request.setDocket(Constants.TOKEN);

            Call<ListofFaqs_Response> call = request.get_FaqList(lListofFaqs_Request);
            call.enqueue(new Callback<ListofFaqs_Response>() {


                @Override
                public void onResponse(Call<ListofFaqs_Response> call, Response<ListofFaqs_Response> response) {
                    if (response.isSuccessful()) {
                        ListofFaqs_Response lListofFaqs_Response = response.body();

                        gListofFaqs_Arraylist = new ArrayList<>(Arrays.asList(lListofFaqs_Response.getFaqs_response()));

                        System.out.println("Faq Array Size is " + gListofFaqs_Arraylist.size());
                        adapter = new faqAdapter(HelpCenterActivity.this,gListofFaqs_Arraylist);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setHasFixedSize(true);

                        progress.dismiss();
                    }
                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<ListofFaqs_Response> call, Throwable t) {
                    Toast.makeText(HelpCenterActivity.this, getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }catch (Exception e) {

            e.printStackTrace();
            progress.dismiss();

        }

    }
}
