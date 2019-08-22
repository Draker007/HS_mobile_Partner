package service.com.surebot.info.serviceperson.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.UserAddress_Location;

import static service.com.surebot.info.serviceperson.utils.UserAddress_Location.VIEW_MAP;


public class TodaysTask_Adapter extends RecyclerView.Adapter<TodaysTask_Adapter.MyViewHolder>{

    Context context;
    ArrayList<String> gUserName_List;
    private final List<UserAddress_Location> items;

    private SupportMapFragment mapFragment;

     UserAddress_Location timeSlot =new UserAddress_Location();

    double latitude, longitude;

    public TodaysTask_Adapter(Context context,  List<UserAddress_Location> items, ArrayList<String> gUserName_List) {
        this.context=context;
        this.items=items;
        this.gUserName_List=gUserName_List;

        System.out.println("Inside Constructor Arralist size is " + items.size());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {



       /* int layoutId = R.layout.content_list_text;
        if (i == UserAddress_Location.VIEW_MAP) {
            layoutId = R.layout.content_list_map;
        }*/


        View inflatedView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.content_list_map, viewGroup, false);
        return new MyViewHolder(inflatedView);
    }

    @Override
    public void onViewAttachedToWindow(MyViewHolder holder) {
        super.onViewAttachedToWindow(holder);

        final UserAddress_Location item = holder.item;

       // if (item != null && item.viewType == VIEW_MAP) {
            holder.getMapFragmentAndCallback(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {

                 /*   for (int i = 0; i < items.size(); i++) {
                        timeSlot = items.get(i);


             System.out.println("Lat and Long from other classes " + items.get(i) );


                }*/
                  /*  String s = timeSlot.getLatitutde();
                    String[] split = s.split(",");
                    double latitude, longitude;
                    latitude = Double.parseDouble(split[0]);
                    longitude = Double.parseDouble(split[1]);*/
                    LatLng location = new LatLng(latitude, longitude);
                    //  LatLng latLng = timeSlot.ge();
                    googleMap.addMarker(new MarkerOptions().position(location));
                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(location));

                    System.out.println("map values inside Adapter is position are " + latitude + longitude + items.size());
                }
            });
       // }
    }

    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);

      //  if (holder.item != null && holder.item.viewType == VIEW_MAP) {
            // If error still occur unpredictably, it's best to remove fragment here
            holder.removeMapFragment();
       // }
    }



    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {

//System.out.println("Position inside onBind is " + position + items.get(position));

        final UserAddress_Location item = items.get(position);
        System.out.println("Position inside onBind is " + position + item.getLatitutde());

        String s = item.getLatitutde();
        String[] split = s.split(",");
     /*   ArrayList<String> gsplitedArraylist = new ArrayList<>();

        for(int i=0;i<split.length;i++){


        }*/


        latitude = Double.parseDouble(split[0]);
        longitude = Double.parseDouble(split[1]);


        System.out.println("only map values inside Adapter is " +  item  );

       /* if (item.viewType == UserAddress_Location.VIEW_TEXT) {
         //  myViewHolder.textView.setText(item.text);
        }
        else if (item.viewType == VIEW_MAP) {
            myViewHolder.item = item;
            myViewHolder.textView.setText(gUserName_List.get(position).toString());
        }*/

      //  myViewHolder.item = item;
        myViewHolder.textView.setText(gUserName_List.get(position).toString());
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


   /* @Override
    public int getItemViewType(int position) {
        return items.get(position).viewType;
    }*/


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public FrameLayout mapLayout;

        public  UserAddress_Location item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

           textView = (TextView) itemView.findViewById(R.id.locationNameTextView);
            mapLayout = (FrameLayout) itemView.findViewById(R.id.map);

        }


        public SupportMapFragment getMapFragmentAndCallback(OnMapReadyCallback callback) {
            if (mapFragment == null) {
                mapFragment = SupportMapFragment.newInstance();
                mapFragment.getMapAsync(callback);
            }

            // for fragment
            // FragmentManager fragmentManager = getChildFragmentManager();
            // for activity
            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.map, mapFragment).commit();
            return mapFragment;
        }
        public void removeMapFragment() {
            if (mapFragment != null) {
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(mapFragment).commitAllowingStateLoss();
                mapFragment = null;
            }
        }
    }
    public interface bannerslist_Communicator {
        void bannerslist(String serviceid);

    }



}

