package service.com.surebot.info.serviceperson.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.MapView;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.com.surebot.info.serviceperson.Activity.SendQuoteActivity;
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.NewRequestListDetails_Request;
import service.com.surebot.info.serviceperson.RequestClass.SendQuotetoUser_Request;
import service.com.surebot.info.serviceperson.ResponseClass.NewRequestListDetails_Response;
import service.com.surebot.info.serviceperson.ResponseClass.NewRequestList_Response;
import service.com.surebot.info.serviceperson.ResponseClass.SendQuotetoUser_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;
import service.com.surebot.info.serviceperson.utils.SendquotetoUser;


public class NewRequests_Adapter extends RecyclerView.Adapter<NewRequests_Adapter.MyViewHolder> {

    Context context;

    ArrayList<NewRequestList_Response.NewRequestList_Response_Records> gNewservicesRequest_List ;

    private Dialog progress;


    ArrayList<String > gServiceName_List= new ArrayList<>();
    ServiceList_Communicator communicator;


    NewTaskSubServicesList_Adapter adapter;
    String quote = "";
    String amnt = "";
    String gTimeForUI;
    String gPremiumPartner_Id = AppicationClass.getPremium_PartenerId();
    public NewRequests_Adapter(Context context ) {
        this.context=context;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.upcomingrequestadapter_list, viewGroup, false);
        return new NewRequests_Adapter.MyViewHolder(view);
        // return null;
    }

    @SuppressLint({"ResourceAsColor", "WrongConstant"})
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {


        myViewHolder.requestid_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, SendQuoteActivity.class));
            }
        });
    }
    public void setServiceList_Communicator(ServiceList_Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView requestid_text;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            requestid_text = itemView.findViewById(R.id.requestid_text);
        }
    }


    public interface ServiceList_Communicator {
        void addquotationlist( ArrayList<String> finalmapingIdList, ArrayList<String> finalammountList,String StatusId);



    }

}