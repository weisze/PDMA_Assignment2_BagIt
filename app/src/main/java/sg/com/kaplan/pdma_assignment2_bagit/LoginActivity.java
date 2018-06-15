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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText loginEmail, loginPass;
    private Button loginBtn, toRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        loginEmail =findViewById(R.id.login_email);
        loginPass=findViewById(R.id.login_password);

        loginBtn = findViewById(R.id.reg_login_btn);
        toRegisterBtn = findViewById(R.id.reg_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLogin();
            }
        });

        toRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            navigateToHome();
        }
    }

    private void startLogin() {
        String email = loginEmail.getText().toString();
        String pass = loginPass.getText().toString();

        if (!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(pass)){
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        navigateToHome();
                    }
                    else {
                        String errorMessage=task.getException().getMessage();
                        Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                    }

                }
            });


        }

        else{
            Toast.makeText(LoginActivity.this,"All data has to be filled",Toast.LENGTH_SHORT).show();
        }

    }

    private void navigateToHome() {
        Intent homeIntent = new Intent(LoginActivity.this, homepage.class);
        startActivity(homeIntent);
        finish();
    }
}
