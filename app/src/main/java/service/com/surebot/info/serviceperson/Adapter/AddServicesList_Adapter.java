package service.com.surebot.info.serviceperson.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import service.com.surebot.info.serviceperson.Activity.ServicesAdd_Activity;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.ResponseClass.ListOfServices_Response;


public class AddServicesList_Adapter extends RecyclerView.Adapter<AddServicesList_Adapter.MyViewHolder> {

    Context context;
    ArrayList<ListOfServices_Response.ListOfServices_Records> gServicesList_Arraylist;

    private  int lastItemClicked = 0;

    serviceslist_Communicator Communicator;

    public AddServicesList_Adapter(ServicesAdd_Activity context, ArrayList<ListOfServices_Response.ListOfServices_Records> gServicesList_Arraylist) {
        this.context=context;
        this.gServicesList_Arraylist=gServicesList_Arraylist;
        this.Communicator=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.addservicesadapter_list, viewGroup, false);
        return new AddServicesList_Adapter.MyViewHolder(view);
        // return null;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {


        myViewHolder.lServicesName_Text.setText(gServicesList_Arraylist.get(position).getService_Name());


        if (position == lastItemClicked) {

              /*  myViewHolder.lTimeSlot_Text.setBackgroundColor(Color.parseColor("#F96F07"));
                myViewHolder.lTimeSlot_Text.setTextColor(Color.WHITE);*/

            myViewHolder.lServicesName_Text.setBackgroundResource(R.drawable.serviceselected_background);
            myViewHolder.lServicesName_Text.setTextColor(context.getResources().getColor(R.color.color_white));

        } else {
               /* myViewHolder.lTimeSlot_Text.setBackgroundColor(Color.WHITE);
                myViewHolder.lTimeSlot_Text.setTextColor(Color.BLACK);*/

            myViewHolder.lServicesName_Text.setBackgroundResource(R.drawable.quantity_background);
            myViewHolder.lServicesName_Text.setTextColor(context.getResources().getColor(R.color.color_black));
        }

        myViewHolder.lMain_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lastItemClicked = position;
                notifyItemRangeChanged(0, gServicesList_Arraylist.size());
                Communicator.servicesslist(gServicesList_Arraylist.get(position).getService_ID());
            }
        });

    }

    @Override
    public int getItemCount() {
        return gServicesList_Arraylist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lServicesName_Text;
        LinearLayout lMain_layout;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lServicesName_Text=itemView.findViewById(R.id.servicename_text);
            lMain_layout=itemView.findViewById(R.id.main_layout);

        }
    }


    public interface serviceslist_Communicator {
        void servicesslist(String serviceid);

    }

}