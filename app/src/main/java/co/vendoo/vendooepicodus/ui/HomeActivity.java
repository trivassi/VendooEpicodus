package co.vendoo.vendooepicodus.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.vendoo.vendooepicodus.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.findButton) Button mFindButton;
    @Bind(R.id.tripsButton) Button mTripsButton;
    @Bind(R.id.storesButton) Button mStoresButton;
    @Bind(R.id.greetingTextView) TextView mGreetingTextView;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
//                } else {
//
//                }
            }
        };


        mFindButton.setOnClickListener(this);
        mTripsButton.setOnClickListener(this);
        mStoresButton.setOnClickListener(this);
//        Intent intent = getIntent();
//        String firstName = intent.getStringExtra("firstName");    //Retrieve extended data from the intent.
//        mGreetingTextView.setText("Welcome " + firstName + "!");
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    @Override
    public void onClick(View v) {

        if (v == mFindButton) {
            Intent intent = new Intent(HomeActivity.this, LocationActivity.class);
            //            intent.putExtra("location", location);
            startActivity(intent);

        }  if (v == mTripsButton) {
            Toast.makeText(HomeActivity.this, "Trips section coming soon!", Toast.LENGTH_SHORT).show();

        } if (v == mStoresButton) {
            Intent intent = new Intent(HomeActivity.this, SavedStoreListActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }



}
