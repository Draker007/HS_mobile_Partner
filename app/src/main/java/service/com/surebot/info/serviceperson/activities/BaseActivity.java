package service.com.surebot.info.serviceperson.activities;

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
        }
        setTheme(theme);
        recreate();
        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        overridePendingTransition(R.anim.fade_in_animation, R.anim.fade_out_animation);
    }
}
