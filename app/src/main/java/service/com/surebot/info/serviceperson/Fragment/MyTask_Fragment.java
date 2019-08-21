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

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.Adapter.HomePackage_Adapter;
import service.com.surebot.info.serviceperson.Adapter.TodaysTask_Adapter;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.UserAddress_Location;

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
        gUserName_List.add("Sahana");
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gPackage_recyclerview.setLayoutManager(layoutManager);


        HomePackage_Adapter lHomePackage_Adapter = new HomePackage_Adapter(getActivity(),gPackages_List);
        gPackage_recyclerview.setAdapter(lHomePackage_Adapter);



        List<UserAddress_Location> items = new ArrayList<>();

      /*  items.add(new UserAddress_Location("Item 1"));
        items.add(new UserAddress_Location("Item 2"));
        items.add(new UserAddress_Location(new LatLng(1.289545, 103.849972)));
        items.add(new UserAddress_Location("Item 3"));

        items.add(new UserAddress_Location("Item 4"));
        items.add(new UserAddress_Location("Item 5"));
        items.add(new UserAddress_Location("Item 6"));
        items.add(new UserAddress_Location("Item 7"));
        items.add(new UserAddress_Location("Item 8"));
        items.add(new UserAddress_Location("Item 9"));*/

        ArrayList<String> gTimeslotArraylist = new ArrayList<>();
        gTimeslotArraylist.add("14.22262, 76.40038");
        gTimeslotArraylist.add("1.289545, 103.849972");
        gTimeslotArraylist.add("14.22262, 76.40038");
        gTimeslotArraylist.add("1.289545, 103.849972");
        UserAddress_Location lUserAddress_Location = new UserAddress_Location();
        /*lUserAddress_Location.setPosition(new LatLng(14.22262, 76.40038));
        lUserAddress_Location.setPosition(new LatLng(1.289545, 103.849972));
        lUserAddress_Location.setPosition(new LatLng(14.22262, 76.40038));
        lUserAddress_Location.setPosition(new LatLng(14.22262, 76.40038));*/

        //Todays Task List
        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        gTodaytask_recyclerview.setLayoutManager(llm);


        for(int i=0;i<gTimeslotArraylist.size();i++){
    lUserAddress_Location.setLatitutde(gTimeslotArraylist.get(i));

    items.add(lUserAddress_Location);

            TodaysTask_Adapter lTodaysTask_Adapter = new TodaysTask_Adapter(getActivity(),items,gUserName_List);
            gTodaytask_recyclerview.setAdapter(lTodaysTask_Adapter);

            lTodaysTask_Adapter.notifyDataSetChanged();
}


       /* items.add(new UserAddress_Location(new LatLng(14.22262, 76.40038)));
        items.add(new UserAddress_Location(new LatLng(1.289545, 103.849972)));
        items.add(new UserAddress_Location(new LatLng(1.289545, 103.849972)));
        items.add(new UserAddress_Location(new LatLng(1.289545, 103.849972)));*/





        return view;

    }  //onCreateView Closed
}
