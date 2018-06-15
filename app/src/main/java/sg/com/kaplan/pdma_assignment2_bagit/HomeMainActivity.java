package sg.com.kaplan.pdma_assignment2_bagit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
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

public class HomeMainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Swipe swipe;


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main_activity);

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
                Intent i = new Intent(HomeMainActivity.this, home_main_activity_2.class);
                startActivity(i);

                Toast.makeText(HomeMainActivity.this, "pending request", Toast.LENGTH_SHORT).show();
                return super.onSwipedLeft(event);
            }

        });

        mRecyclerView = findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // get a list of objects
        ArrayList<MyData> mDataset = new ArrayList<>();

        String fromcountry = getDefaults("fromcountry", HomeMainActivity.this);
        String fromdate = getDefaults("fromdate", HomeMainActivity.this);
        String tocountry = getDefaults("tocountry", HomeMainActivity.this);
        String todate = getDefaults("todate", HomeMainActivity.this);

        mDataset.add(new MyData(fromcountry,fromdate,tocountry,todate));
        mDataset.add(new MyData("SINGAPORE", "KOREA", "28 JUNE 2018", "1 JULY 2018"));
        mDataset.add(new MyData("SINGAPORE", "KOREA", "28 JUNE 2018", "1 JULY 2018"));
        mDataset.add(new MyData("SINGAPORE", "KOREA", "28 JUNE 2018", "1 JULY 2018"));
        mDataset.add(new MyData("SINGAPORE", "KOREA", "28 JUNE 2018", "1 JULY 2018"));
        mDataset.add(new MyData("SINGAPORE", "KOREA", "28 JUNE 2018", "1 JULY 2018"));
        mDataset.add(new MyData("SINGAPORE", "KOREA", "28 JUNE 2018", "1 JULY 2018"));




        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);


        ImageButton btn = (ImageButton) findViewById(R.id.addbutton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeMainActivity.this,addtrip.class);
                startActivity(i);

            }
        });

        ImageButton btn2 = (ImageButton) findViewById(R.id.hamburger);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeMainActivity.this, homepage.class);
                startActivity(i);

            }
        });

    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences_add = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences_add.getString(key, null);
    }
}