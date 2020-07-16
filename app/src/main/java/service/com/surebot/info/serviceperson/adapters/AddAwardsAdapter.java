package service.com.surebot.info.serviceperson.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

import service.com.surebot.info.serviceperson.DataFiles.awardsData;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.AppicationClass;


public class AddAwardsAdapter  extends RecyclerView.Adapter<AddAwardsAdapter.ViewHolder> {

    public Context gContext;

    ArrayList<String> deltedImg = new ArrayList<>();
    private LayoutInflater inflater;
    private SparseBooleanArray mSparseBooleanArray;
    private boolean isCustomGalleryActivity;
    private ArrayList<String> imageUrls;
    private ArrayList<awardsData> awardsDataList =new ArrayList<>() ;

    public AddAwardsAdapter(Context gContext,ArrayList<String> imageUrls,boolean isCustomGalleryActivity){
        this.gContext = gContext;
        this.imageUrls = imageUrls;
        AppicationClass.addAwardsDetails.clear();
        inflater = LayoutInflater.from(gContext);
        this.isCustomGalleryActivity = isCustomGalleryActivity;
        mSparseBooleanArray = new SparseBooleanArray();
    }



    @NonNull
    @Override
    public AddAwardsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.awards_recycler, viewGroup, false);
        return new AddAwardsAdapter.ViewHolder(view);
    }

    //Method to return selected Images
    public ArrayList<String> getCheckedItems() {
        ArrayList<String> mTempArry = new ArrayList<String>();

        for (int i = 0; i < imageUrls.size(); i++) {
            if (mSparseBooleanArray.get(i)) {
                mTempArry.add(String.valueOf(imageUrls.get(i)));
            }
        }

        return mTempArry;
    }

    @Override
    public void onBindViewHolder(@NonNull AddAwardsAdapter.ViewHolder viewHolder, final int position) {
        Glide.with(gContext)
                .load(new File(imageUrls.get(position))) // Uri of the picture
                .into(viewHolder.img_android);



        AppicationClass.addAwardsDetails.add("");

        viewHolder.Text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence!=null) {

                    AppicationClass.addAwardsDetails.set(position,charSequence.toString());

                }}

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




        viewHolder.gDeleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUrls.size()>0){
                    AppicationClass.addAwardsDetails.remove(position);
                    imageUrls.remove(position);
                    notifyItemRemoved(position);
                    getCheckedItems();
                    notifyItemRangeChanged(position, imageUrls.size());

                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_android,gDeleteImage;
        EditText Text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_android = itemView.findViewById(R.id.awardImage);
            gDeleteImage= itemView.findViewById(R.id.AwardClose);
            Text= itemView.findViewById(R.id.awardText);
        }
    }
}
