package service.com.surebot.info.serviceperson.activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.ApplicationClass;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.Utils;

public class PersonalDetailsActivity extends BaseActivity {

    @BindView(R.id.arrowIV)
    ImageView arrowIV;
    @BindView(R.id.salonBgIV)
    ImageView salonBgIV;
    @BindView(R.id.serviceIV)
    ImageView serviceIV;
    @BindView(R.id.femaleGenderIV)
    ImageView femaleGenderIV;
    @BindView(R.id.serviceLayoutCarpenterLL)
    LinearLayout serviceLayoutCarpenterLL;
    private PersonalDetailsActivity context;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_detail_view);
        ButterKnife.bind(this);
        context = PersonalDetailsActivity.this;

        int categorySelection = ApplicationClass.getCategorySelection();
        int headerLayout = R.color.colorElectricianText;
        int layoutServiceImage = R.drawable.electrician;
        switch (categorySelection) {
            case 1:
            case 2: {
                headerLayout = R.drawable.salonlayout;
                layoutServiceImage = R.drawable.salon;
            }
            break;
            case 3: {
                headerLayout = R.drawable.electrician_layout;
                layoutServiceImage = R.drawable.electrician;
                femaleGenderIV.setColorFilter(context.getResources().getColor(R.color.colorElectricianText));
            }
            break;
            case 4: {
                headerLayout = R.drawable.plumber_layout;
                layoutServiceImage = R.drawable.plumber;
                femaleGenderIV.setColorFilter(context.getResources().getColor(R.color.colorPrimaryBlue));
            }
            break;
            case 5: {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    femaleGenderIV.setColorFilter(context.getResources().getColor(R.color.colorBrown));
                }
                Utils.showHideView(true, serviceLayoutCarpenterLL);
                Utils.showHideView(false, serviceIV);
            }
            break;

        }
        salonBgIV.setImageDrawable(ContextCompat.getDrawable(context, headerLayout));
        serviceIV.setImageDrawable(ContextCompat.getDrawable(context, layoutServiceImage));

        arrowIV.setOnClickListener(v -> {
            finish();
        });

    }
}