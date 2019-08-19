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

import service.com.surebot.info.serviceperson.R;



public class NewTaskRequestedServices_Adapter extends RecyclerView.Adapter<NewTaskRequestedServices_Adapter.MyViewHolder> {

    Context context;
    ArrayList<String> gservices_List;

    ArrayList<String> gSub_services_List = new ArrayList<>();

    public NewTaskRequestedServices_Adapter(Context context,   ArrayList<String> gservices_List) {
        this.context=context;
        this.gservices_List=gservices_List;

        gSub_services_List.add("sara fruite cleanup");
        gSub_services_List.add("hair cut");
        gSub_services_List.add("upperlip");
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
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myViewHolder.lSubservice_Recycler.setLayoutManager(llm);

        NewTaskSubServicesList_Adapter lNewTaskSubServicesList_Adapter = new NewTaskSubServicesList_Adapter(context,gSub_services_List);
        myViewHolder.lSubservice_Recycler.setAdapter(lNewTaskSubServicesList_Adapter);


    }

    @Override
    public int getItemCount() {
        return gservices_List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lSubservicename_text;
        EditText lQuantity_Text;
        RecyclerView lSubservice_Recycler;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lSubservicename_text=itemView.findViewById(R.id.subservicename_text);
            lQuantity_Text=itemView.findViewById(R.id.quantity_text);
            lSubservice_Recycler =itemView.findViewById(R.id.subservice_Recycler);






        }
    }




}