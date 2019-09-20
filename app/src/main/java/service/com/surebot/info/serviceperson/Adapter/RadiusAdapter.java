package service.com.surebot.info.serviceperson.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import service.com.surebot.info.serviceperson.R;

public class RadiusAdapter extends RecyclerView.Adapter<RadiusAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> zip = new ArrayList<>();
    ArrayList<String> km = new ArrayList<>();
    String TAG = "hihi";

    public RadiusAdapter(Context context, ArrayList<String> zip, ArrayList<String> km) {
        this.context = context;
        this.zip = zip;
        this.km = km;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.radius_adapter_list, parent, false);
        return new RadiusAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
holder.lRadiusKms.setText(km.get(position));
holder.lZipCode.setText(zip.get(position));

    }

    @Override
    public int getItemCount() {
        return km.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lZipCode,lRadiusKms;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lZipCode = itemView.findViewById(R.id.zipCodetxt);
            lRadiusKms=itemView.findViewById(R.id.kmtxt);


        }
    }
}
