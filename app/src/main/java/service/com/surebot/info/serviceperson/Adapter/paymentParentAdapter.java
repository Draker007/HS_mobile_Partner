package service.com.surebot.info.serviceperson.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import service.com.surebot.info.serviceperson.DataFiles.paymentChildData;
import service.com.surebot.info.serviceperson.DataFiles.paymentParentData;
import service.com.surebot.info.serviceperson.R;

public class paymentParentAdapter extends RecyclerView.Adapter<paymentParentAdapter.MyViewHolder> {

    List<paymentChildData> paymentChildDataList;
    List<paymentParentData> paymentParentDataList;

    public paymentParentAdapter( List<paymentParentData> paymentParentDataList) {

        this.paymentParentDataList = paymentParentDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_recycler,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        paymentParentData a = paymentParentDataList.get(position);
        paymentChildAdapter adapter = new paymentChildAdapter(a.getPaymentChildData());
        Log.e("lola", "onBindViewHolder: "+a.getPaymentChildData() );
        RecyclerView.LayoutManager lm = new LinearLayoutManager(holder.r1.getContext());
        holder.r1.setLayoutManager(lm);
        holder.r1.setAdapter(adapter);
        holder.total.setText(a.getTotal());
        holder.id.setText(a.getId());
        holder.name.setText(a.getName());
        holder.cost.setText(a.getPaidMoney());
        holder.date.setText(a.getDate());
        holder.time.setText(a.getTime());

        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.paymentDetails.setVisibility(View.VISIBLE);
            }
        });
        holder.less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.paymentDetails.setVisibility(View.GONE);
            }
        });



    }

    @Override
    public int getItemCount() {
        return paymentParentDataList.size();
    }

    public static class  MyViewHolder extends  RecyclerView.ViewHolder{
        RecyclerView r1 ;
        TextView id, name, cost,date ,time,total,more,less;
        ConstraintLayout paymentDetails;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            r1 = itemView.findViewById(R.id.paymentChildrecycler);
            id = itemView.findViewById(R.id.paymentParentID);
            name = itemView.findViewById(R.id.paymentParentName);
            cost = itemView.findViewById(R.id.paymentParentcost);
            date = itemView.findViewById(R.id.paymentParentDate);
            time = itemView.findViewById(R.id.paymentParentTime);
            total = itemView.findViewById(R.id.paymentTotal);
            more = itemView.findViewById(R.id.paymentParentMore);
            less = itemView.findViewById(R.id.paymentLess);
            paymentDetails = itemView.findViewById(R.id.PaymentMore);



        }
    }
}
