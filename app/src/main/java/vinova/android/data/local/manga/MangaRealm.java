package vinova.android.data.local.manga;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class MangaRealm extends RealmObject implements Parcelable {

    public static Creator<MangaRealm> CREATOR = new Creator<MangaRealm>() {
        @Override
        public MangaRealm createFromParcel(Parcel source) {
            return new MangaRealm(source);
        }

        @Override
        public MangaRealm[] newArray(int size) {
            return new MangaRealm[size];
        }
    };
    @PrimaryKey
    private int id;
    @Required
    private String title;

    public MangaRealm() {
    }

    private MangaRealm(Parcel in) {
        id = in.readInt();
        title = in.readString();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
    }
}