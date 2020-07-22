package service.com.surebot.info.serviceperson.activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;

public class IdentityVerificationActivity extends AppCompatActivity {

    @BindView(R.id.arrowIV)
    ImageView arrowIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_verification);
        ButterKnife.bind(this);

        arrowIV.setOnClickListener(v -> {
            finish();
        });

    }

}
