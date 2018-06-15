package sg.com.kaplan.pdma_assignment2_bagit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NewRequestActivity extends AppCompatActivity {


    EditText req_productname, req_fromcountry, req_price, req_date;
    private Swipe swipe;
    private FirebaseAuth mAuth;

    @Override public boolean dispatchTouchEvent(MotionEvent event) {
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_request);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String userEmail = user.getEmail().toString();
        String welcomeText = "HELLO " + userEmail;

        TextView hello = findViewById(R.id.hello);
        hello.setText(welcomeText);


        swipe = new Swipe();
        swipe.setListener(new SimpleSwipeListener() {
            @Override
            public boolean onSwipedRight(MotionEvent event) {
                Intent i = new Intent(NewRequestActivity.this, addtrip.class);
                startActivity(i);

                Toast.makeText(NewRequestActivity.this, "add new trip", Toast.LENGTH_SHORT).show();
                return super.onSwipedRight(event);
            }

        });


        req_productname = (EditText) findViewById(R.id.req_productname);
        req_fromcountry = (EditText) findViewById(R.id.req_fromcountry);
        req_price = (EditText) findViewById(R.id.req_price);
        req_date = (EditText) findViewById(R.id.req_date);
        Button button = (Button) findViewById(R.id.req_button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String reqproductname = req_productname.getText().toString();
                String reqfromcountry = req_fromcountry.getText().toString();
                String reqprice = req_price.getText().toString();
                String reqdate = req_date.getText().toString();

                if (!TextUtils.isEmpty(reqproductname) && !TextUtils.isEmpty(reqfromcountry) && !TextUtils.isEmpty(reqprice) && !TextUtils.isEmpty(reqdate)) {

                setDefaults("reqproductname", reqproductname,NewRequestActivity.this);
                setDefaults("reqfromcountry", reqfromcountry,NewRequestActivity.this);
                setDefaults("reqprice", reqprice,NewRequestActivity.this);
                setDefaults("reqdate", reqdate,NewRequestActivity.this);

                Toast.makeText(NewRequestActivity.this, "Request Made!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(NewRequestActivity.this,home_main_activity_2.class);
                startActivity(i);
                finish();

                }

                else{
                    Toast.makeText(NewRequestActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();

                }


            }
        });

        ImageButton btn = (ImageButton) findViewById(R.id.hamburger);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewRequestActivity.this, HomeMainActivity.class);
                startActivity(i);

            }
        });



        }

    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences preferences_req = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences_req.edit();
        editor.putString(key, value);
        editor.commit();

        }
    }


