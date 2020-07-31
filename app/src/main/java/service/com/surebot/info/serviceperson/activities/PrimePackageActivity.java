package service.com.surebot.info.serviceperson.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
        int headerLayout = R.drawable.salonlayout;
        int textColor = R.color.colorElectricianText;

        switch (categorySelection) {
            case 1:
            case 2: {
                layoutImage = R.drawable.salon;
                headerLayout = R.drawable.salonlayout;
                textColor = R.color.colorBlue;
            }
            break;
            case 3: {
                layoutImage = R.drawable.electrician;
                headerLayout = R.drawable.electrician_layout;
                textColor = R.color.colorElectricianText;
            }
            break;
            case 4: {
                layoutImage = R.drawable.plumber;
                headerLayout = R.drawable.plumber_layout;
                textColor = R.color.colorPrimaryBlue;
            }
            break;
            case 5: {
                layoutImage = R.drawable.carpenter;
                textColor = R.color.colorBrown;
                headerLayout = R.drawable.carpenter_layout;
            }
            break;
            case 6: {
                layoutImage = R.drawable.cleaning;
                textColor = R.color.colorGray;
                headerLayout = R.drawable.cleaning_layout;
            }
            break;
            case 7: {
                layoutImage = R.drawable.appliances;
                textColor = R.color.colorApplianceText;
                headerLayout = R.drawable.appliance_layout;
            }
            break;
            case 8: {
                layoutImage = R.drawable.pest;
                textColor = R.color.colorGray;
                headerLayout = R.drawable.pest_layout;
            }
            break;
            case 9: {
                layoutImage = R.drawable.paint;
                textColor = R.color.colorPaintText;
                headerLayout = R.drawable.paint_layout;
            }
            break;
        }

        serviceLayoutIV.setImageDrawable(ContextCompat.getDrawable(context, layoutImage));
        primeAdvantagesTV.setTextColor(getResources().getColor(textColor));
        salonBgIV.setImageDrawable(ContextCompat.getDrawable(context, headerLayout));


    }
}
