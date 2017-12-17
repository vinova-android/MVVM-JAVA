package vinova.android.feature.home;

import vinova.android.base.Lifecycle;

class HomeContract {
    interface View extends Lifecycle.View {
        void hideLoading();
    }

    interface ViewModel extends Lifecycle.ViewModel {
    }
}
