package service.com.surebot.info.serviceperson.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
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
import java.util.ArrayList;
import java.util.Arrays;
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
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.NewRequestListDetails_Request;
import service.com.surebot.info.serviceperson.ResponseClass.NewRequestListDetails_Response;
import service.com.surebot.info.serviceperson.ResponseClass.NewRequestList_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;
import service.com.surebot.info.serviceperson.utils.SendquotetoUser;


public class NewRequests_Adapter extends RecyclerView.Adapter<NewRequests_Adapter.MyViewHolder> {

    Context context;

    ArrayList<NewRequestList_Response.NewRequestList_Response_Records> gNewservicesRequest_List ;

    private Dialog progress;


    ArrayList<String > gServiceName_List= new ArrayList<>();
    ServiceList_Communicator communicator;

    private  ArrayList<String> MappingSubService = new ArrayList<>();

    List<String> MappingIds = new ArrayList<>();

    NewTaskSubServicesList_Adapter adapter;

    public NewRequests_Adapter(Context context,   ArrayList<NewRequestList_Response.NewRequestList_Response_Records> gNewservicesRequest_List) {
        this.context=context;
        this.gNewservicesRequest_List=gNewservicesRequest_List;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.newrequestadapter_list, viewGroup, false);
        return new NewRequests_Adapter.MyViewHolder(view);
        // return null;
    }

    @SuppressLint({"ResourceAsColor", "WrongConstant"})
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {


        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myViewHolder.lRequestedservicelist_recyclerview.setLayoutManager(llm);





        myViewHolder.lUserName_Text.setText(gNewservicesRequest_List.get(position).getUser_Name());
        myViewHolder.lDate_Text.setText(gNewservicesRequest_List.get(position).getBooking_Date());
        myViewHolder.lUserAddress_Text.setText(gNewservicesRequest_List.get(position).getUser_Full_Address());
        myViewHolder.lUserPhonenumber_Text.setText(gNewservicesRequest_List.get(position).getPhone_location());
        myViewHolder.lTime_Text.setText(gNewservicesRequest_List.get(position).getBooking_Start_Time());
        myViewHolder.lSend_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("lol1", "onClick: "+ AppicationClass.test1);
                ArrayList<String > id =new ArrayList<>();
                ArrayList<String > price =new ArrayList<>();

                for (int p = 0 ; p<AppicationClass.test1.size();p++){
                    String[] list = AppicationClass.test1.get(p).split(",");
                    id.add(list[0]);
                    price.add(list[1]);
                }
                Log.e("lola", "onClick: "+id+"  "+price );
                ArrayList<String> finalPriceList = new ArrayList<>();
                ArrayList<String>finalmapingIdList = new ArrayList<>();

                int items=0;
                for ( int j = 0; j<price.size();j++)
                {
                    int i =1;
                    for (int check = 0 ; check<finalmapingIdList.size();check++){
                        if (!id.get(j).equals(finalmapingIdList.get(check))){
                            i = 1;
                        }else{
                            i = 0;
                        }
                    }
                    if (i == 1){
                        for (int o = id.size()-1 ; o>=0;o--){
                            if(id.get(j).equals(id.get(o))){

                                Log.e("asdasd", "onClick: "+ id.get(o) );
                                finalmapingIdList.add(items,id.get(o));
                                finalPriceList.add(items,price.get(o));
                                o=0;

                            }

                        }
                        items++;
                    }

                }
                Log.e("lol1", "onClick: "+finalmapingIdList+finalPriceList );
                if(finalmapingIdList.size()== MappingIds.size()){
                    AppicationClass.test1.clear();

                    communicator.addquotationlist(finalmapingIdList,finalPriceList,"1");

                }else{
                    Toast.makeText(context, "Please Fill Price For All Services", Toast.LENGTH_SHORT).show();
//                    int[] arr = new int[MappingIds.size()];
//                    for(int i=0; i<MappingIds.size(); i++) {
//                        arr[i] = Integer.parseInt(MappingIds.get(i));
//                    }
//                    int[] arr1 = new int[finalmapingIdList.size()];
//
//                    for(int i=0; i<finalmapingIdList.size(); i++) {
//                        arr[i] = Integer.parseInt(finalmapingIdList.get(i));
//                    }
//                    Arrays.sort(arr1);
//                    Arrays.sort(arr);
//                    String temp = null;
//                    for (int i = 0; i < MappingIds.size(); i++){
//                        if (arr1[i] != arr[i]) {
//                            temp = String.valueOf(arr[i]);
//                            break ;
//                        }
//                    }
//
//
//
//                    Toast.makeText(context, "Enter Price for " +MappingSubService.get(MappingIds.indexOf(temp)), Toast.LENGTH_SHORT).show();
//


                }
                 System.out.println("In Send Button " + finalmapingIdList + finalPriceList);


            }
        });

        System.out.println("Request Adapter value is " + gNewservicesRequest_List.get(position).getUser_Name() +gNewservicesRequest_List.get(position).getBooking_Date() );

        //Clicks on More text
        myViewHolder.lMore_Textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewHolder.lServicedetails_Layout.setVisibility(View.VISIBLE);
                myViewHolder.lMore_Textview.setVisibility(View.GONE);
                //For New Request Details
                //Api method starts

                try {

                    progress = new Dialog(context, android.R.style.Theme_Translucent);
                    progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    progress.setContentView(R.layout.progressbar_background);
                    progress.setCancelable(true);
                    progress.show();

                    OkHttpClient.Builder client = new OkHttpClient.Builder();
                    HttpLoggingInterceptor registrationInterceptor = new HttpLoggingInterceptor();
                    registrationInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    client.addInterceptor(registrationInterceptor);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Constants.BASE_URL)
                            .client(client.build())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    ApiInterface request = retrofit.create(ApiInterface.class);
                    NewRequestListDetails_Request lNewRequestListDetails_Request = new NewRequestListDetails_Request();
                    lNewRequestListDetails_Request.setUser_ID("11");
                    lNewRequestListDetails_Request.setTransaction_ID("5");
                    lNewRequestListDetails_Request.setDocket(Constants.TOKEN);

                    Call<NewRequestListDetails_Response> call = request.get_NewServiceRequestDetails(lNewRequestListDetails_Request);
                    call.enqueue(new Callback<NewRequestListDetails_Response>() {


                        @Override
                        public void onResponse(Call<NewRequestListDetails_Response> call, Response<NewRequestListDetails_Response> response) {
                            if (response.isSuccessful()) {
                                NewRequestListDetails_Response lNewRequestList_Response = response.body();
                                ArrayList<NewRequestListDetails_Response.NewRequestserviceDetails_Records> gNewRequestList_Arraylist =  new ArrayList<>(Arrays.asList(lNewRequestList_Response.getPartner_service_details_response()));

                                for(int i=0;i<gNewRequestList_Arraylist.size();i++){

                                    gServiceName_List.add(gNewRequestList_Arraylist.get(i).getService_Name());
                                    MappingIds.add(gNewRequestList_Arraylist.get(i).getService_Mapping_ID());
                                    MappingSubService.add(gNewRequestList_Arraylist.get(i).getSub_Service_Name());
                                }

                                Set<String> set = new HashSet<>(gServiceName_List);
                                gServiceName_List.clear();
                                gServiceName_List.addAll(set);

                                NewTaskRequestedServices_Adapter lNewTaskRequestedServices_Adapter = new NewTaskRequestedServices_Adapter(context,gServiceName_List,gNewRequestList_Arraylist);
                                myViewHolder.lRequestedservicelist_recyclerview.setAdapter(lNewTaskRequestedServices_Adapter);
                                myViewHolder.lButton_layout.setVisibility(View.VISIBLE);
                                myViewHolder.lQuotesend_text.setVisibility(View.VISIBLE);
                                progress.dismiss();
                            }
                            progress.dismiss();
                        }

                        @Override
                        public void onFailure(Call<NewRequestListDetails_Response> call, Throwable t) {
                            Toast.makeText(context, context.getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
                            progress.dismiss();
                        }
                    });
                }catch (Exception e) {

                    e.printStackTrace();
                    progress.dismiss();

                } //Api method close
            }
        });
//Clicks on Less twxt
        myViewHolder.lLess_Textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewHolder.lServicedetails_Layout.setVisibility(View.GONE);
                myViewHolder.lMore_Textview.setVisibility(View.VISIBLE);
            }
        });

        //Clicks on Send Button







        //Clicks on Reject Button
        myViewHolder.lReject_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public void setServiceList_Communicator(ServiceList_Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public int getItemCount() {
        return gNewservicesRequest_List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lUserName_Text;
        TextView lDate_Text;
        TextView lUserAddress_Text;
        TextView lTime_Text;
        TextView lUserPhonenumber_Text;
        RecyclerView lRequestedservicelist_recyclerview;
        TextView lMore_Textview;
        TextView lLess_Textview;
        LinearLayout lServicedetails_Layout;
        RelativeLayout lButton_layout;
        Button lSend_button;
        Button lReject_button;
        TextView lQuotesend_text;

        CardView lMain_Layout;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lUserName_Text=itemView.findViewById(R.id.username_text);
            lDate_Text=itemView.findViewById(R.id.date_text);
            lUserAddress_Text=itemView.findViewById(R.id.useraddress_text);
            lTime_Text=itemView.findViewById(R.id.time_text);
            lUserPhonenumber_Text=itemView.findViewById(R.id.userphonenumber_text);
            lRequestedservicelist_recyclerview=itemView.findViewById(R.id.requestedservicelist_recyclerview);
            lMore_Textview=itemView.findViewById(R.id.more_text);
            lLess_Textview=itemView.findViewById(R.id.less_text);
            lMain_Layout=itemView.findViewById(R.id.main_layout);
            lServicedetails_Layout=itemView.findViewById(R.id.servicedetails_layout);
            lButton_layout=itemView.findViewById(R.id.button_layout);
            lSend_button =itemView.findViewById(R.id.send_button);
            lReject_button =itemView.findViewById(R.id.reject_button);
            lQuotesend_text=itemView.findViewById(R.id.quotesend_text);
        }
    }


    public interface ServiceList_Communicator {
        void addquotationlist( ArrayList<String> finalmapingIdList, ArrayList<String> finalammountList,String StatusId);



    }


}