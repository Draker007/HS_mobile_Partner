package service.com.surebot.info.serviceperson.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;

public class PrimePartnerActivity extends AppCompatActivity {
        @BindView(R.id.PrimePartnerBack)
    ConstraintLayout back;

        @BindView(R.id.buyPackagebtn)
    Button buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime_partner);
        ButterKnife.bind(this);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PrimePartnerActivity.this, ServicePersonHome_Activity.class).putExtra("HomeScreenFlow","frombecomeprime"));
                finish();
            }
        });
    }
}
