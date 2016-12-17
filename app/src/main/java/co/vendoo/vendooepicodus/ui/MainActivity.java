package co.vendoo.vendooepicodus.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import android.widget.Toast;

import co.vendoo.vendooepicodus.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.logInButton) Button mLogInButton;
    @Bind(R.id.createAccountButton) Button mCreateAccountButton;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface josefinSans = Typeface.createFromAsset(getAssets(), "fonts/JosefinSans-Regular.ttf");
        mAppNameTextView.setTypeface(josefinSans);

        mLogInButton.setOnClickListener(this);
        mCreateAccountButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == mLogInButton) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
//            Toast.makeText(MainActivity.this, "Log in with Facebook coming soon!", Toast.LENGTH_LONG).show();
        }

        if (v == mCreateAccountButton) {
            Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
//            intent.putExtra("location", location);
            startActivity(intent);
        }


    }


}
