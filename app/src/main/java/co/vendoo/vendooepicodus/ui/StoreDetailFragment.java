package co.vendoo.vendooepicodus.ui;

import org.parceler.Parcels;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import co.vendoo.vendooepicodus.R;
import co.vendoo.vendooepicodus.models.Store;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreDetailFragment extends Fragment {
    @Bind(R.id.storeImageView) ImageView mImageLabel;
    @Bind(R.id.storeNameTextView) TextView mNameLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveStoreButton) TextView mSaveStoreButton;

    private Store mStore;

    public StoreDetailFragment newInstance(Store store) {
        StoreDetailFragment storeDetailFragment = new StoreDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("store", Parcels.wrap(store));
        storeDetailFragment.setArguments(args);
        return storeDetailFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store_detail, container, false);
    }

}
