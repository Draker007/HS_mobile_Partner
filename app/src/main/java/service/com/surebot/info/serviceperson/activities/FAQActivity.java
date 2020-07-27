package service.com.surebot.info.serviceperson.activities;

import android.os.Bundle;
import android.widget.ImageView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.adapters.FAQAdapter;
import service.com.surebot.info.serviceperson.models.FaqServiceList;

public class FAQActivity extends BaseActivity {

    @BindView(R.id.arrowIV)
    ImageView arrowIV;
    @BindView(R.id.FaqRecyclerView)
    RecyclerView FaqRecyclerView;

    private FAQActivity context;
    List<FaqServiceList> faqServiceLists = new ArrayList<>();
    private FAQAdapter faqAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ButterKnife.bind(this);
        context = FAQActivity.this;

        arrowIV.setOnClickListener(v -> {
            finish();
        });

        faqAdapter = new FAQAdapter(faqServiceLists);
        FaqRecyclerView.setAdapter(faqAdapter);
        loadServiceList();

    }

    private void loadServiceList() {
        for (int i = 0; i <= 5; i++) {
            FaqServiceList faqServiceList = new FaqServiceList();
            faqServiceList.setTextView("Salon at home");

            faqServiceLists.add(faqServiceList);
        }
        faqAdapter.notifyDataSetChanged();
    }
}
