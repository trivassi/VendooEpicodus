package co.vendoo.vendooepicodus.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import co.vendoo.vendooepicodus.Constants;
import co.vendoo.vendooepicodus.R;
import co.vendoo.vendooepicodus.models.Store;
import co.vendoo.vendooepicodus.ui.StoreDetailActivity;
import co.vendoo.vendooepicodus.ui.StoreDetailFragment;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.vendoo.vendooepicodus.util.OnStoreSelectedListener;


/**
 * Created by T on 12/5/16.
 */

public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.StoreViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<Store> mStores = new ArrayList<>();
    private Context mContext;
    private OnStoreSelectedListener mOnStoreSelectedListener;


    public StoreListAdapter(Context context, ArrayList<Store> stores, OnStoreSelectedListener storeSelectedListener) {
        mContext = context;
        mStores = stores;
        mOnStoreSelectedListener = storeSelectedListener;
    }

    @Override
    public StoreListAdapter.StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list_item, parent, false);

        StoreViewHolder viewHolder = new StoreViewHolder(view, mStores, mOnStoreSelectedListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StoreListAdapter.StoreViewHolder holder, int position) {
        holder.bindStore(mStores.get(position));
    }

    @Override
    public int getItemCount() {
        return mStores.size();
    }

    public class StoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.storeImageView) ImageView mStoreImageView;
        @Bind(R.id.storeNameTextView) TextView mNameTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;
        private int mOrientation;
        private ArrayList<Store> mStores = new ArrayList<>();
        private OnStoreSelectedListener mOnStoreSelectedListener;


        public StoreViewHolder(View itemView, ArrayList<Store> stores, OnStoreSelectedListener storeSelectedListener)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            mOrientation = itemView.getResources().getConfiguration().orientation;
            mStores = stores;
            mOnStoreSelectedListener = storeSelectedListener;

            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }

            itemView.setOnClickListener(this);
        }


        public void bindStore(Store store) {
            Picasso.with(mContext)
                    .load(store.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mStoreImageView);

            mNameTextView.setText(store.getName());
            mRatingTextView.setText("Rating: " + store.getRating() + "/5 ");
        }

        private void createDetailFragment(int position) {
            StoreDetailFragment detailFragment = StoreDetailFragment.newInstance(mStores, position);
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.storeDetailContainer, detailFragment);
            ft.commit();
        }
        @Override
        public void onClick(View view) {
            int itemPosition = getLayoutPosition();
            //?????????????????????
            mOnStoreSelectedListener.onStoreSelected(itemPosition, mStores);
            //?????????????????????

            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, StoreDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_STORES, Parcels.wrap(mStores));
                mContext.startActivity(intent);
            }
        }
    }
}