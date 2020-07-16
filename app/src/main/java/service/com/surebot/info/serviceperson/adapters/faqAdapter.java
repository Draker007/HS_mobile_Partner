package service.com.surebot.info.serviceperson.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.ResponseClass.ListofFaqs_Response;

public class faqAdapter extends RecyclerView.Adapter<faqAdapter.MyViewHolder> {
    Context context;
    ArrayList<ListofFaqs_Response.ListofFaqs_Records> gListofFaqs_Arraylist;

    public faqAdapter(Context context,ArrayList<ListofFaqs_Response.ListofFaqs_Records> gListofFaqs_Arraylist) {
        this.context = context;
        this.gListofFaqs_Arraylist = gListofFaqs_Arraylist;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_recyclerlayout, parent, false);
        return new faqAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.question.setText(gListofFaqs_Arraylist.get(position).getQuestion());
        holder.answer.setText(gListofFaqs_Arraylist.get(position).getAnswer());

        holder.question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.answer.setVisibility(View.VISIBLE);
                holder.up.setVisibility(View.VISIBLE);
                holder.down.setVisibility(View.GONE);
            }
        });
        holder.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.answer.setVisibility(View.VISIBLE);
                holder.up.setVisibility(View.VISIBLE);
                holder.down.setVisibility(View.GONE);
            }
        });

        holder.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.answer.setVisibility(View.GONE);
                holder.up.setVisibility(View.GONE);
                holder.down.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return gListofFaqs_Arraylist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView up ,down;
        TextView question,answer;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            up = itemView.findViewById(R.id.FAQupImage);
            down = itemView.findViewById(R.id.FAQdownImage);
            question = itemView.findViewById(R.id.faqQuestion);
            answer = itemView.findViewById(R.id.faqAns);


        }
    }
}
