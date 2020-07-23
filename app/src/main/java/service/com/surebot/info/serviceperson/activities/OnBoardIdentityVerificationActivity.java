package service.com.surebot.info.serviceperson.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.Utils;

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

    @BindView(R.id.updateBtn)
    Button updateBtn;
    @BindView(R.id.arrowBack)
    ImageView arrowBack;

    String identityType;
    private OnBoardIdentityVerificationActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_identity_verification);
        final String[] str = {"Adhaar Card", "PAN Card", "Driving Licence"};
        ButterKnife.bind(this);
        context = OnBoardIdentityVerificationActivity.this;

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, str);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gIdentitySpinner.setAdapter(adp1);
        identityType = "Adhaar Card";
        gIdentitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                identityType = str[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        updateBtn.setOnClickListener(v -> Utils.startIntent(context, BuyAPackageActivity.class, false));
        arrowBack.setOnClickListener(v -> {
            finish();
        });
    }
}
