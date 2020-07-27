package service.com.surebot.info.serviceperson.activities;



import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.Utils;

public class LoginChangePasswordActivity extends BaseActivity {

    @BindView(R.id.changePasswordBtn)
    EditText changePasswordBtn;
    private LoginChangePasswordActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_change_password);
        ButterKnife.bind(this);
        context = LoginChangePasswordActivity.this;

        changePasswordBtn.setOnClickListener(v -> {
            Utils.startIntent(context, PartnerLoginActivity.class, true);
        });
    }
}
