package service.com.surebot.info.serviceperson.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import service.com.surebot.info.serviceperson.R;




public class NewRequests_Adapter extends RecyclerView.Adapter<NewRequests_Adapter.MyViewHolder> {

    Context context;
    ArrayList<String> gUserName_List;

    ArrayList<String> gservices_List = new ArrayList<>();

    public NewRequests_Adapter(Context context,   ArrayList<String> gUserName_List) {
        this.context=context;
        this.gUserName_List=gUserName_List;

        gservices_List.add("sara fruite cleanup");
        gservices_List.add("hair cut");
        gservices_List.add("upperlip");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.newrequestadapter_list, viewGroup, false);
        return new NewRequests_Adapter.MyViewHolder(view);
        // return null;
    }

    @SuppressLint({"ResourceAsColor", "WrongConstant"})
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {


        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myViewHolder.lRequestedservicelist_recyclerview.setLayoutManager(llm);


        NewTaskRequestedServices_Adapter lNewTaskRequestedServices_Adapter = new NewTaskRequestedServices_Adapter(context,gservices_List);
        myViewHolder.lRequestedservicelist_recyclerview.setAdapter(lNewTaskRequestedServices_Adapter);


        myViewHolder.lUserName_Text.setText(gUserName_List.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return gUserName_List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lUserName_Text;
        TextView lDate_Text;
        TextView lUserAddress_Text;
        TextView lTime_Text;
        TextView lUserPhonenumber_Text;
        RecyclerView lRequestedservicelist_recyclerview;



        CardView lMain_Layout;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lUserName_Text=itemView.findViewById(R.id.username_text);
            lDate_Text=itemView.findViewById(R.id.username_text);
            lUserAddress_Text=itemView.findViewById(R.id.username_text);
            lTime_Text=itemView.findViewById(R.id.username_text);
            lUserPhonenumber_Text=itemView.findViewById(R.id.username_text);
            lRequestedservicelist_recyclerview=itemView.findViewById(R.id.requestedservicelist_recyclerview);


            lMain_Layout=itemView.findViewById(R.id.main_layout);



        }
    }




}