package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.Fragment.MyTask_Fragment;
import service.com.surebot.info.serviceperson.Fragment.Profile_Fragment;
import service.com.surebot.info.serviceperson.R;

public class CreateProfileActivity extends AppCompatActivity {

    @BindView(R.id.createprofile_button)
    Button gCreateprofile_button;



    Fragment fragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        ButterKnife.bind(this);

        gCreateprofile_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
             startActivity(new Intent(CreateProfileActivity.this,ServicesAdd_Activity.class));

                Intent userHomeIntent = new Intent(getApplicationContext(),ServicePersonHome_Activity.class);
                userHomeIntent.putExtra("HomeScreenFlow","fromcreateprofile");
                startActivity(userHomeIntent);
                finish();

            }
        });
    }
}
