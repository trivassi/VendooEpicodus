package co.vendoo.vendooepicodus.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.vendoo.vendooepicodus.R;
import co.vendoo.vendooepicodus.adapters.StoreImageAdapter;

public class ImageStoreActivity extends AppCompatActivity {

    @Bind(R.id.imageList) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_images);
        ButterKnife.bind(this);

        setUpList();
    }

    private void setUpList() {
        ArrayList<String> list = getIntent().getStringArrayListExtra("images");
        StoreImageAdapter storeImageAdapter = new StoreImageAdapter(list, this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(storeImageAdapter);
    }
}