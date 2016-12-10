package co.vendoo.vendooepicodus.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import co.vendoo.vendooepicodus.R;
import co.vendoo.vendooepicodus.models.Store;
import co.vendoo.vendooepicodus.ui.StoreDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;



/**
 * Created by T on 12/5/16.
 */

public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.StoreViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<Store> mStores = new ArrayList<>();
    private Context mContext;

    public StoreListAdapter(Context context, ArrayList<Store> stores) {
        mContext = context;
        mStores = stores;
    }

    @Override
    public StoreListAdapter.StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list_item, parent, false);
        StoreViewHolder viewHolder = new StoreViewHolder(view);
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
//        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        private Context mContext;

        public StoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindStore(Store store) {
            Picasso.with(mContext).load(store.getImageUrl()).into(mStoreImageView);
            mNameTextView.setText(store.getName());
//            mCategoryTextView.setText(store.getCategories().get(0));
            mRatingTextView.setText("Rating: " + store.getRating() + "/5");
        }

        @Override
        public void onClick(View view) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, StoreDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("stores", Parcels.wrap(mStores));
            mContext.startActivity(intent);
        }
    }
}
