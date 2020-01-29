package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.Fragment.MyTask_Fragment;
import service.com.surebot.info.serviceperson.Fragment.Profile_Fragment;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.PartnerApprovalStatus_Request;
import service.com.surebot.info.serviceperson.ResponseClass.PartnerApprovalStatus_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class CreateProfileActivity extends AppCompatActivity {

    @BindView(R.id.createprofile_button)
    Button gCreateprofile_button;

    @BindView(R.id.profileapproved_button)
    Button gProfileApproved_button;

    Fragment fragment = null;


    //For Getting Partner status
    ArrayList<PartnerApprovalStatus_Response.PartnerApprovalStatus> gGet_PartnerApprovalStatus;

    String gUserId_FromLogin;


    Dialog gConfirmation_Dialog;
    TextView laddressproofpendingtext ;
    TextView lbankdetailspendingtext;
    TextView ldocumentspendingtext;


    ImageView laddressproof_Image ;
    ImageView lbankdetails_Image ;
    ImageView ldocuments_Image ;

    Boolean gPartner_Status=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        ButterKnife.bind(this);

        gUserId_FromLogin=  AppicationClass.getUserId_FromLogin();



        gCreateprofile_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
             startActivity(new Intent(CreateProfileActivity.this,ServicesAdd_Activity.class));

                Intent userHomeIntent = new Intent(getApplicationContext(),ServicePersonHome_Activity.class);
                userHomeIntent.putExtra("HomeScreenFlow","fromcreateprofile");
                startActivity(userHomeIntent);
                finish();




            }
        });
    }  // On create close





}
