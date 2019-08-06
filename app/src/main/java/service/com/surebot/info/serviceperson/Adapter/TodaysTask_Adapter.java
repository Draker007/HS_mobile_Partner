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

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import service.com.surebot.info.serviceperson.R;




public class TodaysTask_Adapter extends RecyclerView.Adapter<TodaysTask_Adapter.MyViewHolder> {

    Context context;
    ArrayList<String> gUserName_List;

    public TodaysTask_Adapter(Context context,   ArrayList<String> gUserName_List) {
        this.context=context;
        this.gUserName_List=gUserName_List;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.todaytask_list, viewGroup, false);
        return new TodaysTask_Adapter.MyViewHolder(view);
        // return null;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {


        myViewHolder.lUserName_Text.setText(gUserName_List.get(position).toString());
        myViewHolder.lMore_Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewHolder.lMap_Layout.setVisibility(View.VISIBLE);
                myViewHolder.lMore_Text.setVisibility(View.GONE);
                myViewHolder.lSlide_Image.setVisibility(View.VISIBLE);

            }
        });

        myViewHolder.lSlide_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewHolder.lMap_Layout.setVisibility(View.GONE);
                myViewHolder.lMore_Text.setVisibility(View.VISIBLE);

            }
        });
    }

    @Override
    public int getItemCount() {
        return gUserName_List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lUserName_Text;
        TextView lMore_Text;


        LinearLayout lMap_Layout;

        CardView lMain_Layout;
        ImageView lSlide_Image;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lUserName_Text=itemView.findViewById(R.id.username_text);
            lMain_Layout=itemView.findViewById(R.id.main_layout);

            lMap_Layout=itemView.findViewById(R.id.map_layout);
            lMore_Text=itemView.findViewById(R.id.moreheader_text);
            lSlide_Image=itemView.findViewById(R.id.slide_iamge);

        }
    }


    public interface bannerslist_Communicator {
        void bannerslist(String serviceid);

    }

}