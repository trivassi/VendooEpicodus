package co.vendoo.vendooepicodus;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.buyButton) Button mBuyButton;
    @Bind(R.id.sellButton) Button mSellButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mBuyButton.setOnClickListener(this);
        mSellButton.setOnClickListener(this);
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
