package vinova.android.base.recycler.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import vinova.android.base.recycler.item.BaseItemRecyclerView;


public abstract class BaseRecyclerViewAdapter<ITEM_T, VIEW_MODEL_T extends BaseItemRecyclerView<ITEM_T>>
        extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ItemViewHolder<ITEM_T, VIEW_MODEL_T>> {
    protected final ArrayList<ITEM_T> items;

    public BaseRecyclerViewAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public final void onBindViewHolder(ItemViewHolder<ITEM_T, VIEW_MODEL_T> holder, int position) {
        holder.setItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ItemViewHolder<T, VT extends BaseItemRecyclerView<T>>
            extends RecyclerView.ViewHolder {

        protected final VT viewModel;
        private final ViewDataBinding binding;

        public ItemViewHolder(View itemView, ViewDataBinding binding, VT viewModel) {
            super(itemView);
            this.binding = binding;
            this.viewModel = viewModel;
        }

        void setItem(T item) {
            viewModel.setItem(item);
            binding.executePendingBindings();
        }
    }
}