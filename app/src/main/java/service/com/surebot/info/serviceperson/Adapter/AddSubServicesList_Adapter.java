package service.com.surebot.info.serviceperson.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.ResponseClass.ListOfSubServices_Response;


public class AddSubServicesList_Adapter extends RecyclerView.Adapter<AddSubServicesList_Adapter.MyViewHolder> {

    Context context;
    ArrayList<ListOfSubServices_Response.ListOfSubServices_Records> gSubServicesList_Arraylist;

    private  int lastItemClicked = -1;

    public AddSubServicesList_Adapter(Context context,  ArrayList<ListOfSubServices_Response.ListOfSubServices_Records> gSubServicesList_Arraylist) {
        this.context=context;
        this.gSubServicesList_Arraylist=gSubServicesList_Arraylist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.addsubservicesadapter_list, viewGroup, false);
        return new AddSubServicesList_Adapter.MyViewHolder(view);
        // return null;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {


        myViewHolder.lSubServicesName_Text.setText(gSubServicesList_Arraylist.get(position).getService_Name());



    }

    @Override
    public int getItemCount() {
        return gSubServicesList_Arraylist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lSubServicesName_Text;
        CheckBox  lQuantityCheckBox;
        LinearLayout lMain_layout;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lSubServicesName_Text=itemView.findViewById(R.id.subservicename_text);
            lQuantityCheckBox=itemView.findViewById(R.id.selectquantity_checkbox);

        }
    }


    public interface bannerslist_Communicator {
        void bannerslist(String serviceid);

    }

}
