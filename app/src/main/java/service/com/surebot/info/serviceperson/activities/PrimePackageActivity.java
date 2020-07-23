package service.com.surebot.info.serviceperson.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.adapters.PrimePackageAdapter;
import service.com.surebot.info.serviceperson.utils.Utils;

public class PrimePackageActivity extends AppCompatActivity {


    @BindView(R.id.primePackagesImageRV)
    RecyclerView primePackagesImageRV;
    @BindView(R.id.buypackageButton)
    Button buypackageButton;
    private PrimePackageActivity context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime_package);
        ButterKnife.bind(this);
        context = PrimePackageActivity.this;

        PrimePackageAdapter primePackageAdapter = new PrimePackageAdapter();
        primePackagesImageRV.setAdapter(primePackageAdapter);

        buypackageButton.setOnClickListener(v -> {
            Utils.startIntent(context, ServicePersonHomeActivity.class, false);
        });
    }
}
