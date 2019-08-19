package service.com.surebot.info.serviceperson.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import service.com.surebot.info.serviceperson.Adapter.paymentParentAdapter;
import service.com.surebot.info.serviceperson.DataFiles.paymentChildData;
import service.com.surebot.info.serviceperson.DataFiles.paymentParentData;
import service.com.surebot.info.serviceperson.R;

public class PendingPaymentFragment extends Fragment {
    RecyclerView r1;
    paymentParentAdapter adapter;
    View view;
    List<paymentParentData> paymentParentDataList= new ArrayList<>();
    List<paymentChildData> paymentChildDataList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.payment_fragment, container, false);
        r1 = view.findViewById(R.id.paymentRecycler);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
        adapter = new paymentParentAdapter( paymentParentDataList);
        r1.setLayoutManager(lm);
        paymentChildDataList.add(new paymentChildData("asd","asd","asd"));
        paymentChildDataList.add(new paymentChildData("asd","asd","asd"));
        paymentChildDataList.add(new paymentChildData("asd","asd","asd"));
        Log.e("lola", "onCreateView: "+paymentChildDataList );
        paymentParentDataList.add(new paymentParentData("add","dddd","aasd","dwdw","weew","asdd",paymentChildDataList));
        paymentChildDataList.clear();
        Log.e("lola", "onCreateView: "+paymentChildDataList );
        paymentChildDataList.add(new paymentChildData("asd","asd","asd"));
        paymentChildDataList.add(new paymentChildData("as1231d","a3123sd","as1233d"));
        paymentChildDataList.add(new paymentChildData("asd","asd","asd"));
        paymentParentDataList.add(new paymentParentData("aasdasdd","qweqdddd","aasd123123","dw33333dw","weew","asdd",paymentChildDataList));
        paymentChildDataList.clear();

        paymentChildDataList.add(new paymentChildData("asd","asd","asd"));
        paymentChildDataList.add(new paymentChildData("asd","asd","asd"));
        paymentChildDataList.add(new paymentChildData("asd","asd","asd"));
        paymentParentDataList.add(new paymentParentData("add","dddd","aasd","dwdw","weew","asdd",paymentChildDataList));
        paymentChildDataList.clear();

        paymentChildDataList.add(new paymentChildData("asd","asd","asd"));
        paymentChildDataList.add(new paymentChildData("asd","asd","asd"));
        paymentChildDataList.add(new paymentChildData("asd","asd","asd"));
        paymentParentDataList.add(new paymentParentData("add","dddd","aasd","dwdw","weew","asdd",paymentChildDataList));
        paymentChildDataList.clear();
        r1.setAdapter(adapter);
        Log.e("lola", "onCreateView: ");



        return view;
    }
}
