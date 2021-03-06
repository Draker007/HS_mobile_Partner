package service.com.surebot.info.serviceperson.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.Utils;

public class PartnerLoginActivity extends BaseActivity {

    @BindView(R.id.loginEmailET)
    EditText gloginEmailET;

    @BindView(R.id.loginPasswordET)
    EditText gloginPasswordET;

    @BindView(R.id.newUserTV)
    TextView gnewUserTV;
    @BindView(R.id.forgotPasswordTV)
    TextView forgotPasswordTV;

    @BindView(R.id.loginButton)
    Button gloginButton;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    private Dialog progress;
    private PartnerLoginActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_login);
        ButterKnife.bind(this);
        context = PartnerLoginActivity.this;
        progress = new Dialog(this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        gnewUserTV.setOnClickListener(v -> startActivity(new Intent(context, PartnerSignupWorkTypeActivity.class)));

        forgotPasswordTV.setOnClickListener(v -> {
            Utils.startIntent(context, ForgotPasswordActivity.class, true);
        });
/*
        gloginButton.setOnClickListener(v -> {

                }
            if (!gloginEmailET.getText().toString().trim().equals("") && !gloginPasswordET.getText().toString().trim().equals("")) {
                progress.show();
                mAuth.signInWithEmailAndPassword(gloginEmailET.getText().toString().trim(), gloginPasswordET.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

                                    user = mAuth.getCurrentUser();
                                    callforDetails();
                                    Log.d("Testing HS---", String.valueOf(user));

                                    // startActivity(new Intent(PartnerLoginActivity.this,ServicePersonHome_Activity.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("Testing HS", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(PartnerLoginActivity.this, task.getException().toString(),
                                            Toast.LENGTH_SHORT).show();
                                    progress.dismiss();
                                }
                            }
                        });
            } else {
                Toast.makeText(PartnerLoginActivity.this, "Please Fill the required field", Toast.LENGTH_SHORT).show();
            }
        });*/

    }

   /* private void callforDetails() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("partner").whereEqualTo("UserID", user.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Testing HS", document.getId() + " => " + document.getData().get(""));
                                AppicationClass.UserId_FromLogin = (String) document.getData().get("UserID");
                                AppicationClass.UserName_FromLogin = (String) document.getData().get("Partner_Name");
                                AppicationClass.UserTableID = document.getId();
                                AppicationClass.CategoryId_FromLogin = (String) document.getData().get("Partner_Parent_Category");
                                startActivity(new Intent(PartnerLoginActivity.this, OnBoardSalonWomenProfile.class));
                                progress.dismiss();
                            }
                        } else {
                            progress.dismiss();
                            Log.w("Testing HS", "Error getting documents.", task.getException());
                        }

                    }
                });
    }*/


}
