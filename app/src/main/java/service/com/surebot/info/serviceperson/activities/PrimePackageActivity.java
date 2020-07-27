package service.com.surebot.info.serviceperson.activities;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.ApplicationClass;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.adapters.PrimePackageAdapter;
import service.com.surebot.info.serviceperson.utils.Utils;

public class PrimePackageActivity extends BaseActivity {


    @BindView(R.id.primePackagesImageRV)
    RecyclerView primePackagesImageRV;
    @BindView(R.id.buypackageButton)
    Button buypackageButton;
    @BindView(R.id.serviceLayoutIV)
    ImageView serviceLayoutIV;
    @BindView(R.id.salonBgIV)
    ImageView salonBgIV;
    @BindView(R.id.primeAdvantagesTV)
    TextView primeAdvantagesTV;
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

        int categorySelection = ApplicationClass.getCategorySelection();
        int layoutImage = R.drawable.salon;
        int textColor = R.color.colorElectricianText;

        switch (categorySelection) {
            case 1:
            case 2: {
                layoutImage = R.drawable.salon;
                textColor = R.color.colorBlue;
            }
            break;
            case 3: {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    salonBgIV.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorElectricianText)));
                }
                layoutImage = R.drawable.electrician;
                textColor = R.color.colorElectricianText;
            }
            break;
            case 4: {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    salonBgIV.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryBlue)));
                }
                layoutImage = R.drawable.plumber;
                textColor = R.color.colorPrimaryBlue;
            }
            break;
        }

        serviceLayoutIV.setImageDrawable(ContextCompat.getDrawable(context, layoutImage));
        primeAdvantagesTV.setTextColor(getResources().getColor(textColor));


    }
}
