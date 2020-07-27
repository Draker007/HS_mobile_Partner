package service.com.surebot.info.serviceperson.activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;


import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.ApplicationClass;
import service.com.surebot.info.serviceperson.R;

public class IdentityVerificationActivity extends BaseActivity {

    @BindView(R.id.arrowIV)
    ImageView arrowIV;
    @BindView(R.id.borderLL)
    LinearLayout borderLL;
    private IdentityVerificationActivity context;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_verification);
        ButterKnife.bind(this);
        context = IdentityVerificationActivity.this;

        int categorySelection = ApplicationClass.getCategorySelection();

        switch (categorySelection) {
            case 1:
            case 2: {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    borderLL.setBackgroundTintList(context.getColorStateList(R.color.colorPrimaryBlue));
                }
            }
            break;
            case 3: {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    borderLL.setBackgroundTintList(context.getColorStateList(R.color.colorElectricianText));
                }
            }
            break;
        }



        arrowIV.setOnClickListener(v -> {
            finish();
        });

    }

}
