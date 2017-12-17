package vinova.android.data.network.manga.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MangaListResponse {
    @SerializedName("results")
    @Expose
    private List<MangaResponse> results = null;

    public List<MangaResponse> getResults() {
        return results;
    }
}
