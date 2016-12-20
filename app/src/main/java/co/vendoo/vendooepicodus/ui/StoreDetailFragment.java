package co.vendoo.vendooepicodus.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import co.vendoo.vendooepicodus.Constants;
import co.vendoo.vendooepicodus.R;
import co.vendoo.vendooepicodus.models.Store;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoreDetailFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;
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

        Picasso.with(view.getContext())
                .load(mStore.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);

        mNameLabel.setText(mStore.getName());
        mRatingLabel.setText(Double.toString(mStore.getRating()) + "/5");
        mPhoneLabel.setText(mStore.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(", ", mStore.getAddress()));

        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);

        mSaveStoreButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mStore.getWebsite()));
            startActivity(webIntent);
        }

        if (view == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mStore.getPhone()));
            startActivity(phoneIntent);
        }

        if (view == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mStore.getLatitude()
                            + "," + mStore.getLongitude()
                            + "?q=(" + mStore.getName() + ")"));
            startActivity(mapIntent);
        }

        if (view == mSaveStoreButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference storeRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_STORES)
                    .child(uid);

            DatabaseReference pushRef = storeRef.push();
            String pushId = pushRef.getKey();
            mStore.setPushId(pushId);
            pushRef.setValue(mStore);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
