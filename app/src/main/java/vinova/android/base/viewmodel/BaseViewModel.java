package vinova.android.base.viewmodel;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class BaseViewModel extends BaseObservable {
    protected BaseViewModel(@Nullable State savedInstanceState) {
    }

    public State getInstanceState() {
        return new State(this);
    }

    public static class State implements Parcelable {

        public static Creator<State> CREATOR = new Creator<State>() {
            @NonNull
            @Override
            public State createFromParcel(Parcel source) {
                return new State(source);
            }

            @NonNull
            @org.jetbrains.annotations.Contract(pure = true)
            @Override
            public State[] newArray(int size) {
                return new State[size];
            }
        };

        protected State(BaseViewModel viewModel) {

        }

        public State(Parcel in) {

        }

        @Override
        public int describeContents() {
            return 0;
        }

        @CallSuper
        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }
    }
}
