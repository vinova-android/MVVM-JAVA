package vinova.android.feature.home;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import vinova.android.base.Lifecycle;
import vinova.android.base.viewmodel.BaseViewModel;

public class HomeViewModel extends BaseViewModel implements HomeContract.ViewModel {
    private HomeContract.View homeViewCallBack;

    HomeViewModel(@Nullable BaseViewModel.State savedInstanceState) {
        super(savedInstanceState);
    }

    @Override
    public void onViewResumed() {
        homeViewCallBack.hideLoading();
    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View viewCallback) {
        this.homeViewCallBack = (HomeContract.View) viewCallback;
    }

    @Override
    public void onViewDetached() {
        this.homeViewCallBack = null;
    }
}
