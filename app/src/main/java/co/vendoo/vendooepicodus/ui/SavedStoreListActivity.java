package co.vendoo.vendooepicodus.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.vendoo.vendooepicodus.Constants;
import co.vendoo.vendooepicodus.R;
import co.vendoo.vendooepicodus.adapters.FirebaseStoreViewHolder;
import co.vendoo.vendooepicodus.models.Store;

public class SavedStoreListActivity extends AppCompatActivity {
    private DatabaseReference mStoreReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mStoreReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_STORES)
                .child(uid);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Store, FirebaseStoreViewHolder>
                (Store.class, R.layout.store_list_item_drag, FirebaseStoreViewHolder.class,
                        mStoreReference) {

            @Override
            protected void populateViewHolder(FirebaseStoreViewHolder viewHolder,
                                              Store model, int position) {
                viewHolder.bindStore(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

}
