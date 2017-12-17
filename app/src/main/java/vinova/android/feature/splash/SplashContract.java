package vinova.android.feature.splash;

import vinova.android.base.Lifecycle;

public interface SplashContract {
    interface View extends Lifecycle.View {
        void launchHomeActivity();
    }

    interface ViewModel extends Lifecycle.ViewModel {
        void checkingInternet();
    }
}
