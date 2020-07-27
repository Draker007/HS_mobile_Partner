package service.com.surebot.info.serviceperson.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;



import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.Utils;

public class SetNewPasswordActivity extends BaseActivity {

    @BindView(R.id.otpEnter)
    EditText otpEnter;
    private SetNewPasswordActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);
        ButterKnife.bind(this);
        context = SetNewPasswordActivity.this;

        otpEnter.setOnClickListener(v -> {
            Utils.startIntent(context, LoginChangePasswordActivity.class, true);
        });
    }
}