package service.com.surebot.info.serviceperson.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.ResponseClass.ListOfSubServices_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;


public class AddSubServicesList_Adapter extends RecyclerView.Adapter<AddSubServicesList_Adapter.MyViewHolder> {

    Context context;
    ArrayList<ListOfSubServices_Response.ListOfSubServices_Records> gSubServicesList_Arraylist;
    ArrayList<String> SubServiceData = new ArrayList<>();

    ArrayList<String> addedSubService = new ArrayList<>();

   String gPremiumPartner_Id = AppicationClass.getPremium_PartenerId();

    public AddSubServicesList_Adapter(Context context,  ArrayList<ListOfSubServices_Response.ListOfSubServices_Records> gSubServicesList_Arraylist) {
        this.context=context;
        this.gSubServicesList_Arraylist=gSubServicesList_Arraylist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.addsubservicesadapter_list, viewGroup, false);
        return new AddSubServicesList_Adapter.MyViewHolder(view);
        // return null;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {

      System.out.println("Premium partner Id is " + gPremiumPartner_Id);

        if(gPremiumPartner_Id.equals("1")){
            myViewHolder.lPrice.setVisibility(View.VISIBLE);
            myViewHolder.lQuantityCheckBox.setVisibility(View.GONE);
            if(gSubServicesList_Arraylist.get(position).getService_Is_There().equals("Exists")){
                myViewHolder.lPrice.setText(gSubServicesList_Arraylist.get(position).getService_Amount());
                if(!gSubServicesList_Arraylist.get(position).getService_Amount().equals(""))
                AppicationClass.addservicemapingid.add(gSubServicesList_Arraylist.get(position).getService_Mapping_ID()+","+ gSubServicesList_Arraylist.get(position).getService_Amount());
            }

        }

        if(gPremiumPartner_Id.equals("0")){
            myViewHolder.lQuantityCheckBox.setVisibility(View.VISIBLE);
            myViewHolder.lPrice.setVisibility(View.GONE);
            if(gSubServicesList_Arraylist.get(position).getService_Is_There().equals("Exists")){
                myViewHolder.lQuantityCheckBox.setChecked(true);

                AppicationClass.addserviceammount.add(gSubServicesList_Arraylist.get(position).getService_Mapping_ID());
            }

        }

//System.out.println("Premium partner Id is " + gPremiumPartner_Id);

//        if(gPremiumPartner_Id.equals("1")){
//            myViewHolder.lPrice.setVisibility(View.VISIBLE);
//            myViewHolder.lQuantityCheckBox.setVisibility(View.GONE);
//        }
//
//        if(gPremiumPartner_Id.equals("0")){
//            myViewHolder.lQuantityCheckBox.setVisibility(View.VISIBLE);
//            myViewHolder.lPrice.setVisibility(View.GONE);
//
//        }


        myViewHolder.lSubServicesName_Text.setText(gSubServicesList_Arraylist.get(position).getService_Name());
        myViewHolder.lPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence!=null) {

                //    SubServiceData.add(position,charSequence.toString());

                    if(charSequence.length() >0) {
                        AppicationClass.addservicemapingid.add(gSubServicesList_Arraylist.get(position).getService_Mapping_ID()+","+ charSequence.toString());
                    }

                }}

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        myViewHolder.lQuantityCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(AppicationClass.addserviceammount.contains(gSubServicesList_Arraylist.get(position).getService_Mapping_ID())){
                AppicationClass.addserviceammount.remove(gSubServicesList_Arraylist.get(position).getService_Mapping_ID());
                }else{
                   AppicationClass.addserviceammount.add(gSubServicesList_Arraylist.get(position).getService_Mapping_ID());
               }

            }
        });

//        myViewHolder.lQuantityCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b==true){
//                    Log.e("lol", "onCheckedChanged: "+b );
////                    addedSubService.add(gSubServicesList_Arraylist.get(position).getService_Mapping_ID());
////                    AppicationClass.addservicemapingid.add(gSubServicesList_Arraylist.get(position).getService_Mapping_ID());
//                }else {
////                    AppicationClass.addservicemapingid.add(gSubServicesList_Arraylist.);
//                }
//            }
//        });



    }

    public ArrayList getPrimeData(){

        return SubServiceData;

    }
    public ArrayList getDataCheckBox(){
        return addedSubService;
    }

    @Override
    public int getItemCount() {
        return gSubServicesList_Arraylist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lSubServicesName_Text;
        CheckBox  lQuantityCheckBox;
        LinearLayout lMain_layout;
        EditText lPrice;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lSubServicesName_Text=itemView.findViewById(R.id.subservicename_text);
            lQuantityCheckBox=itemView.findViewById(R.id.selectquantity_checkbox);
           lPrice = itemView.findViewById(R.id.addSubServiceMoney);
        }
    }


    public interface bannerslist_Communicator {
        void bannerslist(String serviceid);

    }

}
