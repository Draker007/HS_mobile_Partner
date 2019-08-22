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




public class NewTaskSubServicesList_Adapter extends RecyclerView.Adapter<NewTaskSubServicesList_Adapter.MyViewHolder> {

    Context context;


    ArrayList<String> gSub_services_List;

    public NewTaskSubServicesList_Adapter(Context context,   ArrayList<String> gSub_services_List) {
        this.context=context;
        this.gSub_services_List=gSub_services_List;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.newtasksubservices_adaterlist, viewGroup, false);
        return new NewTaskSubServicesList_Adapter.MyViewHolder(view);
        // return null;
    }

    @SuppressLint({"ResourceAsColor", "WrongConstant"})
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
        myViewHolder.lSubservicename_text.setText(gSub_services_List.get(position).toString());



    }

    @Override
    public int getItemCount() {
        return gSub_services_List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        EditText lQuantity_Text;
        TextView  lSubservicename_text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lQuantity_Text=itemView.findViewById(R.id.quantity_text);

            lSubservicename_text=itemView.findViewById(R.id.subservicename_text);





        }
    }




}