package service.com.surebot.info.serviceperson.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.activities.FAQQuesAndAnsActivity;
import service.com.surebot.info.serviceperson.models.FaqServiceList;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.ViewHolder> {

    private final List<FaqServiceList> faqServiceLists;
    private Context context;

    public FAQAdapter(List<FaqServiceList> faqServiceLists) {
        this.faqServiceLists = faqServiceLists;
    }

    @NonNull
    @Override
    public FAQAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View layoutInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faq, parent, false);
        return new ViewHolder(layoutInflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQAdapter.ViewHolder holder, int position) {
        FaqServiceList faqList = faqServiceLists.get(position);
        holder.textView.setText(faqList.getTextView());

        holder.arrowIV.setOnClickListener(v -> {
            Intent intent = new Intent(context, FAQQuesAndAnsActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return faqServiceLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView arrowIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            arrowIV = itemView.findViewById(R.id.arrowIV);
        }
    }
}