package co.vendoo.vendooepicodus.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.vendoo.vendooepicodus.R;

public class StoresActivity extends AppCompatActivity {

    private String[] marketplaces = new String[] {"eBay", "Etsy", "Amazon", "Letgo", "Craigslist", "Mercari", "Offerup", "Grailed", "Poshmark"};
    public static final String TAG = StoresActivity.class.getSimpleName();

    @Bind(R.id.listView) ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplaces);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, marketplaces);
        mListView.setAdapter(adapter);

    }
}
