package co.vendoo.vendooepicodus.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.vendoo.vendooepicodus.R;
import co.vendoo.vendooepicodus.adapters.StorePagerAdapter;
import co.vendoo.vendooepicodus.models.Store;

public class StoreDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private StorePagerAdapter adapterViewPager;
    ArrayList<Store> mStores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        ButterKnife.bind(this);

        mStores = Parcels.unwrap(getIntent().getParcelableExtra("stores"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new StorePagerAdapter(getSupportFragmentManager(), mStores);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
