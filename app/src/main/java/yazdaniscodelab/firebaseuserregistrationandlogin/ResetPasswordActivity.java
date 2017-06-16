package yazdaniscodelab.firebaseuserregistrationandlogin;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText editTextemail;
    private Button restpassword;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        editTextemail=(EditText)findViewById(R.id.emailReset_xml);
        restpassword=(Button)findViewById(R.id.forget_password_btn);


        restpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=editTextemail.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Input Email Address..",Toast.LENGTH_LONG).show();
                    return;
                }

                progressDialog.setMessage("Password sending...");
                progressDialog.show();

                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"Password Successfully Send to your email address",Toast.LENGTH_LONG).show();
                                    progressDialog.dismiss();
                                }

                                else {
                                    Toast.makeText(getApplicationContext(),"Failed to reset Password",Toast.LENGTH_LONG).show();
                                    progressDialog.dismiss();
                                }

                            }
                        });

            }
        });

    }

}
