package co.vendoo.vendooepicodus.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.vendoo.vendooepicodus.R;

public class LocationActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.zipCodeButton) Button mZipCodeButton;
    @Bind(R.id.currentLocationButton) Button mCurrentLocationButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);

        mZipCodeButton.setOnClickListener(this);
        mCurrentLocationButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mZipCodeButton) {
            String location = mLocationEditText.getText().toString();

            Intent intent = new Intent(LocationActivity.this , StoresActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        } else if (v == mCurrentLocationButton) {
            Toast.makeText(LocationActivity.this , "Coming soon!", Toast.LENGTH_LONG).show();
        }
    }
}
