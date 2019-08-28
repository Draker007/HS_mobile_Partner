package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
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
import service.com.surebot.info.serviceperson.Adapter.SignupProfessional_Adapter;
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.Manager.CacheManager;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.Category_List_Request;
import service.com.surebot.info.serviceperson.RequestClass.PartnerSigup_Request;
import service.com.surebot.info.serviceperson.ResponseClass.Category_List_Response;
import service.com.surebot.info.serviceperson.ResponseClass.PartnerSignup_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;
import service.com.surebot.info.serviceperson.utils.NetworkUtil;

public class SignUp_Activity extends AppCompatActivity {

    @BindView(R.id.username_text)
    EditText gUserName_Text;

    private Dialog progress;

    @BindView(R.id.profession_text)
    EditText gProfession_text;

    @BindView(R.id.gender_radiogroup)
    RadioGroup gGender_Radiogroup;
    @BindView(R.id.radiomale)
    RadioButton gGender_Male;
    @BindView(R.id.radiofemale)
    RadioButton gGender_FeMale;

    @BindView(R.id.emailid_text)
    EditText gUserEmail_text;

    @BindView(R.id.phoneno_text)
    EditText gUserPhoneNumber_text;

    @BindView(R.id.password_text)
    EditText gUserPassword_text;

    @BindView(R.id.alreadyaccount_text)
    TextView gAlreadyaccount_text;

    ArrayList<Category_List_Response.Category_List_Records> categoryList_Arraylist;


    @BindView(R.id.confirmPassword_text)
    EditText gUserConfirmPassword_text;

    @BindView(R.id.signup_button)
    AppCompatButton gSignup_button;


    private RadioButton gRadioGenderButton;
    String MobilePattern = "[0-9]{8,13}";
    String EmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    String network;
    AlertDialog.Builder builder;


    SharedPreferences.Editor editer;
    SharedPreferences gSharedPrefrance;


    CacheManager cacheManager;


    @BindView(R.id.spinner_professionselection)
    Spinner gSpinner_professionselection;

    SignupProfessional_Adapter gSignupFlow_SpinnerAdapter;
    ArrayList<String> gsignupspinner_Arraylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        cacheManager = new CacheManager(this);



        get_CategoryList();

        // here checking for fioled details

        progress = new Dialog(SignUp_Activity.this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);
        //SignUp Button CLickable
   gUserPassword_text.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           gProfession_text.setText(categoryList_Arraylist.get(gSpinner_professionselection.getSelectedItemPosition()).getCategory_Name());
           gProfession_text.setEnabled(false);

       }
   });
        gSignup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("cattegoruID", "user_SignUp: "+categoryList_Arraylist.get(gSpinner_professionselection.getSelectedItemPosition()).getCategory_ID()) ;

                int selectedId = gGender_Radiogroup.getCheckedRadioButtonId();
                gRadioGenderButton = findViewById(selectedId);

                if (!gUserName_Text.getText().toString().trim().equals("")) {
                    if (!gUserPhoneNumber_text.getText().toString().trim().equals("")) {
                        if (gUserPhoneNumber_text.getText().toString().trim().matches(MobilePattern)) {
                            if (!gUserEmail_text.getText().toString().trim().equals("")) {
                                if (gUserEmail_text.getText().toString().trim().matches(EmailPattern)) {
                                    if (selectedId != -1) {
                                        if (!gUserPassword_text.getText().toString().trim().equals("")) {
                                            if (gUserPassword_text.getText().length() >= 5) {
                                                Log.e("lol", "onClick: " + "hihi");
                                                if (!gUserConfirmPassword_text.getText().toString().trim().equals("")) {
                                                    if (gUserPassword_text.getText().toString().trim().equals(gUserConfirmPassword_text.getText().toString().trim())) {

                                                        network = NetworkUtil.getConnectivityStatusString(SignUp_Activity.this);
                                                        if (network == "No internet is available") {

                                                            networkChecking();


                                                        } else {
                                                            user_SignUp();

                                                        }

                                                    } else {
                                                        gUserConfirmPassword_text.setError("Password and confirm password does not match");
                                                        gUserConfirmPassword_text.requestFocus();
                                                    }

                                                } else {
                                                    gUserConfirmPassword_text.setError("Enter Confirm Password");
                                                    gUserConfirmPassword_text.requestFocus();
                                                }
                                            } else {
                                                gUserPassword_text.setError("should contain atleast 5 character");
                                                gUserPassword_text.requestFocus();
                                            }
                                        }else {
                                            gUserPassword_text.setError("Enter Password");
                                            gUserPassword_text.requestFocus();
                                        }


                                    } else {
                                        Toast.makeText(SignUp_Activity.this, "Select Gender", Toast.LENGTH_SHORT).show();
                                        gGender_Radiogroup.requestFocus();
                                    }
                                } else {
                                    gUserEmail_text.setError("Please Enter valid Email id");
                                    gUserEmail_text.requestFocus();
                                }

                            } else {
                                gUserEmail_text.setError("Enter Email");
                                gUserEmail_text.requestFocus();
                            }


                        } else {
                            gUserPhoneNumber_text.setError("Enter Valid Phone Number");
                            gUserPhoneNumber_text.requestFocus();
                        }

                    } else {
                        gUserPhoneNumber_text.setError("Enter Phone Number");
                        gUserPhoneNumber_text.requestFocus();

                    }


                }


        else{
                    gUserName_Text.setError("Enter UserName");
                    gUserName_Text.requestFocus();
                }

            }
        });

    }




    // calling API for SignUP
    private void user_SignUp() {

        try {
            Log.e("cattegoruID", "user_SignUp: "+categoryList_Arraylist.get(gSpinner_professionselection.getSelectedItemPosition()).getCategory_ID()) ;
         //   categoryList_Arraylist.get(gSpinner_professionselection.getSelectedItemPosition()).getCategory_ID();
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
            PartnerSigup_Request partnerLandingPage = new PartnerSigup_Request();
//            // lbannerimage_request.getUserLandingRequest().setUserid("1");
            partnerLandingPage.setDocket(Constants.TOKEN);
            partnerLandingPage.setCategory_ID(categoryList_Arraylist.get(gSpinner_professionselection.getSelectedItemPosition()).getCategory_ID());
            partnerLandingPage.setUser_Contact_Number(gUserPhoneNumber_text.getText().toString().trim());
            partnerLandingPage.setUser_Email(gUserEmail_text.getText().toString().trim());
            partnerLandingPage.setUser_Gender(gRadioGenderButton.getText().toString().trim());
            partnerLandingPage.setUser_Password(gUserPassword_text.getText().toString().trim());
            partnerLandingPage.setUser_Name(gUserName_Text.getText().toString().trim());
            Log.e("lol", "user_SignUp: "+ partnerLandingPage.getCategory_ID()+partnerLandingPage.getDocket()+partnerLandingPage.getUser_Contact_Number()+partnerLandingPage.getUser_Email()+partnerLandingPage.getUser_Gender()+partnerLandingPage.getUser_Password() );
            Call<PartnerSignup_Response> call = request.partnerSignup(partnerLandingPage);
            call.enqueue(new Callback<PartnerSignup_Response>() {
                @Override
                public void onResponse(Call<PartnerSignup_Response> call, Response<PartnerSignup_Response> response) {
                    if (response.isSuccessful()) {

                        gSharedPrefrance = getSharedPreferences("User_Info", 0);
                        editer = gSharedPrefrance.edit();

                        PartnerSignup_Response partnerUserDetails= response.body();
                        if (partnerUserDetails.getRegister_response().equals("valid")){
                            if(partnerUserDetails.getUser_Role().equals("2")) {
                                AppicationClass.setUserId_FromLogin(partnerUserDetails.getUser_ID());
                                AppicationClass.setUserName_FromLogin(partnerUserDetails.getUser_Name());


                                editer.putString("User_Id", partnerUserDetails.getUser_ID());
                                editer.putString("User_Name", partnerUserDetails.getUser_Name());
                                editer.commit();

                                cacheManager.setFirstTimeLaunch(false);
                                cacheManager.setUserName(partnerUserDetails.getUser_Name());
                                cacheManager.setUserRole(partnerUserDetails.getUser_Role());
                                cacheManager.setUserId(partnerUserDetails.getUser_ID());

                                startActivity(new Intent(SignUp_Activity.this, CreateProfileActivity.class));
                                finish();
                            }

                        }
                        else {

                            progress.dismiss();
                            Toast.makeText(SignUp_Activity.this, partnerUserDetails.getRegister_response(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }


                @Override
                public void onFailure(Call<PartnerSignup_Response> call, Throwable t) {
                    System.out.println("In User Login Method 7");
                    progress.dismiss();
                }
            });
        }catch (Exception e) {

            e.printStackTrace();
            progress.dismiss();



        }

    }

    public void networkChecking() {
            builder = new AlertDialog.Builder(this);
            //Uncomment the below code to Set the message and title from the strings.xml file
            builder.setMessage("Network not available").setTitle("Check your connection");

            //Setting message manually and performing action on button click
            builder.setMessage("No internet connection available.")
                    .setCancelable(false)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();

                        }
                    });
            //Creating dialog box
            AlertDialog alert = builder.create();
            //Setting the title manually
            alert.setTitle("Alert");
            alert.show();
        }


    //Get all Category List

    void get_CategoryList(){

        try {
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            HttpLoggingInterceptor registrationInterceptor = new HttpLoggingInterceptor();
            registrationInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(registrationInterceptor);
                System.out.println("Signup entering into 111111111");
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiInterface request = retrofit.create(ApiInterface.class);
            Category_List_Request partnerLandingPage = new Category_List_Request();

            partnerLandingPage.setDocket(Constants.TOKEN);
            System.out.println("Signup entering into 22222");
            Call<Category_List_Response> call = request.categoryList(partnerLandingPage);
            call.enqueue(new Callback<Category_List_Response>() {


                @Override
                public void onResponse(Call<Category_List_Response> call, Response<Category_List_Response> response) {
                    if (response.isSuccessful()) {
                        System.out.println("Signup entering into 3333333333");

                        Category_List_Response responseed = response.body();
                        categoryList_Arraylist=new ArrayList<>(Arrays.asList(responseed.getCategory_response()));
                        Log.e("lols", "onResponse: "+categoryList_Arraylist );
                        Spinner mySpinner = (Spinner) findViewById(R.id.spinner_professionselection);
                        mySpinner.setAdapter(new SignupProfessional_Adapter(SignUp_Activity.this, R.layout.professionselection_layout, categoryList_Arraylist));

                    }
                }


                @Override
                public void onFailure(Call<Category_List_Response> call, Throwable t) {
                    System.out.println("Signup entering into 444444");
                }
            });
        }catch (Exception e) {
            System.out.println("Signup entering into 55555");
            e.printStackTrace();


        }
    }

    }





