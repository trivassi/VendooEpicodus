package co.vendoo.vendooepicodus.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.vendoo.vendooepicodus.R;
import co.vendoo.vendooepicodus.Store;
import co.vendoo.vendooepicodus.YelpService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class StoresActivity extends AppCompatActivity {

    private String[] marketplaces = new String[] {"eBay", "Etsy", "Amazon", "Letgo", "Craigslist", "Mercari", "Offerup", "Grailed", "Poshmark"};
    public static final String TAG = StoresActivity.class.getSimpleName();

    @Bind(R.id.listView) ListView mListView;
    public ArrayList<Store> mStores = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplaces);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, marketplaces);
        mListView.setAdapter(adapter);

        getStores(location);
    }

    private void getStores(String location) {
        final YelpService yelpService = new YelpService();

        yelpService.findStores(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mStores = yelpService.processResults(response);

                StoresActivity.this.runOnUiThread(new Runnable() {


                    @Override
                    public void run() {
                        String[] storeNames = new String[mStores.size()];
                        for (int i = 0; i < storeNames.length; i++) {
                            storeNames[i] = mStores.get(i).getName();
                        }
                        ArrayAdapter adapter = new ArrayAdapter(StoresActivity.this, android.R.layout.simple_list_item_1, storeNames);
                        mListView.setAdapter(adapter);

                        for (Store store : mStores) {
                            Log.d(TAG, "Name: " + store.getName());
                            Log.d(TAG, "Phone: " + store.getPhone());
                            Log.d(TAG, "Website: " + store.getWebsite());
                            Log.d(TAG, "Image url: " + store.getImageUrl());
                            Log.d(TAG, "Rating: " + Double.toString(store.getRating()));
                            Log.d(TAG, "Address: " + android.text.TextUtils.join(", ", store.getAddress()));
                        }

                    }
                }); //runonuithreas

            }//on response

        }); //callback
    }
}
