package co.vendoo.vendooepicodus.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.vendoo.vendooepicodus.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.logInButton) Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
