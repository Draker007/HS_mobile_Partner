package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;

public class CreateProfileActivity extends AppCompatActivity {

    @BindView(R.id.createprofile_button)
    Button gCreateprofile_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        ButterKnife.bind(this);

        gCreateprofile_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
              startActivity(new Intent(CreateProfileActivity.this,ServicesAdd_Activity.class));
            }
        });
    }
}
