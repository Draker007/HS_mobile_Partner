package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import butterknife.BindView;
import service.com.surebot.info.serviceperson.R;

public class OnBoardIdentityVerificationActivity extends AppCompatActivity {

    @BindView(R.id.fullNameET)
    EditText gfullNameET;

    @BindView(R.id.identitySpinner)
    Spinner gIdentitySpinner;

    @BindView(R.id.idnumberET)
    EditText gidnumberET;

    @BindView(R.id.frontimg)
    ImageView gfrontimg;

    @BindView(R.id.backIMG)
    ImageView gbackIMG;

    @BindView(R.id.nextBTN)
    Button gnextBTN;
    String identityType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_identity_verification);
        final String[] str = {"Adhaar Card", "PAN Card", "Driving Licence"};

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, str);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gIdentitySpinner.setAdapter(adp1);
        identityType = "Adhaar Card";
        gIdentitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                identityType=str[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        gnextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
