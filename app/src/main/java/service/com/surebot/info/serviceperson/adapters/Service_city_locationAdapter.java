package service.com.surebot.info.serviceperson.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class Service_city_locationAdapter extends RecyclerView.Adapter<Service_city_locationAdapter.MyViewHolder>{

        Context context;
        ArrayList<String> gAreaName_List;
    ArrayList<String> location_response;
    public Service_city_locationAdapter(Context context, ArrayList<String> gAreaName_List, ArrayList<String> location_response) {
        this.context = context;
        this.gAreaName_List = gAreaName_List;
        this.location_response = location_response;
    }

    @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.serviceslocationadapter_list, viewGroup, false);
            return new Service_city_locationAdapter.MyViewHolder(view);
            // return null;
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
            myViewHolder.lAreaname_text.setText(gAreaName_List.get(position).toString());
            myViewHolder.Selectarea_checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(AppicationClass.addLocation.contains(location_response.get(position))){
                        AppicationClass.addLocation.remove(location_response.get(position));
                    }else
                        AppicationClass.addLocation.add(location_response.get(position));
                }

            });

        }

        @Override
        public int getItemCount() {
            return gAreaName_List.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView lAreaname_text;
            CheckBox Selectarea_checkbox ;


            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                lAreaname_text=itemView.findViewById(R.id.areaname_text);
                Selectarea_checkbox = itemView.findViewById(R.id.selectarea_checkbox);

            }
        }




    }

