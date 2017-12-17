package vinova.android.feature.latest.item;

import android.databinding.Bindable;
import android.util.Log;

import vinova.android.base.recycler.item.BaseItemRecyclerView;
import vinova.android.data.local.manga.MangaRealm;

import static vinova.android.BuildConfig.TAG;

public class LatestItem extends BaseItemRecyclerView<MangaRealm> {

    private MangaRealm mangaRealm;

    @Override
    public void setItem(MangaRealm item) {
        mangaRealm = item;
        notifyChange();
    }

    public void onClick() {
        Log.d(TAG, mangaRealm.getTitle());
    }

    @Bindable
    public String getTitle() {
        return mangaRealm.getTitle();
    }
}
