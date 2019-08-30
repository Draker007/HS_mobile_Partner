package service.com.surebot.info.serviceperson.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.ResponseClass.Select_service_partner_Response;


public class AddServicesLocationlist_Adapter extends RecyclerView.Adapter<AddServicesLocationlist_Adapter.MyViewHolder> {

    Context context;
    ArrayList<String> gAreaName_List;

    ArrayList<Select_service_partner_Response.Location_Records> location_response=new ArrayList<>();

    public AddServicesLocationlist_Adapter(Context context, ArrayList<String> gAreaName_List, ArrayList<Select_service_partner_Response.Location_Records> location_response) {
        this.context = context;
        this.gAreaName_List = gAreaName_List;
        this.location_response = location_response;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.service_city_locationadapter_list, viewGroup, false);
        return new AddServicesLocationlist_Adapter.MyViewHolder(view);
        // return null;
    }

    @SuppressLint({"ResourceAsColor", "WrongConstant"})
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
        myViewHolder.lAreaname_text.setText(gAreaName_List.get(position).toString());
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myViewHolder.locationRecycler.setLayoutManager(llm);
        ArrayList<String> location_area = new ArrayList<>();
        ArrayList<String> gAreaName_List1 = new ArrayList<>();
        for (int i=0;i<location_response.size();i++){
           if(location_response.get(i).getCity_Name().equals(gAreaName_List.get(position))){
               location_area.add(location_response.get(i).getLocation_Name());
               gAreaName_List1.add(location_response.get(i).getLocation_ID());
           }
        }




        Service_city_locationAdapter lAddServicesLocationlist_Adapter = new Service_city_locationAdapter(context,location_area,gAreaName_List1);
        myViewHolder.locationRecycler.setAdapter(lAddServicesLocationlist_Adapter);



    }

    @Override
    public int getItemCount() {
        return gAreaName_List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lAreaname_text;
        RecyclerView locationRecycler;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            locationRecycler = itemView.findViewById(R.id.CityRecycler);
            lAreaname_text=itemView.findViewById(R.id.serviceCityName);


        }
    }




}