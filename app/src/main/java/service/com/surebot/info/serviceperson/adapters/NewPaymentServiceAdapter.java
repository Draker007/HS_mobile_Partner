package service.com.surebot.info.serviceperson.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import service.com.surebot.info.serviceperson.R;

public class NewPaymentServiceAdapter extends RecyclerView.Adapter<NewPaymentServiceAdapter.MyViewHolder>{
    Context context;

    public NewPaymentServiceAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_service_qnty_price, parent, false);
        return new NewPaymentServiceAdapter.MyViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PaymentSubserviceAdapter PaymentSubserviceAdapter = new PaymentSubserviceAdapter(context );
        holder.recyclerView.setAdapter(PaymentSubserviceAdapter);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.newSubServiceRV);


        }
    }



}
