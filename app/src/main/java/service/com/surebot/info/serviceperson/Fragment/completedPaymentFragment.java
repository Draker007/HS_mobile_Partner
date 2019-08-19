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

public class completedPaymentFragment extends Fragment {
    View view;
    RecyclerView r1;
    paymentParentAdapter adapter;
    List<paymentParentData> paymentParentDataList= new ArrayList<>();
    List<paymentChildData> paymentChildDataList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.payment_fragment, container, false);
        r1 = view.findViewById(R.id.paymentRecycler);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());

        r1.setLayoutManager(lm);
        paymentChildDataList.add(new paymentChildData("asd","asd","asd"));
        paymentChildDataList.add(new paymentChildData("asd","asd","asd"));
        paymentChildDataList.add(new paymentChildData("asd","asd","asd"));
        paymentParentDataList.add(new paymentParentData("add","dddd","aasd","dwdw","weew","asdd",paymentChildDataList));
        paymentChildDataList.clear();
        paymentChildDataList.add(new paymentChildData("asd","asd","asd"));
        paymentChildDataList.add(new paymentChildData("asd","asd","asd"));
        paymentChildDataList.add(new paymentChildData("asd","asd","asd"));
        paymentParentDataList.add(new paymentParentData("ad123131d","31231231dddd","aas3123123d","dw123123dw","weew","asdd",paymentChildDataList));
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
        
        Log.e("lola", "onCreateView: ");
        adapter = new paymentParentAdapter( paymentParentDataList);
        r1.setAdapter(adapter);


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
