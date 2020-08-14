package service.com.surebot.info.serviceperson.activities;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.ApplicationClass;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.adapters.SignupWorkDoneListAdapter;
import service.com.surebot.info.serviceperson.models.Category;
import service.com.surebot.info.serviceperson.utils.AppConstants;
import service.com.surebot.info.serviceperson.utils.Utils;

public class PartnerSignupWorkTypeActivity extends BaseActivity implements SignupWorkDoneListAdapter.CategoryListCommunicator {

    @BindView(R.id.workTypeRV)
    RecyclerView workTypeRV;
    private PartnerSignupWorkTypeActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_signup_work_type);
        ButterKnife.bind(this);
        context = PartnerSignupWorkTypeActivity.this;
        loadWorkDoneList();
    }

    private void loadWorkDoneList() {
        ArrayList<Category> categories = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            switch (i) {
                case 1:
                    Category category = new Category();
                    category.setId(i);
                    category.setIcon(R.drawable.signup_salon_women);
                    category.setTitle(AppConstants.SALOON_AT_HOME_FOR_WOMEN);
                    categories.add(category);
                    break;
                case 2:
                    Category category2 = new Category();
                    category2.setId(i);
                    category2.setIcon(R.drawable.signup_salon_men);
                    category2.setTitle(AppConstants.SALOON_AT_HOME_FOR_MEN);
                    categories.add(category2);
                    break;
                case 3:
                    Category category3 = new Category();
                    category3.setId(i);
                    category3.setIcon(R.drawable.signup_electrician);
                    category3.setTitle(AppConstants.ELECTRICIAN);
                    categories.add(category3);
                    break;
                case 4:
                    Category category4 = new Category();
                    category4.setId(i);
                    category4.setIcon(R.drawable.signup_plumber);
                    category4.setTitle(AppConstants.PLUMBER);
                    categories.add(category4);
                    break;
                case 5:
                    Category category5 = new Category();
                    category5.setId(i);
                    category5.setIcon(R.drawable.signup_carpentry);
                    category5.setTitle(AppConstants.CARPENTER);
                    categories.add(category5);
                    break;
                case 6:
                    Category category6 = new Category();
                    category6.setId(i);
                    category6.setIcon(R.drawable.signup_cleaning);
                    category6.setTitle(AppConstants.CLEANING);
                    categories.add(category6);
                    break;
                case 7:
                    Category category7 = new Category();
                    category7.setId(i);
                    category7.setIcon(R.drawable.signup_appliances);
                    category7.setTitle(AppConstants.APPLIANCE_AND_ELECTRONIC_REPAIRS);
                    categories.add(category7);
                    break;
                case 8:
                    Category category8 = new Category();
                    category8.setId(i);
                    category8.setIcon(R.drawable.signup_pest);
                    category8.setTitle(AppConstants.PEST_CONTROL);
                    categories.add(category8);
                    break;
                case 9:
                    Category category9 = new Category();
                    category9.setId(i);
                    category9.setIcon(R.drawable.signup_paint);
                    category9.setTitle(AppConstants.HOUSE_INTERIOR_AND_PAINTING);
                    categories.add(category9);
                    break;
            }
        }

        SignupWorkDoneListAdapter signupWorkDoneListAdapter = new
                SignupWorkDoneListAdapter(context, categories);
        workTypeRV.setAdapter(signupWorkDoneListAdapter);
        signupWorkDoneListAdapter.setCategoryListCommunicator(this);
        signupWorkDoneListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCategorySelect(Category category) {
        ApplicationClass.setCategorySelection(category.getId());
        setAppTheme();
        Utils.startIntent(context, OnBoardProfileActivity.class, false);
        overridePendingTransition(R.anim.fade_in_animation, R.anim.fade_out_animation);
    }
}

