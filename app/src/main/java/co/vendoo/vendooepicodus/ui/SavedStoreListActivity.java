package co.vendoo.vendooepicodus.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.vendoo.vendooepicodus.Constants;
import co.vendoo.vendooepicodus.R;
import co.vendoo.vendooepicodus.adapters.FirebaseStoreListAdapter;
import co.vendoo.vendooepicodus.adapters.FirebaseStoreViewHolder;
import co.vendoo.vendooepicodus.models.Store;
import co.vendoo.vendooepicodus.util.OnStartDragListener;
import co.vendoo.vendooepicodus.util.SimpleItemTouchHelperCallback;

public class SavedStoreListActivity extends AppCompatActivity {
//    private DatabaseReference mStoreReference;
//    private FirebaseStoreListAdapter mFirebaseAdapter;
//    private ItemTouchHelper mItemTouchHelper;
//
//    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_store_list);
//        ButterKnife.bind(this);
//
//        setUpFirebaseAdapter();
    }
//
//    private void setUpFirebaseAdapter() {
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
//
//        Query query = FirebaseDatabase.getInstance()
//                .getReference(Constants.FIREBASE_CHILD_STORES)
//                .child(uid)
//                .orderByChild(Constants.FIREBASE_QUERY_INDEX);
//
////        mStoreReference = FirebaseDatabase
////                .getInstance()
////                .getReference(Constants.FIREBASE_CHILD_STORES)
////                .child(uid);
//
//        mFirebaseAdapter = new FirebaseStoreListAdapter(Store.class,
//                R.layout.store_list_item_drag, FirebaseStoreViewHolder.class,
//                query, this, this);
//
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(mFirebaseAdapter);
//
//        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
//        mItemTouchHelper = new ItemTouchHelper(callback);
//        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mFirebaseAdapter.cleanup();
//    }
//
//    @Override
//    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
//        mItemTouchHelper.startDrag(viewHolder);
//    }

}