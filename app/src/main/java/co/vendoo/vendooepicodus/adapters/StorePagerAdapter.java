package co.vendoo.vendooepicodus.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import co.vendoo.vendooepicodus.models.Store;
import co.vendoo.vendooepicodus.ui.StoreDetailFragment;

/**
 * Created by T on 12/5/16.
 */

public class StorePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Store> mStores;
    private String mSource;

    public StorePagerAdapter(FragmentManager fm, ArrayList<Store> stores, String source) {
        super(fm);
        mStores = stores;
        mSource = source;
    }

    @Override
    public Fragment getItem(int position) {
        return StoreDetailFragment.newInstance(mStores, position, mSource);
    }

    @Override
    public int getCount() {
        return mStores.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStores.get(position).getName();
    }

}
