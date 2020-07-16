package service.com.surebot.info.serviceperson.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class CreateProfileActivity extends AppCompatActivity {

    @BindView(R.id.createprofile_button)
    Button gCreateprofile_button;

    @BindView(R.id.profileapproved_button)
    Button gProfileApproved_button;

    Fragment fragment = null;

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
             startActivity(new Intent(CreateProfileActivity.this, ServicesAdd_Activity.class));

                Intent userHomeIntent = new Intent(getApplicationContext(), ServicePersonHome_Activity.class);
                userHomeIntent.putExtra("HomeScreenFlow","fromcreateprofile");
                startActivity(userHomeIntent);
                finish();




            }
        });
    }  // On create close





}
