package service.com.surebot.info.serviceperson.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.SyncStateContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import service.com.surebot.info.serviceperson.R;


public class HomePackage_Adapter extends RecyclerView.Adapter<HomePackage_Adapter.MyViewHolder> {

    Context context;
    ArrayList<Integer> gPackages_List;

    public HomePackage_Adapter(Context context,    ArrayList<Integer> gPackages_List) {
        this.context=context;
        this.gPackages_List=gPackages_List;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.homepackage_list, viewGroup, false);
        return new HomePackage_Adapter.MyViewHolder(view);
        // return null;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {


        Glide.with(context).load(gPackages_List.get(position)).into(myViewHolder.lPackage_Image);
        myViewHolder.lMain_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return gPackages_List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView lPackage_Image;

        RelativeLayout lMain_Layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lPackage_Image=itemView.findViewById(R.id.package_image);
            lMain_Layout=itemView.findViewById(R.id.main_layout);

        }
    }




}