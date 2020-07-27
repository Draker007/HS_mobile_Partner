package service.com.surebot.info.serviceperson.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.ResponseClass.ListOfSubServices_Response;
import service.com.surebot.info.serviceperson.ApplicationClass;


public class AddSubServicesList_Adapter extends RecyclerView.Adapter<AddSubServicesList_Adapter.MyViewHolder> {

    Context context;
  //  ArrayList<ListOfSubServices_Response.ListOfSubServices_Records> gSubServicesList_Arraylist;

 //   ArrayList<GetSubServicesByZipcode_Response.GetSubServicesByZipcode_Details> gSubServicesList_Arraylist;
    ArrayList<String> SubServiceData = new ArrayList<>();

    ArrayList<String> addedSubService = new ArrayList<>();

   String gPremiumPartner_Id = ApplicationClass.getPremium_PartenerId();
    String gUserTypeFrom_CountryList = ApplicationClass.getUserType_FromCountryList();

    ArrayList<ListOfSubServices_Response.ListOfSubServices_Records> gSubServicesList_Arraylist;

    String gAdminApproval_Status;

public static  int multiple = 0;
    public AddSubServicesList_Adapter(Context context,   ArrayList<ListOfSubServices_Response.ListOfSubServices_Records> gSubServicesList_Arraylist, String gAdminApproval_Status) {
        this.context=context;
        this.gSubServicesList_Arraylist=gSubServicesList_Arraylist;

        if(multiple==0){
            ApplicationClass.addservicemapingid.clear();

            ApplicationClass.addserviceammount.clear();

            System.out.println("multiple services flow entering into 11111");
        }


        this.gAdminApproval_Status= gAdminApproval_Status;
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
        ListOfSubServices_Response.ListOfSubServices_Records item = gSubServicesList_Arraylist.get(position);
        System.out.println("Premium partner Id is " + gUserTypeFrom_CountryList);


        if(gAdminApproval_Status.equals("Newly_Registered")  ||gAdminApproval_Status.equals("Rejected") || gAdminApproval_Status.equals("Approved") ){
            myViewHolder.lQuantityCheckBox.setEnabled(true);

            myViewHolder.lPrice.setEnabled(true);
            myViewHolder.lPrice.setFocusable(true);
            myViewHolder.lPrice.setClickable(true);
            myViewHolder.lPrice.setKeyListener( myViewHolder.lPrice.getKeyListener());

        }

        if(gAdminApproval_Status.equals("Waiting_For_Approval")){

            myViewHolder.lQuantityCheckBox.setEnabled(false);


            myViewHolder.lPrice.setEnabled(false);
            myViewHolder.lPrice.setFocusable(false);
            myViewHolder.lPrice.setClickable(false);
            myViewHolder.lPrice.setKeyListener( myViewHolder.lPrice.getKeyListener());
        }


        if (gUserTypeFrom_CountryList.equals("1")) {

            System.out.println("In Sub Adapter entering into 1");
            myViewHolder.lPrice.setVisibility(View.VISIBLE);
            myViewHolder.lQuantityCheckBox.setVisibility(View.GONE);
            if (gSubServicesList_Arraylist.get(position).getService_Is_There().equals("Exists")) {
                if (gSubServicesList_Arraylist.get(position).getService_Amount() != null) {
                    myViewHolder.lQuantityCheckBox.setChecked(true);
                    myViewHolder.lPrice.setText(gSubServicesList_Arraylist.get(position).getService_Amount());
                }

                if (!gSubServicesList_Arraylist.get(position).getService_Amount().equals("")) {

                    System.out.println("In Adapter Service ammount is " + gSubServicesList_Arraylist.get(position).getService_Amount());
                    ApplicationClass.addserviceammount.add(gSubServicesList_Arraylist.get(position).getService_Amount());
                    ApplicationClass.addservicemapingid.add(gSubServicesList_Arraylist.get(position).getService_Mapping_ID());
                }
                //   AppicationClass.addservicemapingid.add(gSubServicesList_Arraylist.get(position).getService_Mapping_ID()+","+ gSubServicesList_Arraylist.get(position).getService_Amount());
            }

        }

        if (gUserTypeFrom_CountryList.equals("0")) {
            // System.out.println("In Sub Adapter entering into 0");
            myViewHolder.lQuantityCheckBox.setVisibility(View.VISIBLE);
            myViewHolder.lPrice.setVisibility(View.GONE);

            // if(gSubServicesList_Arraylist.get(position).getService_Is_There().equals("Exists")){
            myViewHolder.lQuantityCheckBox.setChecked(false);

                System.out.println("In Adapter Entering into 1111111111"  + ApplicationClass.addservicemapingid.size());
            if (!ApplicationClass.addservicemapingid.contains(gSubServicesList_Arraylist.get(position).getService_Mapping_ID())) {
                System.out.println("Array value is "  + ApplicationClass.addservicemapingid + " and " + gSubServicesList_Arraylist.get(position).getService_Mapping_ID());
                System.out.println("In Adapter Entering into 2222222");
                if (gSubServicesList_Arraylist.get(position).getService_Is_There().equals("Exists")) {
                    System.out.println("In Adapter Entering into 3333333");
                    myViewHolder.lQuantityCheckBox.setChecked(true);
                    ApplicationClass.addservicemapingid.add(gSubServicesList_Arraylist.get(position).getService_Mapping_ID());

                }

                System.out.println("In Adapter Entering into 4444444");
            }


            //// }

            else {
                System.out.println("In Adapter Entering into 5555555");
                myViewHolder.lQuantityCheckBox.setChecked(false);
            }

            if(multiple==1){
                myViewHolder.lQuantityCheckBox.setChecked(true);
                ApplicationClass.addservicemapingid.add(gSubServicesList_Arraylist.get(position).getService_Mapping_ID());
            }

        }

//System.out.println("Premium partner Id is " + gUserTypeFrom_CountryList);

//        if(gUserTypeFrom_CountryList.equals("1")){
//            myViewHolder.lPrice.setVisibility(View.VISIBLE);
//            myViewHolder.lQuantityCheckBox.setVisibility(View.GONE);
//        }
//
//        if(gUserTypeFrom_CountryList.equals("0")){
//            myViewHolder.lQuantityCheckBox.setVisibility(View.VISIBLE);
//            myViewHolder.lPrice.setVisibility(View.GONE);
//
//        }


        myViewHolder.lSubServicesName_Text.setText(gSubServicesList_Arraylist.get(position).getService_Name());


        myViewHolder.lPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                System.out.println("Service ammount inside beforeTextChanged " + charSequence.toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

              /*  if (charSequence!=null) {

                //    SubServiceData.add(position,charSequence.toString());

                    if(charSequence.length() >0) {
                      //  AppicationClass.addserviceammount.add(charSequence.toString());
                        AppicationClass.addnewserviceammount.add(gSubServicesList_Arraylist.get(position).getService_Mapping_ID()+","+charSequence.toString());
                        System.out.println("Service ammount inside onTextChanged " + charSequence.toString());
                    }

                }*/

            }

            @Override
            public void afterTextChanged(Editable editable) {



                if (editable!=null) {
                    System.out.println("In service add adapter entering in first if");
                    if(editable.length() >0) {


                        String enteredamount = editable.toString();



                      System.out.println("In service add adapter entering in second if" + enteredamount);



                        ApplicationClass.addnewserviceammount.add(gSubServicesList_Arraylist.get(position).getService_Mapping_ID()+","+editable.toString());
                    }

                    System.out.println("In service add adapter entering in second else");
                }
                System.out.println("In service add adapter entering in second else");
                System.out.println("Service ammount inside afterTextChanged " + editable.toString());
            }
    });






        myViewHolder.lQuantityCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(myViewHolder.lQuantityCheckBox.isChecked()){
                    ApplicationClass.addservicemapingid.add(gSubServicesList_Arraylist.get(position).getService_Mapping_ID());
                    ApplicationClass.multipleaddservicemapingid.add(gSubServicesList_Arraylist.get(position).getService_Mapping_ID());

                    myViewHolder.lQuantityCheckBox.setChecked(true);
System.out.println("Checkbox entering in to the true");
                    multiple=1;
                }
                   else{
                    if(ApplicationClass.addservicemapingid.contains(gSubServicesList_Arraylist.get(position).getService_Mapping_ID())){
                        ApplicationClass.addservicemapingid.remove(gSubServicesList_Arraylist.get(position).getService_Mapping_ID());
                        ApplicationClass.multipleaddservicemapingid.remove(gSubServicesList_Arraylist.get(position).getService_Mapping_ID());

                        myViewHolder.lQuantityCheckBox.setChecked(false);
                        multiple=2;
                        System.out.println("Checkbox entering in to the false");
                    }

                 //   AppicationClass.addservicemapingid.remove(gSubServicesList_Arraylist.get(position).getService_Mapping_ID());
                }
/*
               if(AppicationClass.addservicemapingid.contains(gSubServicesList_Arraylist.get(position).getService_Mapping_ID())){
                AppicationClass.addservicemapingid.remove(gSubServicesList_Arraylist.get(position).getService_Mapping_ID());
                }else{
                   AppicationClass.addservicemapingid.add(gSubServicesList_Arraylist.get(position).getService_Mapping_ID());
               }*/

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
