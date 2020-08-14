package service.com.surebot.info.serviceperson.adapters;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import service.com.surebot.info.serviceperson.ApplicationClass;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.activities.PartnerSignupWorkTypeActivity;
import service.com.surebot.info.serviceperson.models.Category;

public class SignupWorkDoneListAdapter extends RecyclerView.Adapter<SignupWorkDoneListAdapter.MyViewHolder> {

    private final PartnerSignupWorkTypeActivity context;
    private ArrayList<Category> categoryList;
    private CategoryListCommunicator categoryListCommunicator;
    private int selectedItemId;

    public SignupWorkDoneListAdapter(PartnerSignupWorkTypeActivity context, ArrayList<Category>
            categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_signup_work_type, parent, false);
        selectedItemId = ApplicationClass.getCategorySelection();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.textTV.setText(category.getTitle());
        Glide.with(context).load(category.getIcon()).into(holder.imageIV);

        int icon = R.drawable.signup_checkbox_unchecked_black;
        if (category.getId() == selectedItemId) icon = R.drawable.checkbox_checked_icon;
        holder.checkBoxIV.setImageDrawable(ContextCompat.getDrawable(context, icon));

        holder.itemView.setOnClickListener(v -> {
            selectedItemId = category.getId();
            notifyDataSetChanged();
            categoryListCommunicator.onCategorySelect(category);
        });
    }

    public void setCategoryListCommunicator(CategoryListCommunicator categoryListCommunicator) {
        this.categoryListCommunicator = categoryListCommunicator;
    }

    @Override

    public int getItemCount() {
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView textTV;
        private final ImageView imageIV, checkBoxIV;

        public MyViewHolder(View itemView) {
            super(itemView);
            textTV = itemView.findViewById(R.id.textTV);
            imageIV = itemView.findViewById(R.id.imageIV);
            checkBoxIV = itemView.findViewById(R.id.checkBoxIV);
        }
    }

    public interface CategoryListCommunicator {
        void onCategorySelect(Category category);
    }

}
