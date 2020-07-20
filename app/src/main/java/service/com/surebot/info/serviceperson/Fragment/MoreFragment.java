package service.com.surebot.info.serviceperson.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.firebase.database.collection.LLRBBlackValueNode;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.activities.AboutUsActivity;
import service.com.surebot.info.serviceperson.activities.HelpCenterActivity;
import service.com.surebot.info.serviceperson.activities.PaymentActivity;
import service.com.surebot.info.serviceperson.utils.Utils;

public class MoreFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.paymentLL)
    LinearLayout paymentLL;
    @BindView(R.id.helpCenterLL)
    LinearLayout helpCenterLL;
    @BindView(R.id.aboutUsLL)
    LinearLayout aboutUsLL;
    private FragmentActivity context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.more_fragment, container, false);
        ButterKnife.bind(this, view);
        context = getActivity();

        paymentLL.setOnClickListener(this);
        aboutUsLL.setOnClickListener(this);
        helpCenterLL.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.paymentLL: {
                Utils.startIntent(context, PaymentActivity.class, false);
            }
            break;
            case R.id.aboutUsLL: {
                Utils.startIntent(context, AboutUsActivity.class, false);
            }
            break;
            case R.id.helpCenterLL: {
                Utils.startIntent(context, HelpCenterActivity.class, false);
            }
            break;
        }
    }
}
