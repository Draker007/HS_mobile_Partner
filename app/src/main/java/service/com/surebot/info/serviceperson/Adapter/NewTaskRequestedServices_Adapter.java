package service.com.surebot.info.serviceperson.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.ResponseClass.NewRequestListDetails_Response;
import service.com.surebot.info.serviceperson.utils.SendquotetoUser;


public class NewTaskRequestedServices_Adapter extends RecyclerView.Adapter<NewTaskRequestedServices_Adapter.MyViewHolder> {

    Context context;
    ArrayList<String> gservices_List;

    ArrayList<NewRequestListDetails_Response.NewRequestserviceDetails_Records> gNewRequestList_Arraylist;

    ArrayList<String > gServiceName_List = new ArrayList<>();
    private List<SendquotetoUser> gSendquotetoUserList;


    public NewTaskRequestedServices_Adapter(Context context,  ArrayList<String > gServiceName_List,  ArrayList<NewRequestListDetails_Response.NewRequestserviceDetails_Records> gNewRequestList_Arraylist) {
        this.context=context;
        this.gServiceName_List=gServiceName_List;
        this.gNewRequestList_Arraylist=gNewRequestList_Arraylist;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.newrequestedadapter_list, viewGroup, false);
        return new NewTaskRequestedServices_Adapter.MyViewHolder(view);
        // return null;
    }

    @SuppressLint({"ResourceAsColor", "WrongConstant"})
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {

        myViewHolder.lServicename_text.setText(gServiceName_List.get(position).toString());
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myViewHolder.lSubservice_Recycler.setLayoutManager(llm);


        ArrayList<SendquotetoUser> lSub_ServicesList = new ArrayList<>();
        for (int i=0;i<gNewRequestList_Arraylist.size();i++){
            if(gNewRequestList_Arraylist.get(i).getService_Name().equals(gServiceName_List.get(position))){
               // lSub_ServicesList.add(gNewRequestList_Arraylist.get(i).getSub_Service_Name());

                SendquotetoUser a = new SendquotetoUser(gNewRequestList_Arraylist.get(i).getSub_Service_Name(),gNewRequestList_Arraylist.get(i).getTransaction_Partner_Quote_ID(),gNewRequestList_Arraylist.get(i).getQuantity_Amount());
                lSub_ServicesList.add(a);
               // lSub_ServicesList.add(gNewRequestList_Arraylist.get(i).getSub_Service_Name(),gNewRequestList_Arraylist.get(i).getTransaction_Partner_Quote_ID(),gNewRequestList_Arraylist.get(i).getQuantity_Amount())
            }
        }
        NewTaskSubServicesList_Adapter lNewTaskSubServicesList_Adapter = new NewTaskSubServicesList_Adapter(context,lSub_ServicesList);
        myViewHolder.lSubservice_Recycler.setAdapter(lNewTaskSubServicesList_Adapter);





    }

    @Override
    public int getItemCount() {
        return gServiceName_List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lServicename_text;

        RecyclerView lSubservice_Recycler;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lServicename_text=itemView.findViewById(R.id.servicename_text);

            lSubservice_Recycler =itemView.findViewById(R.id.subservice_Recycler);






        }
    }




}