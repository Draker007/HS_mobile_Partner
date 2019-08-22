package service.com.surebot.info.serviceperson.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import service.com.surebot.info.serviceperson.DataFiles.paymentChildData;
import service.com.surebot.info.serviceperson.DataFiles.paymentParentData;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.Payment_completed_transaction_Request;
import service.com.surebot.info.serviceperson.ResponseClass.Partner_payment_Response;

public class paymentParentAdapter extends RecyclerView.Adapter<paymentParentAdapter.MyViewHolder> {


    List<Partner_payment_Response.Complete_transaction_record> PaymentDetials;
    private Context Context;

    public paymentParentAdapter(List<Partner_payment_Response.Complete_transaction_record> paymentDetials, android.content.Context context) {
        PaymentDetials = paymentDetials;
        Context = context;
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
        holder.id.setText(PaymentDetials.get(position).getBooking_Id());
        holder.cost.setText(PaymentDetials.get(position).getTotal_Amount());
        holder.date.setText(PaymentDetials.get(position).getBooking_Date());
        holder.time.setText(PaymentDetials.get(position).getBooking_Start_Time());
        holder.total.setText(PaymentDetials.get(position).getTotal_Amount());
        holder.name.setText(PaymentDetials.get(position).getUser_Name());
        holder.PaymentCategoryView.setText(PaymentDetials.get(position).getCategory_Name());
        List<String> Services = Arrays.asList(PaymentDetials.get(position).getService().split(","));
        List<String> SubServices = Arrays.asList(PaymentDetials.get(position).getSub_Service_Name().split(","));
        List<String> Quntity = Arrays.asList(PaymentDetials.get(position).getQuantity().split(","));
        List<String> Price = Arrays.asList(PaymentDetials.get(position).getQuantity_Amount().split(","));
        paymentChildAdapter adapter = new paymentChildAdapter(Services,SubServices,Quntity,Price);
        holder.r1.setAdapter(adapter);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(Context);
        holder.r1.setLayoutManager(lm);



        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.paymentDetails.setVisibility(View.VISIBLE);
                holder.more.setVisibility(View.INVISIBLE);
            }
        });
        holder.less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.paymentDetails.setVisibility(View.GONE);
                holder.more.setVisibility(View.VISIBLE);
            }
        });



    }

    @Override
    public int getItemCount() {
        return PaymentDetials.size();
    }

    public static class  MyViewHolder extends  RecyclerView.ViewHolder{
        RecyclerView r1 ;
        TextView id, name, cost,date ,time,total,more,less,PaymentCategoryView;
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
            PaymentCategoryView =itemView.findViewById(R.id.PaymentCategoryView);



        }
    }
}
