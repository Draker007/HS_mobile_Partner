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

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
import service.com.surebot.info.serviceperson.Adapter.AddAwardsAdapter;
import service.com.surebot.info.serviceperson.Adapter.GetAwardsDettailsAdapter;
import service.com.surebot.info.serviceperson.Adapter.awardsAdapter;
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.DataFiles.awardsData;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.GetAwardsDetails_Request;
import service.com.surebot.info.serviceperson.ResponseClass.Awards_and_CertificateResponse;
import service.com.surebot.info.serviceperson.ResponseClass.GetAwardsDetails_Response;
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
    List<awardsData> newAwardList = new ArrayList<>();
    List<String> Awards = new ArrayList<>();
    ArrayList<String>AwardsUP=new ArrayList<>();
    ConstraintLayout back;

    ArrayList<GetAwardsDetails_Response.GetAwards_Details> gGetAwordsDetails_ImagesList;
    public static final String CustomGalleryIntentKey = "ImageArray";

    private int GALLERY = 1, CAMERA = 2;
    String selectedImage_path=null;

    private static final int REQUEST_WRITE_PERMISSION = 100;

    private static final int CustomGallerySelectId = 1;

    int PICK_IMAGE_MULTIPLE = 1;




    ArrayList<String> imagesList = new ArrayList<>();

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
                AppicationClass.getAwardsDetails.clear();
                takePhotoFromCamera();

            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppicationClass.getAwardsDetails.clear();


                choosePhotoFromGallary();
            }
        });




      save.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {


              ArrayList<String> addAwardsDetails =new ArrayList<>();

              addAwardsDetails= AppicationClass.getAddAwardsDetails();

              System.out.println("Awards Details Arraylist size is " +AppicationClass.getAddAwardsDetails());
              awardsDatas2 = adapter.retrieveData();

              System.out.println("In Save Button arraylist sizes are " + imagesList.size() + AppicationClass.getAddAwardsDetails().size());
              if(imagesList.size()>0){

                  //TODO have to clean Array List AwardUP after calling upload api
                  addAward_and_certificate_photos();

              }
              else{

                  Toast.makeText(AwardsAndCertificateActivity.this, "Select Image or click picture", Toast.LENGTH_SHORT).show();


          }
          }

      });


        getAwards_Images();

    }  //On Create Close

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);

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
        if (resultCode == AwardsAndCertificateActivity.this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
               /* Uri contentURI = data.getData();

                String galaryiamge = String.valueOf(data.getData());
                Glide.with(UserPlacerOrderforMedicine_Activity.this).load(galaryiamge).into(gSelected_image);
                selectedImage_path= "/storage/emulated/0/DCIM/Camera/sub1.png";
                System.out.println("Image path in Order by prescription is"+galaryiamge);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(UserPlacerOrderforMedicine_Activity.this.getContentResolver(), contentURI);
                    // String path = saveImage(bitmap);

                    //    Glide.with(getActivity()).load(path).into(gSelected_image);
                    Toast.makeText(UserPlacerOrderforMedicine_Activity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    //gSelected_image.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(UserPlacerOrderforMedicine_Activity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
*/





                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();



                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                selectedImage_path = cursor.getString(columnIndex);
                cursor.close();


                BitmapFactory.Options bitMapOption = new BitmapFactory.Options();
                bitMapOption.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImage_path, bitMapOption);




                imagesList.add(selectedImage_path);
                AddAwardsAdapter imageListAdapter = new AddAwardsAdapter(AwardsAndCertificateActivity.this,imagesList,false);
                // gImagefromGallery_Path =("/storage/emulated/0/DCIM/Camera/sub1.png");
                r1.setVisibility(View.VISIBLE);
                r1.setAdapter(imageListAdapter);





            }

        }

      /*  if (requestCode == GALLERY && resultCode == RESULT_OK && null != data) {
            String imagesArray = data.getStringExtra(CustomGalleryIntentKey);
            //   selectedImages = new ArrayList<>();

            imagefile.addAll(Arrays.asList(imagesArray.replaceAll("-","_").substring(1, imagesArray.length() - 1).split(", ")));

            System.out.println("Selected sub activities imagefile size is" + imagefile.size());
            loadImage();


          *//*  if(imagefile.size()>0) {
                loadImage();
            }
            else {
                gMultipleImage.setVisibility(View.VISIBLE);
            }*//*

        }*/
        else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
           // Glide.with(AwardsAndCertificateActivity.this).load( data.getExtras().get("data")).into(gSelected_image);

            //gSelected_image.setImageBitmap(thumbnail);
            // saveImage(thumbnail);
            Toast.makeText(AwardsAndCertificateActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();

            Uri tempUri = getImageUri(getApplicationContext(), thumbnail);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            File finalFile = new File(getRealPathFromURI(tempUri));

            selectedImage_path =finalFile.getPath();

            imagesList.add(selectedImage_path);
            AddAwardsAdapter imageListAdapter = new AddAwardsAdapter(AwardsAndCertificateActivity.this,imagesList,false);
            // gImagefromGallery_Path =("/storage/emulated/0/DCIM/Camera/sub1.png");
            r1.setVisibility(View.VISIBLE);
            r1.setAdapter(imageListAdapter);

        /*    gPreferences_layout.setVisibility(View.VISIBLE);
            imagesList.add(selectedImage_path);
            ImageListAdapter imageListAdapter = new ImageListAdapter(UserPlacerOrderforMedicine_Activity.this,imagesList);
            // gImagefromGallery_Path =("/storage/emulated/0/DCIM/Camera/sub1.png");

            grecyclerview_emptyimages.setAdapter(imageListAdapter);
            System.out.println("Image Url From Camera is"+finalFile.getPath());*/
        }
    }



    /*@Override
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
            }

            else if (requestCode == 2) {
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
        }*/

    @Override
    public void onAwardsClick(int position) {

    }


    //Add Awards and Certificates
    private void addAward_and_certificate_photos() {
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

            builder.addFormDataPart("User_ID",AppicationClass.getUserId_FromLogin());

            builder.addFormDataPart("docket",Constants.TOKEN);

      /*  for(int o = 0;o<Awards.size();o++){

            File AddressFront = new File(Awards.get(o));
//                AddressFront.getName().replace(" ", "s");
            //Log.e("Draker", "IdentityVeriafy: 1"+AddressFront );

            System.out.println("Description array in Add  " + awardsDatas2.get(o).getText());
            builder.addFormDataPart("FirstImage[]", AddressFront.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressFront));
                if(awardsDatas2.get(o).getText()==null){

                    System.out.println("In Awards Description entering into iffffffff");
                    builder.addFormDataPart("Document_Description[]","");
                }
                else {
                    System.out.println("In Awards Description entering into elseeeeeeeee");
                   builder.addFormDataPart("Document_Description[]", awardsDatas2.get(o).getText());

                }


            System.out.println("In Awards Description entering into for");

        }*/

            if (AppicationClass.getAddAwardsDetails().size() > 0) {
                for (int i = 0; i < AppicationClass.getAddAwardsDetails().size(); i++) {
                    builder.addFormDataPart("Document_Description[]", AppicationClass.getAddAwardsDetails().get(i));

                }
            }

            System.out.println("in Add Method Entrting into request " + imagesList.size() + " and " + AppicationClass.getAddAwardsDetails().size());
//Awards Images
            if (imagesList.size() > 0) {


                for (int i = 0; i < imagesList.size(); i++) {
                    System.out.println("in Add Method Entrting into first If " + imagesList.get(i));

                    File AddAwardsList = new File(imagesList.get(i));
                    builder.addFormDataPart("FirstImage[]", AddAwardsList.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddAwardsList));
                }
            }

            //Award Details Text
/*
            if (AppicationClass.getAddAwardsDetails().size() > 0) {

                for (int i = 0; i < AppicationClass.getAddAwardsDetails().size(); i++) {

                    System.out.println("in Add Method Entrting into Second If " + AppicationClass.getAddAwardsDetails().get(i));

                    File AddAwardsDetailsList = new File(AppicationClass.getAddAwardsDetails().get(i));

                    System.out.println("Awards Details is " + AppicationClass.getAddAwardsDetails().get(i) );
                    builder.addFormDataPart("Document_Description", AddAwardsDetailsList.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddAwardsDetailsList));

                }
            }*/

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
                            onBackPressed();
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

    //Get Awards and Certificates

    private void getAwards_Images() {

        try {
            progress.show();
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
            GetAwardsDetails_Request lGetAwardsDetails_Request = new GetAwardsDetails_Request();

            lGetAwardsDetails_Request.setDocket(Constants.TOKEN);
            lGetAwardsDetails_Request.setUser_ID(AppicationClass.getUserId_FromLogin());

            Call<GetAwardsDetails_Response> call = request.Get_AwardsDetails(lGetAwardsDetails_Request);
            call.enqueue(new Callback<GetAwardsDetails_Response>() {
                @Override
                public void onResponse(Call<GetAwardsDetails_Response> call, Response<GetAwardsDetails_Response> response) {
                    if (response.isSuccessful()) {


                        GetAwardsDetails_Response lGetAwardsDetails_Response = response.body();

                        gGetAwordsDetails_ImagesList = new ArrayList<>(Arrays.asList(lGetAwardsDetails_Response.getGet_award_and_certificate_photos_details_response()));

                        if(!gGetAwordsDetails_ImagesList.get(0).getID().equals("No Results Found")){


                            ArrayList<String> imagesList = new ArrayList<>();
                            AppicationClass.getAwardsDetails.add("");
                            AppicationClass.getAwardsDetails.clear();
                            System.out.println("Award Details Description Size " + gGetAwordsDetails_ImagesList.size());

                            for(int i=0;i<gGetAwordsDetails_ImagesList.size();i++){
                                AppicationClass.getAwardsDetails.add("");
                                imagesList.add(gGetAwordsDetails_ImagesList.get(i).getDocument_Name());
                                System.out.println("Award Details Description Size "+ i + gGetAwordsDetails_ImagesList.get(i).getDocument_Description());

                               AppicationClass.getAwardsDetails.set(i,gGetAwordsDetails_ImagesList.get(i).getDocument_Description());

                            }


                            GetAwardsDettailsAdapter lGetAwardsDettailsAdapter = new GetAwardsDettailsAdapter(AwardsAndCertificateActivity.this,imagesList,false);
                            // gImagefromGallery_Path =("/storage/emulated/0/DCIM/Camera/sub1.png");
                            r1.setVisibility(View.VISIBLE);
                            r1.setAdapter(lGetAwardsDettailsAdapter);





                            progress.dismiss();
                        }
                        progress.dismiss();

                    }

                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<GetAwardsDetails_Response> call, Throwable t) {
                    System.out.println("In User Login Method 7");
                    progress.dismiss();
                }
            });
        }catch (Exception e) {
            progress.dismiss();
            e.printStackTrace();


        }

    }
}

