package service.com.surebot.info.serviceperson.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.ApplicationClass;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.Utils;

public class BuyAPackageActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.arrowBack)
    ImageView arrowBack;
    @BindView(R.id.buyAPackageBtn)
    Button buyAPackageBtn;
    @BindView(R.id.advantagesTV)
    TextView advantagesTV;
    private BuyAPackageActivity context;
    @BindView(R.id.headerBuyPackageCL)
    ConstraintLayout headerBuyPackageCL;
    @BindView(R.id.headerBuyPackageFadeCL)
    ConstraintLayout headerBuyPackageFadeCL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_a_package);
        ButterKnife.bind(this);
        context = BuyAPackageActivity.this;

        arrowBack.setOnClickListener(this);
        buyAPackageBtn.setOnClickListener(this);

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
            case 5: {
                headerProfile = R.drawable.onboard_carpenter;
                headerProfileFade = R.mipmap.carpenter_fade;
            }
            break;
            case 6: {
                headerProfile = R.drawable.onboard_cleaning;
                headerProfileFade = R.mipmap.cleaning_fade;
            }
            break;
        }

        headerBuyPackageCL.setBackground(ContextCompat.getDrawable(context, headerProfile));
        headerBuyPackageFadeCL.setBackground(ContextCompat.getDrawable(context, headerProfileFade));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.arrowBack: {
                finish();
            }
            break;
            case R.id.buyAPackageBtn: {
                Utils.startIntent(context, PrimePackageActivity.class, false);
            }
            break;
        }
    }


}