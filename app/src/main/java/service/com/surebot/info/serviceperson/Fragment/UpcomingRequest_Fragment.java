package service.com.surebot.info.serviceperson.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.adapters.UpcomingRequest_Adapter;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.ResponseClass.UpcomingRequestList_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class UpcomingRequest_Fragment  extends Fragment {


    @BindView(R.id.newrequestlist_recyclerview)
    RecyclerView gUpcomingrequestlist_recyclerview;
    @BindView(R.id.norequest_text)
    TextView gNorequest_text;


    LinearLayoutManager llm;


    private Dialog progress;
    ArrayList<UpcomingRequestList_Response.UpcomingRequestList_Records> gUpcomingRequestList_Arraylist;
    String gUserId_FromLogin;

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.newrequestlist_layout, container, false);
        ButterKnife.bind(this, view);

        progress = new Dialog(getActivity(), android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);

        gUserId_FromLogin = AppicationClass.getUserId_FromLogin();


        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        gUpcomingrequestlist_recyclerview.setLayoutManager(llm);
        UpcomingRequest_Adapter lUpcomingRequest_Adapter = new UpcomingRequest_Adapter(getActivity()  );
        gUpcomingrequestlist_recyclerview.setAdapter(lUpcomingRequest_Adapter);

        return view;
    }

    //Get Upcomiing Request List

}
