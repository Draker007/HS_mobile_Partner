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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import service.com.surebot.info.serviceperson.Activity.NewServiceDetailsActivity;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.ResponseClass.Partner_my_task_today_response;
import service.com.surebot.info.serviceperson.utils.UserAddress_Location;

import static service.com.surebot.info.serviceperson.utils.UserAddress_Location.VIEW_MAP;



public class TodaysTask_Adapter extends RecyclerView.Adapter<TodaysTask_Adapter.MyViewHolder>{

    Context context;
    ArrayList<String> gUserName_List;
    //private final List<String> items;
    int i = 0,j=0;
    private SupportMapFragment mapFragment;

    String TAG = "cocacola";
    UserAddress_Location timeSlot =new UserAddress_Location();

    double latitude, longitude;

    startservicelist_Communicator communicator;
    String gTimeForUI;

    ArrayList<Partner_my_task_today_response.Partner_my_task_today_Records> lTodaysTask_Arraylist ;

    public TodaysTask_Adapter(Context context ) {
        /*Log.e(TAG, "TodaysTask_Adapter: "+items.get(0));
        Log.e(TAG, "TodaysTask_Adapter: "+items.get(1));*/
        this.context=context;
     //   this.items=items;



    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflatedView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.todaytask_list, viewGroup, false);
        return new MyViewHolder(inflatedView);
    }



    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Log.e(TAG, "onViewDetachedFromWindow: ");

     //   holder.removeMapFragment();

    }



    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder,  int position) {
//
//        latitude =Double.parseDouble(lTodaysTask_Arraylist.get(position).getLatitude_val()); //Double.parseDouble(split[0]);
//        longitude = Double.parseDouble(lTodaysTask_Arraylist.get(position).getLongitude_val());
//
//        myViewHolder.username_text.setText(lTodaysTask_Arraylist.get(position).getUser_Name());
//        myViewHolder.date_text.setText(lTodaysTask_Arraylist.get(position).getBooking_Date());
//
//        myViewHolder.useraddress_text.setText(lTodaysTask_Arraylist.get(position).getUser_Full_Address());
//        myViewHolder.userphonenumber_text.setText(lTodaysTask_Arraylist.get(position).getUser_Contact_Number());
//        myViewHolder.requestID_text.setText(lTodaysTask_Arraylist.get(position).getServices());
//
//
//        //Time Conversion
//
//        try {
//            DateFormat f = new SimpleDateFormat("HH:mm:ss");
//            Date d = f.parse(lTodaysTask_Arraylist.get(position).getBooking_Start_Time());
//            DateFormat date = new SimpleDateFormat("hh:ss a");
//
//            gTimeForUI=date.format(d);
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        myViewHolder.time_text.setText(gTimeForUI);
//
//
//
//
        myViewHolder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, NewServiceDetailsActivity.class));
            }
        });
//        myViewHolder.hide.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myViewHolder.map_layout.setVisibility(View.GONE);
//                myViewHolder.more.setVisibility(View.VISIBLE);
//            }
//        });
//
//        myViewHolder.start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//               // communicator.startservice(lTodaysTask_Arraylist.get(position).getTransaction_ID(),"1");
//
//
//
//                //startservice_popup
//
//                Dialog gStartService_Dialog;
//                gStartService_Dialog = new Dialog(context, R.style.dailogboxtheme);
//                gStartService_Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                gStartService_Dialog.getWindow().setBackgroundDrawableResource(R.color.color_transparen);
//                gStartService_Dialog.setContentView(R.layout.startservice_popup);
//
//                TextView lSend_text = gStartService_Dialog.findViewById(R.id.send_text);
//
//                Button lStartservicebyissueprice_button = gStartService_Dialog.findViewById(R.id.startservicebyissueprice_button);
//
//                Button lStartservicebyfinalprice_button = gStartService_Dialog.findViewById(R.id.startservicebyfinalprice_button);
//
//
//                lStartservicebyissueprice_button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        lStartservicebyfinalprice_button.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.updatefinalpricebuttonbackground));
//                        lStartservicebyissueprice_button.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.buttonblue_background));
//                    }
//                });
//
//                lStartservicebyfinalprice_button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        lStartservicebyissueprice_button.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.updatefinalpricebuttonbackground));
//                        lStartservicebyfinalprice_button.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.buttonblue_background));
//                        communicator.startservice(lTodaysTask_Arraylist.get(position).getTransaction_ID(),"1",lTodaysTask_Arraylist.get(position).getUser_Name(),lTodaysTask_Arraylist.get(position).getBooking_Date(),lTodaysTask_Arraylist.get(position).getBooking_Start_Time());
//                    }
//                });
//
//                gStartService_Dialog.show();
//
//            }
//        });
//
//
//        myViewHolder.cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                communicator.startservice(lTodaysTask_Arraylist.get(position).getTransaction_ID(),"0",lTodaysTask_Arraylist.get(position).getUser_Name(),lTodaysTask_Arraylist.get(position).getBooking_Date(),lTodaysTask_Arraylist.get(position).getBooking_Start_Time());
//            }
//        });
//
//
//        //Update final price popup
//        myViewHolder.updatefinalprice_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog gUpdateFinalPrice_Dialog;
//                gUpdateFinalPrice_Dialog = new Dialog(context, R.style.dailogboxtheme);
//                gUpdateFinalPrice_Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                gUpdateFinalPrice_Dialog.getWindow().setBackgroundDrawableResource(R.color.color_transparen);
//                gUpdateFinalPrice_Dialog.setContentView(R.layout.updatefinalprice_popup);
//
//                TextView lSend_text = gUpdateFinalPrice_Dialog.findViewById(R.id.send_text);
//                EditText lEnterfinalprice_text= gUpdateFinalPrice_Dialog.findViewById(R.id.enterfinalprice_text);
//                EditText lEnterreason= gUpdateFinalPrice_Dialog.findViewById(R.id.enterreason);
//
//
//                lSend_text.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        gUpdateFinalPrice_Dialog.dismiss();
//                    }
//                });
//                gUpdateFinalPrice_Dialog.show();
//
//
//            }
//        });


    }

    public void setstartservicelist_Communicator(startservicelist_Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public void onViewRecycled(MyViewHolder holder)
    {
//        // Cleanup MapView here?
//        if (holder.mapCurrnet != null)
//        {
//            holder.mapCurrnet.clear();
//            holder.mapCurrnet.setMapType(GoogleMap.MAP_TYPE_NONE);
//        }
    }


    @Override
    public int getItemCount() {
        return 3;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {
        public TextView username_text,date_text,time_text,useraddress_text,userphonenumber_text,requestID_text,moreheader_text,more;
        public MapView map;
        public LinearLayout map_layout;
        Button cancel,start,updatefinalprice_button;
        ImageView hide;
        public GoogleMap mapCurrnet;
        public  UserAddress_Location item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            username_text = (TextView) itemView.findViewById(R.id.username_text);
            date_text = (TextView) itemView.findViewById(R.id.date_text);
            time_text = (TextView) itemView.findViewById(R.id.time_text);
            useraddress_text = (TextView) itemView.findViewById(R.id.useraddress_text);
            userphonenumber_text = (TextView) itemView.findViewById(R.id.userphonenumber_text);
            requestID_text = (TextView) itemView.findViewById(R.id.requestID_text);
            moreheader_text = (TextView) itemView.findViewById(R.id.moreheader_text);
            updatefinalprice_button = (Button) itemView.findViewById(R.id.updatefinalprice_button);
            cancel = (Button) itemView.findViewById(R.id.cancel_button);
            start = (Button) itemView.findViewById(R.id.startservice_button);
            map_layout = itemView.findViewById(R.id.map_layout);
            map = (MapView) itemView.findViewById(R.id.mapView);
            more = itemView.findViewById(R.id.moreheader_text);
            hide = itemView.findViewById(R.id.slide_image);
            if (map != null)
            {
                ((MapView) map).onCreate(null);
                ((MapView) map).onResume();
                ((MapView) map).getMapAsync(this);
            }

        }


        public SupportMapFragment getMapFragmentAndCallback(OnMapReadyCallback callback) {
            if (mapFragment == null) {
                mapFragment = SupportMapFragment.newInstance();
                mapFragment.getMapAsync(callback);
                Log.e(TAG, "getMapFragmentAndCallback: " );

            }

            // for fragment
            // FragmentManager fragmentManager = getChildFragmentManager();
            // for activity
            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.mapView, mapFragment).commit();
            return mapFragment;
        }
        public void removeMapFragment() {
            if (mapFragment != null) {
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(mapFragment).commitAllowingStateLoss();
                Log.e(TAG, "removeMapFragment: "+i );
                mapFragment = null;

            }
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {

          //  if(i<items.size()) {
//
//            for(int i=0;i<lTodaysTask_Arraylist.size();i++){
//              /*  String s = items.get(i);
//                String[] split = s.split(",");*/
//                latitude = Double.parseDouble(lTodaysTask_Arraylist.get(i).getLatitude_val());
//                longitude = Double.parseDouble(lTodaysTask_Arraylist.get(i).getLongitude_val());
//
//                System.out.println("lat and long in onMapready " +  latitude + longitude);
//                LatLng location = new LatLng(latitude, longitude);
//                //  LatLng latLng = timeSlot.ge();
//                googleMap.addMarker(new MarkerOptions().position(location).title(lTodaysTask_Arraylist.get(i).getUser_Full_Address()));
//                googleMap.animateCamera(CameraUpdateFactory.newLatLng(location));
//                Log.e(TAG, "onMapReady: " + latitude);
//              //  System.out.println(" is position are " + latitude + longitude + items.size());
//                mapCurrnet = googleMap;
//                i++;
//            }
//
//
//          //  }


        }
    }
    public interface startservicelist_Communicator {
        void startservice(String transactionid,String statusid,String username,String bookingdate,String bookingtime);

    }



}

