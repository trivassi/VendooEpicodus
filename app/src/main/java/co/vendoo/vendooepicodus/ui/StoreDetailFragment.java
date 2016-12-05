package co.vendoo.vendooepicodus.ui;

import org.parceler.Parcels;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
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

    public static StoreDetailFragment newInstance(Store store) {
        StoreDetailFragment storeDetailFragment = new StoreDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("store", Parcels.wrap(store));
        storeDetailFragment.setArguments(args);
        return storeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStore = Parcels.unwrap(getArguments().getParcelable("store"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mStore.getImageUrl()).into(mImageLabel);

        mNameLabel.setText(mStore.getName());
        mRatingLabel.setText(Double.toString(mStore.getRating()) + "/5");
        mPhoneLabel.setText(mStore.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(", ", mStore.getAddress()));

        return view;
    }

}
