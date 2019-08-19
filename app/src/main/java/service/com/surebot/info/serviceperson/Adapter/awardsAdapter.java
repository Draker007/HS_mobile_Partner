package service.com.surebot.info.serviceperson.Adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import service.com.surebot.info.serviceperson.DataFiles.awardsData;
import service.com.surebot.info.serviceperson.R;

public class awardsAdapter extends RecyclerView.Adapter<awardsAdapter.MyViewHolder> {

    private List<awardsData> awardsDataList;
    private onAwardsListner onAwardsListner;

    public awardsAdapter(List<awardsData> awardsDataList, awardsAdapter.onAwardsListner onAwardsListner) {
        this.awardsDataList = awardsDataList;
        this.onAwardsListner = onAwardsListner;
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
        //setImage
        //holder.Text.getText();
        holder.Image.setImageBitmap(data.getImage());

        holder.Text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            awardsDataList.get(position).setText(charSequence.toString());
            }

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

        ImageView Image;
        EditText Text;
        onAwardsListner onAwardsListner;

        public MyViewHolder(@NonNull View itemView, onAwardsListner onAwardsListner) {
            super(itemView);
            Image = itemView.findViewById(R.id.awardImage);
            Text = itemView.findViewById(R.id.awardText);
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
