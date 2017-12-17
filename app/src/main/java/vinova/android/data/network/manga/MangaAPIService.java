package vinova.android.data.network.manga;

import android.util.Log;

import org.jetbrains.annotations.Contract;

import vinova.android.data.local.manga.MangaRealmService;
import vinova.android.data.network.manga.exception.MangaFailureException;
import vinova.android.data.network.util.RetrofitUtil;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static vinova.android.BuildConfig.TAG;

public class MangaAPIService {

    private MangaAPI mangaAPI;
    private boolean isRequestingManga;

    public MangaAPIService() {
        this.mangaAPI = RetrofitUtil.create().create(MangaAPI.class);
    }

    public boolean isRequestingManga() {
        return isRequestingManga;
    }

    public Flowable getMangaList() {
        return mangaAPI.getMangaList().take(1)
                .doOnSubscribe(disposable -> isRequestingManga = true)
                .doOnTerminate(() -> isRequestingManga = false)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(mangaListResponse -> MangaRealmService.getInstance().writeToRealm(mangaListResponse))
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> MangaRealmService.getInstance().readFromRealm())
                .doOnError(this::handleError);
    }

    @Contract("_ -> fail")
    private void handleError(Throwable throwable) {
        Log.d(TAG, throwable.toString());
        throw new MangaFailureException();
    }
}
