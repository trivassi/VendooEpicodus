package co.vendoo.vendooepicodus.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.vendoo.vendooepicodus.R;

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
