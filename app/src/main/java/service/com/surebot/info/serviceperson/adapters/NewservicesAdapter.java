package service.com.surebot.info.serviceperson.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import service.com.surebot.info.serviceperson.R;

public class NewservicesAdapter extends RecyclerView.Adapter<NewservicesAdapter.MyViewHolder> {


    Context context;
    //private Dialog progress;
    int i=0;

    public NewservicesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_service_recycler, parent, false);
        return new NewservicesAdapter.MyViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        holder.recyclerView.setLayoutManager(llm);
        NewsubserviceAdapter NewsubserviceAdapter = new NewsubserviceAdapter(context );
        holder.recyclerView.setAdapter(NewsubserviceAdapter);
    }

    @Override
    public int getItemCount() {
        return 2;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

    RecyclerView recyclerView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.newSubServiceREcy);


        }
    }



}
