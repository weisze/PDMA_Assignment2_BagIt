package sg.com.kaplan.pdma_assignment2_bagit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.pwittchen.swipe.library.rx2.SimpleSwipeListener;
import com.github.pwittchen.swipe.library.rx2.Swipe;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class home_main_activity_2 extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Swipe swipe;
    private FirebaseAuth mAuth;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main_2);


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
                Intent i = new Intent(home_main_activity_2.this, HomeMainActivity.class);
                startActivity(i);

                Toast.makeText(home_main_activity_2.this, "upcoming trips", Toast.LENGTH_SHORT).show();
                return super.onSwipedRight(event);
            }

        });


        mRecyclerView = findViewById(R.id.req_my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // get a list of objects
        ArrayList<req_MyData> mDataset = new ArrayList<>();

        String reqproductname= getDefaults("reqproductname", home_main_activity_2.this);
        String reqfromcountry=getDefaults("reqfromcountry", home_main_activity_2.this);
        String reqprice=getDefaults("$"+ "reqprice", home_main_activity_2.this);
        String reqdate=getDefaults("reqdate",home_main_activity_2.this);

        mDataset.add(new req_MyData(reqproductname,reqfromcountry,reqprice,reqdate));
        mDataset.add(new req_MyData("INNISFREE EYE CREAM", "KOREA", "$23.00", "1 JULY 2018"));
        mDataset.add(new req_MyData("INNISFREE EYE CREAM", "KOREA", "$23.00", "1 JULY 2018"));
        mDataset.add(new req_MyData("INNISFREE EYE CREAM", "KOREA", "$23.00", "1 JULY 2018"));
        mDataset.add(new req_MyData("INNISFREE EYE CREAM", "KOREA", "$23.00", "1 JULY 2018"));
        mDataset.add(new req_MyData("INNISFREE EYE CREAM", "KOREA", "$23.00", "1 JULY 2018"));
        mDataset.add(new req_MyData("INNISFREE EYE CREAM", "KOREA", "$23.00", "1 JULY 2018"));

        // specify an adapter (see also next example)
        mAdapter = new req_MyAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

        ImageButton btn = (ImageButton) findViewById(R.id.addbutton);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home_main_activity_2.this,NewRequestActivity.class);
                startActivity(i);

            }
        });

        ImageButton btn2 = (ImageButton) findViewById(R.id.hamburger);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home_main_activity_2.this, homepage.class);
                startActivity(i);

            }
        });



    }
    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences_req = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences_req.getString(key, null);
    }
}