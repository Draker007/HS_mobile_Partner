package service.com.surebot.info.serviceperson.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.models.PaymentCompletedDetailService;
import service.com.surebot.info.serviceperson.models.PaymentCompletedService;
import service.com.surebot.info.serviceperson.utils.Utils;

public class PaymentPendingAdapter extends RecyclerView.Adapter<PaymentPendingAdapter.ViewHolder> {
    private final List<PaymentCompletedService> paymentCompletedServiceList;

    public PaymentPendingAdapter(List<PaymentCompletedService> paymentCompletedServiceList) {
        this.paymentCompletedServiceList = paymentCompletedServiceList;
    }

    @NonNull
    @Override
    public PaymentPendingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment_completed, parent, false);
        return new PaymentPendingAdapter.ViewHolder(layoutInflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentPendingAdapter.ViewHolder holder, int position) {
        PaymentCompletedService paymentCompletedService = paymentCompletedServiceList.get(position);
        holder.name.setText(paymentCompletedService.getName());
        holder.dateTV.setText(paymentCompletedService.getDate());
        holder.timeTV.setText(paymentCompletedService.getTime());
        holder.paidAmountTV.setText(paymentCompletedService.getAmountPaid());
        holder.requestIdNumberTV.setText(paymentCompletedService.getIdNumber());

        holder.moreLL.setOnClickListener(v -> Utils.showHideView(true, holder.completedDetailsRL));
        holder.arrowUpIV.setOnClickListener(v -> Utils.showHideView(false, holder.completedDetailsRL));

        List<PaymentCompletedDetailService> paymentCompletedDetailServiceList = new ArrayList<>();
        Utils.showHideView(true, holder.yetToPayTV);
        for (int i = 0; i <= 3; i++) {
            PaymentCompletedDetailService paymentCompletedDetailService = new PaymentCompletedDetailService();
            paymentCompletedDetailService.setTitle("Title");
            paymentCompletedDetailService.setService("Service");
            paymentCompletedDetailService.setAmount("123");
            paymentCompletedDetailService.setQuantity("$99");
            paymentCompletedDetailServiceList.add(paymentCompletedDetailService);
        }
        PaymentPendingDetailsAdapter paymentPendingDetailsAdapter = new PaymentPendingDetailsAdapter(paymentCompletedDetailServiceList);
        holder.completedDetailServiceRV.setAdapter(paymentPendingDetailsAdapter);
        paymentPendingDetailsAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return paymentCompletedServiceList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name, requestIdNumberTV, dateTV, paidAmountTV, timeTV, yetToPayTV;
        private final RecyclerView completedDetailServiceRV;
        private ImageView arrowUpIV;
        private RelativeLayout completedDetailsRL;
        private LinearLayout moreLL;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameTV);
            requestIdNumberTV = itemView.findViewById(R.id.requestIdNumberTV);
            dateTV = itemView.findViewById(R.id.dateTV);
            timeTV = itemView.findViewById(R.id.timeTV);
            paidAmountTV = itemView.findViewById(R.id.paidAmountTV);
            completedDetailsRL = itemView.findViewById(R.id.completedDetailsRL);
            arrowUpIV = itemView.findViewById(R.id.arrowUpIV);
            moreLL = itemView.findViewById(R.id.moreLL);
            completedDetailServiceRV = itemView.findViewById(R.id.completedDetailServiceRV);
            yetToPayTV = itemView.findViewById(R.id.yetToPayTV);
        }
    }
}
