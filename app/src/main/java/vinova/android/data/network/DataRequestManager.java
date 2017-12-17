package vinova.android.data.network;

import android.util.Log;

import org.reactivestreams.Subscriber;

import vinova.android.data.network.manga.MangaAPIService;
import io.reactivex.Flowable;

import static vinova.android.BuildConfig.TAG;

public class DataRequestManager {

    private static DataRequestManager instance;

    private MangaAPIService mangaAPIService;

    private DataRequestManager() {

        mangaAPIService = new MangaAPIService();
    }

    public static DataRequestManager getInstance() {

        synchronized (DataRequestManager.class) {
            if (instance == null) {
                instance = new DataRequestManager();
            }
            return instance;
        }
    }

    private boolean isRequestingInformation() {

        return mangaAPIService.isRequestingManga();
    }

    public Flowable getMangaList() {

        if (!isRequestingInformation())
            return mangaAPIService.getMangaList();
        else
            return new Flowable() {
                @Override
                protected void subscribeActual(Subscriber s) {
                    Log.d(TAG, "Do nothing");
                }
            };
    }
}
