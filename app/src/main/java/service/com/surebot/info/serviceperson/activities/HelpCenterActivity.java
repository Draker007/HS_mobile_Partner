package service.com.surebot.info.serviceperson.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.Utils;

public class HelpCenterActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.arrowIV)
    ImageView arrowIV;
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
