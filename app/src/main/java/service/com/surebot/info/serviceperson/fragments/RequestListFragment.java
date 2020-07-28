package service.com.surebot.info.serviceperson.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.ApplicationClass;
import service.com.surebot.info.serviceperson.adapters.RequestListAdapter;
import service.com.surebot.info.serviceperson.R;

public class RequestListFragment extends Fragment {

    @BindView(R.id.allrequestlist_tablayout)
    TabLayout gAllrequestlist_tablayout;

    @BindView(R.id.allrequestlist_viewpager)
    ViewPager gAllrequestlist_viewpager;
    @BindView(R.id.headerRL)
    RelativeLayout headerRL;

    RequestListAdapter requestListAdapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.allrequestlist_layout, container, false);
        ButterKnife.bind(this, view);

        int categorySelection = ApplicationClass.getCategorySelection();
        int headerBackground = R.drawable.electrician_header_background;

        switch (categorySelection) {
            case 1:
            case 2: {
                headerBackground = R.drawable.salon_header_bg;
            }
            break;
            case 3: {
                headerBackground = R.drawable.electrician_header_background;
            }
            break;
            case 4: {
                headerBackground = R.drawable.plumber_header_bg;
            }
            break;
            case 5: {
                headerBackground = R.drawable.carpenter_header_bg;
            }
            break;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            headerRL.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getActivity()), headerBackground));
        }

        gAllrequestlist_tablayout.setupWithViewPager(gAllrequestlist_viewpager);
        // gViewpager_Adapter = new MyBookingTab_Adapter(getActivity().getSupportFragmentManager());
        requestListAdapter = new RequestListAdapter(getChildFragmentManager());
        requestListAdapter.AddFragment(new NewRequest_Fragment(), "New");
        requestListAdapter.AddFragment(new UpcomingRequestFragment(), "Upcoming");
        requestListAdapter.AddFragment(new CompletedRequestFragment(), "Completed");
        requestListAdapter.AddFragment(new CancelledRequestFragment(), "Cancelled");
        gAllrequestlist_viewpager.setAdapter(requestListAdapter);


        return view;

    }
}
