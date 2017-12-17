package vinova.android.base;

import android.support.annotation.NonNull;

public interface Lifecycle {
    interface View {

    }

    interface ViewModel {
        void onViewResumed();

        void onViewAttached(@NonNull Lifecycle.View viewCallback);

        void onViewDetached();
    }
}
