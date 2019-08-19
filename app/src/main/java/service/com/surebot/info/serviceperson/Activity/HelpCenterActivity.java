package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.Adapter.faqAdapter;
import service.com.surebot.info.serviceperson.R;

public class HelpCenterActivity extends AppCompatActivity {

    @BindView(R.id.HelpcenterfaqRecycler)
    RecyclerView recyclerView;

    @BindView(R.id.CustomerSupportbtn)
    Button CustomerSupport;

    @BindView(R.id.TermOfUseBTN)
            Button Termofuse;

    faqAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        ButterKnife.bind(this);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(adapter);
        ArrayList<String> gFAQquestionList = new ArrayList<>();
        ArrayList<String> gFAQanswerList = new ArrayList<>();
        gFAQquestionList.add("asdasdasdasdsadasdasdsad");

        gFAQanswerList.add("dddedqqwdqwdaasdgfsadbaskdbah asjsshdg assmdgjasbv djalsgg a kahsdjhhaj, kshdfkasdf hflkhs dfasdf ahsdf asd.1");
        gFAQquestionList.add("asdasdasdasdsadasdasdsad");
        gFAQanswerList.add("dddedqqwdqwdaasdgfsadbaskdbah asjsshdg assmdgjasbv djalsgg a kahsdjhhaj, kshdfkasdf hflkhs dfasdf ahsdf asd.2");
        gFAQquestionList.add("asdasdasdasdsadasdasdsad");
        gFAQanswerList.add("dddedqqwdqwdaasdgfsadbaskdbah asjsshdg assmdgjasbv djalsgg a kahsdjhhaj, kshdfkasdf hflkhs dfasdf ahsdf asd.3");
        gFAQquestionList.add("asdasdasdasdsadasdasdsad");
        gFAQanswerList.add("dddedqqwdqwdaasdgfsadbaskdbah asjsshdg assmdgjasbv djalsgg a kahsdjhhaj, kshdfkasdf hflkhs dfasdf ahsdf asd.4");
        gFAQquestionList.add("asdasdasdasdsadasdasdsad");
        gFAQanswerList.add("dddedqqwdqwdaasdgfsadbaskdbah asjsshdg assmdgjasbv djalsgg a kahsdjhhaj, kshdfkasdf hflkhs dfasdf ahsdf asd.5");
        gFAQquestionList.add("asdasdasdasdsadasdasdsad");
        gFAQanswerList.add("dddedqqwdqwdaasdgfsadbaskdbah asjsshdg assmdgjasbv djalsgg a kahsdjhhaj, kshdfkasdf hflkhs dfasdf ahsdf asd.6");
        gFAQquestionList.add("asdasdasdasdsadasdasdsad");
        gFAQanswerList.add("dddedqqwdqwdaasdgfsadbaskdbah asjsshdg assmdgjasbv djalsgg a kahsdjhhaj, kshdfkasdf hflkhs dfasdf ahsdf asd.7");
        adapter = new faqAdapter(this,gFAQquestionList,gFAQanswerList);
        recyclerView.setAdapter(adapter);

        CustomerSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HelpCenterActivity.this,CustomerSupportActivity.class));
            }
        });
        Termofuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HelpCenterActivity.this,Terms.class));
            }
        });
    }
}
