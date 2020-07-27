package service.com.surebot.info.serviceperson.adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import service.com.surebot.info.serviceperson.activities.SendQuoteActivity;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.ResponseClass.NewRequestList_Response;
import service.com.surebot.info.serviceperson.ApplicationClass;


public class NewRequests_Adapter extends RecyclerView.Adapter<NewRequests_Adapter.MyViewHolder> {

    Context context;

    ArrayList<NewRequestList_Response.NewRequestList_Response_Records> gNewservicesRequest_List ;

    private Dialog progress;


    ArrayList<String > gServiceName_List= new ArrayList<>();
    ServiceList_Communicator communicator;


    NewTaskSubServicesList_Adapter adapter;
    String quote = "";
    String amnt = "";
    String gTimeForUI;
    String gPremiumPartner_Id = ApplicationClass.getPremium_PartenerId();
    public NewRequests_Adapter(Context context ) {
        this.context=context;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.upcomingrequestadapter_list, viewGroup, false);
        return new NewRequests_Adapter.MyViewHolder(view);
        // return null;
    }

    @SuppressLint({"ResourceAsColor", "WrongConstant"})
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {


        myViewHolder.requestid_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, SendQuoteActivity.class));
            }
        });
    }
    public void setServiceList_Communicator(ServiceList_Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView requestid_text;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            requestid_text = itemView.findViewById(R.id.requestid_text);
        }
    }


    public interface ServiceList_Communicator {
        void addquotationlist( ArrayList<String> finalmapingIdList, ArrayList<String> finalammountList,String StatusId);



    }

}