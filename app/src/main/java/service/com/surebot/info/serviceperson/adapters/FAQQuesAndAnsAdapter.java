package service.com.surebot.info.serviceperson.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.models.FAQQuesAndAnsList;
import service.com.surebot.info.serviceperson.utils.Utils;

public class FAQQuesAndAnsAdapter extends RecyclerView.Adapter<FAQQuesAndAnsAdapter.ViewHolder> {
    private final List<FAQQuesAndAnsList> faqQuesAndAnsLists;
    private Context context;

    public FAQQuesAndAnsAdapter(List<FAQQuesAndAnsList> faqQuesAndAnsLists) {
        this.faqQuesAndAnsLists = faqQuesAndAnsLists;
    }

    @NonNull
    @Override
    public FAQQuesAndAnsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View layoutInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faq_ques_and_ans, parent, false);
        return new ViewHolder(layoutInflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQQuesAndAnsAdapter.ViewHolder holder, int position) {
        FAQQuesAndAnsList faqQuesAndAnsList = faqQuesAndAnsLists.get(position);
        holder.questionTV.setText(faqQuesAndAnsList.getQuestion());
        holder.answerTV.setText(faqQuesAndAnsList.getAnswer());

        holder.arrowDownIV.setOnClickListener(v -> {
            Utils.showHideView(true, holder.arrowUpIV, holder.answerTV);
            Utils.showHideView(false, holder.arrowDownIV);
        });

        holder.arrowUpIV.setOnClickListener(v -> {
            Utils.showHideView(false, holder.arrowUpIV, holder.answerTV);
            Utils.showHideView(true, holder.arrowDownIV);
        });
    }

    @Override
    public int getItemCount() {
        return faqQuesAndAnsLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView questionTV, answerTV;
        private final ImageView arrowDownIV, arrowUpIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTV = itemView.findViewById(R.id.questionTV);
            answerTV = itemView.findViewById(R.id.answerTV);
            arrowDownIV = itemView.findViewById(R.id.arrowDownIV);
            arrowUpIV = itemView.findViewById(R.id.arrowUpIV);
        }
    }
}
