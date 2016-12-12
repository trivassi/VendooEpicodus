package co.vendoo.vendooepicodus.ui;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.vendoo.vendooepicodus.Constants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import co.vendoo.vendooepicodus.R;
import co.vendoo.vendooepicodus.adapters.StoreListAdapter;
import co.vendoo.vendooepicodus.models.Store;
import co.vendoo.vendooepicodus.services.YelpService;

public class StoreListActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private String mRecentAddress;
//    public static final String TAG = StoreListActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private StoreListAdapter mAdapter;

    public ArrayList<Store> mStores = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        if (mRecentAddress != null) {
            getStores(mRecentAddress);
        }

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
            public void onResponse(Call call, Response response) {
                mStores = yelpService.processResults(response);

                StoreListActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new StoreListAdapter(getApplicationContext(), mStores);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(StoreListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                }); //runonuithread

            }//on response

        }); //callback
    }
}
