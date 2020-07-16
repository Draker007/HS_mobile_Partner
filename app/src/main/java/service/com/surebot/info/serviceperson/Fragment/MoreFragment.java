package service.com.surebot.info.serviceperson.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.activities.PaymentActivity;
import service.com.surebot.info.serviceperson.utils.Utils;

public class MoreFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.paymentArrowIV)
    ImageView paymentArrowIV;
    private FragmentActivity context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.more_fragment, container, false);
        ButterKnife.bind(this, view);
        context = getActivity();

        paymentArrowIV.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Utils.startIntent(context, PaymentActivity.class, false);
    }
}
