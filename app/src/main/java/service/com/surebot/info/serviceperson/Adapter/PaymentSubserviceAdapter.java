package service.com.surebot.info.serviceperson.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import service.com.surebot.info.serviceperson.R;

public class PaymentSubserviceAdapter extends RecyclerView.Adapter<PaymentSubserviceAdapter.MyViewHolder> {
    Context context;

    public PaymentSubserviceAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newpayment_subservice_recyc, parent, false);
        return new PaymentSubserviceAdapter.MyViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);



        }
    }
}
