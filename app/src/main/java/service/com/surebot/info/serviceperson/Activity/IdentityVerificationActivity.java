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
import android.os.Environment;
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
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

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
import service.com.surebot.info.serviceperson.RequestClass.GetDocumentslist_Request;
import service.com.surebot.info.serviceperson.RequestClass.GetIdentityVerifications_Request;
import service.com.surebot.info.serviceperson.ResponseClass.GetAwardsDetails_Response;
import service.com.surebot.info.serviceperson.ResponseClass.GetDocumentslist_Response;
import service.com.surebot.info.serviceperson.ResponseClass.GetIdentityVerifications_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Identity_verification_Response;
import service.com.surebot.info.serviceperson.utils.AppicationClass;

public class IdentityVerificationActivity extends AppCompatActivity {
   // String[] items;

    ArrayList<String> gDocuments_Arraylist;
    ArrayList<String> gDocumentsID_Arraylist;
    String gDocument_Id;
    Spinner dropdown;
    ImageView front,back;
    Button submit;
    Button gWaitingforAproval_Button;

    @BindView(R.id.identityVerfBack)
    ConstraintLayout goback;

    @BindView(R.id.idProofText)
    TextView gidProofText;
    String front1="" ,back1="";
    int i,j;
    private Dialog progress;

    ArrayList<GetIdentityVerifications_Response.GetIdentityVerifications_Details> gGetIdentityVerifications_ImagesList;

    ArrayList<GetDocumentslist_Response.GetDocumentslist_Records> gGetDocuments_List;
    ArrayAdapter<String> adapter;

    String gUserId_FromLogin,gCategoryId_FromLogin,gPremiumPartner_Id;

    String gFrontSide_Image_FromAPI , gBackSide_Image_FromAPI;

    String gPartnerApproval_Status;


  String  gPicture_Path ;


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


        gUserId_FromLogin= AppicationClass.getUserId_FromLogin();
        gCategoryId_FromLogin=AppicationClass.getCategoryId_FromLogin();
        gPremiumPartner_Id = AppicationClass.getPremium_PartenerId();


         //items = new String[]{"* Select your ID Proof ","Adhar Card", "Driving License", "Registration Certificate","Voter_ID"};


      //  android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinner);

        Initialize();
        listner();
        get_DocumentList();


        getdentityDocuments_Images();


    }  //OnCreate Close

    private void listner() {
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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


                System.out.println("Document id is " + gDocumentsID_Arraylist.get(position));
                gidProofText.setText(gDocuments_Arraylist.get(position).toString());

                gDocument_Id=gDocumentsID_Arraylist.get(position).toString();

                back1="";
              //  Glide.with(IdentityVerificationActivity.this).load(R.drawable.emptyprofile_image).into(front);
                //Glide.with(IdentityVerificationActivity.this).load(R.drawable.emptyprofile_image).into(back);
                front1="";
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


/*

                if(j==0){
                    Toast.makeText(IdentityVerificationActivity.this, "Select ID Proof", Toast.LENGTH_SHORT).show();
                }
                else if(front1.isEmpty()){
                    Toast.makeText(IdentityVerificationActivity.this, "Upload Front Side First", Toast.LENGTH_SHORT).show();
                }
                else if (back1.isEmpty()){
                    Toast.makeText(IdentityVerificationActivity.this, "Upload Reverse Side of Image", Toast.LENGTH_SHORT).show();
                }

                else{


                    add_IdentityVerify();



                    }
*/


                // New

                if(j!=0){

                    if(gPartnerApproval_Status.equals("Newly_Registered")){

                        System.out.println("Document iamage of front side" + front1);
                        System.out.println("Document iamage of reverse side" + back1);
                        if(!front1.isEmpty()){
                            if(!back1.isEmpty()){

                                add_IdentityVerify();

                            }

                            else {
                                Toast.makeText(IdentityVerificationActivity.this, "Upload Reverse Side of Image", Toast.LENGTH_SHORT).show();
                            }

                        }

                        else {
                            Toast.makeText(IdentityVerificationActivity.this, "Upload Front Side First", Toast.LENGTH_SHORT).show();

                        }

                    }
                    else if(!gPartnerApproval_Status.equals("Newly_Registered")){

                        System.out.println("Api method checking 111111111");

                        if(!front1.isEmpty()){
                            System.out.println("Api method checking 222222");
                            front1 = gFrontSide_Image_FromAPI.replaceAll("-", "_");
                        }
                        else{

                        }

                        if(!back1.isEmpty()){
                            System.out.println("Api method checking 33333333333");
                            back1 = gBackSide_Image_FromAPI.replaceAll("-", "_");
                        }
                        else{

                        }

                        if(gGetIdentityVerifications_ImagesList.size()==1 || gGetIdentityVerifications_ImagesList.size()==2 ){
System.out.println("In confirm buttom back end url is " + gFrontSide_Image_FromAPI + "  and  " + gBackSide_Image_FromAPI);
                            add_IdentityVerify();
                        }
                    }

                }

                else {
                    Toast.makeText(IdentityVerificationActivity.this, "Select ID Proof", Toast.LENGTH_SHORT).show();

                }


            }
        });

    }


    private void Initialize() {
        front = findViewById(R.id.verifyFront);
        back = findViewById(R.id.verifyBack);
        submit = findViewById(R.id.btnConfirmVerify);
        gWaitingforAproval_Button = findViewById(R.id.waitingforapproval);
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
                }

                    else {
                    Glide.with(getApplicationContext()).load(data.getExtras().get("data")).into(back);
                    Log.e("yh", "onActivityResult: " + f.toString());
                  back1 = f.getPath();
                }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            else if (requestCode == 2) {

           /*     Log.e("image", "onActivityResult: " );
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

*/

                // New For Repalcing Path name

                Uri uri = data.getData();

                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);

                    //   Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

                    Uri tempUri = getImageUri(IdentityVerificationActivity.this.getApplicationContext(), bitmap);
                    // CALL THIS METHOD TO GET THE ACTUAL PATH
                    File f = new File(getRealPathFromURI(tempUri));

                    gPicture_Path = f.getPath();


                } catch (IOException e) {
                    e.printStackTrace();
                }


                if(i==1){
                    Glide.with(getApplicationContext()).load(gPicture_Path).into(front);
                    front1 = gPicture_Path;

                    System.out.println("In on activity front images is " +  front1);

                }else
                {
                    Glide.with(getApplicationContext()).load(gPicture_Path).into(back);
                    back1 = gPicture_Path;
                    System.out.println("In on activity reverse images is " +  back1);
                }




            }
        }
    }


    //Calling API for uploading document
    private void add_IdentityVerify() {
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
            //AppicationClass.getUserId_FromLogin()
            builder.addFormDataPart("User_ID", gUserId_FromLogin);
            builder.addFormDataPart("Document_Category_ID", gDocument_Id);
            builder.addFormDataPart("docket",Constants.TOKEN);
            Log.e("Draker", "IdentityVerify: 0"+String.valueOf(j) );
            System.out.println("Api method checking 4444444444444"  + gDocument_Id);
          /*  if(front1!=null){
                System.out.println("Enters In add Identity front if");
                File AddressFront = new File(front1);
                   AddressFront.getName().replace(" ", "s");
                Log.e("Draker", "IdentityVerify: 1"+AddressFront );
                builder.addFormDataPart("FrontSideImage", AddressFront.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressFront));
            }
            else {
                System.out.println("Enters In add Identity front else");
            }

            if(back1!=null){
                System.out.println("Enters In add Identity back if");
                File AddressBack = new File(back1);
               AddressBack.getName().replace(" ", "s");
                Log.e("Draker", "IdentityVerify: 2" +AddressBack);
                builder.addFormDataPart("ReverseSideImage", AddressBack.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressBack));
            }

            else{
                System.out.println("Enters In add Identity back else");
            }*/


            //Key adding flow gFrontSide_Image_FromAPI
            System.out.println("Api method checking 55555");

            if (front1.equals(gFrontSide_Image_FromAPI) && back1.equals(gBackSide_Image_FromAPI)) {

                System.out.println("In add personal details 0000  if ");

                File AddressFront = new File(gFrontSide_Image_FromAPI);
                String front_path = AddressFront.getPath();

                String[] front_path_split = front_path.split("/");

                String lfront = front_path_split[front_path_split.length-1];

                builder.addFormDataPart("FrontSideImage", lfront);




                File AddressBack = new File(gBackSide_Image_FromAPI);
                String reverse_path = AddressBack.getPath();

                String[] reverse_path_split = reverse_path.split("/");

                String lreverse = reverse_path_split[reverse_path_split.length-1];


                System.out.println("Reverse side image is " + lreverse);
                builder.addFormDataPart("ReverseSideImage",lreverse);



            }

            else {

                System.out.println ("Images Value is " + front1 + " and " +  gFrontSide_Image_FromAPI  + " and " + gBackSide_Image_FromAPI  + " and " + back1 );

                if(front1.equals(gFrontSide_Image_FromAPI) && !back1.equals(gBackSide_Image_FromAPI)){

                    System.out.println("In add personal details 111111  if ");

                    File AddressFront = new File(gFrontSide_Image_FromAPI);
                    String front_path = AddressFront.getPath();

                    String[] front_path_split = front_path.split("/");

                    String lfront = front_path_split[front_path_split.length-1];

                    builder.addFormDataPart("FrontSideImage", lfront);
                    System.out.println("Front side image is " + lfront);



                    File AddressBack = new File(back1);
                    AddressBack.getName().replace(" ", "s");
                    builder.addFormDataPart("ReverseSideImage", AddressBack.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressBack));



                }

               /* else{
                    System.out.println("In add personal details 11111  else ");

                }*/



              else  if(back1.equals(gBackSide_Image_FromAPI) && !front1.equals(gFrontSide_Image_FromAPI)){


                    File AddressFront = new File(front1);
                    // AddressFront.getName().replace(" ", "s");
                    builder.addFormDataPart("FrontSideImage", AddressFront.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressFront));
                    System.out.println("In add personal details 22222  ifff ");



                    File AddressBack = new File(gBackSide_Image_FromAPI);
                    String reverse_path = AddressBack.getPath();

                    String[] reverse_path_split = reverse_path.split("/");

                    String lreverse = reverse_path_split[reverse_path_split.length-1];


                    System.out.println("Reverse side image is " + lreverse);
                    builder.addFormDataPart("ReverseSideImage",lreverse);


                }

              /*  else{
                    System.out.println("In add personal details 2222222222  else ");

                }*/


              else  if(!back1.equals(gBackSide_Image_FromAPI) && !front1.equals(gFrontSide_Image_FromAPI)){

                    System.out.println("In add personal details 3333  if ");


                    File AddressFront = new File(front1);
                    //  AddressFront.getName().replace(" ", "s");
                    builder.addFormDataPart("FrontSideImage", AddressFront.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressFront));
                    System.out.println("In add personal details 33333333  if ");


                    File AddressBack = new File(back1);
                    AddressBack.getName().replace(" ", "s");
                    builder.addFormDataPart("ReverseSideImage", AddressBack.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), AddressBack));



                }

                else {

                    System.out.println("In add personal details 33333  else ");
                }


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
                            Toast.makeText(IdentityVerificationActivity.this, "Documents  updated successfully", Toast.LENGTH_SHORT).show();

                            getdentityDocuments_Images();
                            // getdentityDocuments_Images();
                        //    onBackPressed();
                        }else
                        {
                            Toast.makeText(IdentityVerificationActivity.this, lResponse.getRequest_response(), Toast.LENGTH_SHORT).show();
                        }
                        progress.dismiss();
                    }

                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<Identity_verification_Response> call, Throwable t) {
                    Log.e("Draker", "onFailed: 1" +t);
                    Toast.makeText(IdentityVerificationActivity.this, getResources().getString(R.string.onfailure), Toast.LENGTH_SHORT).show();


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


    //Get Added Identity Details

    private void getdentityDocuments_Images() {

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
            GetIdentityVerifications_Request lGetIdentityVerifications_Request = new GetIdentityVerifications_Request();

            lGetIdentityVerifications_Request.setDocket(Constants.TOKEN);
            lGetIdentityVerifications_Request.setUser_ID(gUserId_FromLogin);

            Call<GetIdentityVerifications_Response> call = request.Get_IdentityVerifications(lGetIdentityVerifications_Request);
            call.enqueue(new Callback<GetIdentityVerifications_Response>() {
                @Override
                public void onResponse(Call<GetIdentityVerifications_Response> call, Response<GetIdentityVerifications_Response> response) {
                    if (response.isSuccessful()) {

         System.out.println(" GetIdentity Enters into is successfull method");
                        GetIdentityVerifications_Response lGetIdentityVerifications_Response = response.body();

                        gGetIdentityVerifications_ImagesList = new ArrayList<>(Arrays.asList(lGetIdentityVerifications_Response.getGet_identity_verification_details_response()));

                        gPartnerApproval_Status = lGetIdentityVerifications_Response.getGet_identity_verification_details_status();

                        if(gPartnerApproval_Status.equals("Newly_Registered")  ||gPartnerApproval_Status.equals("Rejected") || gPartnerApproval_Status.equals("Approved") ){
                            onApprovedStatus();

                        }
                        if(gPartnerApproval_Status.equals("Waiting_For_Approval")){

                            onWaitingStatus();
                        }

                        if(!gGetIdentityVerifications_ImagesList.get(0).getID().equals("No Results Found")){

                            System.out.println(" GetIdentity Enters into is if Condition");

                            if(gGetIdentityVerifications_ImagesList.size()==1){
                                Glide.with(IdentityVerificationActivity.this).load(Constants.IMAGEBASE_URL+gGetIdentityVerifications_ImagesList.get(0).getDocument_Name()).into(front);
                                gFrontSide_Image_FromAPI = gGetIdentityVerifications_ImagesList.get(0).getDocument_Name();

                            }
                            if(gGetIdentityVerifications_ImagesList.size()>=2){
                                Glide.with(IdentityVerificationActivity.this).load(Constants.IMAGEBASE_URL+gGetIdentityVerifications_ImagesList.get(0).getDocument_Name()).into(front);
                                Glide.with(IdentityVerificationActivity.this).load(Constants.IMAGEBASE_URL+gGetIdentityVerifications_ImagesList.get(1).getDocument_Name()).into(back);
                                gFrontSide_Image_FromAPI=gGetIdentityVerifications_ImagesList.get(0).getDocument_Name();
                                gBackSide_Image_FromAPI=gGetIdentityVerifications_ImagesList.get(1).getDocument_Name();
                            }






                            adapter = new ArrayAdapter<String>(IdentityVerificationActivity.this, android.R.layout.simple_spinner_item, gDocuments_Arraylist);
                            dropdown.setAdapter(adapter);


                           // dropdown.setSelection(((ArrayAdapter<String>) dropdown.getAdapter()).getPosition(gGetIdentityVerifications_ImagesList.get(i).getActivity_Category_Name()));



                            dropdown.setSelection(((ArrayAdapter<String>) dropdown.getAdapter()).getPosition(gGetIdentityVerifications_ImagesList.get(i).getDocument_Category_Name()));
                           // dropdown.setSelection(Integer.parseInt(gGetIdentityVerifications_ImagesList.get(0).getDocument_Category_ID()));
                            gDocument_Id = gGetIdentityVerifications_ImagesList.get(0).getDocument_Category_ID();

                            progress.dismiss();

                        }

                        else{

                            Glide.with(IdentityVerificationActivity.this).load(R.drawable.emptyprofile_image).into(front);
                            Glide.with(IdentityVerificationActivity.this).load(R.drawable.emptyprofile_image).into(back);
                        }
                        System.out.println(" GetIdentity Enters into is after if");
                        progress.dismiss();

                    }

                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<GetIdentityVerifications_Response> call, Throwable t) {
                    System.out.println(" GetIdentity Enters into  onFailure");

                    progress.dismiss();
                }
            });
        }catch (Exception e) {
            System.out.println(" GetIdentity Enters into  catch");
            progress.dismiss();
            e.printStackTrace();


        }

    }


    //Get Documents list


    private void get_DocumentList() {

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
            GetDocumentslist_Request lGetIdentityVerifications_Request = new GetDocumentslist_Request();

            lGetIdentityVerifications_Request.setDocket(Constants.TOKEN);
            lGetIdentityVerifications_Request.setUser_ID(gUserId_FromLogin);

            Call<GetDocumentslist_Response> call = request.Get_Documentslist(lGetIdentityVerifications_Request);
            call.enqueue(new Callback<GetDocumentslist_Response>() {
                @Override
                public void onResponse(Call<GetDocumentslist_Response> call, Response<GetDocumentslist_Response> response) {
                    if (response.isSuccessful()) {

                        System.out.println(" GetIdentity Enters into is successfull method");
                        GetDocumentslist_Response lGetDocuments_List = response.body();

                        gGetDocuments_List = new ArrayList<>(Arrays.asList(lGetDocuments_List.getVerification_document_details_response()));

                        gDocumentsID_Arraylist = new ArrayList<>();
                            for(int i=0;i<gGetDocuments_List.size();i++){


                                gDocuments_Arraylist = new ArrayList<>();

//                            gActivityCategoryId_Arraylist=new ArrayList<>();


                                gDocuments_Arraylist.add(0,lGetDocuments_List.getDocumentHint());
                                gDocumentsID_Arraylist.add(0,gGetDocuments_List.get(0).getDocument_Category_ID());


                                for (int j = 0; j <= i; j++) {
                                    gDocuments_Arraylist.add(1, gGetDocuments_List.get(j).getDocument_Category_Name());
                                    gDocumentsID_Arraylist.add(1, gGetDocuments_List.get(j).getDocument_Category_ID());





                                }


                              /*  gDocuments_Arraylist.add(gGetDocuments_List.get(i).getDocument_Category_Name());
                                gDocumentsID_Arraylist.add(gGetDocuments_List.get(i).getDocument_Category_ID());*/
                            }

                        adapter = new ArrayAdapter<String>(IdentityVerificationActivity.this, android.R.layout.simple_spinner_dropdown_item, gDocuments_Arraylist);

                        dropdown.setAdapter(adapter);
                        progress.dismiss();

                    }

                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<GetDocumentslist_Response> call, Throwable t) {
                    System.out.println(" GetIdentity Enters into  onFailure");

                    progress.dismiss();
                }
            });
        }catch (Exception e) {
            System.out.println(" GetIdentity Enters into  catch");
            progress.dismiss();
            e.printStackTrace();


        }

    }



    private void onApprovedStatus(){

        submit.setVisibility(View.VISIBLE);
        gWaitingforAproval_Button.setVisibility(View.GONE);

        front.setClickable(true);
        back.setClickable(true);
        dropdown.setClickable(true);
        dropdown.setEnabled(true);
    }

    private  void  onWaitingStatus(){
        gWaitingforAproval_Button.setVisibility(View.VISIBLE);
        submit.setVisibility(View.GONE);

        front.setClickable(false);
        back.setClickable(false);
        dropdown.setClickable(false);

        dropdown.setEnabled(false);
    }

}
