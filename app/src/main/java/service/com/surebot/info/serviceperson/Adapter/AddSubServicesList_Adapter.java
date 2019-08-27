package service.com.surebot.info.serviceperson.Adapter;

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

import java.lang.reflect.Array;
import java.util.ArrayList;

import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.ResponseClass.ListOfSubServices_Response;


public class AddSubServicesList_Adapter extends RecyclerView.Adapter<AddSubServicesList_Adapter.MyViewHolder> {

    Context context;
    ArrayList<ListOfSubServices_Response.ListOfSubServices_Records> gSubServicesList_Arraylist;
    ArrayList<String> SubServiceData = new ArrayList<>();

    ArrayList<String> addedSubService;

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


        myViewHolder.lSubServicesName_Text.setText(gSubServicesList_Arraylist.get(position).getService_Name());
        myViewHolder.price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence!=null) {

                    SubServiceData.add(position,charSequence.toString());

                }}

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        myViewHolder.lQuantityCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            addedSubService.add(gSubServicesList_Arraylist.get(position).getService_ID());

            }
        });


    }

    public ArrayList getPrimeData(){return SubServiceData;}
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
        EditText price;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lSubServicesName_Text=itemView.findViewById(R.id.subservicename_text);
            lQuantityCheckBox=itemView.findViewById(R.id.selectquantity_checkbox);
            price = itemView.findViewById(R.id.addSubServiceMoney);
        }
    }


    public interface bannerslist_Communicator {
        void bannerslist(String serviceid);

    }

}
