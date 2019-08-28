package service.com.surebot.info.serviceperson.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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

    ArrayList<Partner_my_task_today_response.Partner_my_task_today_Records> lTodaysTask_Arraylist ;

    public TodaysTask_Adapter(Context context,ArrayList<Partner_my_task_today_response.Partner_my_task_today_Records> lTodaysTask_Arraylist) {
        /*Log.e(TAG, "TodaysTask_Adapter: "+items.get(0));
        Log.e(TAG, "TodaysTask_Adapter: "+items.get(1));*/
        this.context=context;
     //   this.items=items;
        this.lTodaysTask_Arraylist=lTodaysTask_Arraylist;



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

        holder.removeMapFragment();

    }



    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder,  int position) {
      /*  Log.e(TAG, "onBindViewHolder: "+items.size() +position);
        String item = items.get(position);
        System.out.println("Position inside onBind is " + position + item);

        String s = item;
        String[] split = s.split(",");*/
        latitude =Double.parseDouble(lTodaysTask_Arraylist.get(position).getLatitude_val()); //Double.parseDouble(split[0]);
        longitude = Double.parseDouble(lTodaysTask_Arraylist.get(position).getLongitude_val());

        myViewHolder.username_text.setText(lTodaysTask_Arraylist.get(position).getUser_Name());
        myViewHolder.date_text.setText(lTodaysTask_Arraylist.get(position).getBooking_Date());
        myViewHolder.time_text.setText(lTodaysTask_Arraylist.get(position).getBooking_Start_Time());
        myViewHolder.useraddress_text.setText(lTodaysTask_Arraylist.get(position).getUser_Full_Address());
        myViewHolder.userphonenumber_text.setText(lTodaysTask_Arraylist.get(position).getUser_Contact_Number());
        myViewHolder.requestID_text.setText(lTodaysTask_Arraylist.get(position).getServices());


        myViewHolder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewHolder.map_layout.setVisibility(View.VISIBLE);
                myViewHolder.more.setVisibility(View.GONE);
            }
        });
        myViewHolder.hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewHolder.map_layout.setVisibility(View.GONE);
                myViewHolder.more.setVisibility(View.VISIBLE);
            }
        });

        myViewHolder.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                communicator.startservice("1");
            }
        });

    }

    public void setstartservicelist_Communicator(startservicelist_Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public void onViewRecycled(MyViewHolder holder)
    {
        // Cleanup MapView here?
        if (holder.mapCurrnet != null)
        {
            holder.mapCurrnet.clear();
            holder.mapCurrnet.setMapType(GoogleMap.MAP_TYPE_NONE);
        }
    }


    @Override
    public int getItemCount() {
        return lTodaysTask_Arraylist.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {
        public TextView username_text,date_text,time_text,useraddress_text,userphonenumber_text,requestID_text,moreheader_text,more;
        public MapView map;
        public LinearLayout map_layout;
        Button cancel,start;
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

            for(int i=0;i<lTodaysTask_Arraylist.size();i++){
              /*  String s = items.get(i);
                String[] split = s.split(",");*/
                latitude = Double.parseDouble(lTodaysTask_Arraylist.get(i).getLatitude_val());
                longitude = Double.parseDouble(lTodaysTask_Arraylist.get(i).getLongitude_val());

                System.out.println("lat and long in onMapready " +  latitude + longitude);
                LatLng location = new LatLng(latitude, longitude);
                //  LatLng latLng = timeSlot.ge();
                googleMap.addMarker(new MarkerOptions().position(location).title(lTodaysTask_Arraylist.get(i).getUser_Full_Address()));
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(location));
                Log.e(TAG, "onMapReady: " + latitude);
              //  System.out.println(" is position are " + latitude + longitude + items.size());
                mapCurrnet = googleMap;
                i++;
            }


          //  }


        }
    }
    public interface startservicelist_Communicator {
        void startservice(String serviceid);

    }



}

