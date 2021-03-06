package service.com.surebot.info.serviceperson.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import service.com.surebot.info.serviceperson.ApplicationClass;
import service.com.surebot.info.serviceperson.R;

public class BaseActivity extends AppCompatActivity {

    private static int theme = R.style.AppTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public void setAppTheme() {
        int categorySelection = ApplicationClass.getCategorySelection();
        switch (categorySelection) {
            case 1:
            case 2: {
                theme = R.style.ThemeSaloon;
            }
            break;
            case 3: {
                theme = R.style.ThemeElectrician;
            }
            break;
            case 4: {
                theme = R.style.ThemePlumber;
            }
            break;
            case 5: {
                theme = R.style.ThemeCarpenter;
            }
            break;
            case 6:
            case 8: {
                theme = R.style.ThemeCleaning;
            }
            break;
            case 7: {
                theme = R.style.ThemeAppliance;
            }
            break;
            case 9: {
                theme = R.style.ThemePaint;
            }
            break;
        }
        setTheme(theme);
        finish();
        startActivity(new Intent(this, PartnerSignupWorkTypeActivity.class));
        overridePendingTransition(0,0);

    }
}
