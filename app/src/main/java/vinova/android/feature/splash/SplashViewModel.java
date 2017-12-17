package vinova.android.feature.splash;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import vinova.android.base.Lifecycle;
import vinova.android.base.viewmodel.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashViewModel extends BaseViewModel implements SplashContract.ViewModel {

    private Disposable internetDisposable;

    private SplashContract.View splashViewCallback;

    SplashViewModel(@Nullable State savedInstanceState) {
        super(savedInstanceState);
    }

    //Lifecycle
    @Override
    public void onViewResumed() {
    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View viewCallback) {
        this.splashViewCallback = (SplashContract.View) viewCallback;
    }

    @Override
    public void onViewDetached() {
        this.splashViewCallback = null;
        safelyDispose(internetDisposable);
    }
    //end

    @Override
    public void checkingInternet() {
        internetDisposable = ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isConnected -> {
                    if (isConnected)
                        splashViewCallback.launchHomeActivity();
                    {
                        //Todo
                        //Show Try Again
                    }
                });
    }

    private void safelyDispose(Disposable... disposables) {
        for (Disposable subscription : disposables) {
            if (subscription != null && !subscription.isDisposed()) {
                subscription.dispose();
            }
        }
    }
}
