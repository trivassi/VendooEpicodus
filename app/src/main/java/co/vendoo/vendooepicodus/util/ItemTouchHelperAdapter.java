package co.vendoo.vendooepicodus.util;

/**
 * Created by T on 12/20/16.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
