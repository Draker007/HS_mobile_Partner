package service.com.surebot.info.serviceperson.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.Utils;

public class PreLoginActivity extends AppCompatActivity {

    @BindView(R.id.signinButton)
    Button signinButton;
    @BindView(R.id.loginButton)
    Button loginButton;
    private PreLoginActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);
        context = PreLoginActivity.this;
        ButterKnife.bind(this);

        signinButton.setOnClickListener(v -> {
            Utils.startIntent(context, PartnerSignupActivity.class, true);
        });

        loginButton.setOnClickListener(v -> {
            Utils.startIntent(context, PartnerLoginActivity.class, true);
        });
    }
}