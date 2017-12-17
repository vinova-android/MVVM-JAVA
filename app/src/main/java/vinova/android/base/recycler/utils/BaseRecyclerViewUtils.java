package vinova.android.base.recycler.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import vinova.android.base.viewmodel.BaseRecyclerViewViewModel;

public class BaseRecyclerViewUtils {

    @BindingAdapter("recyclerViewViewModel")
    public static void setRecyclerViewViewModel(RecyclerView recyclerView,
                                                BaseRecyclerViewViewModel viewModel) {
        viewModel.setupRecyclerView(recyclerView);
    }
}
