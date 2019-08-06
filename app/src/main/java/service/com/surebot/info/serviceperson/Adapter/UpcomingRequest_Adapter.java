package service.com.surebot.info.serviceperson.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import service.com.surebot.info.serviceperson.R;




public class UpcomingRequest_Adapter extends RecyclerView.Adapter<UpcomingRequest_Adapter.MyViewHolder> {

    Context context;
    ArrayList<String> gUserName_List;

    public UpcomingRequest_Adapter(Context context,   ArrayList<String> gUserName_List) {
        this.context=context;
        this.gUserName_List=gUserName_List;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.upcomingrequestadapter_list, viewGroup, false);
        return new UpcomingRequest_Adapter.MyViewHolder(view);
        // return null;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {


        myViewHolder.lUserName_Text.setText(gUserName_List.get(position).toString());
    ;
    }

    @Override
    public int getItemCount() {
        return gUserName_List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lUserName_Text;


        CardView lMain_Layout;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lUserName_Text=itemView.findViewById(R.id.username_text);
            lMain_Layout=itemView.findViewById(R.id.main_layout);



        }
    }



}