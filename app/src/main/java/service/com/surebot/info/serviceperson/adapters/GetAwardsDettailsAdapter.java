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

import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.DataFiles.awardsData;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.ApplicationClass;



public class GetAwardsDettailsAdapter  extends RecyclerView.Adapter<GetAwardsDettailsAdapter.ViewHolder> {

    public Context gContext;

    ArrayList<String> deltedImg = new ArrayList<>();
    private LayoutInflater inflater;
    private SparseBooleanArray mSparseBooleanArray;
    private boolean isCustomGalleryActivity;
    private ArrayList<String> imageUrls;
    private ArrayList<awardsData> awardsDataList =new ArrayList<>() ;

    public GetAwardsDettailsAdapter(Context gContext,ArrayList<String> imageUrls,boolean isCustomGalleryActivity){
        this.gContext = gContext;
        this.imageUrls = imageUrls;
        ApplicationClass.addAwardsDetails.clear();
        inflater = LayoutInflater.from(gContext);
        this.isCustomGalleryActivity = isCustomGalleryActivity;
        mSparseBooleanArray = new SparseBooleanArray();
    }



    @NonNull
    @Override
    public GetAwardsDettailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.awards_recycler, viewGroup, false);
        return new GetAwardsDettailsAdapter.ViewHolder(view);
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
    public void onBindViewHolder(@NonNull GetAwardsDettailsAdapter.ViewHolder viewHolder, final int position) {
        Glide.with(gContext)
                .load(Constants.IMAGEBASE_URL+new File(imageUrls.get(position))) // Uri of the picture
                .into(viewHolder.img_android);
        System.out.println("In Adapter Array size is @@@@@@" + ApplicationClass.getGetAwardsDetails().size());

        if(ApplicationClass.getGetAwardsDetails().size()>0){
            System.out.println("In Adapter Array size is " + ApplicationClass.getGetAwardsDetails().size());
            viewHolder.Text.setText(ApplicationClass.getGetAwardsDetails().get(position));
        }




        ApplicationClass.addAwardsDetails.add("");

        viewHolder.Text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence!=null) {

                    ApplicationClass.addAwardsDetails.set(position,charSequence.toString());

                }}

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




        viewHolder.gDeleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUrls.size()>0){


                    ApplicationClass.addAwardsDetails.remove(position);
                    imageUrls.remove(position);
                    notifyItemRemoved(position);
                    getCheckedItems();
                    notifyItemRangeChanged(position, imageUrls.size());


                    // New Cancel popup

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