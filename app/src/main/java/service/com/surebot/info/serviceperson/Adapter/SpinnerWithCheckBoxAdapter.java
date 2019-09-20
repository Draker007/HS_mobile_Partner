package service.com.surebot.info.serviceperson.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import service.com.surebot.info.serviceperson.R;
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

        holder.mTextView.setText(listState.get(position).getTitle());

        // To check weather checked event fire from getview() or user input
        isFromView = true;
        holder.mCheckBox.setChecked(listState.get(position).isSelected());
        isFromView = false;

        if ((position == 0)) {
            holder.mCheckBox.setVisibility(View.INVISIBLE);
        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);
        }
        holder.mCheckBox.setTag(position);
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();

                if (!isFromView) {
                    listState.get(position).setSelected(isChecked);
                    if(isChecked == true){
                        checkedItem.add(String.valueOf(position));
                    }else if(checkedItem.contains(position))
                    {
                        checkedItem.remove(position);
                    }
                    Log.e("hihi", "onCheckedChanged: here"+position+isChecked );
                }
            }
        });
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



