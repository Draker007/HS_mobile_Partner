package service.com.surebot.info.serviceperson.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.models.PaymentCompletedDetailService;

public class PaymentCompletedDetailsAdapter extends RecyclerView.Adapter<PaymentCompletedDetailsAdapter.ViewHolder> {

    private final List<PaymentCompletedDetailService> paymentCompletedDetailServiceList;

    PaymentCompletedDetailsAdapter(List<PaymentCompletedDetailService> paymentCompletedDetailServiceList) {
        this.paymentCompletedDetailServiceList = paymentCompletedDetailServiceList;
    }

    @NonNull
    @Override
    public PaymentCompletedDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_completed_subservices, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentCompletedDetailsAdapter.ViewHolder holder, int position) {
        PaymentCompletedDetailService paymentCompletedDetailService = paymentCompletedDetailServiceList.get(position);
        holder.titleTV.setText(paymentCompletedDetailService.getTitle());
        holder.serviceTV.setText(paymentCompletedDetailService.getService());
        holder.quantityTV.setText(paymentCompletedDetailService.getQuantity());
    }

    @Override
    public int getItemCount() {
        return paymentCompletedDetailServiceList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTV, serviceTV, quantityTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.titleTV);
            serviceTV = itemView.findViewById(R.id.serviceTV);
            quantityTV = itemView.findViewById(R.id.quantityTV);
        }
    }
}
