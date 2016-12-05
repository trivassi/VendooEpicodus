package co.vendoo.vendooepicodus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.vendoo.vendooepicodus.ui.MarketplacesActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.buyButton) Button mBuyButton;
    @Bind(R.id.sellButton) Button mSellButton;
    @Bind(R.id.greetingTextView) TextView mGreetingTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mBuyButton.setOnClickListener(this);
        mSellButton.setOnClickListener(this);

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");    //Retrieve extended data from the intent.

        mGreetingTextView.setText("Welcome " + firstName + "!");
    }


    @Override
    public void onClick(View v) {

        if (v == mBuyButton || v == mSellButton) {
            Intent intent = new Intent(HomeActivity.this, MarketplacesActivity.class);
            //            intent.putExtra("location", location);
            startActivity(intent);
        }

    }




}
