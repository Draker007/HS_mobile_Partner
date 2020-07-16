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
import service.com.surebot.info.serviceperson.adapters.CompletedRequest_Adapter;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.ResponseClass.CompletedRequestList_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class CompletedRequest_Fragment  extends Fragment {

    @BindView(R.id.newrequestlist_recyclerview)
    RecyclerView gCompletedrequestlist_recyclerview;
    @BindView(R.id.norequest_text)
    TextView gNorequest_text;


    LinearLayoutManager llm;
String gUserId_FromLogin;

    ArrayList<CompletedRequestList_Response.CompletedRequestList_Records> gCompeltedRequestList_Arraylist;
    private Dialog progress;
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
        gCompletedrequestlist_recyclerview.setLayoutManager(llm);

        CompletedRequest_Adapter lCompletedRequest_Adapter = new CompletedRequest_Adapter(getActivity()   );
        gCompletedrequestlist_recyclerview.setAdapter(lCompletedRequest_Adapter);


        return view;
    }




}
