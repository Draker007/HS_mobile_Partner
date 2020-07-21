package service.com.surebot.info.serviceperson.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.adapters.PrimePackageAdapter;

public class PrimePackageActivity extends AppCompatActivity {


    @BindView(R.id.primePackagesImageRV)
    RecyclerView primePackagesImageRV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime_package);
        ButterKnife.bind(this);

        PrimePackageAdapter primePackageAdapter = new PrimePackageAdapter();
        primePackagesImageRV.setAdapter(primePackageAdapter);
    }
}
