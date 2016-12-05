package co.vendoo.vendooepicodus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.vendoo.vendooepicodus.ui.HomeActivity;


public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.createButton) Button mCreateButton;
    @Bind(R.id.firstNameEditText) EditText mFirstNameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);


        mCreateButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == mCreateButton) {
            String firstName = mFirstNameEditText.getText().toString();

            Intent intent = new Intent(CreateAccountActivity.this, HomeActivity.class);
            //            intent.putExtra("location", location);
            intent.putExtra("firstName", firstName);
            startActivity(intent);
        }
    }
}
