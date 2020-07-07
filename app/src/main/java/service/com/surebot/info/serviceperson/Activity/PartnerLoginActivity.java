package service.com.surebot.info.serviceperson.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.R;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PartnerLoginActivity extends AppCompatActivity {


    @BindView(R.id.loginEmailET)
    EditText gloginEmailET;

    @BindView(R.id.loginPasswordET)
    EditText gloginPasswordET;

    @BindView(R.id.newUserTV)
    TextView gnewUserTV;

    @BindView(R.id.loginButton)
    Button gloginButton;
    private FirebaseAuth mAuth;

    private Dialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_login);
        ButterKnife.bind(this);
        progress = new Dialog(this, android.R.style.Theme_Translucent);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        progress.setContentView(R.layout.progressbar_background);
        progress.setCancelable(true);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        gnewUserTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PartnerLoginActivity.this,PartnerSignupActivity.class));
            }
        });

        gloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!gloginEmailET.getText().toString().trim().equals("") && !gloginPasswordET.getText().toString().trim().equals("")){
                progress.show();
                mAuth.signInWithEmailAndPassword(gloginEmailET.getText().toString().trim(), gloginPasswordET.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Log.d("Testing HS", String.valueOf(user));
                                    progress.dismiss();
                                   startActivity(new Intent(PartnerLoginActivity.this,ServicePersonHome_Activity.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("Testing HS", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(PartnerLoginActivity.this, task.getException().toString(),
                                            Toast.LENGTH_SHORT).show();
                                    progress.dismiss();
                                }
                            }
                        });
            }else{
                    Toast.makeText(PartnerLoginActivity.this, "Please Fill the required field", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
