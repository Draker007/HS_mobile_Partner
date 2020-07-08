package service.com.surebot.info.serviceperson.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.AppicationClass;
import service.com.surebot.info.serviceperson.utils.ImagePicker;

public class OnBoardSalonWomenProfile extends AppCompatActivity {
    @BindView(R.id.fullNameET)
    EditText gfullNameET;

    @BindView(R.id.mobileNumberET)
    EditText gmobileNumberET;

    @BindView(R.id.emailET)
    EditText gemailET;

    @BindView(R.id.dateOfBirthET)
    EditText gdateOfBirthET;

    @BindView(R.id.nameET)
    EditText gnameET;

    @BindView(R.id.houseFlatNoET)
    EditText ghouseFlatNoET;

    @BindView(R.id.streetET)
    EditText gstreetET;

    @BindView(R.id.areaET)
    EditText gareaET;

    @BindView(R.id.cityET)
    EditText gcityET;

    @BindView(R.id.stateET)
    EditText gstateET;

    @BindView(R.id.PincodeET)
    EditText gPincodeET;

    @BindView(R.id.uploadImageIV)
    ImageView guploadImageIV;

    @BindView(R.id.profileGotbtn)
    Button gprofileGotbtn;
    FirebaseFirestore db;
String TAG = "Testing HS";
    static String addid="";
    private Uri filePath;
    FirebaseStorage storage;
    StorageReference storageReference;
    // request code
    private final int PICK_IMAGE_REQUEST = 22;

    private Dialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_salon_at_home_for_women);
        ButterKnife.bind(this);
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        progress = new Dialog(this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);
        getData();
        guploadImageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent chooseImageIntent = ImagePicker.getPickImageIntent(OnBoardSalonWomenProfile.this);
//                startActivityForResult(chooseImageIntent, PICK_IMAGE_REQUEST);

                SelectImage();
            }
        });
        gprofileGotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!gareaET.getText().toString().trim().equals("") && !gcityET.getText().toString().trim().equals("") && !gdateOfBirthET.getText().toString().trim().equals("")
                && !gfullNameET.getText().toString().trim().equals("") && !ghouseFlatNoET.getText().toString().trim().equals("") && !gnameET.getText().toString().trim().equals("")
                && !gmobileNumberET.getText().toString().trim().equals("") && !gstateET.getText().toString().trim().equals("") ){
// Create a new user with a first, middle, and last name
                    progress.show();
                    uploadImage();

                }else {
                    Toast.makeText(OnBoardSalonWomenProfile.this, "Please fill all details before Continuing", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void uploadImage()
    {
        if (filePath != null) {

            // Code for showing progressDialog while uploading


            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "/AddressProof/"
                                    + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                   ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            Log.e(TAG, "onSuccess: "+uri);
                                            Map<String, Object> addressID = new HashMap<>();

                                            Map<String, Object> address = new HashMap<>();
                                            address.put("Address_Name", gnameET.getText().toString().trim());
                                            address.put("Area", gareaET.getText().toString().trim());
                                            address.put("City", gcityET.getText().toString().trim());
                                            address.put("House_No", ghouseFlatNoET.getText().toString().trim());
                                            address.put("Pincode",gPincodeET.getText().toString().trim());
                                            address.put("State", gstateET.getText().toString().trim());
                                            address.put("Street", gstreetET.getText().toString().trim());
                                            address.put("PartnerDocID", AppicationClass.UserTableID);
                                            address.put("AddressProof", uri.toString());

// Add a new document with a generated ID
                                            db.collection("Address")
                                                    .add(address)
                                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                        @Override
                                                        public void onSuccess(DocumentReference documentReference) {
                                                            Log.d("Testing HS", "DocumentSnapshot added with ID::: " + AppicationClass.UserTableID);
                                                            Toast.makeText(OnBoardSalonWomenProfile.this, "Address Sent For Approval", Toast.LENGTH_SHORT).show();
                                                            addid=documentReference.getId();

                                                            addressID.put("AddressID",documentReference.getId() );
                                                            db.collection("partner").document(AppicationClass.UserTableID)
                                                                    .set(addressID , SetOptions.merge())
                                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void aVoid) {
                                                                            // Log.d("Testing HS", "DocumentSnapshot added with ID: " + documentReference.getId());
                                                                            Toast.makeText(OnBoardSalonWomenProfile.this, "Address Sent For Approval", Toast.LENGTH_SHORT).show();
                                                                            progress.dismiss();
                                                                            startActivity(new Intent(OnBoardSalonWomenProfile.this,OnBoardIdentityVerificationActivity.class));

                                                                        }
                                                                    })
                                                                    .addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {
                                                                            progress.dismiss();
                                                                            Log.w("Testing HS", "Error adding document", e);
                                                                        }
                                                                    });
                                                         //   startActivity(new Intent(OnBoardSalonWomenProfile.this,OnBoardIdentityVerificationActivity.class));
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            progress.dismiss();
                                                            Log.w("Testing HS", "Error adding document", e);
                                                        }
                                                    });





                                        }
                                    }) ;
                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progress.dismiss();
                                    Toast
                                            .makeText(OnBoardSalonWomenProfile.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();


                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progress.dismiss();
                            Toast
                                    .makeText(OnBoardSalonWomenProfile.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });

        }
    }




    private void SelectImage()
    {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                guploadImageIV.setImageBitmap(bitmap);
            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }






    private void getData() {
        db.collection("partner").whereEqualTo("UserID",AppicationClass.UserId_FromLogin)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                gfullNameET.setText( document.getData().get("Partner_Name").toString());
                                gmobileNumberET.setText(document.getData().get("Partner_Number").toString());
                                gemailET.setText(document.getData().get("Partner_Email").toString());
                                gdateOfBirthET.setText(document.getData().get("DOB").toString());
                            }
                        } else {
                            Log.w("Testing HS", "Error getting documents.", task.getException());
                        }
                    }
                });

    }
}
