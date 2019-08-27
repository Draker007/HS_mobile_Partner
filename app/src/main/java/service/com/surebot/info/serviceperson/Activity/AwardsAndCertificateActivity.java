package service.com.surebot.info.serviceperson.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.com.surebot.info.serviceperson.Adapter.awardsAdapter;
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.DataFiles.awardsData;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.ResponseClass.Awards_and_CertificateResponse;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class AwardsAndCertificateActivity extends AppCompatActivity implements awardsAdapter.onAwardsListner {

    ImageView camera,gallery;
    RecyclerView r1;
    Button save;
    private Dialog progress;
    awardsAdapter adapter,adapter2;
    int i=0;
    List<awardsData> awardsDatas2 = new ArrayList<>();
    List<awardsData> awardsDatas = new ArrayList<>();
    List<String> Awards = new ArrayList<>();
    ArrayList<String>AwardsUP=new ArrayList<>();
    ConstraintLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awards);
        camera = findViewById(R.id.AwardCamera);
        progress = new Dialog(this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);
        gallery = findViewById(R.id.AwardGallery);
        r1=findViewById(R.id.awardRecycler1);
        save = findViewById(R.id.AwardSave);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(AwardsAndCertificateActivity.this,2);
        r1.setLayoutManager(layoutManager);
        adapter = new awardsAdapter(awardsDatas,this,this);
        back = findViewById(R.id.AwardsBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);

            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        });

      save.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              awardsDatas2 = adapter.retrieveData();
              if(Awards.isEmpty()){
                  Toast.makeText(AwardsAndCertificateActivity.this, "Select Image or click picture", Toast.LENGTH_SHORT).show();
              }else{
                  Log.e("Draker", "onClick: "+Awards.size());

              Log.e("lol", "onCreate: "+awardsDatas2.get(0).getText() );
              //TODO have to clean Array List AwardUP after calling upload api
              award_and_certificate_photos_API();


          }
          }

      });
    }

    private void award_and_certificate_photos_API() {
        try {

           progress.show();
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);

            OkHttpClient.Builder client = new OkHttpClient.Builder();
            HttpLoggingInterceptor registrationInterceptor = new HttpLoggingInterceptor();
            registrationInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(registrationInterceptor);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiInterface request = retrofit.create(ApiInterface.class);

            builder.addFormDataPart("User_ID", AppicationClass.getUserId_FromLogin());

            builder.addFormDataPart("docket",Constants.TOKEN);

            Log.e("draker", "award_and_certificate_photos_API: "+awardsDatas2 );
        for(int o = 0;o<Awards.size();o++){

            File AddressFront = new File(Awards.get(o));
//                AddressFront.getName().replace(" ", "s");
            //Log.e("Draker", "IdentityVeriafy: 1"+AddressFront );
            Log.e("Draker", "IdentityVerify: 1"+awardsDatas2 );
            builder.addFormDataPart("FirstImage[]", AddressFront.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressFront));
if(awardsDatas2.get(o).getText()==null){
    builder.addFormDataPart("Document_Description[]","");
}else {
    builder.addFormDataPart("Document_Description[]", awardsDatas2.get(o).getText());
}       }
            MultipartBody requestBody = builder.build();
            Call<Awards_and_CertificateResponse> call = request.AwardsAndCertificate(requestBody);
            call.enqueue(new Callback<Awards_and_CertificateResponse>() {
                @Override
                public void onResponse(Call<Awards_and_CertificateResponse> call, Response<Awards_and_CertificateResponse> response) {

                    if (response.isSuccessful()) {
                        Log.e("Draker", "onResponse: 1" );
                        Awards_and_CertificateResponse lResponse = response.body();
                   if(lResponse.getAward_cerificate_photo_response().equals("valid")){
                            Log.e("Draker", "onResponse: 1" );
                            System.out.println("Place order entering into method valid");
                            Toast.makeText(AwardsAndCertificateActivity.this, "Awards and Certificate updated successfully", Toast.LENGTH_SHORT).show();

                        }
                       progress.dismiss();
                    }

                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<Awards_and_CertificateResponse> call, Throwable t) {
                    Toast.makeText(AwardsAndCertificateActivity.this, getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
                      progress.dismiss();
                }
            });



        }

        catch (Exception e){

            progress.dismiss();
            Log.e("draker", "addPersonaldetailsAPI: "+e );
            e.printStackTrace();
        }

    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    //  TODO: HAve To fix thisss

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {

                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                try {

                    Uri tempUri = getImageUri(getApplicationContext(), thumbnail);

                    File f = new File(getRealPathFromURI(tempUri));

                    Awards.add(f.getPath());
                    awardsData a = new awardsData(thumbnail);
                        awardsDatas.add(a);
                        r1.setAdapter(adapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {
                Log.e("image", "onActivityResult: " );
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image from ", picturePath + "");

                Awards.add(picturePath);
                awardsData a = new awardsData(thumbnail);
                awardsDatas.add(a);
                r1.setAdapter(adapter);

            }

            }
        }

    @Override
    public void onAwardsClick(int position) {

    }
}

