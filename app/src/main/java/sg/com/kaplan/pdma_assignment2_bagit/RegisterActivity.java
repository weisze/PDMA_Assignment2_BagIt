package sg.com.kaplan.pdma_assignment2_bagit;

import android.content.Intent;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText registerEmail, registerPass, registerConfirmPass;
    private Button registerBtn, toLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        registerEmail = findViewById(R.id.reg_email);
        registerPass = findViewById(R.id.reg_password);
        registerConfirmPass = findViewById(R.id.reg_cfm_password);

        registerBtn = findViewById(R.id.reg_btn);
        toLoginBtn = findViewById(R.id.reg_login_btn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRegister();
            }
        });


        toLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void submitRegister() {
        String email = registerEmail.getText().toString();
        String pass = registerPass.getText().toString();
        String cfmPass = registerConfirmPass.getText().toString();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(cfmPass)) {
            if (!pass.equals(cfmPass)) {
                Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_LONG).show();
            } else {
                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent homeIntent = new Intent(RegisterActivity.this, homepage.class);
                            startActivity(homeIntent);
                            finish();
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        } else {
            Toast.makeText(RegisterActivity.this, "Fields must not be empty", Toast.LENGTH_LONG).show();
        }
    }
}
