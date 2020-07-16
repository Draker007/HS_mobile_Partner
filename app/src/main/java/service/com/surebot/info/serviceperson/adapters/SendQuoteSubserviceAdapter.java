package service.com.surebot.info.serviceperson.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import service.com.surebot.info.serviceperson.R;

public class SendQuoteSubserviceAdapter extends RecyclerView.Adapter<SendQuoteSubserviceAdapter.MyViewHolder> {
    Context context;

    public SendQuoteSubserviceAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.send_quote_subservice_recycler, parent, false);
        return new SendQuoteSubserviceAdapter.MyViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);



        }
    }


}