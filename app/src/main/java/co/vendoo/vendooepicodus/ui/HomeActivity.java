package co.vendoo.vendooepicodus.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.vendoo.vendooepicodus.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.findButton) Button mFindButton;
    @Bind(R.id.tripsButton) Button mTripsButton;
    @Bind(R.id.greetingTextView) TextView mGreetingTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mFindButton.setOnClickListener(this);
        mTripsButton.setOnClickListener(this);

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");    //Retrieve extended data from the intent.

        mGreetingTextView.setText("Welcome " + firstName + "!");
    }


    @Override
    public void onClick(View v) {

        if (v == mFindButton) {
            Intent intent = new Intent(HomeActivity.this, LocationActivity.class);
            //            intent.putExtra("location", location);
            startActivity(intent);

        } else if (v == mTripsButton) {
            Toast.makeText(HomeActivity.this, "Trip info coming soon!", Toast.LENGTH_LONG).show();
        }

    }




}
