package vinova.android.base.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import vinova.android.base.recycler.adapter.BaseRecyclerViewAdapter;

public abstract class BaseRecyclerViewViewModel extends BaseViewModel {

    RecyclerView.LayoutManager layoutManager;
    private Parcelable savedLayoutManagerState;

    public BaseRecyclerViewViewModel(@Nullable State savedInstanceState) {
        super(savedInstanceState);
        if (savedInstanceState instanceof BaseRecyclerViewViewModelState) {
            savedLayoutManagerState =
                    ((BaseRecyclerViewViewModelState) savedInstanceState).layoutManagerState;
        }
    }

    protected abstract BaseRecyclerViewAdapter getAdapter();

    protected abstract RecyclerView.LayoutManager createLayoutManager();

    @Override
    public BaseRecyclerViewViewModelState getInstanceState() {
        return new BaseRecyclerViewViewModelState(this);
    }

    public final void setupRecyclerView(RecyclerView recyclerView) {
        layoutManager = createLayoutManager();
        if (savedLayoutManagerState != null) {
            layoutManager.onRestoreInstanceState(savedLayoutManagerState);
            savedLayoutManagerState = null;
        }
        recyclerView.setAdapter(getAdapter());
        recyclerView.setLayoutManager(layoutManager);
    }

    protected static class BaseRecyclerViewViewModelState extends State {

        public static Creator<BaseRecyclerViewViewModelState> CREATOR =
                new Creator<BaseRecyclerViewViewModelState>() {
                    @Override
                    public BaseRecyclerViewViewModelState createFromParcel(Parcel source) {
                        return new BaseRecyclerViewViewModelState(source);
                    }

                    @Override
                    public BaseRecyclerViewViewModelState[] newArray(int size) {
                        return new BaseRecyclerViewViewModelState[size];
                    }
                };
        private final Parcelable layoutManagerState;

        public BaseRecyclerViewViewModelState(BaseRecyclerViewViewModel viewModel) {
            super(viewModel);
            layoutManagerState = viewModel.layoutManager.onSaveInstanceState();
        }

        public BaseRecyclerViewViewModelState(Parcel in) {
            super(in);
            layoutManagerState = in.readParcelable(
                    RecyclerView.LayoutManager.class.getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeParcelable(layoutManagerState, flags);
        }
    }
}