package service.com.surebot.info.serviceperson.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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
import service.com.surebot.info.serviceperson.ResponseClass.Identity_verification_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class IdentityVerificationActivity extends AppCompatActivity {
    String[] items;
    Spinner dropdown;
    ImageView front,back;
    Button submit;
    @BindView(R.id.idProofText)
    TextView gidProofText;
    String front1 ,back1;
    int i,j;
    private Dialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_verification);
         dropdown = findViewById(R.id.spinner1);
        ButterKnife.bind(this);
        progress = new Dialog(this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);
         items = new String[]{"* Select your ID Proof ","Adhar Card", "Driving License", "Registration Certificate","Voter_ID"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);
      //  android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinner);
        Initialize();
        listner();
    }

    private void listner() {
        front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=1;
                selectImage();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=2;
                selectImage();
            }
        });
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.e("lol", "onItemSelected: "+items[position] );
                gidProofText.setText(items  [position]);
                j=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(front1.isEmpty()|| back1.isEmpty()){
                    Toast.makeText(IdentityVerificationActivity.this, "Upload documents First", Toast.LENGTH_SHORT).show();
                }
                else{
                    IdentityVerify();
                }
            }
        });

    }
//Calling API for uploading document
    private void IdentityVerify() {
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
            builder.addFormDataPart("Document_Category_ID", String.valueOf(j));
            builder.addFormDataPart("docket",Constants.TOKEN);
            Log.e("Draker", "IdentityVerify: 0"+String.valueOf(j) );

            if(front1!=null){
                File AddressFront = new File(front1);
//                AddressFront.getName().replace(" ", "s");
                Log.e("Draker", "IdentityVerify: 1"+AddressFront );
                builder.addFormDataPart("FrontSideImage", AddressFront.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressFront));
            }
            if(back1!=null){
                File AddressBack = new File(back1);
//                AddressBack.getName().replace(" ", "s");
                Log.e("Draker", "IdentityVerify: 2" +AddressBack);
                builder.addFormDataPart("ReverseSideImage", AddressBack.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressBack));
            }
            MultipartBody requestBody = builder.build();
            Call<Identity_verification_Response> call = request.IdentityVerif(requestBody);
            call.enqueue(new Callback<Identity_verification_Response>() {
                @Override
                public void onResponse(Call<Identity_verification_Response> call, Response<Identity_verification_Response> response) {

                    if (response.isSuccessful()) {
                        Log.e("Draker", "onResponse: 1" );
                        Identity_verification_Response lResponse = response.body();
                        System.out.println("Place order entering into method isSuccessful"+lResponse.getRequest_response()+" "+lResponse.getRequest_response());
                        if(lResponse.getRequest_response().equals("valid")){
                            Log.e("Draker", "onResponse: 1" );
                            System.out.println("Place order entering into method valid");
                            Toast.makeText(IdentityVerificationActivity.this, "Profile picture updates successfully", Toast.LENGTH_SHORT).show();

                        }
                        progress.dismiss();
                    }

                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<Identity_verification_Response> call, Throwable t) {
                    Log.e("Draker", "onFailed: 1" +t);
                    //   progress.dismiss();
                }
            });



        }

        catch (Exception e){

            progress.dismiss();
            Log.e("draker", "addPersonaldetailsAPI: "+e );
            e.printStackTrace();
        }

    }

    private void Initialize() {
        front = findViewById(R.id.verifyFront);
        back = findViewById(R.id.verifyBack);
        submit = findViewById(R.id.btnConfirmVerify);
    }


    private void selectImage() {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(
                IdentityVerificationActivity.this);
        myAlertDialog.setTitle("Upload Pictures Option");
        myAlertDialog.setMessage("How do you want to Upload your picture?");

        myAlertDialog.setPositiveButton("Gallery",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 2);
                    }
                });

        myAlertDialog.setNegativeButton("Camera",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {


                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 1);

                    }
                });
        myAlertDialog.show();


    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (IdentityVerificationActivity.this.getContentResolver() != null) {
            Cursor cursor = IdentityVerificationActivity.this.getContentResolver().query(uri, null, null, null, null);
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

                    Uri tempUri = getImageUri(IdentityVerificationActivity.this.getApplicationContext(), thumbnail);
                    // CALL THIS METHOD TO GET THE ACTUAL PATH
                    File f = new File(getRealPathFromURI(tempUri));

                    if(i==1) {
                    Glide.with(getApplicationContext()).load(data.getExtras().get("data")).into(front);
                    Log.e("yh", "onActivityResult: " + f.toString());
                    front1 = f.getPath();
                }else {
                    Glide.with(getApplicationContext()).load(data.getExtras().get("data")).into(back);
                    Log.e("yh", "onActivityResult: " + f.toString());
                  back1 = f.getPath();
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
                if(i==1){
                    Glide.with(getApplicationContext()).load(picturePath).into(front);
                   front1 = picturePath;

                }else
                {
                    Glide.with(getApplicationContext()).load(picturePath).into(back);
                   back1 = picturePath;

                }


            }
        }
    }

}
