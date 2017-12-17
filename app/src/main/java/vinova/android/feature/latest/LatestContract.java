package vinova.android.feature.latest;

import vinova.android.base.Lifecycle;

public interface LatestContract {
    interface View extends Lifecycle.View {

        void hideLoading();
    }

    interface ViewModel extends Lifecycle.ViewModel {

        void fetchData();
    }
}
