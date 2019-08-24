package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParsePosition;

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
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.Manager.CacheManager;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.Partnerlogin_Request;
import service.com.surebot.info.serviceperson.ResponseClass.Partnerlogin_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;
import service.com.surebot.info.serviceperson.utils.NetworkUtil;

public class Login_Activity extends AppCompatActivity {


    @BindView(R.id.signup_text)
    TextView gSignup_text;

    @BindView(R.id.username_text)
    EditText gUserName_Text;

    @BindView(R.id.password_text)
    EditText gUserPassword_Text;

    @BindView(R.id.login_button)
    ImageView gLogin_button;

    String network;

    private CacheManager mCacheManager;

    SharedPreferences.Editor editer;
    SharedPreferences gSharedPrefrance;
    private Dialog progress;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mCacheManager = new CacheManager(this);
        progress = new Dialog(this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);
        gLogin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!gUserName_Text.getText().toString().trim().equals("")) {
                    if (!gUserPassword_Text.getText().toString().trim().equals("")) {

                        System.out.println("In User Login Method Login button clicked 1");
                        network = NetworkUtil.getConnectivityStatusString(Login_Activity.this);
                        System.out.print("network :" + network);
                        if (!network.equals("No internet is available")) {
                            System.out.println("In User Login Method Login button clicked 2");
                            servicePerson_Login();

                        } else {
                            networkChecking();
                        }

                    } else {
                        gUserPassword_Text.setError("Enter Password");
                        gUserPassword_Text.requestFocus();

                    }
                } else {

                    gUserName_Text.setError("Enter Email");
                    gUserName_Text.requestFocus();
                }

            }


        });

        gSignup_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_Activity.this, SignUp_Activity.class));
            }
        });

    }

    //Login
    private void servicePerson_Login() {
        try {

             progress.show();


            //progress.show();


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
            Partnerlogin_Request partnerLandingPage = new Partnerlogin_Request();
            partnerLandingPage.setEmail(gUserName_Text.getText().toString());
            partnerLandingPage.setPassword(gUserPassword_Text.getText().toString());
            partnerLandingPage.setDocket(Constants.TOKEN);

            Call<Partnerlogin_Response> call = request.partnerlogin(partnerLandingPage);
            call.enqueue(new Callback<Partnerlogin_Response>() {


                @Override
                public void onResponse(Call<Partnerlogin_Response> call, Response<Partnerlogin_Response> response) {
                    if (response.isSuccessful()) {
                    Partnerlogin_Response partnerlogin_response = response.body();
                        gSharedPrefrance = getSharedPreferences("User_Info", 0);
                        editer = gSharedPrefrance.edit();

                        System.out.println("In User Login Method 4"+partnerlogin_response.getPartner_login_response()+" "+partnerlogin_response.getUser_Role());
                        if(partnerlogin_response.getPartner_login_response().equals("valid")) {
                            System.out.println("In User Login Method 5");
                            if (partnerlogin_response.getUser_Role().equals("2")) {
                                System.out.println("In User Login Method 6");

                                AppicationClass.setUserId_FromLogin(partnerlogin_response.getUser_ID());
                                AppicationClass.setUserName_FromLogin(partnerlogin_response.getUserName());

                                editer.putString("User_Id", partnerlogin_response.getUser_ID());
                                editer.putString("User_Name", partnerlogin_response.getUserName());
                                editer.commit();

                                startActivity(new Intent(Login_Activity.this, ServicesAdd_Activity.class));
                            }
                        } else{


                            Toast.makeText(Login_Activity.this,partnerlogin_response.getPartner_login_response(), Toast.LENGTH_SHORT).show();
                        }
                        progress.dismiss();
                    }}

                @Override
                public void onFailure(Call<Partnerlogin_Response> call, Throwable t) {
                    System.out.println("In User Login Method 7");
                }
            });
        }catch (Exception e) {

            e.printStackTrace();


        }

    }

    public static boolean isNumeric(String str) {
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        System.out.println("Numberic values in login is" + pos.getIndex());
        return str.length() == pos.getIndex();
    }

    public void networkChecking() {
        builder = new AlertDialog.Builder(Login_Activity.this);
        //Uncomment the below code to Set the message and title from the strings.xml file
        builder.setMessage("Network not available").setTitle("Check your connection");
        final AlertDialog alert1 = builder.create();
        //Setting message manually and performing action on button click
        builder.setMessage("No internet connection available.")
                .setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        alert1.dismiss();


                    }
                });


    }


}
