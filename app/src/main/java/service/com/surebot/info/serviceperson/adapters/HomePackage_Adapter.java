package service.com.surebot.info.serviceperson.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.ResponseClass.Partner_package_Response;


public class HomePackage_Adapter extends RecyclerView.Adapter<HomePackage_Adapter.MyViewHolder> {

    Context context;
    //private Dialog progress;
    int i=0;
    ArrayList<Partner_package_Response.Partner_package_records> partner_package_response ;


    Buypackage_Communicator communicator;

    public HomePackage_Adapter(Context context ) {
        this.context = context;
    }

    // List Package API


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.homepackage_list, viewGroup, false);

        return new HomePackage_Adapter.MyViewHolder(view);
        // return null;
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {

    }



    public void setBuypackage_Communicator(Buypackage_Communicator communicator) {
        this.communicator = communicator;
    }



    @Override
    public int getItemCount() {
        return 4;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView packageBG;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            packageBG= itemView.findViewById(R.id.packageImage);


        }
    }


    public interface Buypackage_Communicator {
        void  buypackage(String packageid);

    }

}