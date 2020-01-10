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

import service.com.surebot.info.serviceperson.Activity.ServicesAdd_Activity;
import service.com.surebot.info.serviceperson.R;

public class RadiusAdapter extends RecyclerView.Adapter<RadiusAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> zip = new ArrayList<>();
    ArrayList<String> km = new ArrayList<>();
    String TAG = "hihi";

    radiuslistremove_Communicator communicator;

    public RadiusAdapter(ServicesAdd_Activity context, ArrayList<String> zip, ArrayList<String> km) {
        this.context = context;
        this.zip = zip;
        this.km = km;
        this.communicator= context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.radius_adapter_list, parent, false);
        return new RadiusAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
           holder.lRadiusKms.setText(km.get(position)+" Kms ");
           holder.lZipCode.setText(zip.get(position));
        holder.lRemoveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


communicator.removeradiuslist(km.get(position),zip.get(position),position);
                removeAt(position);

            }
        });


    }

    @Override
    public int getItemCount() {
        return km.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lZipCode,lRadiusKms,lRemoveText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lZipCode = itemView.findViewById(R.id.zipCodetxt);
            lRadiusKms=itemView.findViewById(R.id.kmtxt);
            lRemoveText=itemView.findViewById(R.id.removetxt);

        }
    }


    public interface radiuslistremove_Communicator {
        void removeradiuslist(String zipcode, String kms, int position);

    }

    public void removeAt(int position) {
        zip.remove(position);
        km.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, zip.size());
        notifyItemRangeChanged(position, km.size());
    }
}
