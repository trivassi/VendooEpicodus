package co.vendoo.vendooepicodus.util;

import java.util.ArrayList;

import co.vendoo.vendooepicodus.models.Store;

/**
 * Created by T on 12/21/16.
 */

public interface OnStoreSelectedListener {
    public void onStoreSelected(Integer position, ArrayList<Store> stores, String source);

}
