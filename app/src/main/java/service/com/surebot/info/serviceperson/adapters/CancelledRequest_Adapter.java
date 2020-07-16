package service.com.surebot.info.serviceperson.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.ResponseClass.CancelledRequestList_Response;


public class CancelledRequest_Adapter extends RecyclerView.Adapter<CancelledRequest_Adapter.MyViewHolder> {

    Context context;
    ArrayList<CancelledRequestList_Response.CancelledRequestList_Records> gCancelledRequestList_Arraylist;
String gTimeForUI;
    public CancelledRequest_Adapter(Context context ) {
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.upcomingrequestadapter_list, viewGroup, false);
        return new CancelledRequest_Adapter.MyViewHolder(view);
        // return null;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lUserName_Text;
        TextView lDate_text;
        TextView lTime_text;
        TextView lUserAddress_text;
        TextView lUserphonenumber_text;
        TextView lRequestID_text;


        CardView lMain_Layout;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lUserName_Text=itemView.findViewById(R.id.username_text);
            lDate_text=itemView.findViewById(R.id.date_text);
            lTime_text=itemView.findViewById(R.id.time_text);
            lUserAddress_text=itemView.findViewById(R.id.useraddress_text);
            lUserphonenumber_text=itemView.findViewById(R.id.userphonenumber_text);
            lRequestID_text=itemView.findViewById(R.id.requestid);


            lMain_Layout=itemView.findViewById(R.id.main_layout);



        }
    }



}