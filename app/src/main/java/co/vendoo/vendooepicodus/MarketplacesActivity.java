package co.vendoo.vendooepicodus;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MarketplacesActivity extends AppCompatActivity {

    private String[] marketplaces = new String[] {"eBay", "Etsy", "Amazon", "Letgo", "Craigslist", "Mercari", "Offerup", "Grailed", "Poshmark"};
    @Bind(R.id.listView) ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplaces);

        ButterKnife.bind(this);


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, marketplaces);
        mListView.setAdapter(adapter);

    }
}
