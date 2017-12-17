package vinova.android.data.local.manga;

import android.util.Log;

import vinova.android.data.network.manga.model.MangaListResponse;
import io.realm.Realm;

import static vinova.android.BuildConfig.TAG;

public class MangaRealmService {

    private static MangaRealmService instance;

    public static MangaRealmService getInstance() {

        synchronized (MangaRealmService.class) {
            if (instance == null) {
                instance = new MangaRealmService();
            }
            return instance;
        }
    }

    public MangaListRealm readFromRealm() {
        Realm realmUI = Realm.getDefaultInstance();
        return realmUI.where(MangaListRealm.class).findFirst();
    }

    public int writeToRealm(MangaListResponse mangaListResponse) {
        Log.d(TAG, "writing list of manga to realm");
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(transactionRealm -> {
            MangaListRealm mangaRealms;
            mangaRealms = transactionRealm.createObject(MangaListRealm.class);
            mangaRealms.setMangaRealms(mangaListResponse.getResults());
        });
        realm.close();
        return 1;
    }
}