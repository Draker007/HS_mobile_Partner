package service.com.surebot.info.serviceperson.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.ApplicationClass;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.Utils;

public class HelpCenterActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.arrowIV)
    ImageView arrowIV;
    @BindView(R.id.headerRL)
    RelativeLayout headerRL;
    @BindView(R.id.customerSupportCV)
    CardView customerSupportCV;
    @BindView(R.id.termsOfUseCV)
    CardView termsOfUseCV;
    @BindView(R.id.faqCV)
    CardView faqCV;
    private HelpCenterActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        ButterKnife.bind(this);
        context = HelpCenterActivity.this;
        arrowIV.setOnClickListener(this);

        int categorySelection = ApplicationClass.getCategorySelection();
        int headerBackground = R.drawable.electrician_header_background;

        switch (categorySelection) {
            case 1:
            case 2: {
                headerBackground = R.drawable.salon_header_bg;
            }
            break;
            case 3: {
                headerBackground = R.drawable.electrician_header_background;
            }
            break;
            case 4: {
                headerBackground = R.drawable.plumber_header_bg;
            }
            break;
            case 5: {
                headerBackground = R.drawable.carpenter_header_bg;
            }
            break;
            case 6: {
                headerBackground = R.drawable.cleaning_header_bg;
            }
            break;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            headerRL.setBackground(ContextCompat.getDrawable(context, headerBackground));
        }

        customerSupportCV.setOnClickListener(this);
        termsOfUseCV.setOnClickListener(this);
        faqCV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.arrowIV: {
                finish();
            }
            break;
            case R.id.customerSupportCV: {
                Utils.startIntent(context, CustomerSupportActivity.class, false);
            }
            break;
            case R.id.termsOfUseCV: {
                Utils.startIntent(context, TermsActivity.class, false);
            }
            break;
            case R.id.faqCV: {
                Utils.startIntent(context, FAQActivity.class, false);
            }
            break;
        }
    }
}
