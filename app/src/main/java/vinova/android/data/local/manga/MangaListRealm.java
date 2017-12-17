package vinova.android.data.local.manga;

import java.util.List;

import vinova.android.data.network.manga.model.MangaResponse;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Required;

public class MangaListRealm extends RealmObject {
    @Required
    private RealmList<MangaRealm> mangaRealms = null;

    public RealmList<MangaRealm> getMangaRealms() {
        return mangaRealms;
    }

    void setMangaRealms(List<MangaResponse> mangaRealms) {
        MangaRealm m = new MangaRealm();
        for (int i = 0; i < mangaRealms.size(); i++) {
            m.setId(mangaRealms.get(i).getId());
            m.setTitle(String.valueOf(mangaRealms.get(i).getTitle()));
            this.mangaRealms.add(m);
        }
    }
}
