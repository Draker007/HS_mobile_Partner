package service.com.surebot.info.serviceperson.Adapter;

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
import service.com.surebot.info.serviceperson.ResponseClass.UpcomingRequestList_Response;



public class CancelledRequest_Adapter extends RecyclerView.Adapter<CancelledRequest_Adapter.MyViewHolder> {

    Context context;
    ArrayList<CancelledRequestList_Response.CancelledRequestList_Records> gCancelledRequestList_Arraylist;

    public CancelledRequest_Adapter(Context context,   ArrayList<CancelledRequestList_Response.CancelledRequestList_Records> gCancelledRequestList_Arraylist) {
        this.context=context;
        this.gCancelledRequestList_Arraylist=gCancelledRequestList_Arraylist;
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


        myViewHolder.lUserName_Text.setText(gCancelledRequestList_Arraylist.get(position).getUser_Name());
        myViewHolder.lUserAddress_text.setText(gCancelledRequestList_Arraylist.get(position).getUser_Full_Address());
        myViewHolder.lDate_text.setText(gCancelledRequestList_Arraylist.get(position).getBooking_Date());
        myViewHolder.lTime_text.setText(gCancelledRequestList_Arraylist.get(position).getBooking_Start_Time());
        myViewHolder.lUserphonenumber_text.setText(gCancelledRequestList_Arraylist.get(position).getPhone_location());
        myViewHolder.lRequestID_text.setText(gCancelledRequestList_Arraylist.get(position).getBooking_Id());
    }

    @Override
    public int getItemCount() {
        return gCancelledRequestList_Arraylist.size();
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