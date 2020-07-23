package service.com.surebot.info.serviceperson.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.Utils;

public class BuyAPackageActivity extends AppCompatActivity {

    @BindView(R.id.arrowBack)
    ImageView arrowBack;
    @BindView(R.id.buyAPackageBtn)
    Button buyAPackageBtn;
    private BuyAPackageActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_a_package);
        ButterKnife.bind(this);
        context = BuyAPackageActivity.this;

        arrowBack.setOnClickListener(v -> {
            finish();
        });

        buyAPackageBtn.setOnClickListener(v -> {
            Utils.startIntent(context, PrimePackageActivity.class, false);
        });
    }
}