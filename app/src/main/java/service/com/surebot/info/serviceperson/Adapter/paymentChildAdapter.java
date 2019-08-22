package service.com.surebot.info.serviceperson.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import service.com.surebot.info.serviceperson.DataFiles.paymentChildData;
import service.com.surebot.info.serviceperson.R;

public class paymentChildAdapter extends RecyclerView.Adapter<paymentChildAdapter.MyViewHolder> {

    List<String> services = new ArrayList<>();
    List<String> subServices =new ArrayList<>();
    List<String> quntity =new ArrayList<>();
    List<String> quntityAmount= new ArrayList<>();

    public paymentChildAdapter(List<String> services, List<String> subServices, List<String> quntity, List<String> quntityAmount) {
        this.services.addAll(services);
        this.subServices.addAll(subServices);
        this.quntity.addAll(quntity);
        this.quntityAmount.addAll(quntityAmount);
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
//        paymentChildData a = paymentChildDataList.get(position);
        holder.serviceCost.setText(quntityAmount.get(position));
        holder.serviceDesc.setText(subServices.get(position));
        holder.serviceName.setText(services.get(position));
        holder.quntity.setText("("+quntity.get(position)+")");


    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public static class  MyViewHolder extends  RecyclerView.ViewHolder{
        TextView serviceName,serviceDesc,serviceCost,quntity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.PayService);
            serviceDesc = itemView.findViewById(R.id.payServiceDesc);
            serviceCost =itemView.findViewById(R.id.PayServiceCharge);
            quntity = itemView.findViewById(R.id.subServiceqty);
        }
    }
}
