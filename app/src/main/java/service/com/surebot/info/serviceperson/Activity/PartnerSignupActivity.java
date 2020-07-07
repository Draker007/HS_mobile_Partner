package service.com.surebot.info.serviceperson.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;

public class PartnerSignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    String email , password;

    private Dialog progress;

    @BindView(R.id.fullNameET)
    EditText gfullNameET;

    @BindView(R.id.mobileNumberET)
    EditText gmobileNumberET;

    @BindView(R.id.emailET)
    EditText gemailET;

    @BindView(R.id.maleGenderIV)
    ImageView gmaleGenderIV;

    @BindView(R.id.femaleGenderIV)
    ImageView gfemaleGenderIV;

    @BindView(R.id.dateOfBirthET)
    EditText gdateOfBirthET;

    @BindView(R.id.passwordET)
    EditText gpasswordET;

    @BindView(R.id.confirmPasswordET)
    EditText gconfirmPasswordET;

    @BindView(R.id.signupButton)
    Button gsignupButton;
    private FirebaseFunctions mFunctions;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_signup);
        ButterKnife.bind(this);
        progress = new Dialog(this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
       db = FirebaseFirestore.getInstance();
        mFunctions = FirebaseFunctions.getInstance();

        gsignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSignUp();
            }
        });

    }

    private void callSignUp() {
        progress.show();
        email = gemailET.getText().toString().trim();
        password = gpasswordET.getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(gemailET.getText().toString(), gpasswordET.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Testing","createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            AddDetails();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Testing", "createUserWithEmail:failure"+ email+password);
                            Log.w("Testing", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(PartnerSignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            progress.dismiss();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void AddDetails() {
        // Create a new user with a first, middle, and last name
        Map<String, Object> user = new HashMap<>();
        user.put("DOB",gdateOfBirthET.getText().toString().trim());
        user.put("Partner_Email", gemailET.getText().toString().trim());
        user.put("Partner_Gender", "male");
        user.put("Partner_Name", gfullNameET.getText().toString().trim());
        user.put("Partner_Number", gmobileNumberET.getText().toString().trim());
        user.put("Partner_Parent_Category","Salon at home for women");
        user.put("Partner_Type", "Free");

// Add a new document with a generated ID
        db.collection("partner")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Testing", "DocumentSnapshot added with ID: " + documentReference.getId());
                        startActivity(new Intent(PartnerSignupActivity.this,OnBoardSalonWomenProfile.class));
progress.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progress.dismiss();
                        Log.w("Testing", "Error adding document", e);
                    }
                });


    }
}
