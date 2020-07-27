package service.com.surebot.info.serviceperson.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.ApplicationClass;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.Utils;

public class OnBoardProfileActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.fullNameET)
    EditText fullNameET;
    @BindView(R.id.mobileNumberET)
    EditText mobileNumberET;
    @BindView(R.id.emailET)
    EditText emailET;
    @BindView(R.id.dateOfBirthET)
    EditText dateOfBirthET;
    @BindView(R.id.nameET)
    EditText nameET;
    @BindView(R.id.houseFlatNoET)
    EditText houseFlatNoET;
    @BindView(R.id.streetET)
    EditText streetET;
    @BindView(R.id.areaET)
    EditText areaET;
    @BindView(R.id.cityET)
    EditText cityET;
    @BindView(R.id.stateET)
    EditText stateET;
    @BindView(R.id.pincodeET)
    EditText pincodeET;
    @BindView(R.id.uploadImageIV)
    ImageView uploadImageIV;
    @BindView(R.id.arrowBack)
    ImageView arrowBack;
    @BindView(R.id.updateBtn)
    Button updateBtn;
    @BindView(R.id.headerProfileCL)
    ConstraintLayout headerProfileCL;
    @BindView(R.id.headerProfileFadeCL)
    ConstraintLayout headerProfileFadeCL;
    @BindView(R.id.nameTL)
    TextInputLayout nameTL;
    @BindView(R.id.mobileNumberTL)
    TextInputLayout mobileNumberTL;
    @BindView(R.id.emailTL)
    TextInputLayout emailTL;
    @BindView(R.id.dateOfBirthTL)
    TextInputLayout dateOfBirthTL;
    @BindView(R.id.currentAddressTV)
    TextView currentAddressTV;
    @BindView(R.id.femaleGenderIV)
    ImageView femaleGenderIV;
    @BindView(R.id.uploadCurrentAddressTV)
    TextView uploadCurrentAddressTV;

    FirebaseFirestore db;
    String TAG = "Testing HS";
    static String addid = "";
    private Uri filePath;
    FirebaseStorage storage;
    StorageReference storageReference;
    // request code
    private final int PICK_IMAGE_REQUEST = 22;

    private Dialog progress;
    private OnBoardProfileActivity context;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board_profile);
        ButterKnife.bind(this);
        context = OnBoardProfileActivity.this;

        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        progress = new Dialog(this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);

        //getData();

        arrowBack.setOnClickListener(this);
        updateBtn.setOnClickListener(this);

        int categorySelection = ApplicationClass.getCategorySelection();
        int headerProfile = R.drawable.salon_onboard_women;
        int headerProfileFade = R.mipmap.saloonfade;

        switch (categorySelection) {
            case 1: {
                headerProfile = R.drawable.salon_onboard_women;
                headerProfileFade = R.mipmap.saloonfade;
            }
            break;
            case 2: {
                headerProfile = R.drawable.salon_onboard_men;
                headerProfileFade = R.mipmap.saloonfade;
            }
            break;
            case 3: {
                headerProfile = R.drawable.onboard_electrician;
                headerProfileFade = R.mipmap.electrician_fade;
                femaleGenderIV.setColorFilter(context.getResources().getColor(R.color.colorElectricianText));
            }
            break;
        }

        headerProfileCL.setBackground(ContextCompat.getDrawable(context, headerProfile));
        headerProfileFadeCL.setBackground(ContextCompat.getDrawable(context, headerProfileFade));

     /*   uploadImageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent chooseImageIntent = ImagePicker.getPickImageIntent(OnBoardSalonWomenProfile.this);
//                startActivityForResult(chooseImageIntent, PICK_IMAGE_REQUEST);

                SelectImage();
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!areaET.getText().toString().trim().equals("") && !cityET.getText().toString().trim().equals("") && !dateOfBirthET.getText().toString().trim().equals("")
                        && !fullNameET.getText().toString().trim().equals("") && !houseFlatNoET.getText().toString().trim().equals("") && !nameET.getText().toString().trim().equals("")
                        && !mobileNumberET.getText().toString().trim().equals("") && !stateET.getText().toString().trim().equals("")) {
// Create a new user with a first, middle, and last name
                    progress.show();
                    uploadImage();

                } else {
                    Toast.makeText(context, "Please fill all details before Continuing", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void uploadImage() {
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
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            Log.e(TAG, "onSuccess: " + uri);
                                            Map<String, Object> addressID = new HashMap<>();

                                            Map<String, Object> address = new HashMap<>();
                                            address.put("Address_Name", nameET.getText().toString().trim());
                                            address.put("Area", areaET.getText().toString().trim());
                                            address.put("City", cityET.getText().toString().trim());
                                            address.put("House_No", houseFlatNoET.getText().toString().trim());
                                            address.put("Pincode", pincodeET.getText().toString().trim());
                                            address.put("State", stateET.getText().toString().trim());
                                            address.put("Street", streetET.getText().toString().trim());
                                            address.put("PartnerDocID", ApplicationClass.UserTableID);
                                            address.put("AddressProof", uri.toString());

// Add a new document with a generated ID
                                            db.collection("Address")
                                                    .add(address)
                                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                        @Override
                                                        public void onSuccess(DocumentReference documentReference) {
                                                            Log.d("Testing HS", "DocumentSnapshot added with ID::: " + ApplicationClass.UserTableID);
                                                            Toast.makeText(context, "Address Sent For Approval", Toast.LENGTH_SHORT).show();
                                                            addid = documentReference.getId();

                                                            addressID.put("AddressID", documentReference.getId());
                                                            db.collection("partner").document(ApplicationClass.UserTableID)
                                                                    .set(addressID, SetOptions.merge())
                                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void aVoid) {
                                                                            // Log.d("Testing HS", "DocumentSnapshot added with ID: " + documentReference.getId());
                                                                            Toast.makeText(context, "Address Sent For Approval", Toast.LENGTH_SHORT).show();
                                                                            progress.dismiss();
                                                                            startActivity(new Intent(context, OnBoardIdentityVerificationActivity.class));

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
                                    });
                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progress.dismiss();
                                    Toast
                                            .makeText(context,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();


                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded
                            progress.dismiss();
                            Toast
                                    .makeText(context,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });

        }
    }


    private void SelectImage() {

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
                uploadImageIV.setImageBitmap(bitmap);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }


    private void getData() {
        db.collection("partner").whereEqualTo("UserID", ApplicationClass.UserId_FromLogin)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                fullNameET.setText(document.getData().get("Partner_Name").toString());
                                mobileNumberET.setText(document.getData().get("Partner_Number").toString());
                                emailET.setText(document.getData().get("Partner_Email").toString());
                                dateOfBirthET.setText(document.getData().get("DOB").toString());
                            }
                        } else {
                            Log.w("Testing HS", "Error getting documents.", task.getException());
                        }
                    }
                });*/

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.arrowBack: {
                finish();
            }
            break;
            case R.id.updateBtn: {
                Utils.startIntent(context, OnBoardIdentityVerificationActivity.class, false);
            }
            break;
        }
    }
}
