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

    public NewTaskRequestedServices_Adapter(Context context,   ArrayList<String> gservices_List) {
        this.context=context;
        this.gservices_List=gservices_List;
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

        myViewHolder.lSubservicename_text.setText(gservices_List.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return gservices_List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lSubservicename_text;
        EditText lQuantity_Text;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lSubservicename_text=itemView.findViewById(R.id.subservicename_text);
            lQuantity_Text=itemView.findViewById(R.id.quantity_text);







        }
    }




}