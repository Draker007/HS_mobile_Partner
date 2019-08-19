package service.com.surebot.info.serviceperson.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.ResponseClass.Category_List_Response;

public class SignupProfessional_Adapter extends ArrayAdapter<Category_List_Response.Category_List_Records> {


    ArrayList<Category_List_Response.Category_List_Records> gsignupspinner_Arraylist;
    Context context;

    public SignupProfessional_Adapter(Context context, int txtViewResourceId, ArrayList<Category_List_Response.Category_List_Records> gsignupspinner_Arraylist) {
        super(context,txtViewResourceId,gsignupspinner_Arraylist);

        this.gsignupspinner_Arraylist = gsignupspinner_Arraylist;
        this.context = context;

    }

    @Override
    public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
        return getCustomView(position, cnvtView, prnt);
    }

    @Override
    public View getView(int pos, View cnvtView, ViewGroup prnt) {
        return getCustomView(pos, cnvtView, prnt);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // LayoutInflater inflater =ctx.getLayoutInflater();
        View mySpinner = inflater.inflate(R.layout.professionselection_layout, parent, false);
        TextView Professional_text = (TextView) mySpinner.findViewById(R.id.professional_text);
        Professional_text.setText(gsignupspinner_Arraylist.get(position).getCategory_Name());

        return mySpinner;
    }
}

