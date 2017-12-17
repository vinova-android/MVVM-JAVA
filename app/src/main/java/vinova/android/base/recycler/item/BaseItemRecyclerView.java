package vinova.android.base.recycler.item;

import vinova.android.base.viewmodel.BaseViewModel;

public abstract class BaseItemRecyclerView<ITEM_T> extends BaseViewModel {

    public BaseItemRecyclerView() {
        super(null);
    }

    public abstract void setItem(ITEM_T item);
}
