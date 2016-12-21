package co.vendoo.vendooepicodus.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

import co.vendoo.vendooepicodus.Constants;
import co.vendoo.vendooepicodus.R;
import co.vendoo.vendooepicodus.models.Store;
import co.vendoo.vendooepicodus.ui.StoreDetailActivity;
import co.vendoo.vendooepicodus.ui.StoreDetailFragment;
import co.vendoo.vendooepicodus.util.ItemTouchHelperAdapter;
import co.vendoo.vendooepicodus.util.OnStartDragListener;

/**
 * Created by T on 12/20/16.
 */
public class FirebaseStoreListAdapter extends FirebaseRecyclerAdapter<Store, FirebaseStoreViewHolder>  implements ItemTouchHelperAdapter {

    private ChildEventListener mChildEventListener;
    private ArrayList<Store> mStores = new ArrayList<>();
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private int mOrientation;

    public FirebaseStoreListAdapter(Class<Store> modelClass, int modelLayout,
                                         Class<FirebaseStoreViewHolder> viewHolderClass,
                                         Query ref, OnStartDragListener onStartDragListener, Context context) {

        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mStores.add(dataSnapshot.getValue(Store.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void populateViewHolder(final FirebaseStoreViewHolder viewHolder, Store model, int position) {
        viewHolder.bindStore(model);

        mOrientation = viewHolder.itemView.getResources().getConfiguration().orientation;
        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            createDetailFragment(0);
        }

//        viewHolder.mStoreImageView.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
//                    mOnStartDragListener.onStartDrag(viewHolder);
//                }
//                return false;
//            }
//        });

        viewHolder.mStoreImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int itemPosition = viewHolder.getAdapterPosition();
                if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    createDetailFragment(itemPosition);
                } else {
                    Intent intent = new Intent(mContext, StoreDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                    intent.putExtra(Constants.EXTRA_KEY_STORES, Parcels.wrap(mStores));
                    mContext.startActivity(intent);
                }
            }
        });

    }

    private void createDetailFragment(int position) {
        StoreDetailFragment detailFragment = StoreDetailFragment.newInstance(mStores, position);
        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.storeDetailContainer, detailFragment);
        ft.commit();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mStores, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mStores.remove(position);
        getRef(position).removeValue();
    }

    private void setIndexInFirebase() {
        for (Store store : mStores) {
            int index = mStores.indexOf(store);
            DatabaseReference ref = getRef(index);
            store.setIndex(Integer.toString(index));
            ref.setValue(store);
        }
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
}