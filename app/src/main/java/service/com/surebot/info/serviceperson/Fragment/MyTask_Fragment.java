package service.com.surebot.info.serviceperson.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.Adapter.HomePackage_Adapter;
import service.com.surebot.info.serviceperson.Adapter.TodaysTask_Adapter;
import service.com.surebot.info.serviceperson.R;

public class MyTask_Fragment  extends Fragment {


    @BindView(R.id.package_recyclerview)
    RecyclerView gPackage_recyclerview;

    @BindView(R.id.todaytask_recyclerview)
    RecyclerView gTodaytask_recyclerview;




    ArrayList<Integer> gPackages_List;
    ArrayList<String> gUserName_List;

    LinearLayoutManager llm;


    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.mytask_fragment_layout, container, false);
        ButterKnife.bind(this, view);

        gPackages_List = new ArrayList<Integer>();
        gPackages_List.add(R.mipmap.package1);
        gPackages_List.add(R.mipmap.package2);
        gPackages_List.add(R.mipmap.package3);

        gUserName_List = new ArrayList<String>();
        gUserName_List.add("Aditi");
        gUserName_List.add("Sahana");
        gUserName_List.add("Aditi");

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gPackage_recyclerview.setLayoutManager(layoutManager);


        HomePackage_Adapter lHomePackage_Adapter = new HomePackage_Adapter(getActivity(),gPackages_List);
        gPackage_recyclerview.setAdapter(lHomePackage_Adapter);


        //Todays Task List
        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        gTodaytask_recyclerview.setLayoutManager(llm);

        TodaysTask_Adapter lTodaysTask_Adapter = new TodaysTask_Adapter(getActivity(),gUserName_List);
        gTodaytask_recyclerview.setAdapter(lTodaysTask_Adapter);

        return view;

    }  //onCreateView Closed
}
