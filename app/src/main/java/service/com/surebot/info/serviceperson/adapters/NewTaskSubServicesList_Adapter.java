package service.com.surebot.info.serviceperson.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.AppicationClass;


public class NewTaskSubServicesList_Adapter extends RecyclerView.Adapter<NewTaskSubServicesList_Adapter.MyViewHolder> {

    Context context;

    //ArrayList<SendquotetoUser> lSub_ServicesList;
    ArrayList<String> gSub_services_List;
    ArrayList<String> MapppingID;
    ArrayList<String> gServiceamount;

    // private  ArrayList<SendquotetoUser> gSendquotetoUserList = new ArrayList<>();
    private ArrayList<String> gSendquotetoUserList_New = new ArrayList<String>();

    String gPremiumPartner_Id = AppicationClass.getPremium_PartenerId();

    public NewTaskSubServicesList_Adapter(Context context, ArrayList<String> gSub_services_List, ArrayList<String> mapppingID, ArrayList<String> gServiceamount) {
        this.context = context;
        this.gSub_services_List = gSub_services_List;
        this.MapppingID = mapppingID;
        this.gServiceamount= gServiceamount;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.newtasksubservices_adaterlist, viewGroup, false);
        return new NewTaskSubServicesList_Adapter.MyViewHolder(view);
        // return null;
    }

    @SuppressLint({"ResourceAsColor", "WrongConstant"})
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
        myViewHolder.lSubservicename_text.setText(gSub_services_List.get(position).toString());


        if (gPremiumPartner_Id.equals("1")) {
            if(!gServiceamount.get(position).toString().equals("")){

                myViewHolder.lQuantity_Text.setText(gServiceamount.get(position).toString());
                AppicationClass.newrequestservicesammount.add(gServiceamount.get(position).toString());
                AppicationClass.newrequestservicesid.add(MapppingID.get(position).toString());


            }

        }


        if (gPremiumPartner_Id.equals("0")) {

            AppicationClass.newrequestservicesid.add(MapppingID.get(position).toString());
        }

        myViewHolder.lQuantity_Text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {



            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable!=null) {
                    // gSendquotetoUserList.get(position).setQuantity(charSequence.toString());
                    gSendquotetoUserList_New.add(editable.toString());
                    Log.e("lol1", "afterTextChanged: "+editable.toString() );
                    if(editable.length() >0) {
                        AppicationClass.test1.add(MapppingID.get(position)+","+ editable.toString());
                    }else{
                        AppicationClass.test1.add(MapppingID.get(position)+","+"0");
                    }

                    System.out.println("Mapping Id In inside adpater" + MapppingID.get(position));
                    System.out.println("Chare Sequesnce Value Are " + editable.toString() + gSendquotetoUserList_New.size());

                    System.out.println("Id and price in inside adapter is " +AppicationClass.test1.size());
                }else if (editable.toString()==""){
                    Log.e("lol1", "afterTextChanged: was here" );
                }
            }
        });

    }

    public ArrayList<String> retrieveData()
    {
        return gSendquotetoUserList_New;
    }

    @Override
    public int getItemCount() {
        return gSub_services_List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        EditText lQuantity_Text;
        TextView  lSubservicename_text;
        RelativeLayout lButton_layout;
        Button lSend_button;
        Button lReject_button;
        TextView lQuotesend_text;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lQuantity_Text=itemView.findViewById(R.id.quantity_text);

            lSubservicename_text=itemView.findViewById(R.id.subservicename_text);





        }
    }




}