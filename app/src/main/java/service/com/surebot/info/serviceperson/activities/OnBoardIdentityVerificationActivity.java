package service.com.surebot.info.serviceperson.activities;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.ApplicationClass;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.Utils;

public class OnBoardIdentityVerificationActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.headerIdentityCL)
    ConstraintLayout headerIdentityCL;
    @BindView(R.id.headerIdentityFadeCL)
    ConstraintLayout headerIdentityFadeCL;
    @BindView(R.id.nameTL)
    TextInputLayout nameTL;
    @BindView(R.id.idNumberTL)
    TextInputLayout idNumberTL;
    @BindView(R.id.selectIdProofTV)
    TextView selectIdProofTV;
    @BindView(R.id.currentAddressTV)
    TextView currentAddressTV;
    @BindView(R.id.typeOfIdTV)
    TextView typeOfIdTV;

    String identityType;
    private OnBoardIdentityVerificationActivity context;
    final String[] str = {"Adhaar Card", "PAN Card", "Driving Licence"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_identity_verification);
        ButterKnife.bind(this);
        context = OnBoardIdentityVerificationActivity.this;

        spinnerIdentityProofs();
        updateBtn.setOnClickListener(this);
        arrowBack.setOnClickListener(this);

        int categorySelection = ApplicationClass.getCategorySelection();
        int headerProfile = R.drawable.salon_onboard_women;
        int headerProfileFade = R.mipmap.saloonfade;

        switch (categorySelection) {
            case 1: {
                headerProfile = R.drawable.salon_onboard_women;
                headerProfileFade = R.mipmap.saloonfade;
            }
            break;
            case 2: {
                headerProfile = R.drawable.salon_onboard_men;
                headerProfileFade = R.mipmap.saloonfade;
            }
            break;
            case 3: {
                headerProfile = R.drawable.onboard_electrician;
                headerProfileFade = R.mipmap.electrician_fade;
            }
            break;
            case 4: {
                headerProfile = R.drawable.onboard_plumber;
                headerProfileFade = R.mipmap.plumber_fade;
            }
            break;
        }

        headerIdentityCL.setBackground(ContextCompat.getDrawable(context, headerProfile));
        headerIdentityFadeCL.setBackground(ContextCompat.getDrawable(context, headerProfileFade));

    }

    private void spinnerIdentityProofs() {
        ArrayAdapter<String> adp1 = new ArrayAdapter<>(this,
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.updateBtn: {
                Utils.startIntent(context, BuyAPackageActivity.class, false);
            }
            break;
            case R.id.arrowBack: {
                finish();
            }
            break;
        }
    }
}
