package service.com.surebot.info.serviceperson.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.Partner_package_Request;
import service.com.surebot.info.serviceperson.ResponseClass.Partner_package_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class HomePackage_Adapter extends RecyclerView.Adapter<HomePackage_Adapter.MyViewHolder> {

    Context context;
    //private Dialog progress;
    int i=0;
    ArrayList<Partner_package_Response.Partner_package_records> partner_package_response ;


    Buypackage_Communicator communicator;

    public HomePackage_Adapter(Context context, ArrayList<Partner_package_Response.Partner_package_records> partner_package_response) {
        this.context = context;
        this.partner_package_response = partner_package_response;
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
        Log.e("darker", "onBindViewHolder: "+position );
        myViewHolder.gHPService.setText(partner_package_response.get(position).getServices());
        myViewHolder.gHPCost.setText(partner_package_response.get(position).getCost());
        if (position%2==0){
            myViewHolder.packageBG.setCardBackgroundColor(Color.parseColor("#673AB7"));
        }else if(position%3==0){
            myViewHolder.packageBG.setCardBackgroundColor(Color.parseColor("#38A1F4"));
        }else{
            myViewHolder.packageBG.setCardBackgroundColor(Color.parseColor("#E1710F"));
        }

//        myViewHolder.lMain_Layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });

        myViewHolder.lBuyPackage_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                communicator.buypackage(partner_package_response.get(position).getPackage_ID());
            }
        });
    }



    public void setBuypackage_Communicator(Buypackage_Communicator communicator) {
        this.communicator = communicator;
    }

    private void CallAPIforPackage() {

    }

    @Override
    public int getItemCount() {
        return partner_package_response.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView gHPCost,gHPService;
        CardView packageBG;
        Button lBuyPackage_Button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            gHPCost= itemView.findViewById(R.id.HPCost);
            gHPService=itemView.findViewById(R.id.HPServices);
           packageBG=itemView.findViewById(R.id.packageBG);
            lBuyPackage_Button=itemView.findViewById(R.id.buy_button);

        }
    }


    public interface Buypackage_Communicator {
        void  buypackage(String packageid);

    }

}