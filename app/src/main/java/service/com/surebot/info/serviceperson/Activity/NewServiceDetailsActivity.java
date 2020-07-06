package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.Adapter.NewPaymentServiceAdapter;
import service.com.surebot.info.serviceperson.Adapter.NewservicesAdapter;
import service.com.surebot.info.serviceperson.Adapter.TodaysTask_Adapter;
import service.com.surebot.info.serviceperson.R;

public class NewServiceDetailsActivity extends AppCompatActivity {
    @BindView(R.id.newservicesRecycler)
    RecyclerView gnewservicesRecycler;

    @BindView(R.id.ServicepaymentRecycler)
    RecyclerView gServicepaymentRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_service_details);
        ButterKnife.bind(this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        gnewservicesRecycler.setLayoutManager(llm);
        NewservicesAdapter NewservicesAdapter = new NewservicesAdapter(this );
        gnewservicesRecycler.setAdapter(NewservicesAdapter);
        LinearLayoutManager llm2 = new LinearLayoutManager(this);
        llm2.setOrientation(LinearLayoutManager.VERTICAL);
        gServicepaymentRecycler.setLayoutManager(llm2);
        NewPaymentServiceAdapter NewPaymentServiceAdapter = new NewPaymentServiceAdapter(this );
        gServicepaymentRecycler.setAdapter(NewPaymentServiceAdapter);
    }
}