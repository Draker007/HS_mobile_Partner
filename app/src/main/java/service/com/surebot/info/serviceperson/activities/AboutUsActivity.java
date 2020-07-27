package service.com.surebot.info.serviceperson.activities;



import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;

public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.arrowBack)
    ImageView arrowBack;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);

        arrowBack.setOnClickListener(v -> {
            finish();
        });

    }
}
