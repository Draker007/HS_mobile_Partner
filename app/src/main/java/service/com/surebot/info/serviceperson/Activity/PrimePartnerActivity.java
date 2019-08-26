package service.com.surebot.info.serviceperson.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;
public class PrimePartnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime_partner);
        ButterKnife.bind(this);
    }
}
