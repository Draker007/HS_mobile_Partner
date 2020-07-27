package service.com.surebot.info.serviceperson.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.adapters.RequestListAdapter;
import service.com.surebot.info.serviceperson.R;

public class RequestListFragment extends Fragment {

    @BindView(R.id.allrequestlist_tablayout)
    TabLayout gAllrequestlist_tablayout;

    @BindView(R.id.allrequestlist_viewpager)
    ViewPager gAllrequestlist_viewpager;

    RequestListAdapter gRequestList_Adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.allrequestlist_layout, container, false);
        ButterKnife.bind(this, view);


        gAllrequestlist_tablayout.setupWithViewPager(gAllrequestlist_viewpager);
        // gViewpager_Adapter = new MyBookingTab_Adapter(getActivity().getSupportFragmentManager());
        gRequestList_Adapter = new RequestListAdapter(getChildFragmentManager());
        gRequestList_Adapter.AddFragment(new NewRequest_Fragment(), "New");
        gRequestList_Adapter.AddFragment(new UpcomingRequestFragment(), "Upcoming");
        gRequestList_Adapter.AddFragment(new CompletedRequestFragment(), "Completed");
        gRequestList_Adapter.AddFragment(new CancelledRequestFragment(), "Cancelled");
        gAllrequestlist_viewpager.setAdapter(gRequestList_Adapter);


        return view;

    }
}
