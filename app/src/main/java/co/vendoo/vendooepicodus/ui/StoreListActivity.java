package co.vendoo.vendooepicodus.ui;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.vendoo.vendooepicodus.Constants;
import co.vendoo.vendooepicodus.util.OnStoreSelectedListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import org.parceler.Parcels;

import co.vendoo.vendooepicodus.R;
import co.vendoo.vendooepicodus.adapters.StoreListAdapter;
import co.vendoo.vendooepicodus.models.Store;
import co.vendoo.vendooepicodus.services.YelpService;

public class StoreListActivity extends AppCompatActivity implements OnStoreSelectedListener {


    private Integer mPosition;
    ArrayList<Store> mStores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);

        if (savedInstanceState != null) {

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mPosition = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION);
                mStores = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_STORES));

                if (mPosition != null && mStores != null) {
                    Intent intent = new Intent(this, StoreDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);
                    intent.putExtra(Constants.EXTRA_KEY_STORES, Parcels.wrap(mStores));
                    startActivity(intent);
                }

            }

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mPosition != null && mStores != null) {
            outState.putInt(Constants.EXTRA_KEY_POSITION, mPosition);
            outState.putParcelable(Constants.EXTRA_KEY_STORES, Parcels.wrap(mStores));
        }

    }

    @Override
    public void onStoreSelected(Integer position, ArrayList<Store> stores) {
        mPosition = position;
        mStores = stores;

    }


}