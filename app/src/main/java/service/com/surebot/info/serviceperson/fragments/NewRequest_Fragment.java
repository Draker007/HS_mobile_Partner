package service.com.surebot.info.serviceperson.fragments;

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
import service.com.surebot.info.serviceperson.adapters.NewRequests_Adapter;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.ResponseClass.NewRequestList_Response;
import service.com.surebot.info.serviceperson.ApplicationClass;

public class NewRequest_Fragment  extends Fragment  {


    @BindView(R.id.newrequestlist_recyclerview)
    RecyclerView gNewrequestlist_recyclerview;
    @BindView(R.id.norequest_text)
    TextView gNorequest_text;

    LinearLayoutManager llm;




    private Dialog progress;

    ArrayList<NewRequestList_Response.NewRequestList_Response_Records> gNewRequestList_Arraylist;
    String gUserId_FromLogin;

    String gStatus_Id;

    String gFinalServicesMaping_Id,gFinalServices_Ammount;

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


        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        gNewrequestlist_recyclerview.setLayoutManager(llm);

        gUserId_FromLogin = ApplicationClass.getUserId_FromLogin();


        NewRequests_Adapter NewRequests_Adapter= new NewRequests_Adapter(getActivity());
        gNewrequestlist_recyclerview.setAdapter(NewRequests_Adapter);

        return view;
    }
    // First New Request List





    //Send Or Reject Qoute to Use



    }
