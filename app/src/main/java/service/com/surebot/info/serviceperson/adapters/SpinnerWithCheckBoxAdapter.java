package service.com.surebot.info.serviceperson.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.ApplicationClass;
import service.com.surebot.info.serviceperson.utils.spinnerData;

public class SpinnerWithCheckBoxAdapter extends ArrayAdapter<spinnerData> {

    private Context mContext;
    private ArrayList<spinnerData> listState;
    private SpinnerWithCheckBoxAdapter myAdapter;
    ArrayList<String> checkedItem = new ArrayList<>();
    private boolean isFromView = false;

    public SpinnerWithCheckBoxAdapter(Context context, int resource, List<spinnerData> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.listState = (ArrayList<spinnerData>) objects;
        this.myAdapter = this;

        System.out.println("In Cities Adapter size is " +  objects.size());
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView,
                              ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.spinner_checkbox, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView) convertView
                    .findViewById(R.id.spinnerText);
            holder.mCheckBox = (CheckBox) convertView
                    .findViewById(R.id.spinnerCheckbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }



       /* holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppicationClass.addlocationservicecities.add(listState.get(position).getCityId());


            }
        });*/



        holder.mTextView.setText(listState.get(position).getTitle());
      //  AppicationClass.Constants.BASE_URL.add(listState.get(position).getCityId());


        // To check weather checked event fire from getview() or user input
        isFromView = true;
        holder.mCheckBox.setChecked(listState.get(position).isSelected());
        isFromView = false;

        if ((position == 0)) {
            holder.mCheckBox.setVisibility(View.INVISIBLE);
        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);

            if(listState.get(position).getPartnerStatus().equals("1")){
                holder.mCheckBox.setChecked(true);
                if(!ApplicationClass.addlocationservicecities.contains(listState.get(position).getCityId())) {
                    ApplicationClass.addlocationservicecities.add(listState.get(position).getCityId());
                }
                System.out.println("In State adapter State status size is " +listState.get(position).getStateId()+" "+position);
            }



        }


        holder.mCheckBox.setTag(position);

        holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.mCheckBox.isChecked()){
                    checkedItem.add(String.valueOf(position));
                    System.out.println("Checked States from States List " + String.valueOf(position));

                    listState.get(position).setPartnerStatus("1");
                    ApplicationClass.addlocationservicecities.add(listState.get(position).getCityId());

                    System.out.println("In Cities Adapter Checked Items addedd " +   ApplicationClass.addlocationservicestates.size());

                }else{
                    listState.get(position).setPartnerStatus("");
                    System.out.println("In Cities Adapter Checked Items removed " +   ApplicationClass.addlocationservicestates.size());
                    checkedItem.remove(listState.get(position).getCityId());
                    ApplicationClass.addlocationservicecities.remove(listState.get(position).getCityId());
                }
            }
        });

      /*  if(listState.get(position).getPartnerStatus().equals("1")){
            holder.mCheckBox.setChecked(true);
           // AppicationClass.addlocationservicecities.add(listState.get(position).getCityId());

        }
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();

                if (!isFromView) {
                    listState.get(position).setSelected(isChecked);
                    if(isChecked == true){
                        checkedItem.add(String.valueOf(position));
                        System.out.println("Checked States from States List " + String.valueOf(position));

                        System.out.println("In Cities Adapter size is checkbox iffff clcikable" +   AppicationClass.addlocationservicecities.size());
                        AppicationClass.addlocationservicecities.add(listState.get(position).getCityId());

                        System.out.println("In Cities Adapter Checked Items addedd " +   AppicationClass.addlocationservicecities.size());

                    }else if(checkedItem.contains(position))
                    {
                        System.out.println("In Cities Adapter Checked Items removed " +   AppicationClass.addlocationservicecities.size());
                        checkedItem.remove(position);
                        System.out.println("In Cities Adapter size is checkbox iffff elseeee" +   AppicationClass.addlocationservicecities.size());
                        AppicationClass.addlocationservicecities.remove(listState.get(position).getCityId());
                    }
                    Log.e("hihi", "onCheckedChanged: here"+position+isChecked );

                }
            }
        });
*/



        return convertView;
    }


public ArrayList getCheckedItems(){
        return checkedItem ;
}

    private class ViewHolder {
        private TextView mTextView;
        private CheckBox mCheckBox;
    }
}



