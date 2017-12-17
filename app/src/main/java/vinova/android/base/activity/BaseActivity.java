package vinova.android.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import vinova.android.base.Lifecycle;
import vinova.android.base.viewmodel.BaseViewModel;

public abstract class BaseActivity extends AppCompatActivity implements Lifecycle.View {
    private static final String VIEW_MODEL_STATE = "viewModelState";

    private BaseViewModel baseViewModel;

    protected abstract Lifecycle.ViewModel getViewModel();

    @Nullable
    protected abstract BaseViewModel createBaseViewModel(@Nullable BaseViewModel.State savedBaseViewModelState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseViewModel.State savedBaseViewModelState = null;
        if (savedInstanceState != null)
            savedBaseViewModelState = savedInstanceState.getParcelable(VIEW_MODEL_STATE);
        baseViewModel = createBaseViewModel(savedBaseViewModelState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getViewModel().onViewAttached(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getViewModel().onViewResumed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (baseViewModel != null)
            outState.putParcelable(VIEW_MODEL_STATE, baseViewModel.getInstanceState());
    }

    @Override
    public void onStop() {
        super.onStop();
        getViewModel().onViewDetached();
    }
}
