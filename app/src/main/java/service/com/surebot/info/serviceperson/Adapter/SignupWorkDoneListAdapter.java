package service.com.surebot.info.serviceperson.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import service.com.surebot.info.serviceperson.Activity.PartnerSignupWorkTypeActivity;
import service.com.surebot.info.serviceperson.R;

public class SignupWorkDoneListAdapter extends RecyclerView.Adapter<SignupWorkDoneListAdapter.MyViewHolder> {

    private final PartnerSignupWorkTypeActivity context;
    private ArrayList<String> workDoneTitle;
    private ArrayList<Integer> workDoneImage;

    public SignupWorkDoneListAdapter(PartnerSignupWorkTypeActivity context, ArrayList<String>
            workDoneTitle, ArrayList<Integer> workDoneImage) {
        this.context = context;
        this.workDoneTitle = workDoneTitle;
        this.workDoneImage = workDoneImage;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_signup_work_type, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textTV.setText(workDoneTitle.get(position));
        Glide.with(context).load(workDoneImage.get(position)).into(holder.imageIV);
    }

    @Override
    public int getItemCount() {
        return workDoneTitle.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView textTV;
        private final ImageView imageIV;

        public MyViewHolder(View itemView) {
            super(itemView);
            textTV = itemView.findViewById(R.id.textTV);
            imageIV = itemView.findViewById(R.id.imageIV);
        }
    }
}
