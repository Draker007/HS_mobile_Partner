package service.com.surebot.info.serviceperson.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.adapters.PaymentPendingAdapter;
import service.com.surebot.info.serviceperson.models.PaymentCompletedService;

public class PaymentPendingFragment extends Fragment {
    @BindView(R.id.paymentCompletedRV)
    RecyclerView paymentCompletedRV;
    private List<PaymentCompletedService> paymentCompletedServiceList = new ArrayList<>();
    private PaymentPendingAdapter paymentPendingAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_completed_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        paymentPendingAdapter = new PaymentPendingAdapter(paymentCompletedServiceList);
        paymentCompletedRV.setAdapter(paymentPendingAdapter);
        loadCompletedServices();
        super.onViewCreated(view, savedInstanceState);
    }

    private void loadCompletedServices() {
        paymentCompletedServiceList.clear();
        for (int i = 0; i <= 3; i++) {
            PaymentCompletedService paymentCompletedService = new PaymentCompletedService();
            paymentCompletedService.setName("Priya");
            paymentCompletedService.setIdNumber("CR002DR93");
            paymentCompletedService.setAmountPaid("$625");
            paymentCompletedService.setDate("01 June 2020");
            paymentCompletedService.setTime("3:30PM");

            paymentCompletedServiceList.add(paymentCompletedService);
        }
        paymentPendingAdapter.notifyDataSetChanged();
    }
}
