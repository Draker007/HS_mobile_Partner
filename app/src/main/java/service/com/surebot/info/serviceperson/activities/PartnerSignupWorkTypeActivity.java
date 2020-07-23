package service.com.surebot.info.serviceperson.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.adapters.SignupWorkDoneListAdapter;
import service.com.surebot.info.serviceperson.R;

public class PartnerSignupWorkTypeActivity extends AppCompatActivity {

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
        ArrayList<String> workDoneTitle = new ArrayList<>();

        workDoneTitle.add("Salon at home for Women");
        workDoneTitle.add("Salon at home for Men");
        workDoneTitle.add("Electrician");
        workDoneTitle.add("Plumber");
        workDoneTitle.add("Carpenter");
        workDoneTitle.add("Cleaning");
        workDoneTitle.add("Appliance & Electronic repairs");
        workDoneTitle.add("Pest Control");
        workDoneTitle.add("House interior & painting");

        ArrayList<Integer> workDoneImage = new ArrayList<>();
        workDoneImage.add(R.drawable.signup_salon_women);
        workDoneImage.add(R.drawable.signup_salon_men);
        workDoneImage.add(R.drawable.signup_electrician);
        workDoneImage.add(R.drawable.signup_plumber);
        workDoneImage.add(R.drawable.signup_carpentry);
        workDoneImage.add(R.drawable.signup_cleaning);
        workDoneImage.add(R.drawable.signup_appliances);
        workDoneImage.add(R.drawable.signup_pest);
        workDoneImage.add(R.drawable.signup_paint);

        SignupWorkDoneListAdapter signupWorkDoneListAdapter = new
                SignupWorkDoneListAdapter(context,
                workDoneTitle, workDoneImage);
        workTypeRV.setAdapter(signupWorkDoneListAdapter);
        signupWorkDoneListAdapter.notifyDataSetChanged();

    }
}
