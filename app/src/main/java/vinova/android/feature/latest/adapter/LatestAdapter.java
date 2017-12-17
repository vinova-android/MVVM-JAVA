package vinova.android.feature.latest.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import vinova.android.R;
import vinova.android.base.recycler.adapter.BaseRecyclerViewAdapter;
import vinova.android.data.local.manga.MangaListRealm;
import vinova.android.data.local.manga.MangaRealm;
import vinova.android.databinding.ItemLatestBinding;
import vinova.android.feature.latest.item.LatestItem;

public class LatestAdapter extends BaseRecyclerViewAdapter<MangaRealm, LatestItem> {
    @Override
    public ItemViewHolder<MangaRealm, LatestItem> onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_latest, parent, false);

        LatestItem item = new LatestItem();

        ItemLatestBinding binding = DataBindingUtil.bind(itemView);
        binding.setItem(item);

        return new LatestViewHolder(itemView, binding, item);
    }

    public void setItems(MangaListRealm newItems) {
        items.clear();
        items.addAll(newItems.getMangaRealms());
        notifyDataSetChanged();
    }

    public ArrayList<MangaRealm> getItems() {
        return items;
    }

    public void setItems(ArrayList<MangaRealm> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    static class LatestViewHolder
            extends ItemViewHolder<MangaRealm, LatestItem> {

        LatestViewHolder(View itemView, ViewDataBinding binding,
                         LatestItem viewModel) {
            super(itemView, binding, viewModel);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.lnl_item_latest)
        void onClickItem() {
            viewModel.onClick();
        }
    }
}
