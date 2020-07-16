package service.com.surebot.info.serviceperson.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import service.com.surebot.info.serviceperson.DataFiles.awardsData;
import service.com.surebot.info.serviceperson.R;

public class awardsAdapter extends RecyclerView.Adapter<awardsAdapter.MyViewHolder> {

    private List<awardsData> awardsDataList;
    private onAwardsListner onAwardsListner;
    Context context;

    public awardsAdapter(List<awardsData> awardsDataList, awardsAdapter.onAwardsListner onAwardsListner, Context context) {
        this.awardsDataList = awardsDataList;
        this.onAwardsListner = onAwardsListner;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(parent.getContext()).inflate(R.layout.awards_recycler,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v,onAwardsListner);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        awardsData data = awardsDataList.get(position);

        System.out.println("Arraylistsize in Adapter " + data.getImagefile().size());
        RequestOptions myOptions = new RequestOptions()
                .override(150, 150);
        Glide.with(context)
                .asBitmap()
                .apply(myOptions)
                .load(data.getImage())
                .into(holder.Image);

        holder.Text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
           if (charSequence!=null) {
               awardsDataList.get(position).setText(charSequence.toString());

           }}

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
    public List<awardsData> retrieveData()
    {
        return awardsDataList;
    }
    @Override
    public int getItemCount() {
        return awardsDataList.size();
    }

    public static class  MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView close,Image;
        EditText Text;

        onAwardsListner onAwardsListner;

        public MyViewHolder(@NonNull View itemView, onAwardsListner onAwardsListner) {
            super(itemView);
            Image = itemView.findViewById(R.id.awardImage);
            Text = itemView.findViewById(R.id.awardText);
            close = itemView.findViewById(R.id.AwardClose);
            this.onAwardsListner = onAwardsListner;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

        }
    }
    public interface onAwardsListner{
        void onAwardsClick(int position);
    }
}
