package vinova.android.data.network.manga;

import vinova.android.data.network.manga.model.MangaListResponse;
import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface MangaAPI {
    @GET("popular")
    default Flowable<MangaListResponse> getMangaList() {
        return null;
    }
}
