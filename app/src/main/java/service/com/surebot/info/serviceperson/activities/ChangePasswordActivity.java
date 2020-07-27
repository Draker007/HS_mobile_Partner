package service.com.surebot.info.serviceperson.activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.ApplicationClass;
import service.com.surebot.info.serviceperson.R;

public class ChangePasswordActivity extends BaseActivity {

    @BindView(R.id.arrowIV)
    ImageView arrowIV;
    @BindView(R.id.headerRL)
    RelativeLayout headerRL;
    private ChangePasswordActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        context = ChangePasswordActivity.this;

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
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            headerRL.setBackground(ContextCompat.getDrawable(context, headerBackground));
        }


        arrowIV.setOnClickListener(v -> {
            finish();
        });

    }
}
