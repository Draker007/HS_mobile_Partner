package service.com.surebot.info.serviceperson.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import service.com.surebot.info.serviceperson.R;

public class PrimePackageAdapter extends RecyclerView.Adapter<PrimePackageAdapter.ViewHolder> {
    private Context context;

    @NonNull
    @Override
    public PrimePackageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View layoutInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prime_package, parent, false);
        return new ViewHolder(layoutInflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PrimePackageAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
