package sg.com.kaplan.pdma_assignment2_bagit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.pwittchen.swipe.library.rx2.SimpleSwipeListener;
import com.github.pwittchen.swipe.library.rx2.Swipe;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;


public class addtrip extends AppCompatActivity {


    EditText fromcountry1, fromdate1, tocountry1, todate1;
    SharedPreferences sharedPref_add;
    private Swipe swipe;
    private FirebaseAuth mAuth;
    private StorageReference storageReference;
    private FirebaseFirestore db;


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtrip);

        //firebase int
        storageReference = FirebaseStorage.getInstance().getReference();
        db = FirebaseFirestore.getInstance();


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String userEmail = user.getEmail().toString();
        String welcomeText = "HELLO " + userEmail;

        TextView hello = findViewById(R.id.hello);
        hello.setText(welcomeText);

        swipe = new Swipe();
        swipe.setListener(new SimpleSwipeListener() {
            @Override
            public boolean onSwipedLeft(MotionEvent event) {
                Intent i = new Intent(addtrip.this, NewRequestActivity.class);
                startActivity(i);

                Toast.makeText(addtrip.this, "add new request", Toast.LENGTH_SHORT).show();
                return super.onSwipedLeft(event);
            }

        });


        sharedPref_add = getPreferences(Context.MODE_PRIVATE);


        fromcountry1 = (EditText) findViewById(R.id.fromcountry1);
        fromdate1 = (EditText) findViewById(R.id.fromdate1);
        tocountry1 = (EditText) findViewById(R.id.tocountry1);
        todate1 = (EditText) findViewById(R.id.todate1);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromcountry = fromcountry1.getText().toString();
                String fromdate = fromdate1.getText().toString();
                String tocountry = tocountry1.getText().toString();
                String todate = todate1.getText().toString();

                if (!TextUtils.isEmpty(fromcountry) && !TextUtils.isEmpty(fromdate) && !TextUtils.isEmpty(tocountry) && !TextUtils.isEmpty(todate)) {

                    setDefaults("fromcountry", fromcountry, addtrip.this);
                    setDefaults("fromdate", fromdate, addtrip.this);
                    setDefaults("tocountry", tocountry, addtrip.this);
                    setDefaults("todate", todate, addtrip.this);
                    Toast.makeText(addtrip.this, "Trip added!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(addtrip.this, HomeMainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(addtrip.this, "Please fill in all fields", Toast.LENGTH_LONG).show();
                }


//                Map<String, Object> addtripmap=new HashMap<>();
//                addtripmap.put("travelling from",fromcountry);
//                addtripmap.put("on",fromdate);
//                addtripmap.put("to",tocountry);
//                addtripmap.put("on",todate);
//
//                db.collection("trips").add(addtripmap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentReference> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(addtrip.this, "New trip added!", Toast.LENGTH_LONG).show();
//                        }
//
//                        else {
//                            String errormessage = task.getException().getMessage();
//                            Toast.makeText(addtrip.this, errormessage, Toast.LENGTH_LONG).show();
//                        }

            }

        });

        ImageButton btn = (ImageButton) findViewById(R.id.hamburger);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(addtrip.this, homepage.class);
                startActivity(i);

            }
        });


    }

    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences preferences_add = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences_add.edit();
        editor.putString(key, value);
        editor.commit();
    }


}

