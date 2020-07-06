package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import service.com.surebot.info.serviceperson.Adapter.AboutServiceAdapter;
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.About_me_Request;
import service.com.surebot.info.serviceperson.RequestClass.AddAboutme_Request;
import service.com.surebot.info.serviceperson.ResponseClass.About_me_Response;
import service.com.surebot.info.serviceperson.ResponseClass.AddAboutme_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class AboutMeActivity extends AppCompatActivity {

    @BindView(R.id.boutMeIntro)
    EditText gIntoroduction_text;


    @BindView(R.id.BoutmeExper)
    EditText gExperience_Text;

    @BindView(R.id.aboutBack)
    ConstraintLayout back;

    @BindView(R.id.aboutServiceRecyler)
    RecyclerView gaboutServiceRecyler;

    private Dialog progress;

    @BindView(R.id.save_button)
    Button gSave_button;
    ArrayList<About_me_Response.About_me_Records> about_me_Response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        ButterKnife.bind(this);
        progress = new Dialog(this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);
    LinearLayoutManager lm = new LinearLayoutManager(this);
        gaboutServiceRecyler.setLayoutManager(lm);
        AboutServiceAdapter adapter = new AboutServiceAdapter(this);
        gaboutServiceRecyler.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        gSave_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!gIntoroduction_text.getText().toString().trim().equals("")){

                    if( !gExperience_Text.getText().toString().trim().equals("")){


                    }

                    else{
                        gExperience_Text.setError("Enter Experience");
                        gExperience_Text.requestFocus();
                    }
                }

                else{
                    gIntoroduction_text.setError("Enter Introduction");
                    gIntoroduction_text.requestFocus();
                }
            }
        });


    }

    //Add About me  Details


    //Get About me Details

}
