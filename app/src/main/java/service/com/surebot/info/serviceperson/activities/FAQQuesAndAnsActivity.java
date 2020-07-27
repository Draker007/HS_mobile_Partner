package service.com.surebot.info.serviceperson.activities;

import android.os.Bundle;
import android.widget.ImageView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.adapters.FAQQuesAndAnsAdapter;
import service.com.surebot.info.serviceperson.models.FAQQuesAndAnsList;

public class  FAQQuesAndAnsActivity extends BaseActivity {

    @BindView(R.id.faqQueAndAndRV)
    RecyclerView faqQueAndAndRV;
    @BindView(R.id.arrowIV)
    ImageView arrowIV;
    List<FAQQuesAndAnsList> faqQuesAndAnsLists = new ArrayList<>();
    private FAQQuesAndAnsAdapter faqQuesAndAnsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_q_ques_and_ans);
        ButterKnife.bind(this);

        arrowIV.setOnClickListener(v -> {
            finish();
        });

        faqQuesAndAnsAdapter = new FAQQuesAndAnsAdapter(faqQuesAndAnsLists);
        faqQueAndAndRV.setAdapter(faqQuesAndAnsAdapter);
        loadQuesAndAns();
    }

    private void loadQuesAndAns() {
        for (int i = 0; i <= 3; i++) {
            FAQQuesAndAnsList faqQuesAndAnsList = new FAQQuesAndAnsList();
            faqQuesAndAnsList.setQuestion("Why at home Beauty Services?");
            faqQuesAndAnsList.setAnswer("A lot of you women out there have crazy hectic schedules; " +
                    "managing office, home, babies," + " elderly parents, in-laws, never ending" +
                    " grocery lists and so on and so forth.");

            faqQuesAndAnsLists.add(faqQuesAndAnsList);
        }
        faqQuesAndAnsAdapter.notifyDataSetChanged();
    }
}
