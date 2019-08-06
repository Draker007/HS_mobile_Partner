package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.Adapter.SignupProfessional_Adapter;
import service.com.surebot.info.serviceperson.R;

public class SignUp_Activity extends AppCompatActivity {


    @BindView(R.id.spinner_professionselection)
    Spinner gSpinner_professionselection;

    SignupProfessional_Adapter gSignupFlow_SpinnerAdapter;
    ArrayList<String> gsignupspinner_Arraylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        gsignupspinner_Arraylist = new ArrayList<>();
        gsignupspinner_Arraylist.add("Saloon at home for women");
        gsignupspinner_Arraylist.add("Saloon at home for men");
        gsignupspinner_Arraylist.add("Electrician");
        gsignupspinner_Arraylist.add("Plumber");
        gsignupspinner_Arraylist.add("Cleaning");
        gsignupspinner_Arraylist.add("Carpenter");

      /*  gSignupFlow_SpinnerAdapter = new SignupProfessional_Adapter(SignUp_Activity.this,R.layout.professionselection_layout,
                gsignupspinner_Arraylist);

        gSpinner_professionselection.setAdapter(gSignupFlow_SpinnerAdapter);*/

        String[] spinnerValues = { "Blur", "NFS", "Burnout","GTA IV", "Racing", };


        Spinner mySpinner = (Spinner) findViewById(R.id.spinner_professionselection);
        mySpinner.setAdapter(new SignupProfessional_Adapter(this, R.layout.professionselection_layout, gsignupspinner_Arraylist));




    }
}
