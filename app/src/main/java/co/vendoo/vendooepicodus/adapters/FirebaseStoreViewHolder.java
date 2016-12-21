package co.vendoo.vendooepicodus.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import co.vendoo.vendooepicodus.Constants;
import co.vendoo.vendooepicodus.R;
import co.vendoo.vendooepicodus.models.Store;
import co.vendoo.vendooepicodus.ui.StoreDetailActivity;

/**
 * Created by T on 12/12/16.
 */

public class FirebaseStoreViewHolder extends RecyclerView.ViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;
    public ImageView mStoreImageView;


    public FirebaseStoreViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
//        itemView.setOnClickListener(this);
    }

    public void bindStore(Store store) {
        mStoreImageView = (ImageView) mView.findViewById(R.id.storeImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.storeNameTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);

        Picasso.with(mContext)
                .load(store.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mStoreImageView);

        nameTextView.setText(store.getName());
        ratingTextView.setText("Rating: " + store.getRating() + "/5");
    }

//    @Override
//    public void onClick(View view) {
//        final ArrayList<Store> stores = new ArrayList<>();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_STORES);
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    stores.add(snapshot.getValue(Store.class));
//                }
//
//                int itemPosition = getLayoutPosition();
//
//                Intent intent = new Intent(mContext, StoreDetailActivity.class);
//                intent.putExtra("position", itemPosition + "");
//                intent.putExtra("stores", Parcels.wrap(stores));
//
//                mContext.startActivity(intent);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//    }

}
