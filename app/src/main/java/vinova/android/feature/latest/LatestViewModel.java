package vinova.android.feature.latest;

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;

import vinova.android.base.Lifecycle;
import vinova.android.base.recycler.adapter.BaseRecyclerViewAdapter;
import vinova.android.base.viewmodel.BaseRecyclerViewViewModel;
import vinova.android.data.local.manga.MangaListRealm;
import vinova.android.data.local.manga.MangaRealm;
import vinova.android.data.network.DataRequestManager;
import vinova.android.feature.latest.adapter.LatestAdapter;
import io.reactivex.disposables.Disposable;
import io.reactivex.processors.AsyncProcessor;
import io.reactivex.subscribers.DisposableSubscriber;
import io.realm.Realm;

import static vinova.android.BuildConfig.TAG;

public class LatestViewModel extends BaseRecyclerViewViewModel implements LatestContract.ViewModel {

    private LatestContract.View latestViewCallBack;
    private LatestAdapter latestAdapter;
    private DataRequestManager dataRequestManager;
    private AsyncProcessor dataProcessor;
    private Disposable disposable;
    private Realm realm;
    private RecyclerView.LayoutManager layoutManager;

    LatestViewModel(Context context, @Nullable State savedInstanceState, DataRequestManager dataRequestManager) {
        super(savedInstanceState);
        this.dataRequestManager = dataRequestManager;

        latestAdapter = new LatestAdapter();
        layoutManager = new LinearLayoutManager(context);

        if (savedInstanceState instanceof LatestState) {
            ArrayList<MangaRealm> mangaListRealm;
            mangaListRealm = ((LatestState) savedInstanceState).mangaListRealm;
            latestAdapter.setItems(mangaListRealm);
        }
    }

    //Recycler
    @Override
    protected BaseRecyclerViewAdapter getAdapter() {
        return latestAdapter;
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return layoutManager;
    }
    //end

    //Subscribe
    @Override
    public void fetchData() {
        dataProcessor = AsyncProcessor.create();
        disposable = (Disposable) dataProcessor.subscribeWith(new LatestSubscriber());
        dataRequestManager.getMangaList().subscribeWith(dataProcessor);
    }
    //end

    //Lifecycle
    @Override
    public void onViewResumed() {
        if (disposable != null && disposable.isDisposed()) {
            disposable = (Disposable) dataProcessor.subscribeWith(new LatestSubscriber());
        }
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View viewCallback) {
        this.latestViewCallBack = (LatestContract.View) viewCallback;
    }

    @Override
    public void onViewDetached() {
        this.latestViewCallBack = null;

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        realm.close();
    }
    //end

    //State
    @Override
    public BaseRecyclerViewViewModelState getInstanceState() {
        return new LatestState(this);
    }
    //end

    private static class LatestState extends BaseRecyclerViewViewModelState {

        public static Creator<LatestState> CREATOR = new Creator<LatestState>() {
            @NonNull
            @Override
            public LatestState createFromParcel(Parcel source) {
                return new LatestState(source);
            }

            @NonNull
            @Contract(pure = true)
            @Override
            public LatestState[] newArray(int size) {
                return new LatestState[size];
            }
        };
        private final ArrayList<MangaRealm> mangaListRealm;

        LatestState(LatestViewModel viewModel) {
            super(viewModel);
            mangaListRealm = viewModel.latestAdapter.getItems();
        }

        LatestState(Parcel in) {
            super(in);
            mangaListRealm = in.createTypedArrayList(MangaRealm.CREATOR);
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeTypedList(mangaListRealm);
        }
    }

    private class LatestSubscriber extends DisposableSubscriber<MangaListRealm> {

        @Override
        public void onNext(MangaListRealm mangaListRealm) {
            //set up and update View here
            latestAdapter.setItems(mangaListRealm);
        }

        @Override
        public void onError(Throwable t) {
            //Show error log
            Log.d(TAG, t.getCause().toString());
            if (latestViewCallBack != null)
                latestViewCallBack.hideLoading();
        }

        @Override
        public void onComplete() {
            //Update view
            if (latestViewCallBack != null)
                latestViewCallBack.hideLoading();
        }
    }
    //end
}
