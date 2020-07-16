package service.com.surebot.info.serviceperson.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import service.com.surebot.info.serviceperson.R;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder>{

    Context context;
    ArrayList<String> state = new ArrayList<>();
    ArrayList<String> noCity = new ArrayList<>();




    public CityAdapter(Context context, ArrayList<String> state, ArrayList<String> noCity) {
        this.context = context;
        this.state = state;
        this.noCity = noCity;
        Log.e("hmhm", "CityAdapter: "+state+noCity );
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_adapter_list, parent, false);
        return new CityAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.cities.setText(noCity.get(position) + " Cities Selected ");
        holder.states.setText(state.get(position));
        holder.RemoveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                removeAt(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return state.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView states ;
        TextView cities;
        TextView RemoveText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cities = itemView.findViewById(R.id.noOfCities);
            states = itemView.findViewById(R.id.stateName);
            RemoveText= itemView.findViewById(R.id.removetext);

        }
    }

    public void removeAt(int position) {
        state.remove(position);
        noCity.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, state.size());
        notifyItemRangeChanged(position, noCity.size());
    }
}
