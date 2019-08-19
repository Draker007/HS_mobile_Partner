package service.com.surebot.info.serviceperson.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import service.com.surebot.info.serviceperson.DataFiles.NotificationData;
import service.com.surebot.info.serviceperson.R;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.Myviewholder> {

    List<NotificationData> notificationDataList;

    public NotificationAdapter(List<NotificationData> notificationDataList) {
        this.notificationDataList = notificationDataList;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.noti_recycler,parent,false);
        Myviewholder myviewholder = new Myviewholder(v);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        NotificationData data = notificationDataList.get(position);
        holder.address.setText(data.getAddress());
        holder.date.setText(data.getDate());
        holder.id.setText(data.getRequestid());
        holder.name.setText(data.getName());
        holder.number.setText(data.getPhone());
        holder.time.setText(data.getTime());
    }

    @Override
    public int getItemCount() {
        return notificationDataList.size();
    }

    public static class Myviewholder extends RecyclerView.ViewHolder{
        TextView name,id,address,date,time,number;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.NotiRqID);
            name = itemView.findViewById(R.id.Notiname);
            address = itemView.findViewById(R.id.NotiAdd);
            date = itemView.findViewById(R.id.NotiDate);
            time = itemView.findViewById(R.id.NotiTime);
            number = itemView.findViewById(R.id.NotiNumber);
        }
    }
}
