package service.com.surebot.info.serviceperson.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
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
import service.com.surebot.info.serviceperson.utils.SendquotetoUser;
import service.com.surebot.info.serviceperson.utils.SendquotetoUser_New;


public class NewTaskSubServicesList_Adapter extends RecyclerView.Adapter<NewTaskSubServicesList_Adapter.MyViewHolder> {

    Context context;
    static ArrayList<SendquotetoUser_New> gAddedServices_ArrayList = new ArrayList<>();
    //ArrayList<SendquotetoUser> lSub_ServicesList;
  //  ArrayList<String> gSub_services_List;
    ArrayList<SendquotetoUser> gSub_services_List = new ArrayList<>();
        // private  ArrayList<SendquotetoUser> gSendquotetoUserList = new ArrayList<>();
       // private ArrayList<String> gSendquotetoUserList_New = new ArrayList<String>();
      public NewTaskSubServicesList_Adapter(Context context,  ArrayList<SendquotetoUser> gSub_services_List) {
        this.context=context;
        this.gSub_services_List=gSub_services_List;



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

        SendquotetoUser data = gSub_services_List.get(position);

        myViewHolder.lSubservicename_text.setText(data.getSubServicename());
        myViewHolder.lQuantity_Text.setText(data.getQuantity());

        myViewHolder.lQuantity_Text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        if (charSequence!=null) {
          // gSendquotetoUserList.get(position).setQuantity(charSequence.toString());
          //  gSendquotetoUserList_New.add(charSequence.toString());


          /*  gSub_services_List.get(position).setQuantity(charSequence.toString());
            gSub_services_List.get(position).setPartnermaping_Id(charSequence.toString());
            System.out.println("Chare Sequesnce Value Are " + charSequence.toString() + gSub_services_List.size());*/
            SendquotetoUser_New lsendquote = new SendquotetoUser_New();
            lsendquote.setQuantity(charSequence.toString());
            lsendquote.setSubservicename(gSub_services_List.get(position).getSubServicename());
            lsendquote.setServicequantityid(gSub_services_List.get(position).getPartnermaping_Id());
            gAddedServices_ArrayList.add(lsendquote);
            AppicationClass.setList(gAddedServices_ArrayList);
        }

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
});

    }

    public ArrayList<SendquotetoUser> retrieveData()
    {
        return gSub_services_List;
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