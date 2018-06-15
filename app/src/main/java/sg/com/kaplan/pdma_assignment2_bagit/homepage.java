package sg.com.kaplan.pdma_assignment2_bagit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class homepage extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        String userEmail = user.getEmail().toString();
        String welcomeText = "HELLO " + userEmail;

        TextView hello = findViewById(R.id.hello);
        hello.setText(welcomeText);

        Button btnviewrequest = (Button) findViewById(R.id.viewrequest);
        Button btnviewtrip = (Button) findViewById(R.id.viewtrip);
        Button btnaddrequest = (Button) findViewById(R.id.addrequest);
        Button btnaddtrip = (Button) findViewById(R.id.addtrip);

        btnviewrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homepage.this, home_main_activity_2.class);
                startActivity(i);
            }
        });


        btnviewtrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homepage.this, HomeMainActivity.class);
                startActivity(i);
            }


        });

        btnaddtrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homepage.this, addtrip.class);
                startActivity(i);
            }


        });

        btnaddrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homepage.this, NewRequestActivity.class);
                startActivity(i);
            }


        });


    }
}