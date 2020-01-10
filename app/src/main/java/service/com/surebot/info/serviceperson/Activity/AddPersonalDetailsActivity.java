package service.com.surebot.info.serviceperson.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.app.Activity;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
import service.com.surebot.info.serviceperson.ApiClient.ApiInterface;
import service.com.surebot.info.serviceperson.Constants.Constants;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.RequestClass.DeleteProfilePicRequest;
import service.com.surebot.info.serviceperson.RequestClass.GetAddressProof_Request;
import service.com.surebot.info.serviceperson.ResponseClass.Add_partner_personal_details_Response;
import service.com.surebot.info.serviceperson.ResponseClass.DeleteProfilePicResponse;
import service.com.surebot.info.serviceperson.ResponseClass.EditPersonalPhotoResponse;
import service.com.surebot.info.serviceperson.ResponseClass.GetAddressProof_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class AddPersonalDetailsActivity extends AppCompatActivity {

    ImageView deleteImg,cameraImg,galleryImg,close1,close2,editImageProf,frontproof,backprof;
    TextView camera,gallery;
    EditText address;
    int i,j=2;
    String[] images = new String[3];

    @BindView(R.id.AddDetailsBack)
    ConstraintLayout back;

    @BindView(R.id.username)
    EditText gUsername;



    @BindView(R.id.editHouseNo)
    EditText gHouseno;

    @BindView(R.id.edditStreet)
    EditText gStreet;

    @BindView(R.id.editArea)
    EditText gArea;

    @BindView(R.id.editCity)
    EditText gCity;

    @BindView(R.id.editState)
    EditText gState;

    @BindView(R.id.editPinCode)
    EditText gPin;

    @BindView(R.id.savebtn)
    Button gSave;

    @BindView(R.id.waitingforapprval)
    Button gWaitingforapprval;









    private Dialog progress;

    ArrayList<GetAddressProof_Response.GetAddressProof_Details> gGettAddressProof_ImagesList;
    String gUserId_FromLogin,gCategoryId_FromLogin,gPremiumPartner_Id;
    String[] gaddresslist;

    String gProfileStatus;

    String gFrontSideImage_FromURL,gBackSideImage_FromURL;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_personal_details);
        ButterKnife.bind(this);
        progress = new Dialog(this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);

        gUserId_FromLogin= AppicationClass.getUserId_FromLogin();
        gCategoryId_FromLogin=AppicationClass.getCategoryId_FromLogin();
        gPremiumPartner_Id = AppicationClass.getPremium_PartenerId();


        progress.setCancelable(true);
        images[1]=images[2]="0";
        initialize();
        listner();

        getAddressProof_Images();
        String imagepath = getIntent().getStringExtra("image");

        String UserName = getIntent().getStringExtra("username");

        String UserAddress = getIntent().getStringExtra("useraddress");
        gProfileStatus= getIntent().getStringExtra("profilestatus");



        if(gProfileStatus.equals("Newly_Registered")  ||gProfileStatus.equals("Rejected") || gProfileStatus.equals("Approved") ){
            onApprovedStatus();

        }
        if(gProfileStatus.equals("Waiting_For_Approval")){

            onWaitingStatus();
        }


        if(UserAddress!=null){

           gaddresslist = UserAddress.split(",");

            gUsername.setText(UserName);
            gHouseno .setText(gaddresslist[0].toString());
            gStreet .setText(gaddresslist[1].toString());
            gArea .setText(gaddresslist[2].toString());
            gCity .setText(gaddresslist[3].toString());
            gState .setText(gaddresslist[4].toString());
            gPin .setText(gaddresslist[5].toString());

        }
     /*   for(int i = 0;i<gaddresslist.length;i++){



        }*/







        if (!imagepath.equals("")){
            Glide.with(AddPersonalDetailsActivity.this).load(Constants.IMAGEBASE_URL+imagepath).into(editImageProf);
        }

    }

    private void onApprovedStatus(){
        gSave.setVisibility(View.VISIBLE);
        gWaitingforapprval.setVisibility(View.GONE);

        gUsername.setEnabled(true);
        gUsername.setFocusable(true);
        gUsername.setClickable(true);
        gUsername.setKeyListener(gUsername.getKeyListener());



        gHouseno.setEnabled(true);
        gHouseno.setFocusable(true);
        gHouseno.setClickable(true);
        gHouseno.setKeyListener(gHouseno.getKeyListener());

        gStreet.setEnabled(true);
        gStreet.setFocusable(true);
        gStreet.setClickable(true);
        gStreet.setKeyListener(gStreet.getKeyListener());

        gArea.setEnabled(true);
        gArea.setFocusable(true);
        gArea.setClickable(true);
        gArea.setKeyListener(gArea.getKeyListener());

        gCity.setEnabled(true);
        gCity.setFocusable(true);
        gCity.setClickable(true);
        gCity.setKeyListener(gCity.getKeyListener());

        gState.setEnabled(true);
        gState.setFocusable(true);
        gState.setClickable(true);
        gState.setKeyListener(gState.getKeyListener());

        gPin.setEnabled(true);
        gPin.setFocusable(true);
        gPin.setClickable(true);
        gPin.setKeyListener(gPin.getKeyListener());

        camera.setClickable(true);
        gallery.setClickable(true);

        galleryImg.setClickable(true);
        cameraImg.setClickable(true);
        deleteImg.setClickable(true);
    }
    private void onWaitingStatus(){

        gWaitingforapprval.setVisibility(View.VISIBLE);
        gSave.setVisibility(View.GONE);

        gUsername.setEnabled(false);
        gUsername.setFocusable(false);
        gUsername.setClickable(false);
        gUsername.setKeyListener(gUsername.getKeyListener());


        gHouseno.setEnabled(false);
        gHouseno.setFocusable(false);
        gHouseno.setClickable(false);
        gHouseno.setKeyListener(gHouseno.getKeyListener());


        gStreet.setEnabled(false);
        gStreet.setFocusable(false);
        gStreet.setClickable(false);
        gStreet.setKeyListener(gStreet.getKeyListener());


        gArea.setEnabled(false);
        gArea.setFocusable(false);
        gArea.setClickable(false);
        gArea.setKeyListener(gArea.getKeyListener());

        gCity.setEnabled(false);
        gCity.setFocusable(false);
        gCity.setClickable(false);
        gCity.setKeyListener(gCity.getKeyListener());

        gState.setEnabled(false);
        gState.setFocusable(false);
        gState.setClickable(false);
        gState.setKeyListener(gState.getKeyListener());

        gPin.setEnabled(false);
        gPin.setFocusable(false);
        gPin.setClickable(false);
        gPin.setKeyListener(gPin.getKeyListener());

        camera.setClickable(false);
        gallery.setClickable(false);

        galleryImg.setClickable(false);
        cameraImg.setClickable(false);
        deleteImg.setClickable(false);
    }
    private void listner() {

        gSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!gUsername.getText().toString().trim().isEmpty()) {
                    if (!gHouseno.getText().toString().trim().isEmpty()) {
                        if (!gStreet.getText().toString().trim().isEmpty()) {
                            if (!gArea.getText().toString().trim().isEmpty()) {
                                if (!gCity.getText().toString().trim().isEmpty()) {
                                    if (!gState.getText().toString().trim().isEmpty()) {
                                        if (!gPin.getText().toString().trim().isEmpty()) {






                                            if(gProfileStatus.equals("Newly_Registered")){
                                            if (images[1].equals("0")) {
                                                Toast.makeText(AddPersonalDetailsActivity.this, "Upload Front Side of Your Address Proof to Continue", Toast.LENGTH_SHORT).show();
                                            } else {
                                                if (images[2].equals(null)) {
                                                    Toast.makeText(AddPersonalDetailsActivity.this, "Upload Back Side of Your Address Proof to Continue", Toast.LENGTH_SHORT).show();

                                                } else
                                                    addPersonaldetailsAPI();
                                            }


                                        }

                                            else if(!gProfileStatus.equals("Newly_Registered")){


                                                if (!images[1].equals("0") ) {
System.out.println("In save button entering into 11111111 if " +   images[1]);
                                                }

                                                else {
                                                    System.out.println("In save button entering into 11111111 else " + gFrontSideImage_FromURL);
                                                    images[1] = gFrontSideImage_FromURL.replaceAll("-", "_");
                                                }

                                                if (!images[2].equals("0")) {
                                                    System.out.println("In save button entering into 22222 if " +   images[2]);
                                                }

                                                else {
                                                    System.out.println("In save button entering into 22222 else " + gBackSideImage_FromURL);
                                                    images[2] = gBackSideImage_FromURL.replaceAll("-", "_");
                                                }




                                                if(gGettAddressProof_ImagesList.size()==1 || gGettAddressProof_ImagesList.size()==2 ){

                                                    addPersonaldetailsAPI();
                                                }



                                            }
                                        } else {
                                            gPin.setError("Enter PinCode");
                                            gPin.requestFocus();
                                        }
                                    } else {
                                        gState.setError("Enter State");
                                        gState.requestFocus();
                                    }
                                } else {
                                    gCity.setError("Enter City");
                                    gCity.requestFocus();
                                }
                            } else {
                                gArea.setError("Enter Area");
                                gArea.requestFocus();
                            }
                        } else {
                            gStreet.setError("Enter Street");
                            gStreet.requestFocus();
                        }
                    } else {
                        gHouseno.setError("Enter House Number");
                        gHouseno.requestFocus();
                    }
                } else {
                    gUsername.setError("Enter Name");
                    gUsername.requestFocus();
                }
            }

        });

        deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(AddPersonalDetailsActivity.this).load(R.drawable.image_border).into(editImageProf);
                deleteProfilePic();
            }
        });
        cameraImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=1;
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);

            }
        });
        galleryImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=1;
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i=j;
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=j;
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        });
        close1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close1.setVisibility(View.GONE);
                Glide.with(getApplicationContext()).load(R.drawable.image_border).into(frontproof);
                images[1]="0";
                j=2;
            }
        });
        close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close2.setVisibility(View.GONE);
                Glide.with(getApplicationContext()).load(R.drawable.image_border).into(backprof);
                images[2]="0";
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            onBackPressed();
            }
        });
    }


    private void initialize() {
        deleteImg = findViewById(R.id.deleteIMG);
        cameraImg = findViewById(R.id.CameraIMG);
        galleryImg = findViewById(R.id.GalleryIMG);
        close1 = findViewById(R.id.btn_close1);
        close2 = findViewById(R.id.btn_close4);
        editImageProf = findViewById(R.id.EditImageProf);
        camera = findViewById(R.id.cameraView);
        gallery = findViewById(R.id.GalleryView);
        //address = findViewById(R.id.editName);
       // name = findViewById(R.id.editAddress);
        frontproof=findViewById(R.id.front);
        backprof=findViewById(R.id.back);
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

                    switch (i) {
                        case 1:
                            Glide.with(getApplicationContext()).load( data.getExtras().get("data")).into(editImageProf);
                            Log.e("yh", "onActivityResult: "+f.toString() );
                            images[0] = f.getPath();
                            add_personal_photo();
                            break;
                        case 2:
                            Glide.with(getApplicationContext()).load( data.getExtras().get("data")).into(frontproof);
                            images[1]=f.getPath();
                            close1.setVisibility(View.VISIBLE);

                            j=3;
                            break;

                        case 3:
                            Glide.with(getApplicationContext()).load( data.getExtras().get("data")).into(backprof);
                            close2.setVisibility(View.VISIBLE);
                            images[2]=f.getPath();


                            break;

                    }

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
                switch (i) {
                    case 1:
                        editImageProf.setImageBitmap(thumbnail);

                        images[0]=picturePath;
                        add_personal_photo();
                        Log.e("yh", "onActivityResult: "+picturePath );
                        break;
                    case 2:
                        frontproof.setImageBitmap(thumbnail);
                        images[1]=picturePath;
                        close1.setVisibility(View.VISIBLE);
                        j=3;
                        break;

                    case 3:
                        backprof.setImageBitmap(thumbnail);

                        images[2]=picturePath;
                        close2.setVisibility(View.VISIBLE);
                        break;


                }

            }
        }
    }


    //Here Calling for API to Update Information

    public void addPersonaldetailsAPI(){
        Log.e("draker", "addPersonaldetailsAPI " );
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

            builder.addFormDataPart("User_ID", gUserId_FromLogin);
            builder.addFormDataPart("User_Name",gUsername.getText().toString());
            builder.addFormDataPart("HouseNo", gHouseno.getText().toString());
            builder.addFormDataPart("Street", gStreet.getText().toString());
            builder.addFormDataPart("Area", gArea.getText().toString());
            builder.addFormDataPart("City", gCity.getText().toString());
            builder.addFormDataPart("State", gState.getText().toString());
            builder.addFormDataPart("Pincode", gPin.getText().toString());
            builder.addFormDataPart("docket", Constants.TOKEN);
            File s1 = new File(images[1]);
            File s2 = new File(images[2]);

            System.out.println(" images value is    " +  images[1] +  " and " + gFrontSideImage_FromURL + images[2] + " and " + gBackSideImage_FromURL);

          //  if(images[1]!=null){
            if (images[1].equals(gFrontSideImage_FromURL) && images[2].equals(gBackSideImage_FromURL)) {

                System.out.println("In add personal details 0000  if ");
            }

            else{

                System.out.println("In add personal details 0000  else ");


                if(images[1].equals(gFrontSideImage_FromURL) || !images[2].equals(gBackSideImage_FromURL)){

                    System.out.println("In add personal details 111111  if ");

                    File AddressFront = new File(gFrontSideImage_FromURL);
                    AddressFront.getName().replace(" ", "s");
                    builder.addFormDataPart("FrontSideImage", AddressFront.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressFront));
                    System.out.println("In add personal details 11111111111  else ");


                    File AddressBack = new File(images[1]);
                    AddressBack.getName().replace(" ", "s");
                    builder.addFormDataPart("ReverseSideImage", AddressBack.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressBack));



                }

              else{
                    System.out.println("In add personal details 11111  else ");

                }
                if(images[2].equals(gBackSideImage_FromURL) || !images[1].equals(gFrontSideImage_FromURL)){


                    File AddressFront = new File(images[1]);
                    AddressFront.getName().replace(" ", "s");
                    builder.addFormDataPart("FrontSideImage", AddressFront.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressFront));
                    System.out.println("In add personal details 11111111111  else ");


                    File AddressBack = new File(gBackSideImage_FromURL);
                    AddressBack.getName().replace(" ", "s");
                    builder.addFormDataPart("ReverseSideImage", AddressBack.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressBack));


                }

                else{
                    System.out.println("In add personal details 2222222222  else ");

                }

                if(!images[2].equals(gBackSideImage_FromURL) && !images[1].equals(gFrontSideImage_FromURL)){

                    System.out.println("In add personal details 3333  if ");


                    File AddressFront = new File(images[1]);
                    AddressFront.getName().replace(" ", "s");
                    builder.addFormDataPart("FrontSideImage", AddressFront.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressFront));
                    System.out.println("In add personal details 11111111111  else ");


                    File AddressBack = new File(images[2]);
                    AddressBack.getName().replace(" ", "s");
                    builder.addFormDataPart("ReverseSideImage", AddressBack.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressBack));



                }

                else {

                    System.out.println("In add personal details 33333  else ");
                }

            }
       /*     if (!images[1].equals(gFrontSideImage_FromURL) && images[2].equals(gBackSideImage_FromURL)) {
                File AddressFront = new File(images[1]);
                AddressFront.getName().replace(" ", "s");
                builder.addFormDataPart("FrontSideImage", AddressFront.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressFront));

            System.out.println("In add personal details 11111111111  if ");
            }

            else{
                File AddressFront = new File(gFrontSideImage_FromURL);
                AddressFront.getName().replace(" ", "s");
                builder.addFormDataPart("FrontSideImage", AddressFront.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressFront));
                System.out.println("In add personal details 11111111111  else ");


                File AddressBack = new File(gBackSideImage_FromURL);
                AddressBack.getName().replace(" ", "s");
                builder.addFormDataPart("ReverseSideImage", AddressBack.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressBack));

            }*/
          //  if(images[2]!=null){

           /* if(!images[2].equals(gBackSideImage_FromURL) &&  images[1].equals(gFrontSideImage_FromURL)){
                File AddressBack = new File(images[2]);
                AddressBack.getName().replace(" ", "s");
                builder.addFormDataPart("ReverseSideImage", AddressBack.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressBack));

                System.out.println("In add personal details 222222222  if ");
            }
            else{

                File AddressFront = new File(gFrontSideImage_FromURL);
                AddressFront.getName().replace(" ", "s");
                builder.addFormDataPart("FrontSideImage", AddressFront.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressFront));



                File AddressBack = new File(gBackSideImage_FromURL);
                AddressBack.getName().replace(" ", "s");
                builder.addFormDataPart("ReverseSideImage", AddressBack.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressBack));
                System.out.println("In add personal details 222222222  else ");

            }*/

            //If Two is also not changed



            System.out.println("Personal details add apis " +  gUserId_FromLogin +" and " + gUsername.getText().toString() +" and " + gHouseno.getText().toString() +" and " + gStreet.getText().toString() +" and " + gCity.getText().toString() +" and " + gState.getText().toString() + gPin.getText().toString() + " and " + s1.getName() +" and " + s2.getName() + " and " +gBackSideImage_FromURL);
            MultipartBody requestBody = builder.build();
            Call<Add_partner_personal_details_Response> call = request.add_personal_details(requestBody);
            call.enqueue(new Callback<Add_partner_personal_details_Response>() {
                @Override
                public void onResponse(Call<Add_partner_personal_details_Response> call, Response<Add_partner_personal_details_Response> response) {

                    if (response.isSuccessful()) {
                        Log.e("Draker", "onResponse: 1" );
                        Add_partner_personal_details_Response lResponse = response.body();
                        System.out.println("Place order entering into method isSuccessful"+lResponse.getRequest_response()+" "+lResponse.getRequest_response());
                        if(lResponse.getRequest_response().equals("valid")){
                            Log.e("Draker", "onResponse: 1" );
                            System.out.println("Place order entering into method valid");
                            Toast.makeText(AddPersonalDetailsActivity.this, "Address Updated Successfully ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddPersonalDetailsActivity.this,PersonalDetailView.class));
                        }else{
                            Toast.makeText(AddPersonalDetailsActivity.this, lResponse.getRequest_response(), Toast.LENGTH_SHORT).show();
                        }
                      progress.dismiss();
                    }

                  progress.dismiss();
                }

                @Override
                public void onFailure(Call<Add_partner_personal_details_Response> call, Throwable t) {
                  Toast.makeText(AddPersonalDetailsActivity.this, getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
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

    ////API for Profile image
        public void add_personal_photo(){

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

                builder.addFormDataPart("User_ID",gUserId_FromLogin);

                builder.addFormDataPart("docket", Constants.TOKEN);

                if(images[0]!=null){
                    Log.e("draker", "add_personal_photo: 1" );
                    File User_Image_Name = new File(images[0]);
                    User_Image_Name.getName().replace(" ", "s");
                    builder.addFormDataPart("User_Image_Name", User_Image_Name.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), User_Image_Name));
                }

                MultipartBody requestBody = builder.build();
                Call<EditPersonalPhotoResponse> call = request.add_personal_photo(requestBody);
                call.enqueue(new Callback<EditPersonalPhotoResponse>() {
                    @Override
                    public void onResponse(Call<EditPersonalPhotoResponse> call, Response<EditPersonalPhotoResponse> response) {
                        Log.e("draker", "onResponse: test" );

                        if (response.isSuccessful()) {
                            Log.e("draker", "add_personal_photo: 2" );

                            EditPersonalPhotoResponse lResponse = response.body();
                            System.out.println("Place order entering into method isSuccessful"+lResponse.getRequest_response()+" "+lResponse.getRequest_response());
                            if(lResponse.getRequest_response().equals("valid")){
                                Log.e("Draker", "onResponse: 1" );
                                System.out.println("Place order entering into method valid");
                                Toast.makeText(AddPersonalDetailsActivity.this, "Profile picture updates successfully", Toast.LENGTH_SHORT).show();

                            }
                            progress.dismiss();
                        }

                        progress.dismiss();
                    }

                    @Override
                    public void onFailure(Call<EditPersonalPhotoResponse> call, Throwable t) {
                        Log.e("draker", "failed: 1" );
                        Toast.makeText(AddPersonalDetailsActivity.this, getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
                          progress.dismiss();
                    }
                });



            }

            catch (Exception e){

                progress.dismiss();
                Log.e("draker", e.toString() );
                e.printStackTrace();
            }

        }

        // Delete Profile Pic API

    public void deleteProfilePic(){

        try {
            System.out.println("In User Login Method 1");
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
            System.out.println("In User Login Method 2");
            ApiInterface request = retrofit.create(ApiInterface.class);
            DeleteProfilePicRequest lservice_request = new DeleteProfilePicRequest();


            lservice_request.setUser_ID(gUserId_FromLogin);
            lservice_request.setDocket(Constants.TOKEN);


            Call<DeleteProfilePicResponse> call = request.DeleteProfilePic(lservice_request);
            call.enqueue(new Callback<DeleteProfilePicResponse>() {
                @Override
                public void onResponse(Call<DeleteProfilePicResponse> call, Response<DeleteProfilePicResponse> response) {
                    if (response.isSuccessful()) {

                        DeleteProfilePicResponse aboutme_response = response.body();

                        if (aboutme_response.getRequest_response().equals("valid")){
                            Toast.makeText(AddPersonalDetailsActivity.this, "ProfilePic Deleted", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AddPersonalDetailsActivity.this, aboutme_response.getRequest_response(), Toast.LENGTH_SHORT).show();
                        }


                    }


                    progress.dismiss();
                }

               // progress.dismiss();

              //  progress.dismiss();



                @Override
                public void onFailure(Call<DeleteProfilePicResponse> call, Throwable t) {
                    Toast.makeText(AddPersonalDetailsActivity.this, getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }
        catch (Exception e) {
            System.out.println("In User Login Method 8");
            e.printStackTrace();
            //progress.dismiss();

        }

    }

    //Get Address Proof images
    private void getAddressProof_Images() {

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
            GetAddressProof_Request lGetAddressProof_Request = new GetAddressProof_Request();

            lGetAddressProof_Request.setDocket(Constants.TOKEN);
            lGetAddressProof_Request.setUser_ID(gUserId_FromLogin);

            Call<GetAddressProof_Response> call = request.Get_AddressProofImages(lGetAddressProof_Request);
            call.enqueue(new Callback<GetAddressProof_Response>() {
                @Override
                public void onResponse(Call<GetAddressProof_Response> call, Response<GetAddressProof_Response> response) {
                    if (response.isSuccessful()) {


                        GetAddressProof_Response lGetAddressProof_Response = response.body();

                        gGettAddressProof_ImagesList = new ArrayList<>(Arrays.asList(lGetAddressProof_Response.getGet_address_proof_details_response()));

                        System.out.println("address Images size is  " + gGettAddressProof_ImagesList.size());
                        if(!gGettAddressProof_ImagesList.get(0).getID().equals("No Results Found")){



                if(gGettAddressProof_ImagesList.size()==1){
                     gFrontSideImage_FromURL = gGettAddressProof_ImagesList.get(0).getDocument_Name();
                    System.out.println("Get Address Proof Images list iffffffffff " + gGettAddressProof_ImagesList.get(0).getDocument_Name() );
                    Glide.with(AddPersonalDetailsActivity.this).load(Constants.IMAGEBASE_URL+gGettAddressProof_ImagesList.get(0).getDocument_Name()).into(frontproof);
                    System.out.println("first Image url is " + Constants.IMAGEBASE_URL+gGettAddressProof_ImagesList.get(0).getDocument_Name());
                }
                else if(gGettAddressProof_ImagesList.size()>=2){
                    gFrontSideImage_FromURL = gGettAddressProof_ImagesList.get(0).getDocument_Name();
                    gBackSideImage_FromURL= gGettAddressProof_ImagesList.get(1).getDocument_Name();
                    System.out.println("Get Address Proof Images list elseeeeee " + gGettAddressProof_ImagesList.get(1).getDocument_Name() );
                    Glide.with(AddPersonalDetailsActivity.this).load(Constants.IMAGEBASE_URL+gGettAddressProof_ImagesList.get(0).getDocument_Name()).into(frontproof);
                    Glide.with(AddPersonalDetailsActivity.this).load(Constants.IMAGEBASE_URL+gGettAddressProof_ImagesList.get(1).getDocument_Name()).into(backprof);

                }




                            progress.dismiss();
                        }
                        progress.dismiss();

                    }

                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<GetAddressProof_Response> call, Throwable t) {
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
