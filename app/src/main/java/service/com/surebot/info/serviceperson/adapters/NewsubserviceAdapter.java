package service.com.surebot.info.serviceperson.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import service.com.surebot.info.serviceperson.R;

public class NewsubserviceAdapter extends RecyclerView.Adapter<NewsubserviceAdapter.MyViewHolder> {
    Context context;
    //private Dialog progress;
    int i=0;

    public NewsubserviceAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newsubservice_recycler, parent, false);
        return new NewsubserviceAdapter.MyViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);



        }
    }

}
