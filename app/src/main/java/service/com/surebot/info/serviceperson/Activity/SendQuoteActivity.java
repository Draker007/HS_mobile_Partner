package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.Adapter.NewRequests_Adapter;
import service.com.surebot.info.serviceperson.Adapter.SendQuoteServiceAdapter;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class SendQuoteActivity extends AppCompatActivity {
    @BindView(R.id.sendQuoteServiceREcy)
    RecyclerView gsendQuoteServiceREcy;

    LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_quote);
        ButterKnife.bind(this);
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        gsendQuoteServiceREcy.setLayoutManager(llm);
        SendQuoteServiceAdapter SendQuoteServiceAdapter= new SendQuoteServiceAdapter(this);
        gsendQuoteServiceREcy.setAdapter(SendQuoteServiceAdapter);
    }
}