package service.com.surebot.info.serviceperson.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import service.com.surebot.info.serviceperson.DataFiles.paymentChildData;
import service.com.surebot.info.serviceperson.R;

public class paymentChildAdapter extends RecyclerView.Adapter<paymentChildAdapter.MyViewHolder> {
    List<paymentChildData> paymentChildDataList;

    public paymentChildAdapter(List<paymentChildData> paymentChildDataList) {
        this.paymentChildDataList = paymentChildDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_child_recycler,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        paymentChildData a = paymentChildDataList.get(position);
        holder.serviceCost.setText(a.getCost());
        holder.serviceDesc.setText(a.getServiceDesc());
        holder.serviceName.setText(a.getServiceName());


    }

    @Override
    public int getItemCount() {
        return paymentChildDataList.size();
    }

    public static class  MyViewHolder extends  RecyclerView.ViewHolder{
        TextView serviceName,serviceDesc,serviceCost;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.PayService);
            serviceDesc = itemView.findViewById(R.id.payServiceDesc);
            serviceCost =itemView.findViewById(R.id.PayServiceCharge);

        }
    }
}
